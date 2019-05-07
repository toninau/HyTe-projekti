package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.HibernateUtil;
import model.Appointment;
import model.Staff;

public class AppointmentDAOTest {
	private Customer customer;
	private Staff staff;
	private Appointment appointment;
	private static CustomerDAO cDAO;
	private static StaffDAO sDAO;
	private static AppointmentDAO aDAO;
	private static SessionFactory sf;
	@BeforeEach
	public void setTest() {
		sf = HibernateUtil.getSessionFactory(true);
		cDAO = new CustomerDAO(sf);
		sDAO = new StaffDAO(sf);
		aDAO = new AppointmentDAO(sf);
	}
	
	@AfterAll
	public static void tearDown() {
		cDAO.delete("firsur");
		sDAO.delete("firsur");
		aDAO.delete(1);		
	}
	
	
	@Test
	public void testDAOMethdods() {
		//Create customer
		customer = createCustomer();
		//Create staff member
		staff = createStaff();
		//Create Appointment
		appointment = new Appointment();
		appointment.setCustomer(customer);
		appointment.setStaff(staff);
		appointment.setDate("12.12.2020");
		appointment.setInfo("Info");
		appointment.setTime("12.12");
		assertTrue(aDAO.create(appointment), "create(appointment): Failed to create appointment.");
		//Read appointment
		appointment = aDAO.read(1);
		assertEquals("Firstame", appointment.getCustomer().getFirstName(), "read(id): Failed to read appointment customer");
		assertEquals("Firstname", appointment.getStaff().getFirstName(), "read(id): Failed to read appointment staff member");
		assertEquals(LocalDate.of(2020, 12, 12), appointment.getDate(), "read(id): Failed to read appointment date");
		assertEquals("Info", appointment.getInfo(), "read(id): Failed to read appointment info");
		assertEquals(LocalTime.of(12, 12), appointment.getTime(), "read(id): Failed to read appointment time");

		//Update appointment
		//appointment.setDate("Update1");
		appointment.setInfo("Update2");
		//appointment.setTime("Update3");
		assertTrue(aDAO.update(appointment), "update(appointment): Failed to update appointment.");
		appointment = aDAO.read(1);
		//assertEquals("Update1", appointment.getDate(), "update(): Failed to update date.");
		assertEquals("Update2", appointment.getInfo(), "update(): Failed to update info.");
		//assertEquals("Update3", appointment.getTime(), "update(): Failed to update time.");
		// read customers appointments
		Appointment[] appointments = aDAO.readCustomerAppointments(customer);
		assertEquals(1, appointments.length, "readCustomerAppointments(customer): Failed to read customer's appointments");
		appointments = aDAO.readStaffAppointments(staff);
		assertEquals(1, appointments.length, "readStaffAppointments(staff): Failed to read staff member's appointments");
		//Delete appointment
		assertTrue(aDAO.delete(1), "delete(id): Failed to delete appointment.");
		appointments = aDAO.readCustomerAppointments(customer);
		assertEquals(0, appointments.length, "readCustomerAppointments(customer): Failed to read customer's appointments after deletion");
		appointments = aDAO.readStaffAppointments(staff);
		assertEquals(0, appointments.length, "readStaffAppointments(staff): Failed to read staff member's appointments after deletion");
		//Delete staff member and customer
		cDAO.delete("firsur");
		sDAO.delete("firsur");
	}
	
	private Customer createCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("Firstname");
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
	
	private Staff createStaff() {
		Staff staff = new Staff();
		staff.setFirstName("FirstName");
		staff.setSurname("Surname");
		staff.setPhoneNumber("PhoneNumber");
		staff.setPassword("Password");
		staff.setAccessLevel("AccessLevel");
		sDAO.create(staff);
		staff = sDAO.read("firsur");
		return staff;
	}
}
