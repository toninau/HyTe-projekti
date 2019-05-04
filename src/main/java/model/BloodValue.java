package model;

import javax.persistence.*;

/**
 * Blood pressure and blood sugar are on this same class, but both of them are
 * not needed.
 * 
 * @author Group 3
 *
 */
@Entity
@Table(name = "bloodvalue")
public class BloodValue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bloodvalueID")
	private int bloodvalueID;

	@Column(name = "date")
	private String date;

	@Column(name = "time")
	private String time;

	@Column(name = "bloodsugar")
	private double bloodsugar;

	@Column(name = "highBloodpressure")
	private int highPressure;
	
	@Column(name = "lowBloodpressure")
	private int lowPressure;

	@ManyToOne
	@JoinColumn(name = "customerID", nullable = false)
	private Customer customer;

	/**
	 * 
	 * @param date date, when the measure is taken
	 * @param time  time, when the measure is taken
	 * @param bloodsugar blood sugar level
	 * @param lowPressure low pressure level
	 * @param highPressure high pressure level
	 * @param customer customer's whose bloodvalues are being measured
	 * 
	 * @see #BloodValue()
	 * @see #BloodValue(String, String, double, Customer)
	 * @see #BloodValue(String, String, int, int, Customer)
	 */
	public BloodValue(String date, String time, double bloodsugar, int lowPressure, int highPressure, Customer customer) {
		this.date = date;
		this.time = time;
		this.bloodsugar = bloodsugar;
		this.lowPressure = lowPressure;
		this.highPressure = highPressure;
		this.customer = customer;
	}

	/**
	 * Bloodvalue constructor for only the blood sugar levels
	 * 
	 * @param date       date of the blood sugar measure
	 * @param time       time of the blood sugar measure
	 * @param bloodsugar blood sugar level
	 * @param customer customer's whose bloodvalues are being measured
	 * 
	 * @see #BloodValue()
	 * @see #BloodValue(String, String, int, int, Customer)
	 * @see #BloodValue(String, String, double, int, int, Customer)
	 */
	public BloodValue(String date, String time, double bloodsugar, Customer customer) {
		//this.highPressure = -1;
		//this.lowPressure = -1;
		this.date = date;
		this.time = time;
		this.bloodsugar = bloodsugar;
	}

	/**
	 * 
	 * @param date date of the blood pressure measure
	 * @param time time of the blood pressure measure
	 * @param highPressure high pressure level
	 * @param lowPressure low pressure level
	 * @param customer customer's whose bloodvalues are being measured
	 * 
	 * @see #BloodValue()
	 * @see #BloodValue(String, String, double, Customer)
	 * @see #BloodValue(String, String, double, int, int, Customer)
	 */
	public BloodValue(String date, String time, int highPressure, int lowPressure, Customer customer) {
		this.bloodsugar = -1;
		this.date = date;
		this.time = time;
		this.lowPressure = lowPressure;
		this.highPressure = highPressure;
	}

	/**
	 * Empty Bloodvalue constructor.
	 * 
	 * @see #BloodValue(String, String, double, Customer)
	 * @see #BloodValue(String, String, int, int, Customer)
	 * @see #BloodValue(String, String, double, int, int, Customer)
	 */
	public BloodValue() {
		this.highPressure = -1;
		this.lowPressure = -1;
		this.bloodsugar = -1;
	}

	/**
	 * Returns the ID of the Bloodvalue. ID is defined automatically in the
	 * database. It is used to connect to the customer taking the measurement.
	 * 
	 * @return bloodvalueID
	 */
	public int getBloodvalueID() {
		return bloodvalueID;
	}

	/**
	 * Returns the ID of the Bloodvalue. ID is defined automatically in the
	 * database. It is used to connect to the customer taking the measurement. Not
	 * used when creating a new Bloodvalue object.
	 * 
	 * @param bloodvalueID ID of the Bloodvalue object
	 */
	public void setBloodvalueID(int bloodvalueID) {
		this.bloodvalueID = bloodvalueID;
	}

	/**
	 * Returns the date of the measurement.
	 * 
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date of the measurement.
	 * 
	 * @param date of the measurement
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Returns the time of the measurement.
	 * 
	 * @return time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * Sets the time of the measurement
	 * 
	 * @param time of the measurement
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * Returns the blood sugar level.
	 * 
	 * @return bloodsugar
	 */
	public double getBloodsugar() {
		return bloodsugar;
	}

	/**
	 * Sets the blood sugar level.
	 * 
	 * @param bloodsugar the blood sugar level
	 */
	public void setBloodsugar(double bloodsugar) {
		this.bloodsugar = bloodsugar;
	}

	/**
	 * Returns the systolic blood pressure level.
	 * 
	 * @return Systolic bloos pressure level.
	 */
	public int getHighPressure() {
		return highPressure;
	}

	/**
	 * Returns the dystolic blood pressure level.
	 * 
	 * @return Dystolic blood pressure level.
	 */
	public int getLowPressure() {
		return lowPressure;
	}
	
	/**
	 * Sets the systolic blood pressure level.
	 * 
	 * @param bloodpressure String value of the blood pressure level
	 */
	public void setHighPressure(int highPressure) {
		this.highPressure = highPressure;
	}
	
	public void setLowPressure(int lowPressure) {
		this.lowPressure = lowPressure;
	}

	/**
	 * Gets the customer of the Bloodvalue object.
	 * 
	 * @return customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets a customer for the measured blood values.
	 * 
	 * @param customer connect to the measured blood values.
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}