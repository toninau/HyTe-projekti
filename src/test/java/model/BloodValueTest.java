package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BloodValueTest {
	private BloodValue bloodValue;
	private Customer customer;

	@BeforeEach
	public void setBloodvalue() {
		bloodValue = new BloodValue();
		customer = new Customer();
	}

	@Test
	public void testEmptyConstructor() {
		try {
			bloodValue = new BloodValue();
		} catch (Exception e) {
			fail("Failed to create bloodValue-object");
		}
	}

	@Test
	public void testConstructorAll() {
		try {
			bloodValue = new BloodValue("12.12.2000", "12:12", 6.0, "120/120/120");
		} catch (Exception e) {
			fail("Failed to create bloodValue-object");
		}
	}

	@Test
	public void testConstructorPressure() {
		try {
			bloodValue = new BloodValue("12.12.2000", "12:12", "120/120/100");
		} catch (Exception e) {
			fail("Failed to create bloodValue-object");
		}
	}

	@Test
	public void testConstructorSugar() {
		try {
			bloodValue = new BloodValue("12.12.2000", "12:12", 6.0);
		} catch (Exception e) {
			fail("Failed to create bloodValue-object");
		}
	}

	@Test
	public void testGetSetID() {
		bloodValue.setBloodvalueID(2);
		assertEquals(2, bloodValue.getBloodvalueID(), "Failed to set and get bloodvalueID");
	}

	@Test
	public void testGetSetTime() {
		bloodValue.setTime("12:12");
		assertEquals("12:12", bloodValue.getTime(), "Failed to set and get bloodvalue time");
	}

	@Test
	public void testGetSetDate() {
		bloodValue.setDate("12.12.2000");
		assertEquals("12.12.2000", bloodValue.getDate(), "Failed to set and get bloodvalue date");
	}

	@Test
	public void testGetSetPressure() {
		bloodValue.setBloodpressure("100/100/100");
		assertEquals("100/100/100", bloodValue.getBloodpressure(), "Failed to set and get bloodvalue pressure");
	}

	@Test
	public void testGetSetSugar() {
		bloodValue.setBloodsugar(5.5);
		assertEquals(5.5, bloodValue.getBloodsugar(), "Failed to set and get bloodvalue sugar");
	}

	@Test
	public void testGetSetCustomer() {
		customer.setCustomerID("testi@mail.com");
		bloodValue.setCustomer(customer);
		assertEquals("testi@mail.com", bloodValue.getCustomer().getCustomerID(), "Failed to set and get bloodvalue customer");
	}
}
