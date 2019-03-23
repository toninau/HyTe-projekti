package model;

import javax.persistence.*;

/**
 * Prescription entity
 * 
 * @author tonin
 *
 */
@Entity
@Table(name = "prescription")
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prescriptionID")
	private int prescriptionID;

	@Column(name = "startDate")
	private String startDate;

	@Column(name = "endDate")
	private String endDate;

	@Column(name = "prescriptionName")
	private String prescriptionName;

	@Column(name = "prescriptionGuide")
	private String prescriptionGuide;

	@ManyToOne
	@JoinColumn(name = "customerID", nullable = false)
	private Customer customer;

	@ManyToOne
	@JoinColumn(name = "staffID", nullable = false)
	private Staff staff;

	/**
	 * Empty prescription constructor. Values are given using set-methods.
	 * 
	 * @see #Prescription(String, String, String, String, Customer, Staff)
	 */
	public Prescription() {
	}

	/**
	 * Prescription constructor.
	 * 
	 * @param startDate         Prescription starting date
	 * @param endDate           Prescription ending date
	 * @param prescriptionName  Prescription's name
	 * @param prescriptionGuide Prescription's guide
	 * @param customer          Prescription's customer
	 * @param staff             Prescription's staff member
	 * @see #Prescription()
	 */
	public Prescription(String startDate, String endDate, String prescriptionName, String prescriptionGuide,
			Customer customer, Staff staff) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.prescriptionName = prescriptionName;
		this.prescriptionGuide = prescriptionGuide;
		this.customer = customer;
		this.staff = staff;
	}
	
	/**
	 * Returns prescriptionID. PrescriptionID value is given automatically in the database.
	 * @return prescriptionID
	 */
	public int getPrescriptionID() {
		return prescriptionID;
	}
	
	/**
	 * Sets prescriptionID. PrescriptionID value is given automatically
	 * in the database. Not used when creating a new prescription.
	 * @param prescriptionID prescriptionID to set
	 */
	public void setPrescriptionID(int prescriptionID) {
		this.prescriptionID = prescriptionID;
	}
	
	/**
	 * Returns prescription start date.
	 * @return startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	
	/**
	 * Sets prescription start date.
	 * @param startDate date when prescription started
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Returns prescription end date.
	 * @return endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets prescription end date.
	 * @param endDate date when prescription is going to end
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * Returns prescription name.
	 * @return prescriptionName
	 */
	public String getPrescriptionName() {
		return prescriptionName;
	}
	
	/**
	 * Sets prescription name.
	 * @param prescriptionName name to set
	 */
	public void setPrescriptionName(String prescriptionName) {
		this.prescriptionName = prescriptionName;
	}
	
	/**
	 * Returns prescription guide.
	 * @return prescriptionGuide
	 */
	public String getPrescriptionGuide() {
		return prescriptionGuide;
	}
	
	/**
	 * Sets prescription guide.
	 * @param prescriptionGuide guide to set
	 */
	public void setPrescriptionGuide(String prescriptionGuide) {
		this.prescriptionGuide = prescriptionGuide;
	}
	
	/**
	 * Returns prescription customer.
	 * @return customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * Sets prescription customer.
	 * @param customer customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/**
	 * Returns prescription staff member.
	 * @return staff
	 */
	public Staff getStaff() {
		return staff;
	}
	
	/**
	 * Sets prescription staff member.
	 * @param staff staff member to set
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
}
