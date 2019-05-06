package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AppointmentTest {
	private Appointment appointment;
	private Staff staffmember;
	private Customer customer;
	
	/**
	 * Creates appointment, staff and customer.
	 */
	@BeforeEach
	public void setAppointment() {
		appointment = new Appointment();
		staffmember = new Staff();
		customer = new Customer();
	}

	@Test
	public void testEmptyConstructor() {
		try {
			appointment = new Appointment();
		} catch (Exception e) {
			fail("Failed to create appointment-object");
		}
	}

	@Test
	public void testConstructor() {
		customer = new Customer();
		staffmember = new Staff();
		try {
			appointment = new Appointment("12.12.2000", "12:20", "testi", customer, staffmember);
		} catch (Exception e) {
			fail("Failed to create appointment-object");
		}
	}

	/**
	 * Tests the get and set methods.
	 */
	@Test
	public void testGetSetID() {
		appointment.setAppointmentID(1);
		assertEquals(1, appointment.getAppointmentID(), "Failed to set and get appointmentID");
	}

	@Test
	public void testGetSetDate() {
		appointment.setDate("12.12.2020");
		assertEquals(LocalDate.of(2020, 12, 12), appointment.getDate(), "Failed to set and get appointment date");
	}

	@Test
	public void testGetSetTime() {
		appointment.setTime("12.12");
		assertEquals(LocalTime.of(12, 12), appointment.getTime(), "Failed to set and get appointment time");
	}

	@Test
	public void testGetSetInfo() {
		appointment.setInfo("testi");
		assertEquals("testi", appointment.getInfo(), "Failed to set and get appointment info");
	}

	@Test
	public void testGetSetCustomer() {
		customer.setCustomerID("testi@mail.com");
		appointment.setCustomer(customer);
		assertEquals("testi@mail.com", appointment.getCustomer().getCustomerID(), "Failed to set and get appointment customer");
	}

	@Test
	public void testGetSetHenkilökunta() {
		staffmember.setStaffID("testi@mail.com");
		appointment.setStaff(staffmember);
		assertEquals("testi@mail.com", appointment.getStaff().getStaffID(), "Failed to set and get appointment staff");
	}

	@Test
	public void testToStringCustomer()  {
		staffmember.setFirstName("mahti");
		staffmember.setSurname("ahtisaari");
		staffmember.setAccessLevel("doctor");
		Appointment appointment = new Appointment("15.04.1995", "13:00", "appointment info", customer, staffmember);
		assertEquals("13:00  appointment info. doctor ahtisaari, mahti", appointment.toStringCustomer(), "Appointment toStringCustomer-method is not right");

	}

	@Test
	public void testToStringStaff() {
		customer.setSurname("Jokunen");
		customer.setFirstName("Jaska");
		Appointment appointment = new Appointment("15.04.1995", "13:00", "appointment info", customer, staffmember);
		assertEquals("13:00  15.04.1995 Jokunen Jaska appointment info", appointment.toStringStaff(), "Appointment toStringStaff-method is not right");

	}
}
