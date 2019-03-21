package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReseptiTest {
	private Prescription prescription;
	private Staff henkilö;
	private Customer customer;
	
	@BeforeEach
	public void setResepti() {
		prescription = new Prescription();
		henkilö = new Staff();
		customer = new Customer();
	}
	
	@Test
	public void testTyhjäKonstruktori() {
		try {
			prescription = new Prescription();
		} catch (Exception e) {
			fail("Prescription-olion luonti epäonnistui");
		}
	}
	
	@Test
	public void testKonstruktori() {
		customer = new Customer();
		henkilö = new Staff();
		try {
			prescription = new Prescription("12.12.2000", "12.20.2001", "testi", "testi-ohje" ,customer, henkilö);
		} catch (Exception e) {
			fail("Prescription-olion luonti epäonnistui");
		}
	}
	
	@Test
	public void testGetSetID() {
		prescription.setReseptiID(12);
		assertEquals(12, prescription.getReseptiID(), "prescription id:n asetus epäonnistui");
	}
	
	@Test
	public void testGetSetAlkupvm() {
		prescription.setAlkupvm("12.12.2012");
		assertEquals("12.12.2012", prescription.getAlkupvm(), "prescription alkupvm asetus epäonnistui");
	}
	
	@Test
	public void testGetSetLoppupvm() {
		prescription.setLoppupvm("20.20.2020");
		assertEquals("20.20.2020", prescription.getLoppupvm(), "prescription loppupvm asetus epäonnistui");
	}
	
	@Test
	public void testGetSetNimi() {
		prescription.setReseptiNimi("testi");
		assertEquals("testi", prescription.getReseptiNimi(), "reseptin nimen asetus epäonnistui");
	}
	
	@Test
	public void testGetSetOhje() {
		prescription.setReseptiOhje("testi-ohje");
		assertEquals("testi-ohje", prescription.getReseptiOhje(), "reseptin ohjeen asetus epäonnistui");
	}
	
	@Test
	public void testGetSetAsiakas() {
		customer.setAsiakasID(5);
		prescription.setAsiakas(customer);
		assertEquals(5, prescription.getAsiakas().getAsiakasID(), "reseptin asiakkaan asetus epäonnistui");
	}
	
	@Test
	public void testGetSetHenkilökunta() {
		henkilö.setHenkilökuntaID(5);
		prescription.setHenkilökunta(henkilö);
		assertEquals(5, prescription.getHenkilökunta().getHenkilökuntaID(), "reseptin henkilökunnan asetus epäonnistui");
	}

}
