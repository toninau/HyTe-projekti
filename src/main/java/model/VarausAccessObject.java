package model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
/**
 * 
 * Asiakkaiden varauksien hallintaan käytettävä DataAccessObject
 *
 */
public class VarausAccessObject {
	/**
	 * Sessionfactory, jota käytetään CRUD-operaatioihin
	 */
	private SessionFactory istuntotehdas = null;
	/**
	 * Luokan konstruktori.
	 * @param istuntotehdas Saa parametrina Sessionfactory-olion, jota käytetään koko sovelluksessa
	 */
	public VarausAccessObject(SessionFactory istuntotehdas) {
		this.istuntotehdas = istuntotehdas;
	}
	/**
	 * Uuden kentän tietokantaan tallentava metodi
	 * @param varaus tietokantaan tallennettava varaus
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
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
	
	/**
	 * Lukee tietokannasta listana kaikki asiakkaan varaukset
	 * @param id asiakas, jonka varauksia luetaan
	 * @return lista, joka sisältää arvot
	 */
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
	/**
	 * Metodi jonkun asiakkaan kaikkien varausten hakemiseen
	 * @param asiakas Asiakas, jonka varaus haetaan
	 * @return Lista haetuista varauksista
	 */
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
	/**
	 * Metodi jonkun henkilökunnan tekemien varausten hakemiseen
	 * @param henkilökunta Henkilökunnan jäsen, jonka tekemät varaukset haetaan
	 * @return Lista varauksista
	 */
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
	/**
	 * Metodi varauksen päivittämistä varten
	 * @param varaus Päivitettävä varaus
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
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
	/**
	 * Metodi jonkin tietyn varauksen poistamista varten
	 * @param id Poistettavan varauksen id
	 * @return true, jos operaatio onnistui, muuten falses
	 */
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
