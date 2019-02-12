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
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		boolean onnistui = false;
		try {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(asiakas);
			transaktio.commit();
			onnistui = true;
		} catch (Exception e) {
			if (transaktio != null) {
				transaktio.rollback();
			}
		} finally {
			istunto.close();
		}
		return onnistui;
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

	@SuppressWarnings("unchecked")
	public Asiakas[] readAsiakkaat() {
		Session istunto = istuntotehdas.openSession();
		List<Asiakas> result = null;
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			result = istunto.createQuery("from Asiakas").list();
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		Asiakas[] returnArray = new Asiakas[result.size()];
		return (Asiakas[]) result.toArray(returnArray);
	}

	public boolean updateAsiakas(Asiakas asiakas) {
		boolean onnistui = false;
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Asiakas a = (Asiakas) istunto.get(Asiakas.class, asiakas.getHeTu());
		if (a != null) {
			a.setEtunimi(asiakas.getEtunimi());
			a.setIcenumero(asiakas.getIcenumero());
			a.setKotiosoite(asiakas.getKotiosoite());
			a.setPuhnumero(asiakas.getPuhnumero());
			a.setSukunimi(asiakas.getSukunimi());
			onnistui = true;
		} else {
			System.out.println("Ei löytynyt päivitettävää");
		}
		istunto.getTransaction().commit();
		istunto.close();
		return onnistui;
	}

	public boolean deleteAsiakas(String hetu) {
		boolean onnistui = false;
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Asiakas a = (Asiakas) istunto.get(Asiakas.class, hetu);
		if (a != null) {
			istunto.delete(a);
			onnistui = true;
		}
		istunto.getTransaction().commit();
		istunto.close();
		return onnistui;
	}

}
