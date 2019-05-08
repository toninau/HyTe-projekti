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
 * DataAccessObject for staff class
 *
 */
public class StaffDAO {
	/**
	 * Sessionfactory for CRUD-operations
	 */
	private SessionFactory sessionFactory = null;
	private static final Logger LOGGER = Logger.getLogger(StaffDAO.class.getName());

	/**
	 * Class constructor.
	 * 
	 * @param session hibernate SessionFactory
	 */
	public StaffDAO(SessionFactory session) {
		this.sessionFactory = session;
	}

	/**
	 * Saves staff member to the database. If given staffID is already in use,
	 * identifying number is added to the end of the staffID.
	 * 
	 * @param staff object
	 * @return <code>true</code> if customer was successfully saved <br>
	 *         <code>false</code> if customer was not saved
	 */
	public boolean create(Staff staff) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean success = false;
		try {
			if (staff.getStaffID() == null || staff.getStaffID().isEmpty()) {
				staff = createStaffIDFromName(staff);
			}
			transaction = session.beginTransaction();
			session.save(staff);
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
	 * Creates staffID using first name and surname of staff member.
	 * 
	 * @param staff staff member whose staffID is created
	 * @return staff
	 */
	private Staff createStaffIDFromName(Staff staff) {
		Session session = sessionFactory.openSession();
		String staffID = "";
		String firstName = staff.getFirstName();
		String surname = staff.getSurname();
		staffID += firstName.toLowerCase().substring(0, Math.min(firstName.length(), 3));
		staffID += surname.toLowerCase().substring(0, Math.min(surname.length(), 3));
		String sql = "SELECT COUNT(*) FROM staff WHERE staffID LIKE :id";
		BigInteger result = (BigInteger) session.createSQLQuery(sql).setParameter("id", staffID + "%").uniqueResult();
		int number = result.intValue() + 1;
		String numberString = "";
		if (number > 1) {
			numberString += number;
		}
		while (true) {
			Staff s = session.get(Staff.class, staffID + numberString);
			if (s == null) {
				staff.setStaffID(staffID + numberString);
				break;
			}
			numberString = "";
			if (--number > 1) {
				numberString += number;
			}
		}
		session.close();
		return staff;
	}

	/**
	 * Adds customer to staff member.
	 * 
	 * @param staff    staff member whom the customer is added
	 * @param customer customer who is added
	 * @return <code>true</code> if customer was successfully added <br>
	 *         <code>false</code> if customer was not added
	 */
	@SuppressWarnings("rawtypes")
	public boolean addCustomer(Staff staff, Customer customer) {
		Session session = sessionFactory.openSession();
		boolean success = false;
		try {
			session.beginTransaction();
			String sql = "INSERT INTO customersStaff (customerID, staffID) VALUES (:customerID,:staffID)";
			Query query = session.createSQLQuery(sql).addEntity(Staff.class).addEntity(Customer.class);
			query.setParameter("customerID", customer.getCustomerID());
			query.setParameter("staffID", staff.getStaffID());
			query.executeUpdate();
			success = true;
		} catch (Exception e) {
			LOGGER.warning("Exception");
		} finally {
			session.close();
		}
		return success;
	}

	/**
	 * Deletes customer from staff member.
	 * 
	 * @param staff    staff member whom the customer is deleted from
	 * @param customer customer who is deleted
	 * @return <code>true</code> if customer was successfully deleted<br>
	 *         <code>false</code> if customer was not deleted
	 */
	@SuppressWarnings("rawtypes")
	public boolean deleteCustomer(Staff staff, Customer customer) {
		Session session = sessionFactory.openSession();
		boolean success = false;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			String sql = "DELETE FROM customersStaff WHERE customersStaff.customerID = :customerID AND customersStaff.staffID = :staffID";
			Query kysely = session.createSQLQuery(sql).addEntity(Staff.class).addEntity(Customer.class);
			kysely.setParameter("customerID", customer.getCustomerID());
			kysely.setParameter("staffID", staff.getStaffID());
			kysely.executeUpdate();
			success = true;
		} catch (Exception e) {
			LOGGER.warning("Exception");
		} finally {
			session.close();
		}
		return success;
	}

