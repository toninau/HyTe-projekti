package model;

import org.hibernate.SessionFactory;


/**
 * Luokan avulla käytetään data access object -luokkia.
 * 
 */
public class DAOManager {
	
	private CustomerDAO asiakasDAO = null;
	private StaffDAO henkilökuntaDAO = null;
	private PrescriptionDAO reseptiDAO = null;
	private IllnessDAO sairausDAO = null;
	private BloodValueDAO veriarvoDAO = null;
	private SessionFactory s;
	
	/**
	 * DAOManagerin konstruktori, joka hakee istuntotehtaan.
	 */
	public DAOManager() {
		s =HibernateUtil.getSessionFactory();
	}

	/**
	 * Luo asiakas data access objectin, jos sitä ei ole vielä luotu,
	 * muuten palauttaa jo olemassa olevan asiakasDAOn.
	 * @return	CustomerDAO.
	 */
	public CustomerDAO getAsiakasDAO() {
		if (this.asiakasDAO == null) {
			this.asiakasDAO = new CustomerDAO(s);
		}
		return this.asiakasDAO;
	}

	/**
	 * Luo henkiökunta data access objectin, jos sitä ei ole vielä luotu,
	 * muuten palauttaa jo olemassa olevan henkilökuntaDAOn.
	 * @return	StaffDAO
	 */
	public StaffDAO getHenkilökuntaDAO() {
		if (henkilökuntaDAO == null) {
			henkilökuntaDAO = new StaffDAO(s);
		}
		return henkilökuntaDAO;
	}

	/**
	 * Luo resepti data access objectin, jos sitä ei ole vielä luotu,
	 * muuten palauttaa jo olemassa olevan reseptiDAOn.
	 * @return	PrescriptionDAO.
	 */
	public PrescriptionDAO getReseptiDAO() {
		if (this.reseptiDAO == null) {
			this.reseptiDAO = new PrescriptionDAO(s);
		}
		return this.reseptiDAO;
	}

	/**
	 * Luo sairaus data access objectin, jos sitä ei ole vielä luotu,
	 * muuten palauttaa jo olemassa olevan sairausDAOn.
	 * @return	IllnessDAO.
	 */
	public IllnessDAO getSairausDAO() {
		if (this.sairausDAO == null) {
			this.sairausDAO = new IllnessDAO(s);
		}
		return this.sairausDAO;
	}

	/**
	 * Luo veriarvo data access objectin, jos sitä ei ole vielä luotu,
	 * muuten palauttaa jo olemassa olevan veriarvoDAON.
	 * @return	BloodValueDAO
	 */
	public BloodValueDAO getVeriarvoDAO() {
		if (this.veriarvoDAO == null) {
			this.veriarvoDAO = new BloodValueDAO(s);
		}
		return this.veriarvoDAO;
	}
}
