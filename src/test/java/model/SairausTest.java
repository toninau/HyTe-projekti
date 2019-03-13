package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SairausTest {
	private Sairaus sairaus;
	private Asiakas asiakas;

	@BeforeEach
	public void setSairaus() {
		sairaus = new Sairaus();
		asiakas = new Asiakas();
	}

	@Test
	public void testTyhjäKonstruktori() {
		try {
			sairaus = new Sairaus();
		} catch (Exception e) {
			fail("Sairaus-olion luonti epäonnistui");
		}
	}

	@Test
	public void testKonstruktori() {
		try {
			sairaus = new Sairaus("testisairaus", asiakas);
		} catch (Exception e) {
			fail("Sairaus-olion luonti epäonnistui");
		}
	}

	@Test
	public void testGetSetID() {
		sairaus.setSairausID(6);
		assertEquals(6, sairaus.getSairausID(), "sairaus id:n asetus epäonnistui");
	}

	@Test
	public void testGetSetNimi() {
		sairaus.setSairausNimi("testi");
		assertEquals("testi", sairaus.getSairausNimi(), "sairaus nimi asetus epäonnistui");
	}

	@Test
	public void testGetSetAsiakas() {
		asiakas.setAsiakasID(5);
		sairaus.setAsiakas(asiakas);
		assertEquals(5, sairaus.getAsiakas().getAsiakasID(), "sairauksen asiakkaan asetus epäonnistui");
	}
}
