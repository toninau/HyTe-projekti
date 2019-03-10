package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class IlmoitusTest {
	private Ilmoitus ilmoitus;
	private Henkilökunta henkilö;
	private Asiakas asiakas;

	@BeforeEach
	public void setResepti() {
		ilmoitus = new Ilmoitus();
		henkilö = new Henkilökunta();
		asiakas = new Asiakas();
	}

	@Test
	public void testTyhjäKonstruktori() {
		try {
			ilmoitus = new Ilmoitus();
		} catch (Exception e) {
			fail("Ilmoitus-olion luonti epäonnistui");
		}
	}

	@Test
	public void testKonstruktori() {
		asiakas = new Asiakas();
		henkilö = new Henkilökunta();
		try {
			ilmoitus = new Ilmoitus("12.12.1212", "testi-ilmoitus", asiakas, henkilö);
		} catch (Exception e) {
			fail("Ilmoitus-olion luonti epäonnistui");
		}
	}

	@Test
	public void testGetSetID() {
		ilmoitus.setIlmoitusID(12);
		assertEquals(12, ilmoitus.getIlmoitusID(), "ilmoitus id:n asetus epäonnistui");
	}

	@Test
	public void testGetSetLuettu() {
		ilmoitus.setLuettu(true);
		assertEquals(true, ilmoitus.isLuettu(), "ilmoitus luettu asetus epäonnistui");
	}

	@Test
	public void testGetSetPvm() {
		ilmoitus.setPvm("12.12.2000");
		assertEquals("12.12.2000", ilmoitus.getPvm(), "ilmoitus pvm asetus epäonnistui");
	}

	@Test
	public void testGetSetTeksti() {
		ilmoitus.setTeksti("testi");
		assertEquals("testi", ilmoitus.getTeksti(), "ilmoituksen tekstin asetus epäonnistui");
	}

	@Test
	public void testGetSetAsiakas() {
		asiakas.setAsiakasID(5);
		ilmoitus.setAsiakas(asiakas);
		assertEquals(5, ilmoitus.getAsiakas().getAsiakasID(), "ilmoituksen asiakkaan asetus epäonnistui");
	}

	@Test
	public void testGetSetHenkilökunta() {
		henkilö.setHenkilökuntaID(5);
		ilmoitus.setHenkilökunta(henkilö);
		assertEquals(5, ilmoitus.getHenkilökunta().getHenkilökuntaID(), "ilmoituksen henkilökunnan asetus epäonnistui");
	}
}
