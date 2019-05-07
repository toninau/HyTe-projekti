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

	/**
	 * Empty class constructor
	 */
	private HibernateUtil() {
	}

	/**
	 * Creates a single SessionFactory or returns an existing one.
	 * Not possible to create multiple instances.
	 * @return SessionFactory.
	 */
	public static synchronized SessionFactory getSessionFactory(boolean forTest) {
		StandardServiceRegistry registry = null;
		if (istuntotehdas == null) {
			if (forTest) {
				registry = new StandardServiceRegistryBuilder().configure(HibernateUtil.class.getResource("/test.cfg.xml")).build();
			} else {
				registry = new StandardServiceRegistryBuilder().configure().build();
			}
			istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		return istuntotehdas;
	}
}
