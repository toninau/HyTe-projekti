package dao;

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
		SessionFactory sf = HibernateUtil.getSessionFactory(true);
		dao = new CustomerDAO(sf);
	}

	@Test
	public void testDAOmethods() {
		//Creates customer
		customer = new Customer();
		customer.setFirstName("Firstname");
		customer.setSurname("Surname");
		customer.setSSN("SSN");
		customer.setIceNumber("ICEnumber");
		customer.setAddress("Address");
		customer.setPhoneNumber("Phonenumber");
		customer.setPassword("Password");
		assertTrue(dao.create(customer), "create(): Failed to create a new customer.");
		//Reads customer
		customer = dao.read("firsur");
		assertEquals("Firstname", customer.getFirstName(), "read(): Failed to read first name.");
		assertEquals("Surname", customer.getSurname(), "read(): Failed to read surname.");
		assertEquals("SSN", customer.getSSN(), "read(): Failed to read SSN.");
		assertEquals("Address", customer.getAddress(), "read(): Failed to read address.");
		assertEquals("Phonenumber", customer.getPhoneNumber(), "read(): Failed to read phone number.");
		assertEquals("ICEnumber", customer.getIceNumber(), "read(): Failed to read ice phone number.");
		assertEquals("Password", customer.getPassword(), "read(): Failed to read password.");
		//Updates customer
		customer.setFirstName("Update1");
		customer.setSurname("Update2");
		customer.setSSN("Update3");
		customer.setIceNumber("Update4");
		customer.setAddress("Update5");
		customer.setPhoneNumber("Update6");
		customer.setPassword("Update7");
		dao.update(customer);
		customer = dao.read("firsur");
		assertEquals("Update1", customer.getFirstName(), "update(): Failed to update first name.");
		assertEquals("Update2", customer.getSurname(), "update(): Failed to update surname.");
		assertEquals("Update3", customer.getSSN(), "update(): Failed to update SSN.");
		assertEquals("Update4", customer.getIceNumber(), "update(): Failed to update ICE number.");
		assertEquals("Update5", customer.getAddress(), "update(): Failed to update address.");
		assertEquals("Update6", customer.getPhoneNumber(), "update(): Failed to update phone number.");
		assertEquals("Update7", customer.getPassword(), "update(): Failed to update password.");
		//deletes customer
		assertTrue(dao.delete("firsur"), "delete(): Failed to delete customer.");
		//reads all customers
		Customer[] list = dao.readAll();
		assertEquals(0, list.length, "readAll(): Failed to read all staff members");
	}
}