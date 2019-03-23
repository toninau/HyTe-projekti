package model;

import javax.persistence.*;

/**
 * Blood pressure and blood sugar are on this same class, but both of them are
 * not needed.
 * 
 * @author tonin
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

	@Column(name = "bloodpressure")
	private String bloodpressure;

	@ManyToOne
	@JoinColumn(name = "customerID", nullable = false)
	private Customer customer;

	/**
	 * Standard Bloodvalue constructor.
	 * 
	 * @param date          date, when the measure is taken
	 * @param time          time, when the measure is taken
	 * @param bloodsugar    blood sugar level
	 * @param bloodpressure blood pressure level
	 * @see #BloodValue(String, String, double)
	 * @see #BloodValue(String, String, String)
	 * @see #BloodValue()
	 */
	public BloodValue(String date, String time, double bloodsugar, String bloodpressure) {
		this.date = date;
		this.time = time;
		this.bloodsugar = bloodsugar;
		this.bloodpressure = bloodpressure;
	}

	/**
	 * Bloodvalue constructor for only the blood sugar levels
	 * 
	 * @param date       date of the blood sugar measure
	 * @param time       time of the blood sugar measure
	 * @param bloodsugar blood sugar level
	 * @see #BloodValue(String, String, double, String)
	 * @see #BloodValue(String, String, String)
	 * @see #BloodValue()
	 */
	public BloodValue(String date, String time, double bloodsugar) {
		this.bloodpressure = null;
		this.date = date;
		this.time = time;
		this.bloodsugar = bloodsugar;
	}

	/**
	 * Bloodvalue constructor for only the blood pressure levels.
	 * 
	 * @param date          date of the blood pressure measure
	 * @param time          time of the blood pressure measure
	 * @param bloodpressure blood pressure level
	 * @see #BloodValue(String, String, double, String)
	 * @see #BloodValue(String, String, String)
	 * @see #BloodValue()
	 */
	public BloodValue(String date, String time, String bloodpressure) {
		this.bloodsugar = 0;
		this.date = date;
		this.time = time;
		this.bloodpressure = bloodpressure;
	}

	/**
	 * Empty Bloodvalue constructor.
	 * 
	 * @see #BloodValue(String, String, double, String)
	 * @see #BloodValue(String, String, double)
	 * @see #BloodValue(String, String, String)
	 */
	public BloodValue() {
		this.bloodpressure = null;
		this.bloodsugar = 0;
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
	 * Returns the blood pressure level.
	 * 
	 * @return bloodpressure
	 */
	public String getBloodpressure() {
		return bloodpressure;
	}

	/**
	 * Sets the blood pressure level.
	 * 
	 * @param bloodpressure String value of the blood pressure level
	 */
	public void setBloodpressure(String bloodpressure) {
		this.bloodpressure = bloodpressure;
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