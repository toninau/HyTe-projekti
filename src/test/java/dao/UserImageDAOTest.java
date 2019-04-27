package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileInputStream;

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
		image = createUserImage(customer);
		assertTrue(iDAO.create(image), "create(UserImage): Failed to create image");
		//Read image
		image = iDAO.read(1);
		assertEquals("test_image", image.getImageName(), "read(id): Failed to read image name");
		//Read customer's all images
		UserImage[] images = iDAO.readCustomerUserImages(customer);
		assertEquals(1, images.length, "readCustomerUserImages(customer): Failed to read all customer's images");
		//Update image
		image.setImageName("update");
		assertTrue(iDAO.update(image), "update(UserImage): Failed to update image");
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
	
	private UserImage createUserImage(Customer customer) {
		File file = new File(getClass().getClassLoader().getResource("/pictures/finland_flag.png").getFile());
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(bFile);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserImage image = new UserImage();
		image.setCustomer(customer);
		image.setImage(bFile);
		image.setImageName("test_image");
		return image;
	}
}
