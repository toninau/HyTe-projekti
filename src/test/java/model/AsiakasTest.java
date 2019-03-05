package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.fail;

public class AsiakasTest {
	private Asiakas asiakas;
	
	@Test
	public void testAsiakasTyhjäKonstruktori() {
		try {
			asiakas = new Asiakas();
		} catch (Exception e) {
			fail("Asiakas-olion luonti epäonnistui");
		}
	}
}
