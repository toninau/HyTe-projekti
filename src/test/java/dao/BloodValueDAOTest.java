package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.HibernateUtil;
import model.BloodValue;

public class BloodValueDAOTest {
	private Customer customer;
	private CustomerDAO cDAO;
	private BloodValue bv;
	private BloodValueDAO bvDAO;
	
	@BeforeEach
	public void setTest() {
		SessionFactory sf = HibernateUtil.getSessionFactory(true);
		cDAO = new CustomerDAO(sf);
		bvDAO = new BloodValueDAO(sf);
	}
	
	@Test
	public void testDAOMethods() {
		//Create customer
		customer = createCustomer();
		//Create bloodvalue
		bv = new BloodValue("Date", "Time", 5.5, 100, 160, customer);
		assertTrue(bvDAO.create(bv), "create(bloodvalue): Failed to create bloodvalue");
		//Read bloodvalue
		BloodValue[] bvs = bvDAO.readCustomerBloodvalues(customer);
		assertEquals(1, bvs.length, "readCustomerBloodvalues(customer): Failed to read customer's bloodvalue");
		assertEquals("Date", bvs[0].getDate(), "readCustomerBloodvalues(customer): Failed to read customer's bloodvalue date");
		assertEquals("Time", bvs[0].getTime(), "readCustomerBloodvalues(customer): Failed to read customer's bloodvalue time");
		assertEquals(5.5, bvs[0].getBloodsugar(), "readCustomerBloodvalues(customer): Failed to read customer's bloodvalue bloodsugar");
		assertEquals(100, bvs[0].getLowPressure(), "readCustomerBloodvalues(customer): Failed to read customer's bloodvalue low pressure");
		assertEquals(160, bvs[0].getHighPressure(), "readCustomerBloodvalues(customer): Failed to read customer's bloodvalue high pressure");
		//Delete bloodvalue
		assertTrue(bvDAO.delete(1), "delete(id): Failed to delete bloodvalue.");
		bvs = bvDAO.readCustomerBloodvalues(customer);
		assertEquals(0, bvs.length, "readCustomerBloodvalues(customer): Failed to read customer's bloodvalue after deletion");
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
