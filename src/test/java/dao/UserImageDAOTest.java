package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.HibernateUtil;
import model.UserImage;

public class UserImageDAOTest {
	private Customer customer;
	private CustomerDAO cDAO;
	private UserImage image;
	private UserImageDAO iDAO;

	@BeforeEach
	public void setTest() {
		SessionFactory sf = HibernateUtil.getSessionFactory(true);
		cDAO = new CustomerDAO(sf);
		iDAO = new UserImageDAO(sf);
	}

	@Test
	public void testDAOMethods() {
		// Create customer
		customer = createCustomer();
		//Create image
		image = new UserImage();
		byte[] bFile = new byte[10];
		image.setCustomer(customer);
		image.setImage(bFile);
		image.setImageName("test_image");
		assertTrue(iDAO.create(image), "create(UserImage): Failed to create image");
		//Read customer's all images
		UserImage[] images = iDAO.readCustomerUserImages(customer);
		assertEquals(1, images.length, "readCustomerUserImages(customer): Failed to read all customer's images");
		assertEquals("test_image", images[0].getImageName(), "read(id): Failed to read image name");
		assertEquals(10, images[0].getImage().length, "read(id): Failed to read image file");
		assertEquals("FirstName", images[0].getCustomer().getFirstName(), "read(id): Failed to read image customer");
		//Update image
		image = iDAO.read(1);
		image.setImageName("update");
		assertTrue(iDAO.update(image), "update(UserImage): Failed to update image");
		image = iDAO.read(1);
		assertEquals("update", image.getImageName(), "read(id): Failed read image name after update");
		//Delete image
		assertTrue(iDAO.delete(1), "delete(id): Failed to delete image");
		images = iDAO.readCustomerUserImages(customer);
		assertEquals(0, images.length, "readCustomerUserImages(customer): Failed to read all customer's images after deleting image");
		//Delete customer
		cDAO.delete("firsur");
	}

	private Customer createCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("FirstName");
		customer.setSurname("Surname");
		customer.setSSN("SSN");
		customer.setIceNumber("ICEnumber");
		customer.setAddress("Address");
		customer.setPhoneNumber("PhoneNumber");
		customer.setPassword("Password");
		cDAO.create(customer);
		customer = cDAO.read("firsur");
		return customer;
	}
}
