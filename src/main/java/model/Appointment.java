package model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Appointment entity
 * 
 * @author Group 3
 *
 */
@SuppressWarnings("squid:S3437")
@Entity
@Table(name = "appointment")
public class Appointment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointmentID")
	private int appointmentID;


	@Column(name = "date")
	private LocalDate date;

	@Column(name = "time")
	private LocalTime time;

	@Column(name = "info")
	private String info;

	@ManyToOne
	@JoinColumn(name = "customerID", nullable = false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "staffID", nullable = false)
	private Staff staff;

	/**
	 * Standard appointment constructor.
	 * 
	 * @param date     Date of reservation
	 * @param time     Time of reservation
	 * @param info     Description
	 * @param customer Who the reservation is for
	 * @param staff    Staff member of the reservation
	 * @see #Appointment()
	 */
	public Appointment(String date, String time, String info, Customer customer, Staff staff) {

		this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
		this.info = info;
		this.customer = customer;
		this.staff = staff;
	}

	/**
	 * Empty reservation constructor.
	 * 
	 * @see #Appointment(String, String, String, Customer, Staff)
	 */
	public Appointment() {
	}

	/**
	 * Returns appointmentID. AppointmentID value is given automatically in the
	 * database.
	 * 
	 * @return appointmentID
	 */
	public int getAppointmentID() {
		return appointmentID;
	}

	/**
	 * Sets appointmentID. AppointmentID value is given automatically in the
	 * database. Not used when creating a new appointment.
	 * 
	 * @param appointmentID appointmentID to set
	 */
	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}

	/**
	 * Returns appointment's date.
	 * 
	 * @return date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Sets appointment date.
	 * 
	 * @param date date to set
	 */
	public void setDate(String date) {
		this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	}

	/**
	 * Returns appointment time.
	 * 
	 * @return time
	 */
	public LocalTime getTime() {
		return time;
	}

	/**
	 * Sets appointment time.
	 * 
	 * @param time appointment time to set
	 */
	public void setTime(String time) {
		this.time = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH.mm"));
	}

	/**
	 * Returns appointment info.
	 * 
	 * @return info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * Sets appointment info.
	 * 
	 * @param info appointment info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * Returns appointment customer.
	 * 
	 * @return customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets appointment customer.
	 * 
	 * @param customer appointment customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Returns appointment staff member.
	 * 
	 * @return staff
	 */
	public Staff getStaff() {
		return staff;
	}

	/**
	 * Sets appointment staff member.
	 * 
	 * @param staff appointment staff member to set
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String toStringCustomer() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String date = getDate().format(formatter);
		return getTime().truncatedTo(ChronoUnit.MINUTES) + "  " + getInfo() + ". " + getStaff().getAccessLevel() + " " + getStaff().getSurname() + ", " + getStaff().getFirstName();
	}

	public String toStringStaff() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		String date = getDate().format(formatter);
		return getTime().truncatedTo(ChronoUnit.MINUTES) + "  " + date + " " + getCustomer().getSurname() + " " + getCustomer().getFirstName() + " " + getInfo();
	}
}
