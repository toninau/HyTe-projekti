package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
/**
 * 
 * Asiakkaiden hallintaan käytettävä DataAccessObject
 *
 */
public class CustomerDAO {
	/**
	 * Sessionfactory, jota käytetään CRUD-operaatioihin
	 */
	private SessionFactory sessionFactory = null;

	/**
	 * Luokan konstruktori.
	 * @param session Saa parametrina Sessionfactory-olion, jota käytetään koko sovelluksessa
	 */
	public CustomerDAO(SessionFactory session) {
		this.sessionFactory = session;
	}
	
	/**
	 * Uuden kentän tietokantaan tallentava metodi
	 * @param customer tietokantaan tallennettava asiakas
	 * @return true, mikäli operaatio onnistui, muuten false
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
	 * Metodi yhden asiakkaan tietojen hakemiseen tietokannasta
	 * @param id Haettavan asiakkaan id
	 * @return Asiakkaan tiedot
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
	 * Lukee tietokannasta listana kaikki asiakkaat
	 *
	 *
	 * @return lista, joka sisltää asiakkaat
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
	 * Metodi henkilökunnan jäsenten, joiden asiakkaana tietty asiakas on, hakemiseen
	 * @param customer Asiakas, jonka henkilökunta haetaan
	 * @return Lista henkilökunnan jäsenistä
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
	 * Metodi asiakkaan tietojen päivittämistä varten
	 * @param customer Asiakas, jonka tietoja päivitetään
	 * @return true, jos operaatio onnistui, muuten false
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
	 * Metodi asiakkaan tietojen poistamiseen
	 * @param id Poistettavan asiakkaan id
	 * @return true, mikäli operaatio onnistui, muuten false
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
