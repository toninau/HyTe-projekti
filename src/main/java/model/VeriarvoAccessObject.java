package model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
/**
 * 
 * 
 *	Asiakkaan veriarvojen DataAccessObject.
 */
public class VeriarvoAccessObject {
	/**
	 * Sessionfactory, jota käytetään CRUD-operaatioihin
	 */
	private SessionFactory istuntotehdas = null;
	/**
	 * Luokan konstruktori.
	 * @param istuntotehdas Saa parametrina Sessionfactory-olion, jota käytetään koko sovelluksessa
	 */
	public VeriarvoAccessObject(SessionFactory istuntotehdas) {
		this.istuntotehdas = istuntotehdas;
	}
	/**
	 * Uuden kentän tietokantaan tallentava metodi
	 * @param veriarvo tietokantaan tallennettava arvo
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
	public boolean createVeriarvo(Veriarvo veriarvo) {
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		boolean onnistui = false;
		try {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(veriarvo);
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
	 * Lukee tietokannasta listana kaikki asiakkaan veriarvot
	 * @param asiakas asiakas, jonka veriarvoja luetaan
	 *
	 * @return lista, joka sisältää arvot
	 */
	@SuppressWarnings("unchecked")
	public Veriarvo[] readAsiakkaanVeriarvot(Asiakas asiakas) {
		Session istunto = istuntotehdas.openSession();
		List<Veriarvo> result = null;
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			String sql = "SELECT * FROM veriarvo INNER JOIN asiakas on veriarvo.asiakasID = asiakas.asiakasID WHERE asiakas.asiakasID = :id";
			Query<Veriarvo> kysely = istunto.createSQLQuery(sql).addEntity(Veriarvo.class);
			kysely.setParameter("id", asiakas.getAsiakasID());
			result = kysely.list();
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		Veriarvo[] returnArray = new Veriarvo[result.size()];
		return (Veriarvo[]) result.toArray(returnArray);
	}
	/**
	 * Poistaa halutun arvon tietokannasta
	 * @param id poistettavan arvon id
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
	public boolean deleteVeriarvo(int id) {
		boolean onnistui = false;
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Veriarvo v = (Veriarvo) istunto.get(Veriarvo.class, id);
		if (v != null) {
			istunto.delete(v);
			onnistui = true;
		}
		istunto.getTransaction().commit();
		istunto.close();
		return onnistui;
	}
}
