package model;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;



public class TestHibernateUtil {
	
	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure(HibernateUtil.class.getResource("/test.cfg.xml")).build();

	SessionFactory sessionFactory;
	
	public TestHibernateUtil() {

		try{
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		catch(Exception e){
		}
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
