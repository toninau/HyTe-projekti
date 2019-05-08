package dao;

import model.Customer;
import model.UserImage;

import java.util.logging.Logger;

import org.hibernate.*;
import org.hibernate.query.Query;

/**
 * DataAccessObject for customers images
 * 
 * @author tonin
 *
 */
public class UserImageDAO {
	/**
	 * Sessionfactory used for CRUD operations
	 */
	private SessionFactory sessionFactory = null;
	private static final Logger LOGGER = Logger.getLogger(UserImageDAO.class.getName());

	/**
	 * Standard constructor for UserImageDAO
	 * 
	 * @param sessionFactory hibernate SessionFactory
	 */
	public UserImageDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Saves image to the database
	 * 
	 * @param image image to be saved in the database
	 * @return <code>true</code> if image was successfully saved <br>
	 *         <code>false</code> if image was not saved
	 */
	public boolean create(UserImage image) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		boolean success = false;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(image);
			transaction.commit();
			success = true;
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return success;
	}

	/**
	 * Retrieves image from the database
	 * 
	 * @param id imageID of the wanted image
	 * @return UserImage object
	 */
	public UserImage read(String id) {
		Session session = sessionFactory.openSession();
		UserImage image = new UserImage();
		try {
			session.beginTransaction();
			session.load(image, id);
			session.getTransaction().commit();
		} catch (HibernateException oe) {
			LOGGER.warning("Hibernate exception");
		} finally {
			session.close();
		}
		return image;
	}

	/**
	 * Retrieves all customers images from the database
	 * 
	 * @param customer customer whose images are retrieved from the database
	 * @return array of UserImages
	 */
	@SuppressWarnings("unchecked")
	public UserImage[] readCustomerUserImages(Customer customer) {
		Session session = sessionFactory.openSession();
		UserImage[] result = new UserImage[0];
		try {
			session.beginTransaction();
			String sql = "SELECT * FROM userImage INNER JOIN customer on userImage.customerID = customer.customerID WHERE customer.customerID = :id";
			Query<UserImage> query = session.createSQLQuery(sql).addEntity(UserImage.class);
			query.setParameter("id", customer.getCustomerID());
			result = query.list().toArray(new UserImage[query.list().size()]);
			session.getTransaction().commit();
		} catch (Exception e) {
			LOGGER.warning("Exception");
		} finally {
			session.close();
		}
		return result;
	}

	/**
	 * Updates userImage information to the database
	 * 
	 * @param userImage image to be updated in the database
	 * @return <code>true</code> if image was successfully updated <br>
	 *         <code>false</code> if image was not updated
	 */
	public boolean update(UserImage userImage) {
		boolean success = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserImage img = session.get(UserImage.class, userImage.getImageID());
		if (img != null) {
			img.setCustomer(userImage.getCustomer());
			img.setImage(userImage.getImage());
			img.setImageName(userImage.getImageName());
			success = true;
		} 
		session.getTransaction().commit();
		session.close();
		return success;
	}

	/**
	 * Deletes image from the database
	 * 
	 * @param string imageID of the image to be deleted
	 * @return <code>true</code> if image was successfully deleted<br>
	 *         <code>false</code> if image was not deleted
	 */
	public boolean delete(String string) {
		boolean success = false;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserImage img = session.get(UserImage.class, string);
		if (img != null) {
			session.delete(img);
			success = true;
		}
		session.getTransaction().commit();
		session.close();
		return success;
	}
}
