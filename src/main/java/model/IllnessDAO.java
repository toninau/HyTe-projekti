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
public class IllnessDAO {
	/**
	 * Sessionfactory, jota käytetään CRUD-operaatioihin
	 */
	private SessionFactory sessionFactory = null;
	/**
	 * Luokan konstruktori.
	 * @param sessionFactory Saa parametrina Sessionfactory-olion, jota käytetään koko sovelluksessa
	 */
	public IllnessDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}
	/**
	 * Uuden kentän tietokantaan tallentava metodi
	 * @param illness Tietokantaan tallennettava arvo
	 * @return true, Mikäli operaatio onnistui, muuten false
	 */
	public boolean create(Illness illness) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean onnistui = false;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(illness);
			transaction.commit();
			onnistui = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return onnistui;
	}
	/**
	 * Lukee tietokannasta listana kaikki asiakkaan sairauksia
	 * @param customer asiakas, jonka sairauksia luetaan
	 *
	 * @return lista, joka sisältää arvot
	 */
	@SuppressWarnings("unchecked")
	public Illness[] readCustomersIllnessess(Customer customer) {
		Session istunto = sessionFactory.openSession();
		List<Illness> result = null;
		try {
			istunto = sessionFactory.openSession();
			istunto.beginTransaction();
			String sql = "SELECT * FROM illness INNER JOIN customer on illness.customerID = customer.customerID WHERE customer.customerID = :id";
			Query<Illness> kysely = istunto.createSQLQuery(sql).addEntity(Illness.class);
			kysely.setParameter("id", customer.getCustomerID());
			result = kysely.list();
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		Illness[] returnArray = new Illness[result.size()];
		return (Illness[]) result.toArray(returnArray);
	}
	/**
	 * Metodi poistaa jonkin sairauden tietokannasta
	 * @param id Poistettavan sairauden id
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
	public boolean delete(int id) {
		boolean success = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Illness s = (Illness) session.get(Illness.class, id);
		if (s != null) {
			session.delete(s);
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
}
