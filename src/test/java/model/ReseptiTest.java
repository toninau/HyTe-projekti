package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReseptiTest {
	private Resepti resepti;
	private Henkilökunta henkilö;
	private Asiakas asiakas;
	
	@BeforeEach
	public void setResepti() {
		resepti = new Resepti();
		henkilö = new Henkilökunta();
		asiakas = new Asiakas();
	}
	
	@Test
	public void testTyhjäKonstruktori() {
		try {
			resepti = new Resepti();
		} catch (Exception e) {
			fail("Resepti-olion luonti epäonnistui");
		}
	}
	
	@Test
	public void testKonstruktori() {
		asiakas = new Asiakas();
		henkilö = new Henkilökunta();
		try {
			resepti = new Resepti("12.12.2000", "12.20.2001", "testi", "testi-ohje" ,asiakas, henkilö);
		} catch (Exception e) {
			fail("Resepti-olion luonti epäonnistui");
		}
	}
	
	@Test
	public void testGetSetID() {
		resepti.setReseptiID(12);
		assertEquals(12, resepti.getReseptiID(), "resepti id:n asetus epäonnistui");
	}
	
	@Test
	public void testGetSetAlkupvm() {
		resepti.setAlkupvm("12.12.2012");
		assertEquals("12.12.2012", resepti.getAlkupvm(), "resepti alkupvm asetus epäonnistui");
	}
	
	@Test
	public void testGetSetLoppupvm() {
		resepti.setLoppupvm("20.20.2020");
		assertEquals("20.20.2020", resepti.getLoppupvm(), "resepti loppupvm asetus epäonnistui");
	}
	
	@Test
	public void testGetSetNimi() {
		resepti.setReseptiNimi("testi");
		assertEquals("testi", resepti.getReseptiNimi(), "reseptin nimen asetus epäonnistui");
	}
	
	@Test
	public void testGetSetOhje() {
		resepti.setReseptiOhje("testi-ohje");
		assertEquals("testi-ohje", resepti.getReseptiOhje(), "reseptin ohjeen asetus epäonnistui");
	}
	
	@Test
	public void testGetSetAsiakas() {
		asiakas.setAsiakasID(5);
		resepti.setAsiakas(asiakas);
		assertEquals(5, resepti.getAsiakas().getAsiakasID(), "reseptin asiakkaan asetus epäonnistui");
	}
	
	@Test
	public void testGetSetHenkilökunta() {
		henkilö.setHenkilökuntaID(5);
		resepti.setHenkilökunta(henkilö);
		assertEquals(5, resepti.getHenkilökunta().getHenkilökuntaID(), "reseptin henkilökunnan asetus epäonnistui");
	}

}
