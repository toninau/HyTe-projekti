package model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * 
 * Luokka SessionFactoryn luomiseen.
 *
 */
public class HibernateUtil {

	private static SessionFactory istuntotehdas;
	private static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

	/**
	 * Luokan konstruktori.
	 */
	public HibernateUtil() {
	}

	/**
	 * Luodaan istuntotehdas, jos sitä ei ole vielä luotu. Mahdollistaa sen, ettei
	 * tarvitse luoda useampaa istuntotehdasta.
	 * 
	 * @return HibernateUtil.
	 */
	public static synchronized SessionFactory getSessionFactory(boolean forTest) {
		if (istuntotehdas == null) {
			if (forTest) {
				registry = new StandardServiceRegistryBuilder().configure("test.cfg.xml").build();
			}
			istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			System.out.println("tehdas tulilla");
		}
		return istuntotehdas;
	}
}
