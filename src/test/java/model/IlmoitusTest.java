package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class IlmoitusTest {
	private Notification notification;
	private Staff henkilö;
	private Customer customer;

	@BeforeEach
	public void setResepti() {
		notification = new Notification();
		henkilö = new Staff();
		customer = new Customer();
	}

	@Test
	public void testTyhjäKonstruktori() {
		try {
			notification = new Notification();
		} catch (Exception e) {
			fail("Notification-olion luonti epäonnistui");
		}
	}

	@Test
	public void testKonstruktori() {
		customer = new Customer();
		henkilö = new Staff();
		try {
			notification = new Notification("12.12.1212", "testi-notification", customer, henkilö);
		} catch (Exception e) {
			fail("Notification-olion luonti epäonnistui");
		}
	}

	@Test
	public void testGetSetID() {
		notification.setIlmoitusID(12);
		assertEquals(12, notification.getNotificationID(), "notification id:n asetus epäonnistui");
	}

	@Test
	public void testGetSetLuettu() {
		notification.setLuettu(true);
		assertEquals(true, notification.isRead(), "notification luettu asetus epäonnistui");
	}

	@Test
	public void testGetSetPvm() {
		notification.setPvm("12.12.2000");
		assertEquals("12.12.2000", notification.getDate(), "notification pvm asetus epäonnistui");
	}

	@Test
	public void testGetSetTeksti() {
		notification.setTeksti("testi");
		assertEquals("testi", notification.getText(), "ilmoituksen tekstin asetus epäonnistui");
	}

	@Test
	public void testGetSetAsiakas() {
		customer.setAsiakasID(5);
		notification.setAsiakas(customer);
		assertEquals(5, notification.getAsiakas().getAsiakasID(), "ilmoituksen asiakkaan asetus epäonnistui");
	}

	@Test
	public void testGetSetHenkilökunta() {
		henkilö.setHenkilökuntaID(5);
		notification.setHenkilökunta(henkilö);
		assertEquals(5, notification.getHenkilökunta().getHenkilökuntaID(), "ilmoituksen henkilökunnan asetus epäonnistui");
	}
}