	/**
	 * Returns an array of staff members customers.
	 * 
	 * @param staff staff member whose customers are retrieved
	 * @return array of staff members customers
	 */
	@SuppressWarnings("unchecked")
	public Customer[] readStaffMembersCustomers(Staff staff) {
		Session session = sessionFactory.openSession();
		Customer[] result = new Customer[0];
		try {
			session.beginTransaction();
			String sql = "select * from customer inner join customersStaff, staff where customersStaff.customerID = customer.customerID and customersStaff.staffID = :id";
			Query<Customer> query = session.createSQLQuery(sql).addEntity(Customer.class);
			query.setParameter("id", staff.getStaffID());
			session.getTransaction().commit();
			result = query.list().toArray(new Customer[query.list().size()]);
		} catch (Exception e) {
			LOGGER.warning("Exception");
		} finally {
			session.close();
		}
		return result;
	}

	/**
	 * Retrieves staff member from the database.
	 * 
	 * @param id staffID of the wanted staff member
	 * @return staff object
	 */
	public Staff read(String id) {
		Session session = sessionFactory.openSession();
		Staff staff = new Staff();
		try {
			session.beginTransaction();
			session.load(staff, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.warning("Object not found");
		} finally {
			session.close();
		}
		return staff;
	}

	/**
	 * Retrieves all staff members from the database.
	 * 
	 * @return array of staff members.
	 */
	@SuppressWarnings("unchecked")
	public Staff[] readAll() {
		Session session = sessionFactory.openSession();
		List<Staff> result = new ArrayList<>();
		try {
			session.beginTransaction();
			result = session.createQuery("from Staff").list();
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.warning("Exception");
		} finally {
			session.close();
		}
		Staff[] returnArray = new Staff[result.size()];
		return result.toArray(returnArray);
	}

	/**
	 * Updates staff information to the database.
	 * 
	 * @param staff staff member to be updated in the database
	 * @return <code>true</code> if staff member was successfully updated <br>
	 *         <code>false</code> if staff member was not updated
	 */
	public boolean update(Staff staff) {
		boolean success = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Staff s = session.get(Staff.class, staff.getStaffID());
		if (s != null) {
			s.setFirstName(staff.getFirstName());
			s.setSurname(staff.getSurname());
			s.setAccessLevel(staff.getAccessLevel());
			s.setPhoneNumber(staff.getPhoneNumber());
			s.setPassword(staff.getPassword());
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}

	/**
	 * Deletes staff member from the database.
	 * 
	 * @param id staffID of the staff member to be deleted
	 * @return <code>true</code> if staff member was successfully deleted<br>
	 *         <code>false</code> if staff member was not deleted
	 */
	@SuppressWarnings("unchecked")
	public boolean delete(String id) {
		boolean success = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Staff s = session.get(Staff.class, id);
		if (s != null) {
			session.delete(s);
			String sql = "DELETE FROM customersStaff WHERE customersStaff.staffID = :id";
			Query<Staff> query = session.createSQLQuery(sql).addEntity(Staff.class);
			query.setParameter("id", s.getStaffID());
			query.executeUpdate();
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
	@SuppressWarnings("unchecked")
	public boolean addCustomerToStaff(Customer customer, Staff staff) {
		boolean success = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Staff s = session.get(Staff.class, staff.getStaffID());
		Customer c = session.get(Customer.class, customer.getCustomerID());
		if (s != null && c != null) {
			String sql = "INSERT INTO `hyte`.`customersStaff` (`customerID`, `staffID`) VALUES (:cid, :id)";
			Query<Staff> query = session.createSQLQuery(sql).addEntity(Staff.class).addEntity(Customer.class);
			query.setParameter("id", s.getStaffID());
			query.setParameter("cid", c.getCustomerID());
			query.executeUpdate();
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
}
