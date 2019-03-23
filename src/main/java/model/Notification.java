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
	 * Notification constructor.
	 * 
	 * @param date     notification's creation date
	 * @param text     notification's text
	 * @param customer customer whom the notification is sent
	 * @param staff    staff member who created the notification
	 * @see #Notification()
	 */
	public Notification(String date, String text, Customer customer, Staff staff) {
		this.date = date;
		this.text = text;
		this.customer = customer;
		this.staff = staff;
	}

	/**
	 * Empty notification constructor. Values are given using set-methods.
	 * 
	 * @see #Notification(String, String, Customer, Staff)
	 */
	public Notification() {
	}

	/**
	 * Returns notificationID. NotificationID value is given automatically in the
	 * database.
	 * 
	 * @return notificationID
	 */
	public int getNotificationID() {
		return notificationID;
	}

	/**
	 * Sets notificationID. NotificationID value is given automatically in the
	 * database. Not used when creating a new notification.
	 * 
	 * @param notificationID notificationID to set
	 */
	public void setNotificationID(int notificationID) {
		this.notificationID = notificationID;
	}

	/**
	 * Returns notification's date.
	 * 
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets date to notification.
	 * 
	 * @param date date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Returns notification's text.
	 * 
	 * @return text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets text to notification.
	 * 
	 * @param text text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Returns the read status of the notification.
	 * 
	 * @return <code>true</code> if notification has been read <br>
	 *         <code>false</code> if notification has not been read
	 */
	public boolean isRead() {
		return read;
	}

	/**
	 * Sets the read status of the notification.
	 * 
	 * @param read <code>true</code> if notification has been read <br>
	 *             <code>false</code> if notifications has not been read
	 */
	public void setRead(boolean read) {
		this.read = read;
	}

	/**
	 * Returns notification's customer.
	 * 
	 * @return customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets customer to notification.
	 * 
	 * @param customer customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * Returns notification's staff member.
	 * 
	 * @return staff
	 */
	public Staff getStaff() {
		return staff;
	}

	/**
	 * Sets staff member to notification.
	 * 
	 * @param staff staff member to set
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
}
