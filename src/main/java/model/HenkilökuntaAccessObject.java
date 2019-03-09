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
			String sql = "INSERT INTO asiakkaanhenkilökunta (asiakasID, henkilökuntaID) VALUES (:asiakasID,:henkilökuntaID)";
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
	
	@SuppressWarnings("rawtypes")
	public boolean deleteAsiakas(Henkilökunta henkilö, Asiakas asiakas) {
		Session istunto = istuntotehdas.openSession();
		boolean onnistui = false;
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			String sql = "DELETE FROM asiakkaanhenkilökunta WHERE asiakkaanhenkilökunta.asiakasID = :asiakasID AND asiakkaanhenkilökunta.henkilökuntaID = :henkilökuntaID";
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
	
	@SuppressWarnings("unchecked")
	public Henkilökunta[] readAll() {
		Session istunto = istuntotehdas.openSession();
		List<Henkilökunta> result = null;
	try {	
		istunto.beginTransaction();
		result = istunto.createQuery("from Henkilökunta").list();
		istunto.getTransaction().commit();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		istunto.close();
	}
		Henkilökunta[] returnArray = new Henkilökunta[result.size()];
		return (Henkilökunta[]) result.toArray(returnArray);
	}
	
	public boolean updateHenkilökunta(Henkilökunta henkilökunta) {
		boolean onnistui = false;
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Henkilökunta h = (Henkilökunta) istunto.get(Henkilökunta.class, henkilökunta.getHenkilökuntaID());
		if (h != null) {
			h.setEtunimi(henkilökunta.getEtunimi());
			h.setSukunimi(henkilökunta.getSukunimi());
			h.setOikeus(henkilökunta.getOikeus());
			h.setSposti(henkilökunta.getSposti());
			h.setPuhnumero(henkilökunta.getPuhnumero());
			onnistui = true;
		} else {
			System.out.println("Ei löytynyt päivitettävää");
		}
		istunto.getTransaction().commit();
		istunto.close();
		return onnistui;
	}
	
	@SuppressWarnings("unchecked")
	public boolean deleteHenkilökunta(int id) {
		boolean onnistui = false;
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Henkilökunta h = (Henkilökunta) istunto.get(Henkilökunta.class, id);
		if (h != null) {
			istunto.delete(h);
			String sql = "DELETE FROM asiakkaanhenkilökunta WHERE asiakkaanhenkilökunta.henkilökuntaID = :id";
			Query<Henkilökunta> kysely = istunto.createSQLQuery(sql).addEntity(Henkilökunta.class);
			kysely.setParameter("id", h.getHenkilökuntaID());
			kysely.executeUpdate();
			onnistui = true;
		}
		istunto.getTransaction().commit();
		istunto.close();
		return onnistui;
	}
}
