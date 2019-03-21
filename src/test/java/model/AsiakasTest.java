package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AsiakasTest {
	private Customer customer;

	@BeforeEach
	public void setAsiakas() {
		customer = new Customer();
	}

	@Test
	public void testTyhjäKonstruktori() {
		try {
			customer = new Customer();
		} catch (Exception e) {
			fail("Asiakas-olion luonti epäonnistui");
		}
	}

	@Test
	public void testKonstruktori() {
		try {
			customer = new Customer("Testi", "Testinen", "111111-111A", "Testitie 3", "testi@mail.com", "1234567890",
					"0987654321");
		} catch (Exception e) {
			fail("Asiakas-olion luonti epäonnistui");
		}
	}

	@Test
	public void testGetSetID() {
		customer.setAsiakasID(12);
		assertEquals(12, customer.getAsiakasID(), "Asiakkaan id:n asetus epäonnistui");
	}

	@Test
	public void testGetSetEtunimi() {
		customer.setEtunimi("TestName");
		assertEquals("TestName", customer.getFirstName(), "Asiakkaan etunimen asetus epäonnistui");
	}

	@Test
	public void testGetSetSukunimi() {
		customer.setSukunimi("NameTest");
		assertEquals("NameTest", customer.getSurname(), "Asiakkaan sukunimen asetus epäonnistui");
	}

	@Test
	public void testGetSetHetu() {
		customer.setHetu("098765-1234");
		assertEquals("098765-1234", customer.getHetu(), "Asiakkaan HeTu:n asetus epäonnistui");
	}

	@Test
	public void testGetSetIcenumero() {
		customer.setIcenumero("123123123");
		assertEquals("123123123", customer.getIcenumero(), "Asiakkaan Icenumeron asetus epäonnistui");
	}

	@Test
	public void testGetSetKotiosoite() {
		customer.setKotiosoite("testikuja 10");
		assertEquals("testikuja 10", customer.getKotiosoite(), "Asiakkaan kotiosoitteen asetus epäonnistui");
	}

	@Test
	public void testGetSetPuhnumero() {
		customer.setPuhnumero("12341234");
		assertEquals("12341234", customer.getPhoneNumber(), "Asiakkaan puhnumeron asetus epäonnistui");
	}

	@Test
	public void testGetSetSposti() {
		customer.setSposti("test@mail.com");
		assertEquals("test@mail.com", customer.getEmail(), "Asiakkaan Spostin asetus epäonnistui");
	}
}
