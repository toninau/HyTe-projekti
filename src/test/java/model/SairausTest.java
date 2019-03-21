package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SairausTest {
	private Illness illness;
	private Customer customer;

	@BeforeEach
	public void setSairaus() {
		illness = new Illness();
		customer = new Customer();
	}

	@Test
	public void testTyhjäKonstruktori() {
		try {
			illness = new Illness();
		} catch (Exception e) {
			fail("Illness-olion luonti epäonnistui");
		}
	}

	@Test
	public void testKonstruktori() {
		try {
			illness = new Illness("testisairaus", customer);
		} catch (Exception e) {
			fail("Illness-olion luonti epäonnistui");
		}
	}

	@Test
	public void testGetSetID() {
		illness.setSairausID(6);
		assertEquals(6, illness.getSairausID(), "illness id:n asetus epäonnistui");
	}

	@Test
	public void testGetSetNimi() {
		illness.setSairausNimi("testi");
		assertEquals("testi", illness.getSairausNimi(), "illness nimi asetus epäonnistui");
	}

	@Test
	public void testGetSetAsiakas() {
		customer.setAsiakasID(5);
		illness.setAsiakas(customer);
		assertEquals(5, illness.getAsiakas().getAsiakasID(), "sairauksen asiakkaan asetus epäonnistui");
	}
}
