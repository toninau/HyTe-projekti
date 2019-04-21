package dao;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Customer;
import model.HibernateUtil;
import model.Staff;

import java.math.BigInteger;
import java.util.List;

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
			customer = createCustomerIDFromName(customer);
			transaction = session.beginTransaction();
			session.saveOrUpdate(customer);
			transaction.commit();
			success = true;
		} catch (Exception e) {
			transaction.rollback();
		} finally {
			session.close();
		}
		return success;
	}

	/**
	 * Creates customerID using first name and surname of customer.
	 * 
	 * @param customer customer whose customerID is created
	 * @return customer
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
		if (result.intValue() > 0) {
			int number = result.intValue() + 1;
			customer.setCustomerID(customerID + number);
		} else {
			customer.setCustomerID(customerID);
		}
		session.close();
		return customer;
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
			System.out.println("Customer not found");
		} catch (Exception e) {
			e.printStackTrace();
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
		List<Customer> result = null;
		try {
			session.beginTransaction();
			result = session.createQuery("from Customer").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		Customer[] returnArray = new Customer[result.size()];
		return (Customer[]) result.toArray(returnArray);
	}

	/**
	 * Reads the staff members connected to a customer
	 * 
	 * @param customer customer whose staff members are retrieved from the database
	 * @return List of staff objects
	 */
	@SuppressWarnings("unchecked")
	public Staff[] readAsiakkaanHenkilökunta(Customer customer) {
		Session session = sessionFactory.openSession();
		List<Staff> result = null;
		try {
			session.beginTransaction();
			String sql = "SELECT * FROM staff INNER JOIN customersStaff on customersStaff.staffID = staff.staffID WHERE customersStaff.customerID = :id";
			Query<Staff> query = session.createSQLQuery(sql).addEntity(Staff.class);
			query.setParameter("id", customer.getCustomerID());
			result = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		Staff[] returnArray = new Staff[result.size()];
		return (Staff[]) result.toArray(returnArray);
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
		Customer a = (Customer) istunto.get(Customer.class, customer.getCustomerID());
		if (a != null) {
			a.setCustomerID(customer.getCustomerID());
			a.setFirstName(customer.getFirstName());
			a.setSurname(customer.getSurname());
			a.setSSN(customer.getSSN());
			a.setIceNumber(customer.getIceNumber());
			a.setAddress(customer.getAddress());
			a.setPhoneNumber(customer.getPhoneNumber());
			success = true;
		} else {
			System.out.println("Nothing to update");
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
		Customer a = (Customer) session.get(Customer.class, id);
		if (a != null) {
			session.delete(a);
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
}
