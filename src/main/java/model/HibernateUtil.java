package model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * 
 * Singleton class for creating and getting the SessionFactory
 *
 */
public class HibernateUtil {

	private static SessionFactory istuntotehdas;
	private static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

	/**
	 * Empty class constructor
	 */
	public HibernateUtil() {
	}

	/**
	 * Creates a single SessionFactory or returns an existing one.
	 * Not possible to create multiple instances.
	 * @return SessionFactory.
	 */
	public static synchronized SessionFactory getSessionFactory(boolean forTest) {
		if (istuntotehdas == null) {
			if (forTest) {
				registry = new StandardServiceRegistryBuilder().configure("/test.cfg.xml").build();
			}
			istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			System.out.println("tehdas tulilla");
		}
		return istuntotehdas;
	}
}
