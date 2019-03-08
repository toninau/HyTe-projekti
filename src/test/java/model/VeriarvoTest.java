package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class VeriarvoTest {
	private Veriarvo veriarvo;
	private Asiakas asiakas;
	
	@BeforeEach
	public void setVeriarvo() {
		veriarvo = new Veriarvo();
		asiakas = new Asiakas();
	}
	
	@Test
	public void testTyhjäKonstruktori() {
		try {
			 veriarvo = new Veriarvo();
		} catch (Exception e) {
			fail("Veriarvo-olion luonti epäonnistui");
		}
	}
	
	@Test
	public void testGetSetID() {
		veriarvo.setVeriarvoID(2);
		assertEquals(2, veriarvo.getVeriarvoID(), "veriarvo id:n asetus epäonnistui");
	}
	
	@Test
	public void testGetSetAika() {
		veriarvo.setAika("12:12");
		assertEquals("12:12", veriarvo.getAika(), "veriarvon ajan asetus epäonnistui");
	}
	
	@Test
	public void testGetSetPvm() {
		veriarvo.setPvm("12.12.2000");
		assertEquals("12.12.2000", veriarvo.getPvm(), "veriarvon pvm asetus epäonnistui");
	}
	
	@Test
	public void testGetSetVerenpaine() {
		veriarvo.setVerenpaine("100/100/100");
		assertEquals("100/100/100", veriarvo.getVerenpaine(), "veriarvon verenpaineen asetus epäonnistui");
	}
	
	@Test
	public void testGetSetVerensokeri() {
		veriarvo.setVerensokeri(5.5);
		assertEquals(5.5, veriarvo.getVerensokeri(), "veriarvon verensokerin asetus epäonnistui");
	}
	
	@Test
	public void testGetSetAsiakas() {
		asiakas.setAsiakasID(3);
		veriarvo.setAsiakas(asiakas);
		assertEquals(3, veriarvo.getAsiakas().getAsiakasID(), "veriarvon asiakkaan asetus epäonnistui");
	}
}
