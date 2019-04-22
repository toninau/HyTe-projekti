package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.HibernateUtil;
import model.Staff;

public class StaffDAOTest {
	private StaffDAO sDAO;
	private Staff staff;
	private Customer customer;
	private CustomerDAO cDAO;

	@BeforeEach
	public void setTest() {
		SessionFactory sf = HibernateUtil.getSessionFactory(true);
		sDAO = new StaffDAO(sf);
		cDAO = new CustomerDAO(sf);
	}

	@Test
	public void testDAOmethods() {
		//Create staff member
		staff = new Staff("Firstname", "Surname", "PhoneNumber", "AccessLevel", "Password");
		assertTrue(sDAO.create(staff), "create(): Failed to create a new staff member.");
		//Read staff member
		staff = sDAO.read("firsur");
		assertEquals("Firstname", staff.getFirstName(), "read(): Failed to read first name.");
		assertEquals("Surname", staff.getSurname(), "read(): Failed to read surname.");
		assertEquals("PhoneNumber", staff.getPhoneNumber(), "read(): Failed to read phone number.");
		assertEquals("AccessLevel", staff.getAccessLevel(), "read(): Failed to read access level.");
		assertEquals("Password", staff.getPassword(), "read(): Failed to read password.");
		//Update staff member
		staff.setFirstName("Update1");
		staff.setSurname("Update2");
		staff.setPhoneNumber("Update3");
		staff.setAccessLevel("Update4");
		staff.setPassword("Update5");
		sDAO.update(staff);
		staff = sDAO.read("firsur");
		assertEquals("Update1", staff.getFirstName(), "update(): Failed to update firstname.");
		assertEquals("Update2", staff.getSurname(), "update(): Failed to update surname.");
		assertEquals("Update3", staff.getPhoneNumber(), "update(): Failed to update phone number.");
		assertEquals("Update4", staff.getAccessLevel(), "update(): Failed to update access level.");
		assertEquals("Update5", staff.getPassword(), "update(): Failed to update password.");
		//Delete staff member
		assertTrue(sDAO.delete("firsur"), "delete(): Failed to delete staff member.");
		Staff[] list = sDAO.readAll();
		assertEquals(0, list.length, "readAll(): Failed to read all staff members");
	}
}

