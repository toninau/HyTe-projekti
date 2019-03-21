package model;

import org.hibernate.Session;
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
	private final static StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

	/**
	 * Luokan konstruktori.
	 */
	public HibernateUtil() {
	}
	
	/**
	 * Luodaan istuntotehdas, jos sitä ei ole vielä luotu. Mahdollistaa sen, ettei tarvitse luoda useampaa istuntotehdasta.
	 * @return HibernateUtil.
	 */
	public static synchronized SessionFactory getSessionFactory() {
		if (istuntotehdas == null) {
			istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			System.out.println("tehdas tulilla");
		}
		return istuntotehdas;
	}	
}
