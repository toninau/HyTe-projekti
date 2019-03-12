package model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class VarausAccessObject {

	private Istuntotehdas istuntotehdas = null;

	public VarausAccessObject(Istuntotehdas istuntotehdas) {
		this.istuntotehdas = istuntotehdas;
	}

	public boolean createVaraus(Varaus varaus) {
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		boolean onnistui = false;
		try {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(varaus);
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

	public Varaus readVaraus(int id) {
		Session istunto = istuntotehdas.openSession();
		Varaus varaus = new Varaus();
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			istunto.load(varaus, id);
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return varaus;
	}

	@SuppressWarnings("unchecked")
	public Varaus[] readAsiakkaanVaraukset(Asiakas asiakas) {
		Session istunto = istuntotehdas.openSession();
		List<Varaus> result = null;
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			String sql = "SELECT * FROM varaus INNER JOIN asiakas on varaus.asiakasID = asiakas.asiakasID WHERE asiakas.asiakasID = :id";
			Query<Varaus> kysely = istunto.createSQLQuery(sql).addEntity(Varaus.class);
			kysely.setParameter("id", asiakas.getAsiakasID());
			result = kysely.list();
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		Varaus[] returnArray = new Varaus[result.size()];
		return (Varaus[]) result.toArray(returnArray);
	}

	@SuppressWarnings("unchecked")
	public Varaus[] readHenkilökunnanVaraukset(Henkilökunta henkilökunta) {
		Session istunto = istuntotehdas.openSession();
		List<Varaus> result = null;
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			String sql = "SELECT * FROM varaus INNER JOIN henkilökunta on varaus.henkilökuntaID = henkilökunta.henkilökuntaID WHERE henkilökunta.henkilökuntaID = :id";
			Query<Varaus> kysely = istunto.createSQLQuery(sql).addEntity(Varaus.class);
			kysely.setParameter("id", henkilökunta.getHenkilökuntaID());
			result = kysely.list();
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		Varaus[] returnArray = new Varaus[result.size()];
		return (Varaus[]) result.toArray(returnArray);
	}

	public boolean updateVaraus(Varaus varaus) {
		boolean onnistui = false;
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Varaus v = (Varaus) istunto.get(Varaus.class, varaus.getVarausID());
		if (v != null) {
			v.setAsiakas(varaus.getAsiakas());
			v.setHenkilökunta(varaus.getHenkilökunta());
			v.setInfo(varaus.getInfo());
			v.setKellonaika(varaus.getKellonaika());
			v.setPäivämäärä(varaus.getPäivämäärä());
			onnistui = true;
		} else {
			System.out.println("Ei löytynyt päivitettävää");
		}
		istunto.getTransaction().commit();
		istunto.close();
		return onnistui;
	}

	public boolean deleteVaraus(int id) {
		boolean onnistui = false;
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Varaus v = (Varaus) istunto.get(Varaus.class, id);
		if (v != null) {
			istunto.delete(v);
			onnistui = true;
		}
		istunto.getTransaction().commit();
		istunto.close();
		return onnistui;
	}
}
