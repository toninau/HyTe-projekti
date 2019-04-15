package dao;
/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.HibernateUtil;

public class CustomerDAOTest {
	private CustomerDAO dao;
	private Customer customer;

	@BeforeEach
	public void setTest() {
		SessionFactory istuntotehdas = HibernateUtil.getSessionFactory(true);
		dao = new CustomerDAO(istuntotehdas);
	}

	@Test
	public void testDAOmethods() {
		customer = new Customer();
		customer.setFirstName("Firstname");
		customer.setSurname("Surname");
		customer.setSSN("SSN");
		customer.setIceNumber("ICEnumber");
		customer.setAddress("Address");
		customer.setPhoneNumber("Phonenumber");
		customer.setPassword("Password");
		customer.setCustomerID("12");
		assertTrue(dao.create(customer), "create(): Failed to create a new customer.");
		customer = dao.read("12");
		assertEquals("Firstname", customer.getFirstName(), "read(): Failed to read firstname.");
		assertEquals("Surname", customer.getSurname(), "read(): Failed to read surname.");
		assertEquals("SSN", customer.getSSN(), "read(): Failed to read SSN.");
		assertEquals("Address", customer.getAddress(), "read(): Failed to read address.");
		assertEquals("12", customer.getCustomerID(), "read(): Failed to read customer id.");
		assertEquals("Phonenumber", customer.getPhoneNumber(), "read(): Failed to read phone number.");
		assertEquals("ICEnumber", customer.getIceNumber(), "read(): Failed to read ice phone number.");
		assertEquals("Password", customer.getPassword(), "read(): Failed to read password.");
		customer.setFirstName("Update");
		dao.update(customer);
		customer = dao.read("12");
		assertEquals("Update", customer.getFirstName(), "update(): Failed to update firstname.");
		Customer[] list = dao.readAll();
		assertEquals(1, list.length, "readAll(): Failed to read all staff members");
	}
}
*/
