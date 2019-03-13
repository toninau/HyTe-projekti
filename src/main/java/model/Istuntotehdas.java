package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Istuntotehdas {
	
	private static SessionFactory istuntotehdas;
	private final static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

	public Istuntotehdas() {
		/*try {
			istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			System.out.println("tehdasluotu");
		} catch (Exception e) {
			System.out.println("Oh no");
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}*/
	}
	
	public static synchronized SessionFactory getSessionFactory() {
		if (istuntotehdas == null) {
			istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			System.out.println("tehdas tulilla");
		}
		return istuntotehdas;
	}
	
	public Session openSession() {
		return istuntotehdas.openSession();
	}
}
