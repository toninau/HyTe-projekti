package model;

import org.hibernate.SessionFactory;

import dao.AppointmentDAO;
import dao.BloodValueDAO;
import dao.CustomerDAO;
import dao.IllnessDAO;
import dao.NotificationDAO;
import dao.PrescriptionDAO;
import dao.StaffDAO;
import dao.UserImageDAO;

/**
 *
 * This manager class manages all DAO-classes in the project.
 *
 */
public class DAOManager implements DAOManagerIF {

	private CustomerDAO customerDAO = null;
	private StaffDAO staffDAO = null;
	private PrescriptionDAO prescriptionDAO = null;
	private IllnessDAO illnessDAO = null;
	private BloodValueDAO bloodValueDAO = null;
	private NotificationDAO notificationDAO = null;
	private AppointmentDAO appointmentDAO = null;
	private UserImageDAO userImageDAO = null;

	private SessionFactory s;
	private ImageLoader i;
	private InfoLoader info;

	/**
	 * Class constructor to get the SessionFactory, ImageLoader and InfoLoader singletons.
	 */
	public DAOManager() {
		s = HibernateUtil.getSessionFactory(false);
		i = ImageLoader.getImageLoader();
		info = InfoLoader.getInfoLoader();
	}

	/**
	 * Writes all the customer's information to temporary files.
	 * @param customer Logged in customer.
	 * @see InfoLoader#writeAppointmentsToFile(Customer)
	 * @see InfoLoader#writeBloodvaluesToFile(Customer)
	 * @see InfoLoader#writePrescriptionsToFile(Customer)
	 * @see ImageLoader#writeImagesToFile(Customer)
	 */
	public void writeAllCustomerInformation(Customer customer) {
		info.writeAppointmentsToFile(customer);
		info.writePrescriptionsToFile(customer);
		info.writeBloodvaluesToFile(customer);
		i.writeImagesToFile(customer);	
	}
	
	/**
	 * Method for writing customer's image to a file during session.
	 * @param customer The logged in customer.
	 * @see ImageLoader#writeImagesToFile(Customer)
	 */
	public void writeImageToFileDuringSession(Customer customer) {
		i.writeImagesToFile(customer);
	}
	
	/**
	 * Method for writing customer's blood values to a file during session.
	 * @param customer The logged in customer.
	 * @see InfoLoader#writeBloodvaluesToFile(Customer)
	 */
	public void writeBloodValueToFileDuringSession(Customer customer) {
		info.writeBloodvaluesToFile(customer);
	}

	/**
	 * Reads customer's images from file.
	 * @see ImageLoader#readCustomerImages()
	 */
	public UserImage[] readCustomerImages() {
		return i.readCustomerImages();
	}

	/**
	 * Reads customer's appointments from file.
	 * @see InfoLoader#readAppointmentsFromFile()
	 */
	public Appointment[] readCustomerAppointments() {
		return info.readAppointmentsFromFile();
	}
	
	/**
	 * Reads customer's prescriptions from file.
	 * @see InfoLoader#readPrescriptionsFromFile()
	 */
	public Prescription[] readCustomerPrescriptions() {
		return info.readPrescriptionsFromFile();
	}
	
	/**
	 * Reads customer's blood values from file.
	 * @see InfoLoader#readBloodValuesFromFile()
	 */
	public BloodValue[] readCustomerBloodValues() {
		return info.readBloodValuesFromFile();
	}

	/**
	 * Create method for all objects.
	 * @param obj Object to be created.
	 */
	public void create(Object obj) {
		if (obj instanceof Staff) {
			getStaffDAO().create((Staff) obj);
		} else if (obj instanceof Customer) {
			getCustomerDAO().create((Customer) obj);
		} else if (obj instanceof Illness) {
			getIllnessDAO().create((Illness) obj);
		} else if (obj instanceof Prescription) {
			getPrescriptionDAO().create((Prescription) obj);
		} else if (obj instanceof Notification) {
			getNotificationDAO().create((Notification) obj);
		} else if (obj instanceof BloodValue) {
			getBloodValueDAO().create((BloodValue) obj);
		} 
	}

