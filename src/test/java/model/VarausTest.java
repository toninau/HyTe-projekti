package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class VarausTest {
	private Varaus varaus;
	private Henkilökunta henkilö;
	private Asiakas asiakas;
	
	@BeforeEach
	public void setVaraus() {
		varaus = new Varaus();
		henkilö = new Henkilökunta();
		asiakas = new Asiakas();
	}
	
	@Test
	public void testTyhjäKonstruktori() {
		try {
			varaus = new Varaus();
		} catch (Exception e) {
			fail("Varaus-olion luonti epäonnistui");
		}
	}
	
	@Test
	public void testKonstruktori() {
		asiakas = new Asiakas();
		henkilö = new Henkilökunta();
		try {
			varaus = new Varaus("12.12.2000", "12:20", "testi", asiakas, henkilö);
		} catch (Exception e) {
			fail("Varaus-olion luonti epäonnistui");
		}
	}

	@Test
	public void testGetSetID() {
		varaus.setVarausID(1);
		assertEquals(1, varaus.getVarausID(), "varaus id:n asetus epäonnistui");
	}
	
	@Test
	public void testGetSetPVM() {
		varaus.setPäivämäärä("12.12.2020");
		assertEquals("12.12.2020", varaus.getPäivämäärä(), "varauksen päivämäärän asetus epäonnistui");
	}
	
	@Test
	public void testGetSetAika() {
		varaus.setKellonaika("12:12");
		assertEquals("12:12", varaus.getKellonaika(), "varauksen kellonajan asetus epäonnistui");
	}
	
	@Test
	public void testGetSetInfo() {
		varaus.setInfo("testi");
		assertEquals("testi", varaus.getInfo(), "varauksen infon asetus epäonnistui");
	}
	
	@Test
	public void testGetSetAsiakas() {
		asiakas.setAsiakasID(5);
		varaus.setAsiakas(asiakas);
		assertEquals(5, varaus.getAsiakas().getAsiakasID(), "varauksen asiakkaan asetus epäonnistui");
	}
	
	@Test
	public void testGetSetHenkilökunta() {
		henkilö.setHenkilökuntaID(5);
		varaus.setHenkilökunta(henkilö);
		assertEquals(5, varaus.getHenkilökunta().getHenkilökuntaID(), "varauksen henkilökunnan asetus epäonnistui");
	}
}
