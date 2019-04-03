package dao;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hsqldb.rights.User;

import model.Customer;
import model.Staff;
/**
 * 
 * Henkilökunnan jäsenten hallintaan käytettävä DataAccessObject
 *
 */
public class StaffDAO {
	/**
	 * Sessionfactory, jota käytetään CRUD-operaatioihin
	 */
	private SessionFactory sessionFactory = null;
	
	/**
	 * Luokan konstruktori.
	 * @param session Saa parametrina Sessionfactory-olion, jota käytetään koko sovelluksessa
	 */
	public StaffDAO(SessionFactory session) {
		this.sessionFactory = session;
	}
	/**
	 * Uuden kentän tietokantaan tallentava metodi
	 * @param staff tietokantaan tallennettava henkilökunnan jäsen
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
	public boolean create(Staff staff) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean success = false;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(staff);
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
	 * Lisää asiakkaan jonkin henkilökunnan jäsenen asiakkaaksi
	 * @param staff Henkilökunnan jäsen, jolle asiakas lisätään
	 * @param customer Lisättävä asiakas
	 * @return true, jos operaatio onnistui, muuten false
	 */
	@SuppressWarnings("rawtypes")
	public boolean addCustomer(Staff staff, Customer customer) {
		Session session = sessionFactory.openSession();
		boolean success = false;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			String sql = "INSERT INTO customersStaff (customerID, staffID) VALUES (:customerID,:staffID)";
			Query kysely = session.createSQLQuery(sql);
			kysely.setParameter("customerID", customer.getCustomerID());
			kysely.setParameter("staffID", staff.getStaffID());
			kysely.executeUpdate();
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return success;
	}
	/**
	 * Poistaa asiakkaan "asiakkuuden" joltain henkilökunnan jäseneltä
	 * @param henkilö Henkilökunnan jäsen, jolta asiakas poistetaan
	 * @param customer Poistettava asiakas
	 * @return true, jos operaatio onnistui, muuten false
	 */
	@SuppressWarnings("rawtypes")
	public boolean deleteCustomer(Staff staff, Customer customer) {
		Session session = sessionFactory.openSession();
		boolean success = false;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			String sql = "DELETE FROM customersStaff WHERE customersStaff.customerID = :customerID AND customersStaff.staffID = :staffID";
			Query kysely = session.createSQLQuery(sql);
			kysely.setParameter("customerID", customer.getCustomerID());
			kysely.setParameter("staffID", staff.getStaffID());
			kysely.executeUpdate();
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return success;
	}
	/**
	 * Palauttaa listan jonkun henkilökunnan jäsenen asiakkaista
	 * @param henkilö Henkilökunnan jäsen, jonka asiakkaita haetaan
	 * @return Lista henkilökunnan jäsenen asiakkaista
	 */
	@SuppressWarnings("unchecked")
	public Customer[] readHenkilönAsiakkaat(Staff staff) {
		Session session = sessionFactory.openSession();
		List<Customer> result = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			String sql = "SELECT * FROM customer INNER JOIN customersStaff on customersStaff.customerID = customer.customerID WHERE customersStaff.staffID = :id";
			Query<Customer> kysely = session.createSQLQuery(sql).addEntity(Customer.class);
			kysely.setParameter("id", staff.getStaffID());
			result = kysely.list();
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
	 * Hakee tietokannasta halutun henkilön
	 * @param id Haettavan henkilön id
	 * @return Haettu henkilökunnan jäsen
	 */
	public Staff read(String id) {
		Session session = sessionFactory.openSession();
		Staff staff = new Staff();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.load(staff, id);
			session.getTransaction().commit();
		}catch (ObjectNotFoundException oe) {
			System.out.println("User not found");
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return staff;
	}
	
	public Staff readEmail(String emaila) {
		Session session = sessionFactory.openSession();
		String a = emaila;
		String sql = "select id from Staff where email = :emailp";

		//List<Customer> result = session.createQuery(sql).setParameter("emailp", a).list();
		String id = (String) session.createQuery(sql).setParameter("emailp", a).getSingleResult();
		System.out.println(id);
		return read(id);
	}
	/**
	 * Lukee tietokannasta listana kaikki henkilökunnan jäsenet
	 * 
	 *
	 * @return lista, joka sisltää henkilökunnan jäsenet
	 */
	@SuppressWarnings("unchecked")
	public Staff[] readAll() {
		Session session = sessionFactory.openSession();
		List<Staff> result = null;
		try {
			session.beginTransaction();
			result = session.createQuery("from Staff").list();
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
	 * Metodi jonkin henkilökunnan jäsenen tietojen päivittämiseen
	 * @param staff Päivitettävä henkilökunnan jäsen
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
	public boolean update(Staff staff) {
		boolean success = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Staff h = (Staff) session.get(Staff.class, staff.getStaffID());
		if (h != null) {
			h.setFirstName(staff.getFirstName());
			h.setSurname(staff.getSurname());
			h.setAccessLevel(staff.getAccessLevel());
			h.setPhoneNumber(staff.getPhoneNumber());
			success = true;
		} else {
			System.out.println("Ei löytynyt päivitettävää");
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
/**
 * Poistaa yhden henkilökunnan jäsenen
 * @param id poistettavan henkilökunnan jäsenen id
 * @return true, jos operaatio onnistui, muuten false
 */
	@SuppressWarnings("unchecked")
	public boolean delete(String id) {
		boolean success = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Staff h = (Staff) session.get(Staff.class, id);
		if (h != null) {
			session.delete(h);
			String sql = "DELETE FROM customersStaff WHERE customersStaff.staffID = :id";
			Query<Staff> kysely = session.createSQLQuery(sql).addEntity(Staff.class);
			kysely.setParameter("id", h.getStaffID());
			kysely.executeUpdate();
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
}
