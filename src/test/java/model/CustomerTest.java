package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CustomerTest {
	private Customer customer;

	@BeforeEach
	public void setCustomer() {
		customer = new Customer();
	}

	@Test
	public void testEmptyConstructor() {
		try {
			customer = new Customer();
		} catch (Exception e) {
			fail("Failed to create customer-object");
		}
	}

	@Test
	public void testConstructor() {
		try {
			customer = new Customer("Testi", "Testinen", "111111-111A", "Testitie 3", "testi@mail.com", "1234567890",
					"0987654321", "password");
		} catch (Exception e) {
			fail("Failed to create customer-object");
		}
	}

	@Test
	public void testGetSetID() {
		customer.setCustomerID("testi@mail.com");
		assertEquals("testi@mail.com", customer.getCustomerID(), "Failed to set and get customerID");
	}

	@Test
	public void testGetSetFirstName() {
		customer.setFirstName("TestName");
		assertEquals("TestName", customer.getFirstName(), "Failed to set and get customer's first name");
	}

	@Test
	public void testGetSetSukunimi() {
		customer.setSurname("NameTest");
		assertEquals("NameTest", customer.getSurname(), "Failed to set and get customer's surname");
	}

	@Test
	public void testGetSetHetu() {
		customer.setSSN("098765-1234");
		assertEquals("098765-1234", customer.getSSN(), "Failed to set and get customer's social security number");
	}

	@Test
	public void testGetSetIcenumero() {
		customer.setIceNumber("123123123");
		assertEquals("123123123", customer.getIceNumber(), "Failed to set and get customer's ICE number");
	}

	@Test
	public void testGetSetKotiosoite() {
		customer.setAddress("testikuja 10");
		assertEquals("testikuja 10", customer.getAddress(), "Failed to set and get customer's home address");
	}

	@Test
	public void testGetSetPuhnumero() {
		customer.setPhoneNumber("12341234");
		assertEquals("12341234", customer.getPhoneNumber(), "Failed to set and get customer's phone number");
	}

}
