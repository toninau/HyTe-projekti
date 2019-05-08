package dao;

import model.Customer;
import model.Staff;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 
 * DataAccessObject for customer class
 *
 */
public class CustomerDAO {
	/**
	 * Sessionfactory for CRUD-operations
	 */
	private SessionFactory sessionFactory = null;
	private static final Logger LOGGER = Logger.getLogger(CustomerDAO.class.getName());

	/**
	 * Class constructor.
	 * 
	 * @param sessionfactory hibernate SessionFactory
	 */
	public CustomerDAO(SessionFactory sessionfactory) {
		this.sessionFactory = sessionfactory;
	}

	/**
	 * Create-method to save a customer object to the database. If given customerID
	 * is already in use, identifying number is added to the end of the customerID.
	 * 
	 * @param customer object
	 * @return <code>true</code> if customer was successfully saved <br>
	 *         <code>false</code> if customer was not saved
	 */
	public boolean create(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean success = false;
		try {
			if (customer.getCustomerID() == null || customer.getCustomerID().isEmpty()) {
				customer = createCustomerIDFromName(customer);
			}
			transaction = session.beginTransaction();
			session.save(customer);
			transaction.commit();
			success = true;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
		}
		session.close();
		return success;
	}

	/**
	 * Creates customerID using first name and surname of customer.
	 * 
	 * @param customer customer whose customerID is created
	 * @return customer
	 * @see #addNumberToCustomerID(String, int)
	 */
	private Customer createCustomerIDFromName(Customer customer) {
		Session session = sessionFactory.openSession();
		String customerID = "";
		String firstName = customer.getFirstName();
		String surname = customer.getSurname();
		customerID += firstName.toLowerCase().substring(0, Math.min(firstName.length(), 3));
		customerID += surname.toLowerCase().substring(0, Math.min(surname.length(), 3));
		String sql = "SELECT COUNT(*) FROM customer WHERE customerID LIKE :id";
		BigInteger result = (BigInteger) session.createSQLQuery(sql).setParameter("id", customerID + "%")
				.uniqueResult();
		session.close();
		int numberOfSameID = result.intValue() + 1;
		customer.setCustomerID(addNumberToCustomerID(customerID, numberOfSameID));
		return customer;
	}
	
	/**
	 * Adds number to customerID if it is needed.
	 * @param customerID basic id, without number
	 * @param number number of same basic id's
	 * @return customerID String
	 */
	private String addNumberToCustomerID(String customerID, int number) {
		Session session = sessionFactory.openSession();
		String numberString = "";
		if (number > 1) {
			numberString += number;
		}
		while (true) {
			Customer c = session.get(Customer.class, customerID + numberString);
			if (c == null) {
				customerID += numberString;
				break;
			}
			numberString = "";
			if (--number > 1) {
				numberString += number;
			}
		}
		session.close();
		return customerID;
	}

	/**
	 * Read-method to read customer data from database using customer ID which is an
	 * email
	 * 
	 * @param id of the customer
	 * @return Customer data
	 */
	public Customer read(String id) {
		Session session = sessionFactory.openSession();
		Customer customer = new Customer();
		try {
			session.beginTransaction();
			session.load(customer, id);
			session.getTransaction().commit();
		} catch (ObjectNotFoundException oe) {
			LOGGER.warning("Object not found");
		} catch (Exception e) {
			LOGGER.warning("Exception in read");
		} finally {
			session.close();
		}
		return customer;
	}

	/**
	 * Reads all customers from the database as a list
	 *
	 *
	 * @return list of customer objects
	 */
	@SuppressWarnings("unchecked")
	public Customer[] readAll() {
		Session session = sessionFactory.openSession();
		List<Customer> result = new ArrayList<>();
		try {
			session.beginTransaction();
			result = session.createQuery("from Customer").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.warning("Exception in read all");
		} finally {
			session.close();
		}
		Customer[] returnArray = new Customer[result.size()];
		return result.toArray(returnArray);
	}

	/**
	 * Reads the staff members connected to a customer
	 * 
	 * @param customer customer whose staff members are retrieved from the database
	 * @return List of staff objects
	 */
	@SuppressWarnings("unchecked")
	public Staff[] readCustomersStaff(Customer customer) {
		Session session = sessionFactory.openSession();
		Staff[] result = new Staff[0];
		try {
			session.beginTransaction();
			String sql = "SELECT * FROM staff INNER JOIN customersStaff on customersStaff.staffID = staff.staffID WHERE customersStaff.customerID = :id";
			Query<Staff> query = session.createSQLQuery(sql).addEntity(Staff.class);
			query.setParameter("id", customer.getCustomerID());
			result = query.list().toArray(new Staff[query.list().size()]);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.warning("Exception");
		} finally {
			session.close();
		}
		return result;
	}

	/**
	 * Update-method to update a customer's data
	 * 
	 * @param customer Customer who you want to update
	 * @return <code>true</code> if customer was successfully updated <br>
	 *         <code>false</code> if customer was not updated
	 */
	public boolean update(Customer customer) {
		boolean success = false;
		Session istunto = sessionFactory.openSession();
		istunto.beginTransaction();
		Customer a = istunto.get(Customer.class, customer.getCustomerID());
		if (a != null) {
			a.setCustomerID(customer.getCustomerID());
			a.setFirstName(customer.getFirstName());
			a.setSurname(customer.getSurname());
			a.setSSN(customer.getSSN());
			a.setIceNumber(customer.getIceNumber());
			a.setAddress(customer.getAddress());
			a.setPhoneNumber(customer.getPhoneNumber());
			a.setPassword(customer.getPassword());
			success = true;
		} 
		istunto.getTransaction().commit();
		istunto.close();
		return success;
	}

	/**
	 * Delete-method to delete a customer object from the database
	 * 
	 * @param id of the removable customer object
	 * @return <code>true</code> if customer was successfully deleted<br>
	 *         <code>false</code> if customer was not deleted
	 */
	public boolean delete(String id) {
		boolean success = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Customer a = session.get(Customer.class, id);
		if (a != null) {
			session.delete(a);
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
}
