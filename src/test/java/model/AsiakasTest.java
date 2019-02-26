package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AsiakasTest {
	private Asiakas asiakas;
	
	@BeforeEach
	public void setAsiakas() {
		asiakas = new Asiakas();
	}
	
	@Test
	public void testTyhjäKonstruktori() {
		try {
			 asiakas = new Asiakas();
		} catch (Exception e) {
			fail("Asiakas-olion luonti epäonnistui");
		}
	}
	
	@Test
	public void testGetSetID() {
		asiakas.setAsiakasID(12);
		assertEquals(12, asiakas.getAsiakasID(), "Asiakkaan id:n asetus epäonnistui");
	}
	
	@Test
	public void testGetSetEtunimi() {
		asiakas.setEtunimi("TestName");
		assertEquals("TestName", asiakas.getEtunimi(), "Asiakkaan etunimen asetus epäonnistui");
	}
	
	@Test
	public void testGetSetSukunimi() {
		asiakas.setSukunimi("NameTest");
		assertEquals("NameTest", asiakas.getSukunimi(), "Asiakkaan sukunimen asetus epäonnistui");
	}
	
	@Test
	public void testGetSetHetu() {
		asiakas.setHetu("098765-1234");
		assertEquals("098765-1234", asiakas.getHetu(), "Asiakkaan HeTu:n asetus epäonnistui");
	}
	
	@Test
	public void testGetSetIcenumero() {
		asiakas.setIcenumero("123123123");
		assertEquals("123123123", asiakas.getIcenumero(), "Asiakkaan Icenumeron asetus epäonnistui");
	}
	
	@Test
	public void testGetSetKotiosoite() {
		asiakas.setKotiosoite("testikuja 10");
		assertEquals("testikuja 10", asiakas.getKotiosoite(), "Asiakkaan kotiosoitteen asetus epäonnistui");
	}
	
	@Test
	public void testGetSetPuhnumero() {
		asiakas.setPuhnumero("12341234");
		assertEquals("12341234", asiakas.getPuhnumero(), "Asiakkaan puhnumeron asetus epäonnistui");
	}
	
	@Test
	public void testGetSetSposti() {
		asiakas.setSposti("test@mail.com");
		assertEquals("test@mail.com", asiakas.getSposti(), "Asiakkaan Spostin asetus epäonnistui");
	}
}
