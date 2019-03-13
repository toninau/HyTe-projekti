package model;

import org.hibernate.SessionFactory;


/**
 * Luokan avulla käytetään data access object -luokkia.
 * 
 */
public class DAOManager {
	
	private AsiakasAccessObject asiakasDAO = null;
	private HenkilökuntaAccessObject henkilökuntaDAO = null;
	private ReseptiAccessObject reseptiDAO = null;
	private SairausAccessObject sairausDAO = null;
	private VeriarvoAccessObject veriarvoDAO = null;
	private SessionFactory s;
	
	/**
	 * DAOManagerin konstruktori, joka hakee istuntotehtaan.
	 */
	public DAOManager() {
		s =Istuntotehdas.getSessionFactory();
	}

	/**
	 * Luo asiakas data access objectin, jos sitä ei ole vielä luotu,
	 * muuten palauttaa jo olemassa olevan asiakasDAOn.
	 * @return	AsiakasAccessObject.
	 */
	public AsiakasAccessObject getAsiakasDAO() {
		if (this.asiakasDAO == null) {
			this.asiakasDAO = new AsiakasAccessObject(s);
		}
		return this.asiakasDAO;
	}

	/**
	 * Luo henkiökunta data access objectin, jos sitä ei ole vielä luotu,
	 * muuten palauttaa jo olemassa olevan henkilökuntaDAOn.
	 * @return	HenkilökuntaAccessObject
	 */
	public HenkilökuntaAccessObject getHenkilökuntaDAO() {
		if (henkilökuntaDAO == null) {
			henkilökuntaDAO = new HenkilökuntaAccessObject(s);
		}
		return henkilökuntaDAO;
	}

	/**
	 * Luo resepti data access objectin, jos sitä ei ole vielä luotu,
	 * muuten palauttaa jo olemassa olevan reseptiDAOn.
	 * @return	ReseptiAccessObject.
	 */
	public ReseptiAccessObject getReseptiDAO() {
		if (this.reseptiDAO == null) {
			this.reseptiDAO = new ReseptiAccessObject(s);
		}
		return this.reseptiDAO;
	}

	/**
	 * Luo sairaus data access objectin, jos sitä ei ole vielä luotu,
	 * muuten palauttaa jo olemassa olevan sairausDAOn.
	 * @return	SairausAccessObject.
	 */
	public SairausAccessObject getSairausDAO() {
		if (this.sairausDAO == null) {
			this.sairausDAO = new SairausAccessObject(s);
		}
		return this.sairausDAO;
	}

	/**
	 * Luo veriarvo data access objectin, jos sitä ei ole vielä luotu,
	 * muuten palauttaa jo olemassa olevan veriarvoDAON.
	 * @return	VeriarvoAccessObject
	 */
	public VeriarvoAccessObject getVeriarvoDAO() {
		if (this.veriarvoDAO == null) {
			this.veriarvoDAO = new VeriarvoAccessObject(s);
		}
		return this.veriarvoDAO;
	}
}
