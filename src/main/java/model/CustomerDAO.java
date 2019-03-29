package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
	 * @param sessionfactory
	 */
	public CustomerDAO(SessionFactory session) {
		this.sessionFactory = session;
	}
	
	/**
	 * Create-method to save a customer object to the database
	 * @param customer object
	 * @return true if success
	 */
	public boolean create(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean success = false;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(customer);
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
	 * Read-method to read customer data from database using customer ID
	 * @param id of the customer
	 * @return Customer data
	 */
	public Customer read(int id) {
		Session session = sessionFactory.openSession();
		Customer customer = new Customer();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.load(customer, id);
			session.getTransaction().commit();
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
			session = sessionFactory.openSession();
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
	 * @param customer 
	 * @return List of staff objects
	 */
	@SuppressWarnings("unchecked")
	public Staff[] readAsiakkaanHenkilökunta(Customer customer) {
		Session istunto = sessionFactory.openSession();
		List<Staff> result = null;
		try {
			istunto = sessionFactory.openSession();
			istunto.beginTransaction();
			String sql = "SELECT * FROM staff INNER JOIN customersStaff on customersStaff.staffID = staff.staffID WHERE customersStaff.customerID = :id";
			Query<Staff> kysely = istunto.createSQLQuery(sql).addEntity(Staff.class);
			kysely.setParameter("id", customer.getCustomerID());
			result = kysely.list();
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		Staff[] returnArray = new Staff[result.size()];
		return (Staff[]) result.toArray(returnArray);
	}
	/**
	 * Update-method to update a customer's data
	 * @param customer Customer who you want to update
	 * @return true if success
	 */
	public boolean update(Customer customer) {
		boolean success = false;
		Session istunto = sessionFactory.openSession();
		istunto.beginTransaction();
		Customer a = (Customer) istunto.get(Customer.class, customer.getCustomerID());
		if (a != null) {
			a.setFirstName(customer.getFirstName());
			a.setSurname(customer.getSurname());
			a.setSSN(customer.getSSN());
			a.setEmail(customer.getEmail());
			a.setIceNumber(customer.getIceNumber());
			a.setAddress(customer.getAddress());
			a.setPhoneNumber(customer.getPhoneNumber());
			success = true;
		} else {
			System.out.println("Ei löytynyt päivitettävää");
		}
		istunto.getTransaction().commit();
		istunto.close();
		return success;
	}
	/**
	 * Delete-method to delete a customer object from the database
	 * @param id of the removable customer object
	 * @return true if success
	 */
	public boolean delete(int id) {
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
