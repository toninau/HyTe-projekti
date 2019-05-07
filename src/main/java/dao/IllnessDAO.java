package dao;

import model.Customer;
import model.Illness;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


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
	 * 
	 * @param sessionFactory SessionFactory object
	 */
	public IllnessDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	/**
	 * Create-method to save an illness object to the database
	 * 
	 * @param illness object to be created
	 * @return <code>true</code> if illness was successfully saved <br>
	 *         <code>false</code> if illness was not saved
	 */
	public boolean create(Illness illness) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean success = false;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(illness);
			transaction.commit();
			success = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return success;
	}

	/**
	 * Read-method to save an illness object from the database
	 * 
	 * @param customer customer whom the illness belongs to
	 * @return list of customers illnesses
	 */
	@SuppressWarnings("unchecked")
	public Illness[] readCustomersIllnessess(Customer customer) {
		Session session = sessionFactory.openSession();
		Illness[] result = new Illness[0];
		try {
			session.beginTransaction();
			String sql = "SELECT * FROM illness INNER JOIN customer on illness.customerID = customer.customerID WHERE customer.customerID = :id";
			Query<Illness> query = session.createSQLQuery(sql).addEntity(Illness.class);
			query.setParameter("id", customer.getCustomerID());
			result = query.list().toArray(new Illness[query.list().size()]);
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return result;
	}

	/**
	 * Delete-method to remove an illness from the database
	 * 
	 * @param id of the illness object
	 * @return <code>true</code> if illness was successfully deleted<br>
	 *         <code>false</code> if illness was not deleted
	 */
	public boolean delete(int id) {
		boolean success = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Illness s = session.get(Illness.class, id);
		if (s != null) {
			session.delete(s);
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
	
}
