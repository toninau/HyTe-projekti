package dao;

import model.Appointment;
import model.Customer;
import model.Staff;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

/**
 * 
 * Customer appointment DataAccessObject
 *
 */
public class AppointmentDAO {
	/**
	 * Sessionfactory used for CRUD operations
	 */
	private SessionFactory sessionfactory = null;
	private final Logger LOGGER = Logger.getLogger(AppointmentDAO.class.getName());


	/**
	 * Class constructor
	 * 
	 * @param sessionfactory used in all CRUD methods
	 */
	public AppointmentDAO(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}

	/**
	 * CREATE-Method for saving an appointment to the database
	 * 
	 * @param appointment object to save into the database
	 * @return <code>true</code> if appointment was successfully created<br>
	 *         <code>false</code> if appointment was not created
	 */
	public boolean create(Appointment appointment) {
		Session session = sessionfactory.openSession();
		Transaction transaction = null;
		boolean success = false;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(appointment);
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
	 * READ-Method to read an appointment by ID
	 * 
	 * @param id of the appointment
	 * @return appointment
	 */
	public Appointment read(int id) {
		Session session = sessionfactory.openSession();
		Appointment appointment = new Appointment();
		try {
			session.beginTransaction();
			session.load(appointment, id);
			session.getTransaction().commit();
		} catch (Exception oe) { 
			LOGGER.warning("Object not found.");
		}
		finally {
			session.close();
		}
		return appointment;
	}

	/**
	 * READ-Method to read all appointments from a customer
	 * 
	 * @param customer customer whose appointments are retrieved from the database
	 * @return List of all appointments for customer
	 * @see #readStaffAppointments(Staff)
	 */
	@SuppressWarnings("unchecked")
	public Appointment[] readCustomerAppointments(Customer customer) {
		Session session = sessionfactory.openSession();
		Appointment[] result = new Appointment[0];
		try {
			session.beginTransaction();
			String sql = "SELECT * FROM appointment INNER JOIN customer on appointment.customerID = customer.customerID WHERE customer.customerID = :id";
			Query<Appointment> query = session.createSQLQuery(sql).addEntity(Appointment.class);
			query.setParameter("id", customer.getCustomerID());
			result = query.list().toArray(new Appointment[query.list().size()]);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.warning("Exception");
		}
		finally {
			session.close();
		}
		return result;
	}

	/**
	 * READ-Method to read all appointments made by a staff member
	 * 
	 * @param staff staff member whose appointments are retrieved from the database
	 * @return List of all appointments for staff member
	 * @see #readCustomerAppointments(Customer)
	 */
	@SuppressWarnings("unchecked")
	public Appointment[] readStaffAppointments(Staff staff) {
		Session session = sessionfactory.openSession();
		Appointment[] result = new Appointment[0];
		try {
			session = sessionfactory.openSession();
			session.beginTransaction();
			String sql = "SELECT * FROM appointment INNER JOIN staff on appointment."
					+ "staffID = staff.staffID WHERE staff.staffID = :id";
			Query<Appointment> query = session.createSQLQuery(sql).addEntity(Appointment.class);
			query.setParameter("id", staff.getStaffID());
			result = query.list().toArray(new Appointment[query.list().size()]);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.warning("Exception");
		} finally {
			session.close();
		}
		return result;
	}

	/**
	 * UPDATE-Method to update an appointment in the database
	 * 
	 * @param appointment an existing appointment
	 * @return <code>true</code> if appointment was successfully updated<br>
	 *         <code>false</code> if appointment was not updated
	 */
	public boolean update(Appointment appointment) {
		DateTimeFormatter dformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		DateTimeFormatter tformatter = DateTimeFormatter.ofPattern("HH.mm");

		boolean success = false;
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		Appointment a = session.get(Appointment.class, appointment.getAppointmentID());
		if (a != null) {
			a.setCustomer(appointment.getCustomer());
			a.setStaff(appointment.getStaff());
			a.setInfo(appointment.getInfo());
			a.setTime(appointment.getTime().format(tformatter));
			a.setDate(appointment.getDate().format(dformatter));
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}

	/**
	 * DELETE-Method to remove an appointment from the database
	 * 
	 * @param id of the Appointment object
	 * @return <code>true</code> if appointment was successfully deleted<br>
	 *         <code>false</code> if appointment was not deleted
	 */
	public boolean delete(int id) {
		boolean success = false;
		Session session = sessionfactory.openSession();
		session.beginTransaction();
		Appointment a = session.get(Appointment.class, id);
		if (a != null) {
			session.delete(a);
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
}
