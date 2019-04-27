package dao;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import model.Customer;
import model.Notification;

/**
 * 
 * DataAccessObject for notification management
 *
 */
public class NotificationDAO {

	/**
	 * Sessionfactory for CRUD operations
	 */
	private SessionFactory sessionFactory = null;

	/**
	 * Class constructor
	 * 
	 * @param sessionFactory hibernate SessionFactory
	 */
	public NotificationDAO(SessionFactory istuntotehdas) {
		this.sessionFactory = istuntotehdas;
	}

	/**
	 * Create-method to save a Notification object to the database
	 * 
	 * @param notification object
	 * @return <code>true</code> if notification was successfully saved <br>
	 *         <code>false</code> if notification was not saved
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
	 * Retrieves notification with id from the database.
	 * 
	 * @param id id of the notification
	 * @return notification object
	 */
	public Notification read(int id) {
		Session session = sessionFactory.openSession();
		Notification notification = new Notification();
		try {
			session.beginTransaction();
			session.load(notification, id);
			session.getTransaction().commit();
		} catch (ObjectNotFoundException oe) {
			System.out.println("Notification not found");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return notification;
	}

	/**
	 * Reads customer's all notifications from database.
	 * 
	 * @param customer customer whose notifications are read
	 *
	 * @return array of notifications
	 */
	@SuppressWarnings("unchecked")
	public Notification[] readCustomersNotifications(Customer customer) {
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
	 * Updates notification
	 * 
	 * @param notification notification to be updated
	 * @return <code>true</code> if notification was successfully updated<br>
	 *         <code>false</code> if notification was not updated
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
			System.out.println("Nothing to update");
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}

	/**
	 * Deletes notification from database.
	 * 
	 * @param id id of the notification
	 * @return <code>true</code> if notification was successfully deleted<br>
	 *         <code>false</code> if notification was not deleted
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
