package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class HenkilökuntaTest {
	private Staff jäsen;

	@BeforeEach
	public void setHenkilökunta() {
		jäsen = new Staff();
	}

	@Test
	public void testTyhjäKonstruktori() {
		try {
			jäsen = new Staff();
		} catch (Exception e) {
			fail("Staff-olion luonti epäonnistui");
		}
	}

	@Test
	public void testKonstruktori() {
		try {
			jäsen = new Staff("Testi", "Testinen", "1234567890", "testi@mail.com", "Lääkäri");
		} catch (Exception e) {
			fail("Staff-olion luonti epäonnistui");
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
		assertEquals("tohtori", jäsen.getFirstName(), "henkilökunta etunimi asetus epäonnistui");
	}

	@Test
	public void testGetSetSukunimi() {
		jäsen.setSukunimi("dr.tohtori");
		assertEquals("dr.tohtori", jäsen.getSurname(), "henkilökunta sukunimi asetus epäonnistui");
	}

	@Test
	public void testGetSetOikeus() {
		jäsen.setOikeus("Lääkäri");
		assertEquals("Lääkäri", jäsen.getAccessLevel(), "henkilökunta oikeus asetus epäonnistui");
	}

	@Test
	public void testGetSetPuhnumero() {
		jäsen.setPuhnumero("12341234");
		assertEquals("12341234", jäsen.getPhoneNumber(), "henkilökunta puhnumero asetus epäonnistui");
	}

	@Test
	public void testGetSetSposti() {
		jäsen.setSposti("test@mail.com");
		assertEquals("test@mail.com", jäsen.getEmail(), "henkilökunta Sposti asetus epäonnistui");
	}
}
