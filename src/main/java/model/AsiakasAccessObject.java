package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
/**
 * 
 * Asiakkaiden hallintaan käytettävä DataAccessObject
 *
 */
public class AsiakasAccessObject {
	/**
	 * Sessionfactory, jota käytetään CRUD-operaatioihin
	 */
	private SessionFactory istuntotehdas = null;

	/**
	 * Luokan konstruktori.
	 * @param istunto Saa parametrina Sessionfactory-olion, jota käytetään koko sovelluksessa
	 */
	public AsiakasAccessObject(SessionFactory istunto) {
		this.istuntotehdas = istunto;
	}
	
	/**
	 * Uuden kentän tietokantaan tallentava metodi
	 * @param asiakas tietokantaan tallennettava asiakas
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
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
	/**
	 * Metodi yhden asiakkaan tietojen hakemiseen tietokannasta
	 * @param id Haettavan asiakkaan id
	 * @return Asiakkaan tiedot
	 */
	public Asiakas readAsiakas(int id) {
		Session istunto = istuntotehdas.openSession();
		Asiakas asiakas = new Asiakas();
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			istunto.load(asiakas, id);
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		return asiakas;
	}
	/**
	 * Lukee tietokannasta listana kaikki asiakkaat
	 *
	 *
	 * @return lista, joka sisltää asiakkaat
	 */
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
	/**
	 * Metodi henkilökunnan jäsenten, joiden asiakkaana tietty asiakas on, hakemiseen
	 * @param asiakas Asiakas, jonka henkilökunta haetaan
	 * @return Lista henkilökunnan jäsenistä
	 */
	@SuppressWarnings("unchecked")
	public Henkilökunta[] readAsiakkaanHenkilökunta(Asiakas asiakas) {
		Session istunto = istuntotehdas.openSession();
		List<Henkilökunta> result = null;
		try {
			istunto = istuntotehdas.openSession();
			istunto.beginTransaction();
			String sql = "SELECT * FROM henkilökunta INNER JOIN asiakkaanhenkilökunta on asiakkaanhenkilökunta.henkilökuntaID = henkilökunta.henkilökuntaID WHERE asiakkaanhenkilökunta.asiakasID = :id";
			Query<Henkilökunta> kysely = istunto.createSQLQuery(sql).addEntity(Henkilökunta.class);
			kysely.setParameter("id", asiakas.getAsiakasID());
			result = kysely.list();
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		Henkilökunta[] returnArray = new Henkilökunta[result.size()];
		return (Henkilökunta[]) result.toArray(returnArray);
	}
	/**
	 * Metodi asiakkaan tietojen päivittämistä varten
	 * @param asiakas Asiakas, jonka tietoja päivitetään
	 * @return true, jos operaatio onnistui, muuten false
	 */
	public boolean updateAsiakas(Asiakas asiakas) {
		boolean onnistui = false;
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Asiakas a = (Asiakas) istunto.get(Asiakas.class, asiakas.getAsiakasID());
		if (a != null) {
			a.setEtunimi(asiakas.getEtunimi());
			a.setSukunimi(asiakas.getSukunimi());
			a.setHetu(asiakas.getHetu());
			a.setSposti(asiakas.getSposti());
			a.setIcenumero(asiakas.getIcenumero());
			a.setKotiosoite(asiakas.getKotiosoite());
			a.setPuhnumero(asiakas.getPuhnumero());
			onnistui = true;
		} else {
			System.out.println("Ei löytynyt päivitettävää");
		}
		istunto.getTransaction().commit();
		istunto.close();
		return onnistui;
	}
	/**
	 * Metodi asiakkaan tietojen poistamiseen
	 * @param id Poistettavan asiakkaan id
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
	public boolean deleteAsiakas(int id) {
		boolean onnistui = false;
		Session istunto = istuntotehdas.openSession();
		istunto.beginTransaction();
		Asiakas a = (Asiakas) istunto.get(Asiakas.class, id);
		if (a != null) {
			istunto.delete(a);
			onnistui = true;
		}
		istunto.getTransaction().commit();
		istunto.close();
		return onnistui;
	}

}
