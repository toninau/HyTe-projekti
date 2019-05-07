package model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.*;

/**
 * Customer entity
 * 
 * @author Group 3
 *
 */
@SuppressWarnings("squid:S3437")
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "customerID", length = 11)
	private String customerID;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "surname")
	private String surname;

	@Column(name = "ssn")
	private String ssn;

	@Column(name = "address")
	private String address;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "iceNumber")
	private String iceNumber;

	@Column(name = "customerPassword")
	private String password;

	@OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Illness> illnesses = new HashSet<>();

	@OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Appointment> reservations = new HashSet<>();

	@OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Prescription> prescriptions = new HashSet<>();

	@OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<BloodValue> bloodValues = new HashSet<>();

	@OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Notification> notifications = new HashSet<>();

	@OneToMany(mappedBy = "customer", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<UserImage> images = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "customersStaff", joinColumns = { @JoinColumn(name = "customerID") }, inverseJoinColumns = {
			@JoinColumn(name = "staffID") })
	private Set<Staff> customersStaff = new HashSet<>();

	/**
	 * Empty customer constructor. Values are given using set-methods.
	 * 
	 * @see #Customer(String, String, String, String, String, String, String)
	 */
	public Customer() {

	}

	/**
	 * 
	 * @param firstName   Customer's first name
	 * @param surname     Customer's surname
	 * @param ssn         Customer's social security number
	 * @param address     Customer's home address
	 * @param phoneNumber Customer's phone number
	 * @param iceNumber   Customer's "in case of emergency" phone number
	 * @param password    Customer's password
	 * 
	 * @see #Customer()
	 */
	public Customer(String firstName, String surname, String ssn, String address, String phoneNumber, String iceNumber,
			String password) {
		this.firstName = firstName;
		this.surname = surname;
		this.ssn = ssn;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.iceNumber = iceNumber;
		this.password = password;
	}

	/**
	 * Returns customer's customerID.
	 * 
	 * @return customerID
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * Sets customerID.
	 * 
	 * @param customerID customerID to set
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	/**
	 * Returns customer's social security number.
	 * 
	 * @return ssn
	 */
	public String getSSN() {
		return ssn;
	}

	/**
	 * Sets social security number.
	 * 
	 * @param ssn social security number to set
	 */
	public void setSSN(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * Returns customer's first name.
	 * 
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets first name.
	 * 
	 * @param firstName first name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Returns customer's surname.
	 * 
	 * @return surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * Sets surname.
	 * 
	 * @param surname surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Returns customer's home address.
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets home address.
	 * 
	 * @param address address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Returns customer's phone number
	 * 
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets phone number.
	 * 
	 * @param phoneNumber phone number to set.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Returns customer's ICEnumber ("in case of emergency" phone number).
	 * 
	 * @return iceNumber
	 */
	public String getIceNumber() {
		return iceNumber;
	}

	/**
	 * Sets ICEnumber ("in case of emergency" phone number).
	 * 
	 * @param iceNumber ice phone number to set
	 */
	public void setIceNumber(String iceNumber) {
		this.iceNumber = iceNumber;
	}

	/**
	 * Sets customers password.
	 * 
	 * @param password password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Returns customer password.
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
}
