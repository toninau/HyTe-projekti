package model;

import org.hibernate.SessionFactory;

import javassist.expr.Instanceof;


/**
 * Luokan avulla käytetään data access object -luokkia.
 * 
 */
public class DAOManager {
	
	private CustomerDAO customerDAO = null;
	private StaffDAO staffDAO = null;
	private PrescriptionDAO prescriptionDAO = null;
	private IllnessDAO illnessDAO = null;
	private BloodValueDAO bloodValueDAO = null;
	private NotificationDAO notificationDAO = null;
	private SessionFactory s;
	
	/**
	 * DAOManagerin konstruktori, joka hakee istuntotehtaan.
	 */
	public DAOManager() {
		s =HibernateUtil.getSessionFactory();
	}

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
	
	public Object[] readAll(int xd){
		switch (xd) {
		case 1:
			return getStaffDAO().readAll();
		case 2:
			return getCustomerDAO().readAll();	
		default:
			return null;
		} 
	}
	/**
	 * Luo asiakas data access objectin, jos sitä ei ole vielä luotu,
	 * muuten palauttaa jo olemassa olevan asiakasDAOn.
	 * @return	CustomerDAO.
	 */
	public CustomerDAO getCustomerDAO() {
		if (this.customerDAO == null) {
			this.customerDAO = new CustomerDAO(s);
		}
		return this.customerDAO;
	}

	/**
	 * Luo henkiökunta data access objectin, jos sitä ei ole vielä luotu,
	 * muuten palauttaa jo olemassa olevan henkilökuntaDAOn.
	 * @return	StaffDAO
	 */
	public StaffDAO getStaffDAO() {
		if (staffDAO == null) {
			staffDAO = new StaffDAO(s);
		}
		return staffDAO;
	}

	/**
	 * Luo resepti data access objectin, jos sitä ei ole vielä luotu,
	 * muuten palauttaa jo olemassa olevan reseptiDAOn.
	 * @return	PrescriptionDAO.
	 */
	public PrescriptionDAO getPrescriptionDAO() {
		if (this.prescriptionDAO == null) {
			this.prescriptionDAO = new PrescriptionDAO(s);
		}
		return this.prescriptionDAO;
	}

	/**
	 * Luo sairaus data access objectin, jos sitä ei ole vielä luotu,
	 * muuten palauttaa jo olemassa olevan sairausDAOn.
	 * @return	IllnessDAO.
	 */
	public IllnessDAO getIllnessDAO() {
		if (this.illnessDAO == null) {
			this.illnessDAO = new IllnessDAO(s);
		}
		return this.illnessDAO;
	}

	/**
	 * Luo veriarvo data access objectin, jos sitä ei ole vielä luotu,
	 * muuten palauttaa jo olemassa olevan veriarvoDAON.
	 * @return	BloodValueDAO
	 */
	public BloodValueDAO getBloodValueDAO() {
		if (this.bloodValueDAO == null) {
			this.bloodValueDAO = new BloodValueDAO(s);
		}
		return this.bloodValueDAO;
	}
	
	public NotificationDAO getNotificationDAO() {
		if (this.notificationDAO == null) {
			this.notificationDAO = new NotificationDAO(s);
		}
		return this.notificationDAO;
	}
}
