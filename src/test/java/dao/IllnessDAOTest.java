package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.HibernateUtil;
import model.Illness;

public class IllnessDAOTest {
	private static IllnessDAO iDAO;
	private Illness illness;
	private Customer customer;
	private static CustomerDAO cDAO;

	@BeforeEach
	public void setTest() {
		SessionFactory sf = HibernateUtil.getSessionFactory(true);
		iDAO = new IllnessDAO(sf);
		cDAO = new CustomerDAO(sf);
	}
	
	@AfterAll
	public static void tearDown() {
		cDAO.delete("firsur");
		iDAO.delete(1);
	}

	@Test
	public void testDAOmethods() {
		//Create customer for illness
		customer = new Customer();
		customer.setFirstName("FirstName");
		customer.setSurname("Surname");
		customer.setSSN("SSN");
		customer.setIceNumber("ICEnumber");
		customer.setAddress("Address");
		customer.setPhoneNumber("PhoneNumber");
		cDAO.create(customer);
		customer = cDAO.read("firsur");
		//Create illnesses
		illness = new Illness("Illness", customer);
		assertTrue(iDAO.create(illness), "create(): Failed to create a new illness.");
		//Read illness
		Illness[] i = iDAO.readCustomersIllnessess(customer);
		assertEquals("Illness", i[0].getIllnessName(), "read(): Failed to read customer's illness.");
		//Delete illness
		assertTrue(iDAO.delete(1), "delete(): Failed to delete illness.");
		i = iDAO.readCustomersIllnessess(customer);
		assertEquals(0, i.length, "delete(): Failed to delete illness");
		//Delete customer
		cDAO.delete("firsur");
	}
}

