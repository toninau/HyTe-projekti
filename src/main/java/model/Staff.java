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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "staffID")
	private int staffID;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "surname")
	private String surname;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	@Column(name = "email")
	private String email;

	@Column(name = "accessLevel")
	private String accessLevel;

	@OneToMany(mappedBy = "staff", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Appointment> appointments = new HashSet<Appointment>();

	@OneToMany(mappedBy = "staff", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Prescription> prescriptions = new HashSet<Prescription>();

	@OneToMany(mappedBy = "staff", orphanRemoval = true, cascade = CascadeType.PERSIST)
	private Set<Notification> notification = new HashSet<Notification>();

	@ManyToMany(mappedBy = "customersStaff")
	private Set<Customer> customersStaff = new HashSet<Customer>();

	/**
	 * Tyhjä henkilökunta konstruktori. Arvot annetaan set-metodeja käyttäen.
	 * 
	 * @see #Staff(String, String, String, String, String)
	 */
	public Staff() {
	}

	/**
	 * Vakio henkilökunta konstruktori.
	 * 
	 * @param firstName   Henkilökunnan jäsenen firstName
	 * @param surname  Henkilökunnan jäsenen surname
	 * @param phoneNumber Henkilökunnan jäsenen puhelinnumero
	 * @param email    Henkilökunnan jäsenen sähköpostiosoite
	 * @param accessLevel    Henkilökunnan jäsenen accessLevel. Määrittää jäsenen oikeudet
	 *                  esimerkiksi resepteiden määrämäämiseen asiakkaille.
	 * @see #Staff()
	 */

	public Staff(String firstName, String surname, String phoneNumber, String email, String accessLevel) {
		this.firstName = firstName;
		this.surname = surname;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.accessLevel = accessLevel;
	}

	public int getStaffID() {
		return staffID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}
	
	


}