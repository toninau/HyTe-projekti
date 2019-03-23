package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StaffTest {
	private Staff staffmember;

	@BeforeEach
	public void setStaff() {
		staffmember = new Staff();
	}

	@Test
	public void testEmptyConstructor() {
		try {
			staffmember = new Staff();
		} catch (Exception e) {
			fail("Failed to create staff-object");
		}
	}

	@Test
	public void testConstructor() {
		try {
			staffmember = new Staff("Testi", "Testinen", "1234567890", "testi@mail.com", "Lääkäri");
		} catch (Exception e) {
			fail("Failed to create staff-object");
		}
	}

	@Test
	public void testGetSetID() {
		staffmember.setStaffID(23);
		assertEquals(23, staffmember.getStaffID(), "Failed to set and get staffID");
	}

	@Test
	public void testGetSetFirstName() {
		staffmember.setFirstName("tohtori");
		assertEquals("tohtori", staffmember.getFirstName(), "Failed to set and get staff member's first name");
	}

	@Test
	public void testGetSetSurname() {
		staffmember.setSurname("dr.tohtori");
		assertEquals("dr.tohtori", staffmember.getSurname(), "Failed to set and get staff member's surname");
	}

	@Test
	public void testGetSetOikeus() {
		staffmember.setAccessLevel("Lääkäri");
		assertEquals("Lääkäri", staffmember.getAccessLevel(), "Failed to set and get staff member's access level");
	}

	@Test
	public void testGetSetPuhnumero() {
		staffmember.setPhoneNumber("12341234");
		assertEquals("12341234", staffmember.getPhoneNumber(), "Failed to set and get staff member's phone number");
	}

	@Test
	public void testGetSetSposti() {
		staffmember.setEmail("test@mail.com");
		assertEquals("test@mail.com", staffmember.getEmail(), "Failed to set and get staff member's email");
	}
}