	/**
	 * Update method for all objects.
	 * @param Object to be updated.
	 * @return true if update succeeded.
	 */
	public boolean update(Object obj) {
		if (obj instanceof Staff)
			return getStaffDAO().update((Staff) obj);
		else if (obj instanceof Customer)
			return getCustomerDAO().update((Customer) obj);
		else if (obj instanceof Notification)
			return getNotificationDAO().update((Notification) obj);
		else
			return false;
	}

	/**
	 * 
	 * @param obj Name of the wanted object.
	 * @return
	 */
	public Object[] readAll(String obj) {
		switch (obj) {
		case "staff":
			return getStaffDAO().readAll();
		case "customer":
			return getCustomerDAO().readAll();
		default:
			return new Object[0];
		}
	}

	/**
	 * 
	 * @param id  Id of the object to be read.
	 * @param obj Name of the wanted object.
	 * @return
	 */
	public Object readWithID(int id, String obj) {
		switch (obj) {
		case "appointment":
			return getAppointmentDAO().read(id);
		case "notification":
			return getNotificationDAO().read(id);
		case "prescription":
			return getPrescriptionDAO().read(id);
		default:
			return null;
		}
	}

	public Object readWithEmail(String key, String email) {
		switch (key) {
		case "staff":
			return getStaffDAO().read(email);
		case "customer":
			return getCustomerDAO().read(email);
		default:
			return null;
		}
	}

	/**
	 * Creates a Customer Data Access Object. If it exists, return the existing one.
	 * 
	 * @return CustomerDAO.
	 */
	public CustomerDAO getCustomerDAO() {
		if (this.customerDAO == null) {
			this.customerDAO = new CustomerDAO(s);
		}
		return this.customerDAO;
	}

	/**
	 * Creates a Staff Data Access Object. If it exists, return the existing one.
	 * 
	 * @return StaffDAO
	 */
	public StaffDAO getStaffDAO() {
		if (staffDAO == null) {
			staffDAO = new StaffDAO(s);
		}
		return staffDAO;
	}

	/**
	 * Creates a Prescription Data Access Object. If it exists, return the existing
	 * one.
	 * 
	 * @return PrescriptionDAO.
	 */
	public PrescriptionDAO getPrescriptionDAO() {
		if (this.prescriptionDAO == null) {
			this.prescriptionDAO = new PrescriptionDAO(s);
		}
		return this.prescriptionDAO;
	}

	/**
	 * Creates an Illness Data Access Object. If it exists, return the existing one.
	 * 
	 * @return IllnessDAO.
	 */
	public IllnessDAO getIllnessDAO() {
		if (this.illnessDAO == null) {
			this.illnessDAO = new IllnessDAO(s);
		}
		return this.illnessDAO;
	}

	/**
	 * Creates a BloodValue Data Access Object. If it exists, return the existing
	 * one.
	 * 
	 * @return BloodValueDAO
	 */
	public BloodValueDAO getBloodValueDAO() {
		if (this.bloodValueDAO == null) {
			this.bloodValueDAO = new BloodValueDAO(s);
		}
		return this.bloodValueDAO;
	}

	/**
	 * Creates a Notification Data Access Object. If it exists, return the existing
	 * one.
	 * 
	 * @return NotificationDAO
	 */
	public NotificationDAO getNotificationDAO() {
		if (this.notificationDAO == null) {
			this.notificationDAO = new NotificationDAO(s);
		}
		return this.notificationDAO;
	}

	/**
	 * Creates a new appointment data access object if it's null, otherwise returns
	 * existing appointment data access object.
	 * 
	 * @return AppointmentDAO
	 */
	public AppointmentDAO getAppointmentDAO() {
		if (this.appointmentDAO == null) {
			this.appointmentDAO = new AppointmentDAO(s);
		}
		return this.appointmentDAO;
	}

	/**
	 * Creates a new appointment data access object if it's null, otherwise returns
	 * existing appointment data access object.
	 * 
	 * @return AppointmentDAO
	 */
	public UserImageDAO getUserImageDAO() {
		if (this.userImageDAO == null) {
			this.userImageDAO = new UserImageDAO(s);
		}
		return this.userImageDAO;
	}
}
