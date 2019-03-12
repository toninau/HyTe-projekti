package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Istuntotehdas {
	
	private SessionFactory istuntotehdas = null;
	private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

	public Istuntotehdas() {
		try {
			istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			System.out.println("Oh no");
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}
	}
	
	public Session openSession() {
		return istuntotehdas.openSession();
	}
}
