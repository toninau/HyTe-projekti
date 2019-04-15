package model;

import javax.persistence.*;
import java.util.*;

/**
 * Staff entity
 * 
 * @author tonin
 *
 */
@Entity
@Table(name = "staff")
public class Staff {

	@Id
	@Column(name = "staffID")
	private String staffID;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "surname")
	private String surname;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "accessLevel")
	private String accessLevel;

	@Column(name = "staffPassword")
	private String password;

	@OneToMany(mappedBy = "staff", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Appointment> appointments = new HashSet<Appointment>();

	@OneToMany(mappedBy = "staff", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Prescription> prescriptions = new HashSet<Prescription>();

	@OneToMany(mappedBy = "staff", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Notification> notifications = new HashSet<Notification>();

	@ManyToMany(mappedBy = "customersStaff")
	private Set<Customer> customersStaff = new HashSet<Customer>();

	/**
	 * Empty staff constructor. Values are given using set-methods.
	 * 
	 * @see #Staff(String, String, String, String, String, String)
	 */
	public Staff() {
	}

	/**
	 * 
	 * @param staffID     Staff member's staffID
	 * @param firstName   Staff member's first name
	 * @param surname     Staff member's surname
	 * @param phoneNumber Staff member's phone number
	 * @param accessLevel Staff member's access level. For example defines staff
	 *                    member's authority to prescribe a prescription
	 * @param password    Staff member's password
	 * @see #Staff()
	 */
	public Staff(String staffID, String firstName, String surname, String phoneNumber, String accessLevel,
			String password) {
		this.staffID = staffID;
		this.firstName = firstName;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.accessLevel = accessLevel;
		this.password = password;
	}

	/**
	 * Returns staff member's staffID.
	 * 
	 * @return staffID
	 */
	public String getStaffID() {
		return staffID;
	}

	/**
	 * Sets staffID.
	 * 
	 * @param staffID staffID to set
	 */
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	/**
	 * Returns staff member's first name.
	 * 
	 * @return first name
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
	 * Returns staff member's surname.
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
	 * Returns staff member's phone number.
	 * 
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets phone number.
	 * 
	 * @param phoneNumber phone number to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Returns staff member's access level.
	 * 
	 * @return accessLevel
	 */
	public String getAccessLevel() {
		return accessLevel;
	}

	/**
	 * Sets access level.
	 * 
	 * @param accessLevel access level to set
	 */
	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	/**
	 * Sets staff member's password.
	 * 
	 * @param password password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Return staff member's password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
}