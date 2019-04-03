package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Customer;
import model.Illness;

import java.util.List;
/**
 * 
 * DataAccessObject for Illness class.
 *
 */
public class IllnessDAO {
	/**
	 * Sessionfactory for CRUD operations.
	 */
	private SessionFactory sessionFactory = null;
	/**
	 * Class constructor.
	 * @param sessionFactory SessionFactory object
	 */
	public IllnessDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}
	/**
	 * Create-method to save an illness object to the database
	 * @param Illness object
	 * @return true if success
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
	 * Read-method to save an illness object from the database
	 * @param Customer customer, which the illness belongs to
	 * @return true if success
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
	 * Delete-method to remove an illness from the database
	 * @param id of the illness object
	 * @return true if success
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
