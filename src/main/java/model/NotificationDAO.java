package model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
/**
 * 
 * Ilmoitusten hallinnointiin käytettävä DataAccessObject
 *
 */
public class NotificationDAO {
	/**
	 * Sessionfactory, jota käytetään CRUD-operaatioihin
	 */
	private SessionFactory sessionFactory = null;
	/**
	 * Luokan konstruktori.
	 * @param sessionFactory Saa parametrina Sessionfactory-olion, jota käytetään koko sovelluksessa
	 */
	public NotificationDAO(SessionFactory istuntotehdas) {
		this.sessionFactory = istuntotehdas;
	}
	/**
	 * Uuden kentän tietokantaan tallentava metodi
	 * @param notification tietokantaan tallennettava ilmoitus
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
	public boolean create(Notification notification) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean success = false;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(notification);
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
	 * Hakee tietyn ilmoituksen tietokannasta
	 * @param id Haettavan ilmoituksen id
	 * @return Haettu ilmoitus
	 */
	public Notification read(int id) {
		Session session = sessionFactory.openSession();
		Notification notification = new Notification();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.load(notification, id);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return notification;
	}
	/**
	 * Lukee tietokannasta listana kaikki asiakkaan ilmoitukset
	 * @param customer asiakas, jonka ilmoituksia luetaan
	 *
	 * @return lista, joka sisältää arvot
	 */
	@SuppressWarnings("unchecked")
	public Notification[] readAsiakkaanIlmoitukset(Customer customer) {
		Session session = sessionFactory.openSession();
		List<Notification> result = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			String sql = "SELECT * FROM notification INNER JOIN customer on notification.customerID = customer.customerID WHERE customer.customerID = :id";
			Query<Notification> kysely = session.createSQLQuery(sql).addEntity(Notification.class);
			kysely.setParameter("id", customer.getCustomerID());
			result = kysely.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		Notification[] returnArray = new Notification[result.size()];
		return (Notification[]) result.toArray(returnArray);
	}
	/**
	 * Metodi päivittää ilmoituksen tietokantaan
	 * @param notification Päivitettävä ilmoitus
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
	public boolean update(Notification notification) {
		boolean success = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Notification i = (Notification) session.get(Notification.class, notification.getNotificationID());
		if (i != null) {
			i.setRead(notification.isRead());
			i.setText(notification.getText());
			success = true;
		} else {
			System.out.println("Ei löytynyt päivitettävää");
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
	/**
	 * Metodi poistaa halutun ilmoituksen
	 * @param id Poistettavan ilmoituksen id
	 * @return true, mikäli operaatio onnistui, muuten false
	 */
	public boolean delete(int id) {
		boolean success = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Notification i = (Notification) session.get(Notification.class, id);
		if (i != null) {
			session.delete(i);
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
}
