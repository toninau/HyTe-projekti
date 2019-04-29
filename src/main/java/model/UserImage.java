package model;

import javax.persistence.*;

@Entity
@Table(name = "userImage")
public class UserImage {

	@Id
	@Column(name = "imageID")
	private String imageID;

	@Column(name = "imageName", nullable = false)
	private String imageName;

	@Lob
	@Column(name = "imageFile", nullable = false, columnDefinition = "mediumblob")
	private byte[] image;

	@ManyToOne
	@JoinColumn(name = "customerID", nullable = false)
	private Customer customer;

	/**
	 * Empty UserImage constructor
	 * 
	 * @see #UserImage(String, byte[], Customer)
	 */
	public UserImage() {
	}

	/**
	 * Standard UserImage object constructor
	 * 
	 * @param imageName name of the image
	 * @param image     image file in byte array
	 * @param customer  customer of the image
	 */
	public UserImage(String imageName, byte[] image, Customer customer) {
		this.imageName = imageName;
		this.image = image;
		this.customer = customer;
	}
	
	/**
	 * Returns the imageID. The value is defined automatically in the database.
	 * @return imageID
	 */
	public String getImageID() {
		return imageID;
	}
	
	/**
	 * Sets the imageID. The value is defined automatically in the database. Not
	 * used in the creation of an userImage object.
	 * @param imageID imageID to set
	 */
	public void setImageID(String imageID) {
		this.imageID = imageID;
	}
	
	/**
	 * Returns the name of the image.
	 * @return imageName
	 */
	public String getImageName() {
		return imageName;
	}
	
	/**
	 * Sets a name for the image.
	 * @param imageName name of the image
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	/**
	 * Returns image file in byte array.
	 * @return image byte array
	 */
	public byte[] getImage() {
		return image;
	}
	
	/**
	 * Sets image file.
	 * @param image byte array
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	/**
	 * Returns customer of the image.
	 * @return customer
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * Sets customer for the image.
	 * @param customer customer of the image
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
