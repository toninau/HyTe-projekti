package controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.lambdaworks.crypto.SCryptUtil;

import model.Customer;
import model.DAOManager;
import model.DAOManager_IF;
import model.HibernateUtil;
import model.Staff;
import view.admin.*;

public class AdminControllerTest {
	
	private static AddCustomerView addCustomerView;
	private static AdminController adminController;
	private AddStaffView addStaffView;
	private EditStaffView editStaffView;
	private EditCustomerView editCustomerView;
	private static DAOManager_IF daom;
	private static Customer customer;
	private static Staff staff;
	
	@BeforeAll
	public static void setUp() {
		SessionFactory istuntotehdas = HibernateUtil.getSessionFactory(true);

		adminController = new AdminController();
		daom = new DAOManager();
		customer = new Customer("John", "Smith", "12345", "Homestead 5", "040124567", "0501234567", "password");
		daom.getCustomerDAO().create(customer);
		staff = new Staff("Jane", "Doe", "0401234568", "Doctor", "passowrd");
		daom.getStaffDAO().create(staff);
	}
	
	@AfterAll
	public static void tearDown() {
		daom.getCustomerDAO().delete("johsmi");
		daom.getCustomerDAO().delete("marsmi");
		daom.getStaffDAO().delete("jandoe");
		daom.getStaffDAO().delete("marman");
		daom.getStaffDAO().delete("marman2");
		daom.getCustomerDAO().delete("t@testi.com");
	}
	
	
	@Test
	public void updateCustomerTest() {
		assertTrue(customer.getSurname().equals("Smith"));
		customer.setSurname("Smath");
		adminController.updateCustomer(customer);
		assertTrue(customer.getSurname().equals("Smath"));
	}
	
	@Test
	public void updateStaffTest() {
		assertTrue(staff.getSurname().equals("Doe"));
		staff.setSurname("Doa");
		adminController.updateStaff(staff);
		assertTrue(staff.getSurname().equals("Doa"));
	}
	
	/**
	 * Tests the names of the customers in database
	 */
	@Test
	public void findCustomerAllTest() {
		Customer newCustomer = new Customer("Mary", "Smith", "12345", "Homestead 5", "040124567", "0501234567", "password");
		daom.getCustomerDAO().create(newCustomer);
		assertTrue(adminController.findCustomerAll()[0].getFirstName().equals("John"));
		assertTrue(adminController.findCustomerAll()[1].getFirstName().equals("Mary"));
	}
	
	/**
	 * Tests the amount of customers in test database.
	 * Should be 1 and 2 because the customers are deleted from test database after tests.
	 */
	/*@Test
	public void findCustomerAllLengthTest() {
		assertTrue(adminController.findCustomerAll().length == 1);
		Customer newCustomer = new Customer("Mary", "Smith", "12345", "Homestead 5", "m@cust.com", "040124567", "0501234567", "password");
		daom.getCustomerDAO().create(newCustomer);
		assertTrue(adminController.findCustomerAll().length == 2);
		
	}*/
	
	/**
	 * Tests the names of the employees in database
	 */
	@Test
	public void findStaffAllTest() {
		Staff newStaff = new Staff("Mark", "Manson", "0401234568", "Nurse", "passowrd");
		daom.getStaffDAO().create(newStaff);
		assertTrue(adminController.findStaffAll()[0].getFirstName().equals("Jane"));
		assertTrue(adminController.findStaffAll()[1].getFirstName().equals("Mark"));
	}
	
	/**
	 * Tests the amount of customers in test database.
	 * Should be 1 and 2 because the customers are deleted from test database after tests.
	 */
	@Test
	public void findStaffAllLengthTest() {
		assertTrue(adminController.findStaffAll().length == 1);
		Staff newStaff = new Staff("Mark", "Manson", "0401234568", "Nurse", "passowrd");
		daom.getStaffDAO().create(newStaff);
		assertTrue(adminController.findStaffAll().length == 2);
	}
	
	/**
	 * Tests finding a customer with specified id in database.
	 */
	@Test
	public void findCustomerWithIDTest() {
		assertTrue(adminController.findCustomerWithID("johsmi").getFirstName().equals("John"));
	}
	
	/**
	 * Tests finding an employee with specified id in database.
	 */
	@Test
	public void findStaffWithIDTest() {
		assertTrue(adminController.findStaffWithID("jandoe").getFirstName().equals("Jane"));
	}
	
	@Test
	public void passwordEncryptionTest() {
		String hashed = adminController.encryptPassword("password");
		assertTrue(SCryptUtil.check("password", hashed));
	}
	
	@Test
	public void testEditCustomerConstructor() {
		try {
			 editCustomerView = new EditCustomerView();
			 adminController = new AdminController(editCustomerView);
		} catch (Exception e) {
			fail("Failed to create appointment-object");
		}
	}
	
	@Test
	public void testAddCustomerConstructor() {
		try {
			 addCustomerView = new AddCustomerView();
			 adminController = new AdminController(addCustomerView);
		} catch (Exception e) {
			fail("Failed to create appointment-object");
		}
	}
	
	@Test
	public void testEditStaffConstructor() {
		try {
			 editStaffView = new EditStaffView();
			 adminController = new AdminController(editStaffView);
		} catch (Exception e) {
			fail("Failed to create appointment-object");
		}
	}
	@Test
	public void testAddStaffConstructor() {
		try {
			 addStaffView = new AddStaffView();
			 adminController = new AdminController(addStaffView);
		} catch (Exception e) {
			fail("Failed to create add staff view");
		}
	}
	
	

	

}
