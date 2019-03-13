package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
/**
 * 
 * Asiakkaan sairauksien hallintaan käytettävä DataAccessObject
 *
 */
public class SairausAccessObject {
	/**
	 * Sessionfactory, jota käytetään CRUD-operaatioihin
	 */
	private SessionFactory istuntotehdas = null;
	/**
	 * Luokan konstruktori.
	 * @param istuntotehdas Saa parametrina Sessionfactory-olion, jota käytetään koko sovelluksessa
	 */
	public SairausAccessObject(SessionFactory istuntotehdas) {
		this.istuntotehdas = istuntotehdas;

	}
	/**
	 * Uuden kentän tietokantaan tallentava metodi
	 * @param sairaus Tietokantaan tallennettava arvo
	 * @return true, Mikäli operaatio onnistui, muuten false
	 */
	public boolean createSairaus(Sairaus sairaus) {
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		boolean onnistui = false;
		try {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(sairaus);
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
	 * Lukee tietokannasta listana kaikki asiakkaan sairauksia
	 * @param asiakas asiakas, jonka sairauksia luetaan
	 *
	 * @return lista, joka sisältää arvot
	 */
	@SuppressWarnings("unchecked")
	public Sairaus[] readAsiakkaanSairaudet(Asiakas asiakas) {
		Session istunto = istuntotehdas.openSession();
		List<Sairaus> result = null;
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			String sql = "SELECT * FROM sairaus INNER JOIN asiakas on sairaus.asiakasID = asiakas.asiakasID WHERE asiakas.asiakasID = :id";
			Query<Sairaus> kysely = istunto.createSQLQuery(sql).addEntity(Sairaus.class);
			kysely.setParameter("id", asiakas.getAsiakasID());
			result = kysely.list();
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		Sairaus[] returnArray = new Sairaus[result.size()];
		return (Sairaus[]) result.toArray(returnArray);
	}
	/**
	 * Metodi poistaa jonkin sairauden tietokannasta
	 * @param id Poistettavan sairauden id
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
	public boolean deleteSairaus(int id) {
		boolean onnistui = false;
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Sairaus s = (Sairaus) istunto.get(Sairaus.class, id);
		if (s != null) {
			istunto.delete(s);
			onnistui = true;
		}
		istunto.getTransaction().commit();
		istunto.close();
		return onnistui;
	}
}
