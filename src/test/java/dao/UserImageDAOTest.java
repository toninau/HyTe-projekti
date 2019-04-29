package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.HibernateUtil;
import model.UserImage;

public class UserImageDAOTest {
	private static Customer customer;
	private static CustomerDAO cDAO;
	private static UserImage image;
	private static UserImageDAO iDAO;

	@BeforeAll
	public static void setTest() {
		SessionFactory sf = HibernateUtil.getSessionFactory(true);
		cDAO = new CustomerDAO(sf);
		iDAO = new UserImageDAO(sf);
		customer = createCustomer();
		
		image = new UserImage();
		byte[] bFile = new byte[10];
		image.setCustomer(customer);
		image.setImage(bFile);
		image.setImageName("test_image");
		image.setImageID(customer.getCustomerID() + 1);
		iDAO.create(image);
	}
	
	@AfterAll
	public static void tearDown() {
		cDAO.delete(customer.getCustomerID());
		iDAO.delete(customer.getCustomerID()+1);
	}
	
	@Test
	public void testCreate() {
		UserImage image = new UserImage();
		byte[] bFile = new byte[10];
		image.setCustomer(customer);
		image.setImage(bFile);
		image.setImageName("test_image");
		image.setImageID(customer.getCustomerID()+1);
		assertTrue(iDAO.create(image), "create(UserImage): Failed to create image");
	}
	
	@Test
	public void testUpdate() {
		UserImage image = iDAO.read(customer.getCustomerID()+1);
		image.setImageName("test_update");
		iDAO.update(image);
		assertTrue(iDAO.read(customer.getCustomerID()+1).getImageName().equals("test_update"));
	}
	
	@Test
	public void testRead() {
		assertTrue(iDAO.read(customer.getCustomerID()+1).getImageName().equals("test_image"));
	}

	@Test
	public void testReadAll() {
		UserImage[] images = iDAO.readCustomerUserImages(customer);
		assertTrue(images.length == 1);
		assertTrue(images[0].getImageID().equals(customer.getCustomerID()+1));
	}
	
	
/*	@Test
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
		//Read image
		image = iDAO.read(customer.getCustomerID()+1);
		assertEquals("test_image", image.getImageName(), "read(id): Failed to read image name");
		assertEquals(10, image.getImage().length, "read(id): Failed to read image file");
		assertEquals("ImageFirstName", image.getCustomer().getFirstName(), "read(id): Failed to read image customer");
		//Read customer's all images
		UserImage[] images = iDAO.readCustomerUserImages(customer);
		assertEquals(1, images.length, "readCustomerUserImages(customer): Failed to read all customer's images");
		assertEquals("test_image", images[0].getImageName(), "read(id): Failed to read image name");
		assertEquals(10, images[0].getImage().length, "read(id): Failed to read image file");
		assertEquals("ImageFirstName", images[0].getCustomer().getFirstName(), "read(id): Failed to read image customer");
		//Update image
		image = iDAO.read(customer.getCustomerID()+1);
		image.setImageName("update");
		assertTrue(iDAO.update(image), "update(UserImage): Failed to update image");
		image = iDAO.read(customer.getCustomerID()+1);
		assertEquals("update", image.getImageName(), "read(id): Failed read image name after update");
		//Delete image
		assertTrue(iDAO.delete(customer.getCustomerID()+1), "delete(id): Failed to delete image");
		images = iDAO.readCustomerUserImages(customer);
		assertEquals(0, images.length, "readCustomerUserImages(customer): Failed to read all customer's images after deleting image");
		//Delete customer
		cDAO.delete("imasur");
	}*/

	private static Customer createCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("ImageFirstName");
		customer.setSurname("Surname");
		customer.setSSN("SSN");
		customer.setIceNumber("ICEnumber");
		customer.setAddress("Address");
		customer.setPhoneNumber("PhoneNumber");
		customer.setPassword("Password");
		cDAO.create(customer);
		customer = cDAO.read("imasur");
		return customer;
	}
}
