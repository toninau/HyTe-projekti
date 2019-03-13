package model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
/**
 * 
 * Reseptien hallintaan käytettävä DataAccessObject
 *
 */
public class ReseptiAccessObject {
	/**
	 * Sessionfactory, jota käytetään CRUD-operaatioihin
	 */
	private SessionFactory istuntotehdas = null;
	/**
	 * Luokan konstruktori.
	 * @param istuntotehdas Saa parametrina Sessionfactory-olion, jota käytetään koko sovelluksessa
	 */
	public ReseptiAccessObject(SessionFactory istuntotehdas) {
		this.istuntotehdas = istuntotehdas;
	}
	/**
	 * Uuden kentän tietokantaan tallentava metodi
	 * @param resepti tietokantaan tallennettava reseptiolio
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
	public boolean createResepti(Resepti resepti) {
		Session istunto = istuntotehdas.openSession();
		Transaction transaktio = null;
		boolean onnistui = false;
		try {
			transaktio = istunto.beginTransaction();
			istunto.saveOrUpdate(resepti);
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
	 * Hakee jonkin tietyn reseptin tietokannasta
	 * @param id Haettavan reseptin id
	 * @return Haettu resepti
	 */
	public Resepti readResepti(int id) {
		Session istunto = istuntotehdas.openSession();
		Resepti resepti = new Resepti();
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			istunto.load(resepti, id);
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return resepti;
	}
	/**
	 * Lukee tietokannasta listana kaikki asiakkaan reseptit
	 * @param asiakas asiakas, jonka reseptit luetaan
	 *
	 * @return lista, joka sisltää reseptit
	 */
	@SuppressWarnings("unchecked")
	public Resepti[] readAsiakkaanReseptit(Asiakas asiakas) {
		Session istunto = istuntotehdas.openSession();
		List<Resepti> result = null;
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			String sql = "SELECT * FROM resepti INNER JOIN asiakas on resepti.asiakasID = asiakas.asiakasID WHERE asiakas.asiakasID = :id";
			Query<Resepti> kysely = istunto.createSQLQuery(sql).addEntity(Resepti.class);
			kysely.setParameter("id", asiakas.getAsiakasID());
			result = kysely.list();
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		Resepti[] returnArray = new Resepti[result.size()];
		return (Resepti[]) result.toArray(returnArray);
	}
	/**
	 * Metodi jonkin henkilökunnan jäsenen kirjoittamien reseptien hakemiseen
	 * @param henkilökunta Henkilökunnan jäsen, jonka reseptejä haetaan
	 * @return Lista resepteistä
	 */
	@SuppressWarnings("unchecked")
	public Resepti[] readHenkilökunnanReseptit(Henkilökunta henkilökunta) {
		Session istunto = istuntotehdas.openSession();
		List<Resepti> result = null;
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			String sql = "SELECT * FROM resepti INNER JOIN henkilökunta on resepti.henkilökuntaID = henkilökunta.henkilökuntaID WHERE henkilökunta.henkilökuntaID = :id";
			Query<Resepti> kysely = istunto.createSQLQuery(sql).addEntity(Resepti.class);
			kysely.setParameter("id", henkilökunta.getHenkilökuntaID());
			result = kysely.list();
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		Resepti[] returnArray = new Resepti[result.size()];
		return (Resepti[]) result.toArray(returnArray);
	}
	/**
	 * Metodi jonkin reseptin poistoon tietokannasta
	 * @param id Poistettavan reseptin id
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
	public boolean deleteResepti(int id) {
		boolean onnistui = false;
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Resepti r = (Resepti) istunto.get(Resepti.class, id);
		if (r != null) {
			istunto.delete(r);
			onnistui = true;
		}
		istunto.getTransaction().commit();
		istunto.close();
		return onnistui;
	}
}
