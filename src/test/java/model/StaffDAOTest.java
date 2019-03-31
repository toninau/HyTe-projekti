package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StaffDAOTest {
	private StaffDAO dao;
	private Staff staff;

	@BeforeEach
	public void setTest() {
		SessionFactory istuntotehdas = HibernateUtil.getSessionFactory(true);
		dao = new StaffDAO(istuntotehdas);
	}

	@Test
	public void testDAOmethods() {
		staff = new Staff("Firstname", "Surname", "test", "test", "test", "test");
		assertTrue(dao.create(staff), "create(): Failed to create a new staff member.");
		staff = dao.read(1);
		assertEquals("Firstname", staff.getFirstName(), "read(): Failed to read firstname.");
		staff.setFirstName("Update");
		dao.update(staff);
		staff = dao.read(1);
		assertEquals("Update", staff.getFirstName(), "update(): Failed to update firstname.");
		assertTrue(dao.delete(1), "delete(): Failed to delete staff member.");
		Staff[] list = dao.readAll();
		assertEquals(0, list.length, "readAll(): Failed to read all staff members");
	}
}
