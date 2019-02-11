package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import java.util.List;

public class AsiakasAccessObject implements AsiakasDAO_IF {
	
	SessionFactory istuntotehdas = null;
	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

	public AsiakasAccessObject() {
		try {
			istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			System.out.println("Oh no");
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}
	}
	
	public boolean createAsiakas(Asiakas asiakas) {
		// TODO Auto-generated method stub
		return false;
	}

	public Asiakas readAsiakas(String hetu) {
		Session istunto = istuntotehdas.openSession();
		Asiakas asiakas = new Asiakas();
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			istunto.load(asiakas, hetu);
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return asiakas;
	}

	public Asiakas[] readAsiakas() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateAsiakas(Asiakas asiakas) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteAsiakas(String hetu) {
		// TODO Auto-generated method stub
		return false;
	}

}
