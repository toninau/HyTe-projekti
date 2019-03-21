package model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
/**
 * 
 * Reseptien hallintaan käytettävä DataAccessObject
 *
 */
public class PrescriptionDAO {
	/**
	 * Sessionfactory, jota käytetään CRUD-operaatioihin
	 */
	private SessionFactory sessionFactory = null;
	/**
	 * Luokan konstruktori.
	 * @param sessionFactory Saa parametrina Sessionfactory-olion, jota käytetään koko sovelluksessa
	 */
	public PrescriptionDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	/**
	 * Uuden kentän tietokantaan tallentava metodi
	 * @param prescription tietokantaan tallennettava reseptiolio
	 * @return true, mikäli operaatio onnistui, muuten false
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
	 * Hakee jonkin tietyn reseptin tietokannasta
	 * @param id Haettavan reseptin id
	 * @return Haettu resepti
	 */
	public Prescription read(int id) {
		Session session = sessionFactory.openSession();
		Prescription prescription = new Prescription();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.load(prescription, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return prescription;
	}
	/**
	 * Lukee tietokannasta listana kaikki asiakkaan reseptit
	 * @param customer asiakas, jonka reseptit luetaan
	 *
	 * @return lista, joka sisltää reseptit
	 */
	@SuppressWarnings("unchecked")
	public Prescription[] readCustomersPrescriptions(Customer customer) {
		Session istunto = sessionFactory.openSession();
		List<Prescription> result = null;
		try {
			istunto = sessionFactory.openSession();
			istunto.beginTransaction();
			String sql = "SELECT * FROM prescription INNER JOIN customer on prescription.customerID = customer.customerID WHERE customer.customerID = :id";
			Query<Prescription> kysely = istunto.createSQLQuery(sql).addEntity(Prescription.class);
			kysely.setParameter("id", customer.getCustomerID());
			result = kysely.list();
			istunto.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			istunto.close();
		}
		Prescription[] returnArray = new Prescription[result.size()];
		return (Prescription[]) result.toArray(returnArray);
	}
	/**
	 * Metodi jonkin henkilökunnan jäsenen kirjoittamien reseptien hakemiseen
	 * @param staff Henkilökunnan jäsen, jonka reseptejä haetaan
	 * @return Lista resepteistä
	 */
	@SuppressWarnings("unchecked")
	public Prescription[] readStaffsPrescriptions(Staff staff) {
		Session session = sessionFactory.openSession();
		List<Prescription> result = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			String sql = "SELECT * FROM prescription INNER JOIN staff on prescription.staffID = staff.staffID WHERE staff.staffID = :id";
			Query<Prescription> kysely = session.createSQLQuery(sql).addEntity(Prescription.class);
			kysely.setParameter("id", staff.getStaffID());
			result = kysely.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		Prescription[] returnArray = new Prescription[result.size()];
		return (Prescription[]) result.toArray(returnArray);
	}
	/**
	 * Metodi jonkin reseptin poistoon tietokannasta
	 * @param id Poistettavan reseptin id
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
	public boolean delete(int id) {
		boolean success = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Prescription r = (Prescription) session.get(Prescription.class, id);
		if (r != null) {
			session.delete(r);
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
}
