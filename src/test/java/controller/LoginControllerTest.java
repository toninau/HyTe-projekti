package controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.lambdaworks.crypto.SCryptUtil;

import view.LoginView;
import controller.LoginController;
import model.Customer;
import model.DAOManager;
import model.DAOManagerIF;
import model.HibernateUtil;
import model.Staff;

public class LoginControllerTest {
	
	static LoginController loginController;
	private static DAOManagerIF daom;
	private static Customer customer;
	private static Staff staff;
	
	@BeforeAll
	public static void setUp() {
		SessionFactory istuntotehdas = HibernateUtil.getSessionFactory(true);

		daom = new DAOManager();
		LoginView view = new LoginView();
		loginController = new LoginController(view);
		String pw1 = SCryptUtil.scrypt("passwordJohn", 16, 16, 16);
		String pw2 = SCryptUtil.scrypt("passwordJane", 16, 16, 16);
		customer = new Customer("John", "Smith", "12345", "Homestead 5", "040124567", "0501234567", pw1);
		daom.getCustomerDAO().create(customer);
		staff = new Staff("Jane", "Doe", "0401234568", "Doctor", pw2);
		daom.getStaffDAO().create(staff);
	}
	
	@AfterAll
	public static void tearDown() {
		daom.getCustomerDAO().delete("johsmi");
		daom.getStaffDAO().delete("jandoe");
	}
	
	@Test
	public void checkUsernameTest() {
		assertTrue(loginController.checkUsername("jandoe", staff.getStaffID()));
	}
	
	@Test
	public void checkPasswordTest() {
		assertTrue(loginController.checkPassword("passwordJohn", customer.getPassword()));
	}
	
	@Test
	public void checkLoginCustomerTest() {
		assertTrue(loginController.checkLoginCustomer("johsmi", "passwordJohn"));
	}
	
	@Test
	public void checkLoginStaffTest() {
		assertTrue(loginController.checkLoginStaff("jandoe", "passwordJane"));
	}
	
	@Test
	public void getStaffFromDatabaseTest() {
		assertTrue(loginController.getStaffFromDatabase("jandoe").getFirstName().equals(staff.getFirstName()));
	}
	
	@Test
	public void getCustomerFromDatabaseTest() {
		assertTrue(loginController.getCustomerFromDatabase("johsmi").getFirstName().equals(customer.getFirstName()));
	}
	
	@Test
	public void testConstructor() {
		try {
			LoginView loginView = new LoginView();
			loginController = new LoginController(loginView);
		} catch (Exception e) {
			fail("Failed to create loginview");
		}
	}
	

}
