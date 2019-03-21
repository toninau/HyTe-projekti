package model;

import javax.persistence.*;

/**
 * Varaus entity
 * 
 * @author tonin
 *
 */
@Entity
@Table(name = "appointment")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointmentID")
	private int appointmentID;

	@Column(name = "date")
	private String date;

	@Column(name = "time")
	private String time;

	@Column(name = "info")
	private String info;

	@ManyToOne
	@JoinColumn(name = "customerID", nullable = false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "staffID", nullable = false)
	private Staff staff;

	/**
	 * Vakio varaus konstruktori.
	 * 
	 * @param päivämäärä   Date of reservation
	 * @param kellonaika   Time of reservation
	 * @param info         Description
	 * @param asiakas      Who the reservation is for
	 * @param henkilökunta Staff member of the reservation
	 * @see #Reservation()
	 */
	public Appointment(String date, String time, String info, Customer customer, Staff staff) {
		this.date = date;
		this.time = time;
		this.info = info;
		this.customer = customer;
		this.staff = staff;
	}

	/**
	 * Empty reservation constructor.
	 * 
	 * @see #Reservation(String, String, String, Asiakas, Henkilökunta)
	 */
	public Appointment() {
	}

	/**
	 * Palauttaa varauksen varausID:n. VarausID-arvo määritellään automaattisesti
	 * tietokannassa.
	 * 
	 * @return varausIDF
	 */
	public int getAppointmentID() {
		return appointmentID;
	}

	/**
	 * Asettaa varauksen varausID:n. VarausID-arvo määritellään automaattisesti
	 * tietokannassa. Ei käytetä uuden varauksen luomisessa.
	 * 
	 * @param varausID varaukselle annettava varausID
	 */
	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}

	/**
	 * Palauttaa varauksen päivämäärän.
	 * 
	 * @return päivämäärä
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Asettaa varaukselle päivämäärän.
	 * 
	 * @param päivämäärä varaukselle asetettava päivämäärä
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Returns the time of the reservation.
	 * 
	 * @return time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the time of the reservation.
	 * 
	 * @param kellonaika varaukselle asetettava kellonaika
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Returns the reservation description.
	 * 
	 * @return info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * Sets the reservation description.
	 * 
	 * @param text
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * Returns the customer of the reservation.
	 * 
	 * @return asiakas
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets a customer for the reservation.
	 * 
	 * @param Customer object
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Returns the staff member of the reservation.
	 * 
	 * @return Object of the Staff class
	 */
	public Staff getStaff() {
		return staff;
	}

	/**
	 * Sets the staff member of the reservation
	 * 
	 * @param Object of the Staff class
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

}
