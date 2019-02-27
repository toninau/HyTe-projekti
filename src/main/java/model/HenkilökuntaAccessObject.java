package model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

public class HenkilökuntaAccessObject {
	SessionFactory istuntotehdas = null;
	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

	public HenkilökuntaAccessObject() {
		try {
			istuntotehdas = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			System.out.println("Oh no");
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}
	}
	
	public boolean createHenkilökunta(Henkilökunta henkilö) {
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		boolean onnistui = false;
		try {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(henkilö);
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
	
	@SuppressWarnings("rawtypes")
	public boolean addAsiakas(Henkilökunta henkilö, Asiakas asiakas) {
		Session istunto = istuntotehdas.openSession();
		boolean onnistui = false;
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			String sql = "INSERT INTO asiakkaanhenkilökunta (AsiakasID, HenkilökuntaID) VALUES (:asiakasID,:henkilökuntaID)";
			Query kysely = istunto.createSQLQuery(sql);
			kysely.setParameter("asiakasID", asiakas.getAsiakasID());
			kysely.setParameter("henkilökuntaID", henkilö.getHenkilökuntaID());
			kysely.executeUpdate();
			onnistui = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return onnistui;
	}
	
	@SuppressWarnings("unchecked")
	public Asiakas[] readHenkilönAsiakkaat(Henkilökunta henkilö) {
		Session istunto = istuntotehdas.openSession();
		List<Asiakas> result = null;
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			String sql = "SELECT * FROM asiakas INNER JOIN asiakkaanhenkilökunta on asiakkaanhenkilökunta.asiakasID = asiakas.asiakasID WHERE asiakkaanhenkilökunta.HenkilökuntaID = :id";
			Query<Asiakas> kysely = istunto.createSQLQuery(sql).addEntity(Asiakas.class);
			kysely.setParameter("id", henkilö.getHenkilökuntaID());
			result = kysely.list();
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		Asiakas[] returnArray = new Asiakas[result.size()];
		return (Asiakas[]) result.toArray(returnArray);
	}

	public Henkilökunta readHenkilökunta(int id) {
		Session istunto = istuntotehdas.openSession();
		Henkilökunta henkilö = new Henkilökunta();
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			istunto.load(henkilö, id);
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return henkilö;
	}
}
