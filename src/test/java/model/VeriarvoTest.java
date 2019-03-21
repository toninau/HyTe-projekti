package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class VeriarvoTest {
	private BloodValue bloodValue;
	private Customer customer;

	@BeforeEach
	public void setVeriarvo() {
		bloodValue = new BloodValue();
		customer = new Customer();
	}

	@Test
	public void testTyhjäKonstruktori() {
		try {
			bloodValue = new BloodValue();
		} catch (Exception e) {
			fail("BloodValue-olion luonti epäonnistui");
		}
	}

	@Test
	public void testKonstruktoriKaikki() {
		try {
			bloodValue = new BloodValue("12.12.2000", "12:12", 6.0, "120/120/120");
		} catch (Exception e) {
			fail("BloodValue-olion luonti epäonnistui");
		}
	}

	@Test
	public void testKonstruktoriVerenpaine() {
		try {
			bloodValue = new BloodValue("12.12.2000", "12:12", "120/120/100");
		} catch (Exception e) {
			fail("BloodValue-olion luonti epäonnistui");
		}
	}

	@Test
	public void testKonstruktoriVerensokeri() {
		try {
			bloodValue = new BloodValue("12.12.2000", "12:12", 6.0);
		} catch (Exception e) {
			fail("BloodValue-olion luonti epäonnistui");
		}
	}

	@Test
	public void testGetSetID() {
		bloodValue.setVeriarvoID(2);
		assertEquals(2, bloodValue.getVeriarvoID(), "bloodValue id:n asetus epäonnistui");
	}

	@Test
	public void testGetSetAika() {
		bloodValue.setAika("12:12");
		assertEquals("12:12", bloodValue.getAika(), "veriarvon ajan asetus epäonnistui");
	}

	@Test
	public void testGetSetPvm() {
		bloodValue.setPvm("12.12.2000");
		assertEquals("12.12.2000", bloodValue.getDate(), "veriarvon pvm asetus epäonnistui");
	}

	@Test
	public void testGetSetVerenpaine() {
		bloodValue.setVerenpaine("100/100/100");
		assertEquals("100/100/100", bloodValue.getVerenpaine(), "veriarvon verenpaineen asetus epäonnistui");
	}

	@Test
	public void testGetSetVerensokeri() {
		bloodValue.setVerensokeri(5.5);
		assertEquals(5.5, bloodValue.getVerensokeri(), "veriarvon verensokerin asetus epäonnistui");
	}

	@Test
	public void testGetSetAsiakas() {
		customer.setAsiakasID(3);
		bloodValue.setAsiakas(customer);
		assertEquals(3, bloodValue.getAsiakas().getAsiakasID(), "veriarvon asiakkaan asetus epäonnistui");
	}
}
