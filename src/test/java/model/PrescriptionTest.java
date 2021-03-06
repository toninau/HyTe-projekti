package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class PrescriptionTest {
	private Prescription prescription;
	private Staff staffmember;
	private Customer customer;
	
	@BeforeEach
	public void setPrescription() {
		prescription = new Prescription();
		staffmember = new Staff();
		customer = new Customer();
	}
	
	@Test
	public void testEmptyConstructor() {
		try {
			prescription = new Prescription();
		} catch (Exception e) {
			fail("Failed to create prescription-object");
		}
	}
	
	@Test
	public void testConstructor() {
		customer = new Customer();
		staffmember = new Staff();
		try {
			prescription = new Prescription("12.12.2000", "12.12.2001", "testi", "testi-ohje", "aika", "2x400mg", false ,customer, staffmember);
		} catch (Exception e) {
			fail("Failed to create prescription-object");
		}
	}
	
	@Test
	public void testGetSetID() {
		prescription.setPrescriptionID(12);
		assertEquals(12, prescription.getPrescriptionID(), "Failed to set and get prescriptionID");
	}
	
	@Test
	public void testGetSetStartDate() {
		prescription.setStartDate("12.12.2012");
		assertEquals(LocalDate.of(2012, 12, 12), prescription.getStartDate(), "Failed to set and get prescription's start date");
	}
	
	@Test
	public void testGetSetEndDate() {
		prescription.setEndDate("20.12.2020");
		assertEquals(LocalDate.of(2020, 12, 20), prescription.getEndDate(), "Failed to set and get prescription's end date");
	}
	
	@Test
	public void testGetSetName() {
		prescription.setPrescriptionName("testi");
		assertEquals("testi", prescription.getPrescriptionName(), "Failed to set and get prescription's name");
	}
	
	@Test
	public void testGetSetOhje() {
		prescription.setPrescriptionGuide("testi-ohje");
		assertEquals("testi-ohje", prescription.getPrescriptionGuide(), "Failed to set and get prescription's guide");
	}
	
	@Test
	public void testGetSetAsiakas() {
		customer.setCustomerID("testi@mail.com");
		prescription.setCustomer(customer);
		assertEquals("testi@mail.com", prescription.getCustomer().getCustomerID(), "Failed to set and get prescription's customer");
	}
	
	@Test
	public void testGetSetHenkilökunta() {
		staffmember.setStaffID("testi@mail.com");
		prescription.setStaff(staffmember);
		assertEquals("testi@mail.com", prescription.getStaff().getStaffID(), "Failed to set and get prescription's staff member");
	}

	@Test
	public void testToStringAllInfo() {
		testConstructor();
		staffmember.setFirstName("Mahti");
		staffmember.setSurname("Ahtisaari");
		staffmember.setAccessLevel("Doctor");

		assertEquals("12.12.2000 - 12.12.2001 testi-ohje. Doctor Ahtisaari, Mahti", prescription.toStringAllInfo(), "Prescription toString-method is not working correctly");
	}

}
