package model;

import org.hibernate.SessionFactory;

import javassist.expr.Instanceof;


/**
 * Class offering data access object classes' CRUD operations for controllers.
 * 
 */
public class DAOManager {
	
	private CustomerDAO customerDAO = null;
	private StaffDAO staffDAO = null;
	private PrescriptionDAO prescriptionDAO = null;
	private IllnessDAO illnessDAO = null;
	private BloodValueDAO bloodValueDAO = null;
	private NotificationDAO notificationDAO = null;
	private AppointmentDAO appointmentDAO = null;
	private SessionFactory s;
	
	/**
	 * DAOManagerin konstruktori, joka hakee istuntotehtaan.
	 */
	public DAOManager() {
		s =HibernateUtil.getSessionFactory(false);
	}


	/**
	 * 
	 * @param obj 
	 */
	public void create(Object obj) {
		if(obj instanceof Staff) {
			getStaffDAO().create((Staff)obj);
			System.out.println(((Staff) obj).getPhoneNumber());
		} else if (obj instanceof Customer) {
			getCustomerDAO().create((Customer)obj);
		} else if (obj instanceof Illness) {
			getIllnessDAO().create((Illness)obj);
		} else if (obj instanceof Prescription) {
			getPrescriptionDAO().create((Prescription)obj);
		} else if (obj instanceof Notification) {
			getNotificationDAO().create((Notification)obj);
		} else if (obj instanceof BloodValue) {
			getBloodValueDAO().create((BloodValue)obj);
		} else { System.out.println("ei ole"); }
		
	}
	
	/**
	 * 
	 * @param obj Name of the wanted object.
	 * @return
	 */
	public Object[] readAll(String obj){
		switch (obj) {
		case "staff":
			return getStaffDAO().readAll();
		case "customer":
			return getCustomerDAO().readAll();
		default:
			return null;
		} 
	}
	
	/**
	 * 
	 * @param id Id of the object to be read.
	 * @param obj Name of the wanted object.
	 * @return 
	 */
	public Object readWithID(int id, String obj) {
		switch (obj) {
		case "staff":
			return getStaffDAO().read(id);
		case "customer":
			return getCustomerDAO().read(id);
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
	
	/**
	 * Creates a new customer data access object if it's null,
	 * otherwise returns existing customer data access object.
	 * @return	CustomerDAO.
	 */
	public CustomerDAO getCustomerDAO() {
		if (this.customerDAO == null) {
			this.customerDAO = new CustomerDAO(s);
		}
		return this.customerDAO;
	}

	/**
	 * Creates a new staff data access object if it's null,
	 * otherwise returns existing staff data access object.
	 * @return	StaffDAO
	 */
	public StaffDAO getStaffDAO() {
		if (staffDAO == null) {
			staffDAO = new StaffDAO(s);
		}
		return staffDAO;
	}

	/**
	 * Creates a new prescription data access object if it's null,
	 * otherwise returns existing prescription data access object.
	 * @return	PrescriptionDAO.
	 */
	public PrescriptionDAO getPrescriptionDAO() {
		if (this.prescriptionDAO == null) {
			this.prescriptionDAO = new PrescriptionDAO(s);
		}
		return this.prescriptionDAO;
	}

	/**
	 * Creates a new illness data access object if it's null,
	 * otherwise returns existing illness data access object.
	 * @return	IllnessDAO.
	 */
	public IllnessDAO getIllnessDAO() {
		if (this.illnessDAO == null) {
			this.illnessDAO = new IllnessDAO(s);
		}
		return this.illnessDAO;
	}

	/**
	 * Creates a new blood value data access object if it's null,
	 * otherwise returns existing blood value data access object.
	 * @return	BloodValueDAO
	 */
	public BloodValueDAO getBloodValueDAO() {
		if (this.bloodValueDAO == null) {
			this.bloodValueDAO = new BloodValueDAO(s);
		}
		return this.bloodValueDAO;
	}

	/**
	 * Creates a new notification data access object if it's null,
	 * otherwise returns existing notification data access object.
	 * @return NotificationDAO
	 */
	public NotificationDAO getNotificationDAO() {
		if (this.notificationDAO == null) {
			this.notificationDAO = new NotificationDAO(s);
		}
		return this.notificationDAO;
	}
	
	/**
	 * Creates a new appointment data access object if it's null,
	 * otherwise returns existing appointment data access object.
	 * @return	AppointmentDAO
	 */
	public AppointmentDAO getAppointmentDAO() {
		if (this.appointmentDAO == null) {
			this.appointmentDAO = new AppointmentDAO(s);
		}
		return this.appointmentDAO;
	}
}
