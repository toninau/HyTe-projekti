package model;

import javax.persistence.*;

/**
 * Notification entity
 * 
 * @author tonin
 *
 */
@Entity
@Table(name = "notification")
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "notificationID")
	private int notificationID;

	@Column(name = "date")
	private String date;

	@Column(name = "text")
	private String text;

	@Column(name = "readText")
	private boolean read = false;

	@ManyToOne
	@JoinColumn(name = "customerID", nullable = false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "staffID", nullable = false)
	private Staff staff;

	/**
	 * Vakio ilmoitus konstruktori.
	 * 
	 * @param date          ilmoituksen luonti päivämäärä
	 * @param text       ilmoitukseen liitetty text
	 * @param customer      asiakas, jolle ilmoitus lähetetään
	 * @param staff ilmoituksen tekijä
	 * @see #Notification()
	 */
	public Notification(String date, String text, Customer customer, Staff staff) {
		this.date = date;
		this.text = text;
		this.customer = customer;
		this.staff = staff;
	}

	/**
	 * Tyhjä ilmoitus konstruktori. Arvot annetaan set-metodeja käyttäen.
	 * 
	 * @see #Notification(String, String, Customer, Staff)
	 */
	public Notification() {

	}

	public int getNotificationID() {
		return notificationID;
	}

	public String getDate() {
		return date;
	}

	public String getText() {
		return text;
	}

	public boolean isRead() {
		return read;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	
}
