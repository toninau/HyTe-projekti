package dao;
/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		customer = new Customer("Firstname", "Surname", "SSN", "Address", "Email", "Phonenumber", "ICEnumber",
				"Password");
		assertTrue(dao.create(customer), "create(): Failed to create a new customer.");
		customer = dao.read(1);
		assertEquals("Firstname", customer.getFirstName(), "read(): Failed to read firstname.");
		assertEquals("Surname", customer.getSurname(), "read(): Failed to read surname.");
		assertEquals("SSN", customer.getSSN(), "read(): Failed to read SSN.");
		assertEquals("Address", customer.getAddress(), "read(): Failed to read address.");
		assertEquals("Email", customer.getEmail(), "read(): Failed to read email.");
		assertEquals("Phonenumber", customer.getPhoneNumber(), "read(): Failed to read phone number.");
		assertEquals("ICEnumber", customer.getIceNumber(), "read(): Failed to read ice phone number.");
		assertEquals("Password", customer.getPassword(), "read(): Failed to read password.");
		customer.setFirstName("Update");
		dao.update(customer);
		customer = dao.read(1);
		assertEquals("Update", customer.getFirstName(), "update(): Failed to update firstname.");
		assertTrue(dao.delete(1), "delete(): Failed to delete customer.");
		Customer[] list = dao.readAll();
		assertEquals(0, list.length, "readAll(): Failed to read all staff members");
	}
}
*/