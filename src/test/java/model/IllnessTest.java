package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class IllnessTest {
	private Illness illness;
	private Customer customer;

	@BeforeEach
	public void setIllness() {
		illness = new Illness();
		customer = new Customer();
	}

	@Test
	public void testEmptyConstructor() {
		try {
			illness = new Illness();
		} catch (Exception e) {
			fail("Failed to create illness-object");
		}
	}

	@Test
	public void testConstructor() {
		try {
			illness = new Illness("testisairaus", customer);
		} catch (Exception e) {
			fail("Failed to create illness-object");
		}
	}

	@Test
	public void testGetSetID() {
		illness.setIllnessID(6);
		assertEquals(6, illness.getIllnessID(), "Failed to set and get illnessID");
	}

	@Test
	public void testGetSetNimi() {
		illness.setIllnessName("testi");
		assertEquals("testi", illness.getIllnessName(), "Failed to set and get illness' name");
	}

	@Test
	public void testGetSetAsiakas() {
		customer.setCustomerID("testi@mail.com");
		illness.setCustomer(customer);
		assertEquals("testi@mail.com", illness.getCustomer().getCustomerID(), "Failed to set and get illness' customer");
	}
}
