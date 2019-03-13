package model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class IlmoitusAccessObject {
	private SessionFactory istuntotehdas = null;

	public IlmoitusAccessObject(SessionFactory istuntotehdas) {
		this.istuntotehdas = istuntotehdas;
	}

	public boolean createIlmoitus(Ilmoitus ilmoitus) {
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		boolean onnistui = false;
		try {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(ilmoitus);
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

	public Ilmoitus readIlmoitus(int id) {
		Session istunto = istuntotehdas.openSession();
		Ilmoitus ilmoitus = new Ilmoitus();
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			istunto.load(ilmoitus, id);
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return ilmoitus;
	}

	@SuppressWarnings("unchecked")
	public Ilmoitus[] readAsiakkaanIlmoitukset(Asiakas asiakas) {
		Session istunto = istuntotehdas.openSession();
		List<Ilmoitus> result = null;
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			String sql = "SELECT * FROM ilmoitus INNER JOIN asiakas on ilmoitus.asiakasID = asiakas.asiakasID WHERE asiakas.asiakasID = :id";
			Query<Ilmoitus> kysely = istunto.createSQLQuery(sql).addEntity(Ilmoitus.class);
			kysely.setParameter("id", asiakas.getAsiakasID());
			result = kysely.list();
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		Ilmoitus[] returnArray = new Ilmoitus[result.size()];
		return (Ilmoitus[]) result.toArray(returnArray);
	}

	public boolean updateIlmoitus(Ilmoitus ilmoitus) {
		boolean onnistui = false;
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Ilmoitus i = (Ilmoitus) istunto.get(Ilmoitus.class, ilmoitus.getIlmoitusID());
		if (i != null) {
			i.setLuettu(ilmoitus.isLuettu());
			i.setTeksti(ilmoitus.getTeksti());
			onnistui = true;
		} else {
			System.out.println("Ei löytynyt päivitettävää");
		}
		istunto.getTransaction().commit();
		istunto.close();
		return onnistui;
	}

	public boolean deleteIlmoitus(int id) {
		boolean onnistui = false;
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Ilmoitus i = (Ilmoitus) istunto.get(Ilmoitus.class, id);
		if (i != null) {
			istunto.delete(i);
			onnistui = true;
		}
		istunto.getTransaction().commit();
		istunto.close();
		return onnistui;
	}
}
