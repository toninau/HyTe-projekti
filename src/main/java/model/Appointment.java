package model;

import javax.persistence.*;

/**
 * Appointment entity
 * 
 * @author Group 3
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
		this.date = date;
		this.time = time;
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
	public String getDate() {
		return date;
	}

	/**
	 * Sets appointment date.
	 * 
	 * @param date date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Returns appointment time.
	 * 
	 * @return time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets appointment time.
	 * 
	 * @param time appointment time to set
	 */
	public void setTime(String time) {
		this.time = time;
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
		return getStaff().getSurname() + " " + getStaff().getFirstName() + " " + getInfo() + " " + getDate() + " " + getTime();
	}
	
	public String toStringStaff() {
		return getCustomer().getSurname() + " " + getCustomer().getFirstName() + " " + getInfo() + " " + getDate() + " " + getTime();

	}
}
