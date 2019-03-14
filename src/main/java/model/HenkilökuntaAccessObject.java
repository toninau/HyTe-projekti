package model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
/**
 * 
 * Henkilökunnan jäsenten hallintaan käytettävä DataAccessObject
 *
 */
public class HenkilökuntaAccessObject {
	/**
	 * Sessionfactory, jota käytetään CRUD-operaatioihin
	 */
	private SessionFactory istuntotehdas = null;
	
	/**
	 * Luokan konstruktori.
	 * @param istunto Saa parametrina Sessionfactory-olion, jota käytetään koko sovelluksessa
	 */
	public HenkilökuntaAccessObject(SessionFactory istunto) {
		this.istuntotehdas = istunto;
	}
	/**
	 * Uuden kentän tietokantaan tallentava metodi
	 * @param henkilö tietokantaan tallennettava henkilökunnan jäsen
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
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
	/**
	 * Lisää asiakkaan jonkin henkilökunnan jäsenen asiakkaaksi
	 * @param henkilö Henkilökunnan jäsen, jolle asiakas lisätään
	 * @param asiakas Lisättävä asiakas
	 * @return true, jos operaatio onnistui, muuten false
	 */
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
	/**
	 * Poistaa asiakkaan "asiakkuuden" joltain henkilökunnan jäseneltä
	 * @param henkilö Henkilökunnan jäsen, jolta asiakas poistetaan
	 * @param asiakas Poistettava asiakas
	 * @return true, jos operaatio onnistui, muuten false
	 */
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
	/**
	 * Palauttaa listan jonkun henkilökunnan jäsenen asiakkaista
	 * @param henkilö Henkilökunnan jäsen, jonka asiakkaita haetaan
	 * @return Lista henkilökunnan jäsenen asiakkaista
	 */
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
	/**
	 * Hakee tietokannasta halutun henkilön
	 * @param id Haettavan henkilön id
	 * @return Haettu henkilökunnan jäsen
	 */
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
	/**
	 * Lukee tietokannasta listana kaikki henkilökunnan jäsenet
	 * 
	 *
	 * @return lista, joka sisltää henkilökunnan jäsenet
	 */
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
	/**
	 * Metodi jonkin henkilökunnan jäsenen tietojen päivittämiseen
	 * @param henkilökunta Päivitettävä henkilökunnan jäsen
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
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
/**
 * Poistaa yhden henkilökunnan jäsenen
 * @param id poistettavan henkilökunnan jäsenen id
 * @return true, jos operaatio onnistui, muuten false
 */
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
