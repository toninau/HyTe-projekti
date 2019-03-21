package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class VarausTest {
	private Appointment appointment;
	private Staff henkilö;
	private Customer customer;
	
	@BeforeEach
	public void setVaraus() {
		appointment = new Appointment();
		henkilö = new Staff();
		customer = new Customer();
	}
	
	@Test
	public void testTyhjäKonstruktori() {
		try {
			appointment = new Appointment();
		} catch (Exception e) {
			fail("Appointment-olion luonti epäonnistui");
		}
	}
	
	@Test
	public void testKonstruktori() {
		customer = new Customer();
		henkilö = new Staff();
		try {
			appointment = new Appointment("12.12.2000", "12:20", "testi", customer, henkilö);
		} catch (Exception e) {
			fail("Appointment-olion luonti epäonnistui");
		}
	}

	@Test
	public void testGetSetID() {
		appointment.setVarausID(1);
		assertEquals(1, appointment.getVarausID(), "appointment id:n asetus epäonnistui");
	}
	
	@Test
	public void testGetSetPVM() {
		appointment.setPäivämäärä("12.12.2020");
		assertEquals("12.12.2020", appointment.getPäivämäärä(), "varauksen päivämäärän asetus epäonnistui");
	}
	
	@Test
	public void testGetSetAika() {
		appointment.setKellonaika("12:12");
		assertEquals("12:12", appointment.getKellonaika(), "varauksen kellonajan asetus epäonnistui");
	}
	
	@Test
	public void testGetSetInfo() {
		appointment.setInfo("testi");
		assertEquals("testi", appointment.getInfo(), "varauksen infon asetus epäonnistui");
	}
	
	@Test
	public void testGetSetAsiakas() {
		customer.setAsiakasID(5);
		appointment.setAsiakas(customer);
		assertEquals(5, appointment.getAsiakas().getAsiakasID(), "varauksen asiakkaan asetus epäonnistui");
	}
	
	@Test
	public void testGetSetHenkilökunta() {
		henkilö.setHenkilökuntaID(5);
		appointment.setHenkilökunta(henkilö);
		assertEquals(5, appointment.getHenkilökunta().getHenkilökuntaID(), "varauksen henkilökunnan asetus epäonnistui");
	}
}
