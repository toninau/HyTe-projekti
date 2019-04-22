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
		assertTrue(sDAO.create(staff), "create(staff): Failed to create a new staff member.");
		//Read staff member
		staff = sDAO.read("firsur");
		assertEquals("Firstname", staff.getFirstName(), "read(id): Failed to read first name.");
		assertEquals("Surname", staff.getSurname(), "read(id): Failed to read surname.");
		assertEquals("PhoneNumber", staff.getPhoneNumber(), "read(id): Failed to read phone number.");
		assertEquals("AccessLevel", staff.getAccessLevel(), "read(id): Failed to read access level.");
		assertEquals("Password", staff.getPassword(), "read(id): Failed to read password.");
		//Update staff member
		staff.setFirstName("Update1");
		staff.setSurname("Update2");
		staff.setPhoneNumber("Update3");
		staff.setAccessLevel("Update4");
		staff.setPassword("Update5");
		sDAO.update(staff);
		staff = sDAO.read("firsur");
		assertEquals("Update1", staff.getFirstName(), "update(staff): Failed to update firstname.");
		assertEquals("Update2", staff.getSurname(), "update(staff): Failed to update surname.");
		assertEquals("Update3", staff.getPhoneNumber(), "update(staff): Failed to update phone number.");
		assertEquals("Update4", staff.getAccessLevel(), "update(staff): Failed to update access level.");
		assertEquals("Update5", staff.getPassword(), "update(staff): Failed to update password.");
		//Add customer to staff member
		customer = new Customer();
		customer.setFirstName("Firstname");
		customer.setSurname("Surname");
		customer.setSSN("SSN");
		customer.setIceNumber("ICEnumber");
		customer.setAddress("Address");
		customer.setPhoneNumber("Phonenumber");
		customer.setPassword("Password");
		cDAO.create(customer);
		customer = cDAO.read("firsur");
		assertTrue(sDAO.addCustomer(staff, customer), "addCustomer(staff, customer): Failed to add customer to staff member.");
		Customer[] customers = sDAO.readStaffMembersCustomers(staff);
		assertEquals(1, customers.length, "readStaffMembersCustomers(staff): Failed to read staff members customers after adding customer.");
		//delete customer from staff member
		assertTrue(sDAO.deleteCustomer(staff, customer), "deleteCustomer(staff, customer): Failed to delete customer from staff member.");
		customers = sDAO.readStaffMembersCustomers(staff);
		assertEquals(0, customers.length, "readStaffMembersCustomer(staff): Failed to read staff members customes after deleting customer");
		//Delete staff member
		assertTrue(sDAO.delete("firsur"), "delete(id): Failed to delete staff member.");
		Staff[] list = sDAO.readAll();
		assertEquals(0, list.length, "readAll(): Failed to read all staff members.");
	}
}