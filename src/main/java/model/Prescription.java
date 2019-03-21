package model;

import javax.persistence.*;

/**
 * Prescription entity
 * 
 * @author tonin
 *
 */
@Entity
@Table(name = "prescription")
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prescriptionID")
	private int reseptiID;

	@Column(name = "alkupvm")
	private String alkupvm;

	@Column(name = "loppupvm")
	private String loppupvm;

	@Column(name = "reseptinimi")
	private String reseptiNimi;

	@Column(name = "reseptiohje")
	private String reseptiOhje;

	@ManyToOne
	@JoinColumn(name = "customerID", nullable = false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "staffID", nullable = false)
	private Staff staff;

	/**
	 * Vakio resepti konstruktori.
	 * 
	 * @param alkupvm      Päivämäärä, jolloin resepti on määrätty
	 * @param loppupvm     Päivämäärä, johon saakka resepti on voimassa
	 * @param reseptiNimi  Reseptin nimi, esimerkiksi lääke <i>Testilääke 600mg</i>
	 * @param reseptiOhje  Reseptin käyttöohjeet
	 * @param customer      Asiakas, jolle resepti on määrätty
	 * @param staff Henkilökunnan jäsen, joka on määrännyt reseptin
	 * @see #Prescription()
	 */
	public Prescription(String alkupvm, String loppupvm, String reseptiNimi, String reseptiOhje, Customer customer,
			Staff staff) {
		this.alkupvm = alkupvm;
		this.loppupvm = loppupvm;
		this.reseptiNimi = reseptiNimi;
		this.reseptiOhje = reseptiOhje;
		this.customer = customer;
		this.staff = staff;
	}

	/**
	 * Tyhjä resepti konstruktori. Arvot annetaan set-metodeja käyttäen.
	 * 
	 * @see #Prescription()
	 */
	public Prescription() {
	}

	/**
	 * Palauttaa reseptin reseptiID:n. ReseptiID-arvo määritellään automaattisesti
	 * tietokannassa.
	 * 
	 * @return reseptiID
	 */
	public int getReseptiID() {
		return reseptiID;
	}

	/**
	 * Asettaa reseptin reseptiID:n. ReseptiID-arvo määritellään automaattisesti
	 * tietokannassa. Ei käytetä uuden reseptin luomisessa.
	 * 
	 * @param reseptiID reseptille annettava reseptiID
	 */
	public void setReseptiID(int reseptiID) {
		this.reseptiID = reseptiID;
	}

	/**
	 * Palauttaa reseptin alkamispäivämäärän.
	 * 
	 * @return alkamispäivämäärä
	 */
	public String getAlkupvm() {
		return alkupvm;
	}

	/**
	 * Asettaa reseptille alkamispäivämäärän.
	 * 
	 * @param alkupvm reseptille asetettava alkamispäivämäärä
	 */
	public void setAlkupvm(String alkupvm) {
		this.alkupvm = alkupvm;
	}

	/**
	 * Palauttaa reseptin loppupäivämäärän.
	 * 
	 * @return loppupäivämäärä
	 */
	public String getLoppupvm() {
		return loppupvm;
	}

	/**
	 * Asettaa reseptille loppupäivämäärän.
	 * 
	 * @param loppupvm reseptille asetettava loppupäivämäärä
	 */
	public void setLoppupvm(String loppupvm) {
		this.loppupvm = loppupvm;
	}

	/**
	 * Palauttaa reseptin nimen.
	 * 
	 * @return reseptinimi
	 */
	public String getReseptiNimi() {
		return reseptiNimi;
	}

	/**
	 * Asettaa reseptille nimen.
	 * 
	 * @param reseptiNimi reseptille asetettava nimi
	 */
	public void setReseptiNimi(String reseptiNimi) {
		this.reseptiNimi = reseptiNimi;
	}

	/**
	 * Palauttaa reseptin käyttöohjeen.
	 * 
	 * @return käyttöohje
	 */
	public String getReseptiOhje() {
		return reseptiOhje;
	}

	/**
	 * Asettaa reseptille käyttöohjeen.
	 * 
	 * @param reseptiOhje reseptille asetettava käyttöohje
	 */
	public void setReseptiOhje(String reseptiOhje) {
		this.reseptiOhje = reseptiOhje;
	}

	/**
	 * Palauttaa reseptin asiakkaan.
	 * 
	 * @return asiakas
	 */
	public Customer getAsiakas() {
		return customer;
	}

	/**
	 * Asettaa reseptin asiakkaan.
	 * 
	 * @param customer reseptille asetettava asiakas
	 */
	public void setAsiakas(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Palauttaa reseptin henkilökunnan jäsenen.
	 * 
	 * @return henkilökunnan jäsen
	 */
	public Staff getHenkilökunta() {
		return staff;
	}

	/**
	 * Asettaa reseptin henkilökunnan jäsenen.
	 * 
	 * @param staff reseptille asetettava henkilökunnan jäsen
	 */
	public void setHenkilökunta(Staff staff) {
		this.staff = staff;
	}
}
