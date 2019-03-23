package model;

import javax.persistence.*;

/**
 * Illness entity
 * 
 * @author tonin
 *
 */
@Entity
@Table(name = "illness")
public class Illness {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "illnessID")
	private int illnessID;

	@Column(name = "illnessName")
	private String illnessName;

	@ManyToOne
	@JoinColumn(name = "customerID", nullable = false)
	private Customer customer;

	/**
	 * Standard illness object constructor.
	 * 
	 * @param illnessName Name of the illness
	 * @param customer    Customer of the illness
	 * @see #Illness()
	 */
	public Illness(String illnessName, Customer customer) {
		this.illnessName = illnessName;
		this.customer = customer;
	}

	/**
	 * Empty illness constructor.
	 * 
	 * @see #Illness(String, Customer)
	 */
	public Illness() {
	}

	/**
	 * Returns the illnessID. The value is defined automatically in the database.
	 * 
	 * @return illnessID
	 */
	public int getIllnessID() {
		return illnessID;
	}

	/**
	 * Sets the illnessID. The value is defined automatically in the database. Not
	 * used in the creation of an illness object.
	 * 
	 * @param illnessID illnessID to set
	 */
	public void setIllnessID(int illnessID) {
		this.illnessID = illnessID;
	}

	/**
	 * Returns the name of the illness.
	 * 
	 * @return illnessName
	 */
	public String getIllnessName() {
		return illnessName;
	}

	/**
	 * Sets a name for the illness.
	 * 
	 * @param illnessName name of the illness
	 */
	public void setIllnessName(String illnessName) {
		this.illnessName = illnessName;
	}

	/**
	 * Returns a customer object containing the illness.
	 * 
	 * @return customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * Sets a customer for the illness.
	 * 
	 * @param customer customer for the illness
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
