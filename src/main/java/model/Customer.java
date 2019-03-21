package model;

import javax.persistence.*;
import java.util.*;

/**
 * Asiakas entity
 * 
 * @author tonin
 *
 */
@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customerID")
	private int customerID;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "surname")
	private String surname;

	@Column(name = "ssn")
	private String ssn;

	@Column(name = "address")
	private String address;

	@Column(name = "email")
	private String email;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "iceNumber")
	private String iceNumber;

	@OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Illness> illnesses = new HashSet<Illness>();

	@OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Appointment> reservations = new HashSet<Appointment>();

	@OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Prescription> prescriptions = new HashSet<Prescription>();

	@OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<BloodValue> bloodValues = new HashSet<BloodValue>();

	@OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Notification> notification = new HashSet<Notification>();

	@ManyToMany
	@JoinTable(name = "customersStaff", joinColumns = { @JoinColumn(name = "customerID") }, inverseJoinColumns = {
			@JoinColumn(name = "staffID") })
	private Set<Staff> customersStaff = new HashSet<Staff>();

	/**
	 * Tyhjä asiakas konstruktori. Arvot annetaan set-metodeja käyttäen.
	 * 
	 * @see #Asiakas(String, String, String, String, String, String, String)
	 */
	public Customer() {

	}

	/**
	 * Vakio asiakas konstruktori.
	 * 
	 * @param firstName    Asiakkaan etunimi
	 * @param surname   Asiakkaan sukunimi
	 * @param ssn       Asiakkaan henkilötunnus
	 * @param address Asiakkaan kotiosoite
	 * @param email     Asiakkaan sähköpostiosoite
	 * @param phoneNumber  Asiakkaan puhelinnumero
	 * @param icenumero  Asiakkaan "in case of emergency" puhelinnumero
	 * @see #Asiakas()
	 */
	public Customer(String firstName, String surname, String ssn, String address, String email, String phoneNumber,
			String iceNumber) {
		this.firstName = firstName;
		this.surname = surname;
		this.ssn = ssn;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.iceNumber = iceNumber;
	}

	/**
	 * Palauttaa asiakkaan asiakasID:n. AsiakasID-arvo määritellään automaattisesti
	 * tietokannassa.
	 * 
	 * @return asiakasID
	 */
	public int getCustomerID() {
		return customerID;
	}

	/**
	 * Asettaa asiakkaan asiakasID:n. AsiakasID-arvo määritellään automaattisesti
	 * tietokannassa. Ei käytetä uuden asiakkaan luomisessa.
	 * 
	 * @param asiakasID asiakkaalle annettava asiakasID
	 */
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	/**
	 * Palauttaa asiakkaan henkilötunnuksen.
	 * 
	 * @return henkilötunnus
	 */
	public String getSSN() {
		return ssn;
	}

	/**
	 * Asettaa asiakkaalle henkilötunnuksen.
	 * 
	 * @param hetu asiakkaalle asetettava henkilötunnus
	 */
	public void setSSN(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * Palauttaa asiakkaan etunimen.
	 * 
	 * @return etunimi
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Asettaa asiakkaalle etunimen.
	 * 
	 * @param etunimi asiakkaalle asetettava etunimi
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Palauttaa asiakkaan sukunimen.
	 * 
	 * @return sukunimi
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Asettaa asiakkaalle sukunimen.
	 * 
	 * @param sukunimi asiakkaalle asetettava sukunimi
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Palauttaa asiakkaan kotiosoitteen.
	 * 
	 * @return kotiosoite
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Asettaa asiakkaalle kotiosoitteen.
	 * 
	 * @param kotiosoite asiakkaalle asetettava kotiosoite
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Palauttaa asiakkaan sähköpostiosoitteen.
	 * 
	 * @return sähköpostiosoite
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Asettaa asiakkaalle sähköpostiosoitteen.
	 * 
	 * @param sposti asiakkaalle asetettava sähköpostiosoite
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Palauttaa asiakkaan puhelinnumeron.
	 * 
	 * @return puhelinnumero
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Asettaa asiakkaalle sähköpostiosoitteen.
	 * 
	 * @param puhnumero asiakkaalle asetettava puhelinnumero
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Palauttaa asiakkaan ICEnumeron ("in case of emergency" puhelinnumero).
	 * 
	 * @return ICEnumero
	 */
	public String getIceNumber() {
		return iceNumber;
	}

	/**
	 * Asettaa asiakkaalle ICEnumeron ("in case of emergency" puhelinnumero).
	 * 
	 * @param icenumero asiakkaalle asetettava ICEnumero
	 */
	public void setIceNumber(String iceNumber) {
		this.iceNumber = iceNumber;
	}
}
