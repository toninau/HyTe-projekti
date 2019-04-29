package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UserImageTest {
	private UserImage image;
	private Customer customer;
	
	@BeforeEach
	public void setUserImage() {
		image = new UserImage();
		customer = new Customer();
	}
	
	@Test
	public void testEmptyConstructor() {
		try {
			image = new UserImage();
		} catch (Exception e) {
			fail("Failed to create UserImage-object");
		}
	}
	
	@Test
	public void testConstructor() {
		customer = new Customer();
		byte[] array = new byte[12];
		try {
			image = new UserImage("test", array, customer);
		} catch (Exception e) {
			fail("Failed to create UserImage-object");
		}
	}
	
	@Test
	public void testGetSetID() {
		image.setImageID("1");
		assertEquals("1", image.getImageID(), "Failed to set and get imageID");
	}
	
	@Test
	public void testGetSetName() {
		image.setImageName("test");
		assertEquals("test", image.getImageName(), "Failed to set and get image name");
	}
	
	@Test
	public void testGetSetCustomer() {
		customer.setCustomerID("2");
		image.setCustomer(customer);
		assertEquals("2", image.getCustomer().getCustomerID(), "Failed to set and get image customer");
	}
	
	@Test
	public void testGetSetImage() {
		byte[] array = new byte[10];
		image.setImage(array);
		assertEquals(10, image.getImage().length, "Failed to set and get image (byte array)");
	}
}
