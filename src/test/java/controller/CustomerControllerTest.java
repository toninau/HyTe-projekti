package controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.Appointment;
import model.Customer;
import model.DAOManager;
import model.HibernateUtil;
import model.Prescription;
import model.Staff;
import view.customer.CustomerCalendarView;
import view.customer.CustomerHealthView;
import view.customer.CustomerHelpView;
import view.customer.CustomerHomeView;

public class CustomerControllerTest {

	private static CustomerController customerController;
	private CustomerHomeView customerHomeView;
	private CustomerHealthView customerHealthView;
	private CustomerHelpView customerHelpView;
	private CustomerCalendarView customerCalendarView;
	private static Customer customer;
	private static DAOManager daom;
	private static Staff staff;

	/**
	 * Set up before tests. Creates a test customer and a staff member. Sets the created customer
	 * as logged in.
	 */
	@BeforeAll
	public static void setUp() {
		SessionFactory istuntotehdas = HibernateUtil.getSessionFactory(true);
		daom = new DAOManager();
		customerController = new CustomerController();
		customer = new Customer("Jane", "Doe", "112405-1222", "Homestreet 1", "0502145523", "020202", "password");
		daom.getCustomerDAO().create(customer);

		staff = new Staff("Mark", "Manson", "0401234568", "Doctor", "passowrd");
		daom.getStaffDAO().create(staff);

		customerController.loggedCustomer(customer);
	}

	/**
	 * Deletes all the objects used in testing.
	 */
	@AfterAll
	public static void tearDown() {
		daom.getCustomerDAO().delete("jandoe");
		daom.getStaffDAO().delete("marman");
		daom.getPrescriptionDAO().delete(1);
		daom.getAppointmentDAO().delete(1);
		daom.getUserImageDAO().delete(1);
		assertTrue(daom.getAppointmentDAO().readCustomerAppointments(customer).length == 0);
	}
	
	/**
	 * Tests putting an image to database and retrieving it.
	 */
	@Test
	public void imageDatabaseTest() {
		File file = new File(getClass().getResource("/pictures/finland_flag.png").getFile());
		customerController.imageToDatabase(file, 1);
		assertTrue(daom.getUserImageDAO().readCustomerUserImages(customer)[0].getImageName().equals("jandoe1"));
	}

	/**
	 * Tests getting all the logged customer's appointments.
	 */
	/*@Test
	public void customersAppointmentsTest() {
		Appointment a = new Appointment("24.04.2019", "10.00", "lääkäriaika", customer, staff);
		daom.getAppointmentDAO().create(a);
		assertTrue(customerController.customersAppointments()[0].getDate().equals("24.04.2019"));
	}*/

	/**
	 * Tests getting all the logged customer's prescriptions.
	 */
	@Test
	public void prescriptionsTest() {
		Prescription p = new Prescription("24.04.2019", "24.04.2020", "Burana", "Tarvittaessa kipuun", "tarvittaessa",
				"2x400mg", false, customer, staff);
		daom.getPrescriptionDAO().create(p);
		assertTrue(customerController.prescriptions()[0].getPrescriptionName().equals("Burana"));
	}

	/**
	 * Tests returning the current customer logged in.
	 */
	@Test
	public void getCustomerTest() {
		assertTrue(customerController.getLoggedCustomer().equals(customer));
	}
	
	@Test
	public void bloodValueDataTest() {

	}

	/**
	 * Tests the controller's constructor for customer home view.
	 */
	@Test
	public void customerHomeViewConstructorTest() {
		try {
			customerHomeView = new CustomerHomeView();
			customerController = new CustomerController(customerHomeView);
		} catch (Exception e) {
			fail("Failed to create customer home view");
		}
	}

	/**
	 * Tests the controller's constructor for customer health view.
	 */
	@Test
	public void customerHealthViewConstructorTest() {
		try {
			customerHealthView = new CustomerHealthView();
			customerController = new CustomerController(customerHealthView);
		} catch (Exception e) {
			fail("Failed to create customer health view");
		}
	}

	/**
	 * Tests the controller's constructor for customer help view.
	 */
	@Test
	public void customerHelpViewConstructorTest() {
		try {
			customerHelpView = new CustomerHelpView();
			customerController = new CustomerController(customerHelpView);
		} catch (Exception e) {
			fail("Failed to create customer help view");
		}
	}

	/**
	 * Tests the controller's constructor for customer calendar view.
	 */
	@Test
	public void customerCalendarViewConstructorTest() {
		try {
			customerCalendarView = new CustomerCalendarView();
			customerController = new CustomerController(customerCalendarView);
		} catch (Exception e) {
			fail("Failed to create customer help view");
		}
	}

}
