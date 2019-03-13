package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class HenkilökuntaTest {
	private Henkilökunta jäsen;

	@BeforeEach
	public void setHenkilökunta() {
		jäsen = new Henkilökunta();
	}

	@Test
	public void testTyhjäKonstruktori() {
		try {
			jäsen = new Henkilökunta();
		} catch (Exception e) {
			fail("Henkilökunta-olion luonti epäonnistui");
		}
	}

	@Test
	public void testKonstruktori() {
		try {
			jäsen = new Henkilökunta("Testi", "Testinen", "1234567890", "testi@mail.com", "Lääkäri");
		} catch (Exception e) {
			fail("Henkilökunta-olion luonti epäonnistui");
		}
	}

	@Test
	public void testGetSetID() {
		jäsen.setHenkilökuntaID(23);
		assertEquals(23, jäsen.getHenkilökuntaID(), "henkilökunta id:n asetus epäonnistui");
	}

	@Test
	public void testGetSetEtunimi() {
		jäsen.setEtunimi("tohtori");
		assertEquals("tohtori", jäsen.getEtunimi(), "henkilökunta etunimi asetus epäonnistui");
	}

	@Test
	public void testGetSetSukunimi() {
		jäsen.setSukunimi("dr.tohtori");
		assertEquals("dr.tohtori", jäsen.getSukunimi(), "henkilökunta sukunimi asetus epäonnistui");
	}

	@Test
	public void testGetSetOikeus() {
		jäsen.setOikeus("Lääkäri");
		assertEquals("Lääkäri", jäsen.getOikeus(), "henkilökunta oikeus asetus epäonnistui");
	}

	@Test
	public void testGetSetPuhnumero() {
		jäsen.setPuhnumero("12341234");
		assertEquals("12341234", jäsen.getPuhnumero(), "henkilökunta puhnumero asetus epäonnistui");
	}

	@Test
	public void testGetSetSposti() {
		jäsen.setSposti("test@mail.com");
		assertEquals("test@mail.com", jäsen.getSposti(), "henkilökunta Sposti asetus epäonnistui");
	}
}
