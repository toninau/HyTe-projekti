package dao;

import model.Customer;
import model.Notification;

import java.util.logging.Logger;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


/**
 * 
 * DataAccessObject for notification management
 *
 */
public class NotificationDAO {

	/**
	 * Sessionfactory for CRUD operations
	 */
	private SessionFactory sessionFactory;
	private static final Logger LOGGER = Logger.getLogger(NotificationDAO.class.getName());


	/**
	 * Class constructor
	 * 
	 * @param sessionFactory Hibernate SessionFactory
	 */
	public NotificationDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
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
			LOGGER.warning("Notification not found");
		} catch (Exception e) {
			LOGGER.warning("Exception in read");
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
		Notification[] result = new Notification[0];
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			String sql = "SELECT * FROM notification INNER JOIN customer on notification.customerID = :id";
			Query<Notification> query = session.createSQLQuery(sql).addEntity(Notification.class);
			query.setParameter("id", customer.getCustomerID());
			result = query.list().toArray(new Notification[query.list().size()]);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.warning("Exception");
		} finally {
			session.close();
		}
		return result;
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
		Notification i = session.get(Notification.class, notification.getNotificationID());
		if (i != null) {
			i.setRead(notification.isRead());
			i.setText(notification.getText());
			success = true;
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
		Notification i = session.get(Notification.class, id);
		if (i != null) {
			session.delete(i);
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
}
