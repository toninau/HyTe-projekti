package dao;

import model.BloodValue;
import model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * 
 * 
 * Customer's blood value DataAccessObject
 */
public class BloodValueDAO {
	/**
	 * Sessionfactory for CRUD operations
	 */
	private SessionFactory sessionfactory = null;

	/**
	 * Class constructor
	 * 
	 * @param sessionfactory hibernate SessionFactory
	 * 
	 */
	public BloodValueDAO(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}

	/**
	 * Method for saving a blood value object to the database
	 * 
	 * @param bloodvalue value for the database
	 * @return <code>true</code> if bloodvalue was successfully created<br>
	 *         <code>false</code> if bloodvalue was not created
	 */
	public boolean create(BloodValue bloodvalue) {
		Session session = sessionfactory.openSession();
		Transaction transaction = null;
		boolean success = false;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(bloodvalue);
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
	 * Reads all the saved blood values from a customer
	 * 
	 * @param customer customer from where the values are retrieved
	 * @return list of the values
	 */
	@SuppressWarnings("unchecked")
	public BloodValue[] readCustomerBloodvalues(Customer customer) {
		Session session = sessionfactory.openSession();
		List<BloodValue> result = null;
		try {
			session.beginTransaction();
			String sql = "SELECT * FROM bloodvalue INNER JOIN customer on bloodvalue.customerID = customer.customerID WHERE customer.customerID = :id";
			Query<BloodValue> query = session.createSQLQuery(sql).addEntity(BloodValue.class);
			query.setParameter("id", customer.getCustomerID());
			result = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		BloodValue[] returnArray = new BloodValue[result.size()];
		return result.toArray(returnArray);
	}

	/**
	 * Removes a blood value from the database
	 * 
	 * @param id of the blood value object
	 * @return <code>true</code> if bloodvalue was successfully deleted<br>
	 *         <code>false</code> if bloodvalue was not deleted
	 */
	public boolean delete(int id) {
		boolean success = false;
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		BloodValue v = session.get(BloodValue.class, id);
		if (v != null) {
			session.delete(v);
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
}