package dao;

import model.Customer;
import model.Prescription;
import model.Staff;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.format.DateTimeFormatter;

/**
 * 
 * DataAccessObject for prescription class
 *
 */
public class PrescriptionDAO {
	/**
	 * SessionFactory for CRUD-operations
	 */
	private SessionFactory sessionFactory = null;

	/**
	 * Class constructor
	 * 
	 * @param sessionFactory hibernate SessionFactory
	 */
	public PrescriptionDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Saves prescription to the database.
	 * 
	 * @param prescription prescription object to be saved in database
	 * @return <code>true</code> if prescription was successfully saved <br>
	 *         <code>false</code> if prescription was not saved
	 */
	public boolean create(Prescription prescription) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean success = false;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(prescription);
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
	 * Retrieves prescription from database.
	 * 
	 * @param id prescriptionID of the wanted prescription
	 * @return Prescription object
	 */
	public Prescription read(int id) {
		Session session = sessionFactory.openSession();
		Prescription prescription = new Prescription();
		try {
			session.beginTransaction();
			session.load(prescription, id);
			session.getTransaction().commit();
		} catch (ObjectNotFoundException oe) {
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return prescription;
	}

	/**
	 * Retrieves all customers prescription from the database.
	 * 
	 * @param customer customer whose prescriptions are retrieved from the database
	 * @return array of prescription objects
	 */
	@SuppressWarnings("unchecked")
	public Prescription[] readCustomersPrescriptions(Customer customer) {
		Session session = sessionFactory.openSession();
		Prescription[] result = new Prescription[0];
		try {
			session.beginTransaction();
			String sql = "SELECT * FROM prescription INNER JOIN customer on prescription.customerID = customer.customerID WHERE customer.customerID = :id";
			Query<Prescription> query = session.createSQLQuery(sql).addEntity(Prescription.class);
			query.setParameter("id", customer.getCustomerID());
			result = query.list().toArray(new Prescription[query.list().size()]);
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return result;
	}

	/**
	 * Retrieves all prescriptions that a staff member has assigned to a customer.
	 * 
	 * @param staff staff member whose assigned prescription are retrieved from the
	 *              database
	 * @return array of prescription objects
	 */
	@SuppressWarnings("unchecked")
	public Prescription[] readStaffsPrescriptions(Staff staff) {
		Session session = sessionFactory.openSession();
		Prescription[] result = new Prescription[0];
		try {
			session.beginTransaction();
			String sql = "SELECT * FROM prescription INNER JOIN staff on prescription.staffID = staff.staffID WHERE staff.staffID = :id";
			Query<Prescription> query = session.createSQLQuery(sql).addEntity(Prescription.class);
			query.setParameter("id", staff.getStaffID());
			result = query.list().toArray(new Prescription[query.list().size()]);
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}
		return result;
	}

	/**
	 * Deletes prescription from the database.
	 * 
	 * @param id prescriptionID of the prescription to be deleted
	 * @return <code>true</code> if prescription was successfully deleted<br>
	 *         <code>false</code> if prescription was not deleted
	 */
	public boolean delete(int id) {
		boolean success = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Prescription r = session.get(Prescription.class, id);
		if (r != null) {
			session.delete(r);
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
	
	public boolean update(Prescription prescription) {
		DateTimeFormatter dformatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH.mm");
		
		boolean success = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Prescription p = session.get(Prescription.class, prescription.getPrescriptionID());
		if (p != null) {
			p.setCustomer(prescription.getCustomer());
			p.setStaff(prescription.getStaff());
			p.setDosage(prescription.getDosage());
			p.setEndDate(prescription.getEndDate().format(dformatter));
			p.setPrescriptionGuide(prescription.getPrescriptionGuide());
			p.setPrescriptionName(prescription.getPrescriptionName());
			p.setTimeToTake(prescription.getTimeToTake());
			p.setRenewPrescription(prescription.isRenewPrescription());
			p.setStartDate(prescription.getStartDate().format(dformatter));
			p.setTakenAt(prescription.getTakenAt().format(dtFormatter));
			success = true;
		} 
		session.getTransaction().commit();
		session.close();
		return success;
	}
}
