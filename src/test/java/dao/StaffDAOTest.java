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
import model.Staff;

public class StaffDAOTest {
	private static StaffDAO sDAO;
	private static Staff staff;
	private static Customer customer;
	private static CustomerDAO cDAO;
	
	@BeforeAll
	public static void setUp() {
		SessionFactory sf = HibernateUtil.getSessionFactory(false);
		sDAO = new StaffDAO(sf);
		cDAO = new CustomerDAO(sf);
	
		staff = new Staff();
		staff.setFirstName("staffTestFirst");
		staff.setSurname("staffTestSur");
		staff.setPhoneNumber("PhoneNumber");
		staff.setAccessLevel("AccessLevel");
		staff.setPassword("Password");	
		sDAO.create(staff);
		
		customer = new Customer();
		customer.setFirstName("customerfirst");
		customer.setSurname("customersur");
		customer.setSSN("SSN");
		customer.setIceNumber("ICEnumber");
		customer.setAddress("Address");
		customer.setPhoneNumber("Phonenumber");
		customer.setPassword("Password");
		cDAO.create(customer);
	}
	
	
	@Test
	public void testRead() {
		staff = sDAO.read("stasta");
		assertEquals("staffTestFirst", staff.getFirstName(), "read(id): Failed to read first name.");
		assertEquals("staffTestSur", staff.getSurname(), "read(id): Failed to read surname.");
		assertEquals("PhoneNumber", staff.getPhoneNumber(), "read(id): Failed to read phone number.");
		assertEquals("AccessLevel", staff.getAccessLevel(), "read(id): Failed to read access level.");
		assertEquals("Password", staff.getPassword(), "read(id): Failed to read password.");
	}
	
	@Test
	public void testUpdate() {
		Staff newStaff = new Staff("newStaffFirst", "newStaffSur", "newStaffPh", "newStaffAcc", "newStaffPW");
		sDAO.create(newStaff);
		
		newStaff.setFirstName("Update1");
		newStaff.setSurname("Update2");
		newStaff.setPhoneNumber("Update3");
		newStaff.setAccessLevel("Update4");
		newStaff.setPassword("Update5");
		sDAO.update(newStaff);
		staff = sDAO.read("newnew");
		assertEquals("Update1", staff.getFirstName(), "update(staff): Failed to update firstname.");
		assertEquals("Update2", staff.getSurname(), "update(staff): Failed to update surname.");
		assertEquals("Update3", staff.getPhoneNumber(), "update(staff): Failed to update phone number.");
		assertEquals("Update4", staff.getAccessLevel(), "update(staff): Failed to update access level.");
		assertEquals("Update5", staff.getPassword(), "update(staff): Failed to update password.");
		
		sDAO.delete("newnew");
		sDAO.delete("updupd");
	}
	
	@Test
	public void testStaffMembersCustomers() {

		assertTrue(sDAO.addCustomer(staff, customer), "addCustomer(staff, customer): Failed to add customer to staff member.");
		Customer[] customers = sDAO.readStaffMembersCustomers(staff);
		assertEquals("cuscus", sDAO.readStaffMembersCustomers(staff)[0].getCustomerID());
		assertTrue(sDAO.deleteCustomer(staff, customer), "deleteCustomer(staff, customer): Failed to delete customer from staff member.");
		customers = sDAO.readStaffMembersCustomers(staff);
		assertEquals(0, customers.length, "readStaffMembersCustomer(staff): Failed to read staff members customes after deleting customer.");
	}

	
	@Test
	public void testReadAll() {
		assertTrue(sDAO.delete("stasta"), "delete(id): Failed to delete staff member.");
		Staff[] list = sDAO.readAll();
		assertEquals(0, list.length, "readAll(): Failed to read all staff members.");
	}

	
	@AfterAll
	public static void tearDown() {

		cDAO.delete("cuscus");
		sDAO.delete("stasta");
	}

}