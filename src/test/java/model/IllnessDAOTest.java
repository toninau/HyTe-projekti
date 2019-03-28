package model;
/*
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IllnessDAOTest {
	private IllnessDAO iDAO;
	private Illness illness;
	private Customer customer;
	private CustomerDAO cDAO;

	@BeforeEach
	public void setTest() {
		SessionFactory istuntotehdas = HibernateUtil.getSessionFactory(true);
		iDAO = new IllnessDAO(istuntotehdas);
		cDAO = new CustomerDAO(istuntotehdas);
	}

	@Test
	public void testDAOmethods() {
		customer = new Customer();
		customer.setFirstName("Jorma");
		customer.setSurname("Testi");
		customer.setSSN("123456-7890");
		customer.setIceNumber("12312145");
		customer.setAddress("Testikuja 2");
		customer.setEmail("jorma@mail.com");
		customer.setPhoneNumber("12341235");
		cDAO.create(customer);
		customer = cDAO.read(1);
		illness = new Illness("illness", customer);
		assertTrue(iDAO.create(illness), "create(): Failed to create a new illness.");
	}
}
*/