package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.HibernateUtil;
import model.Notification;
import model.Staff;

public class NotificationDAOTest {
	private Customer customer;
	private CustomerDAO cDAO;
	private Staff staff;
	private StaffDAO sDAO;
	private Notification n;
	private NotificationDAO nDAO;
	
	@BeforeEach
	public void setTest() {
		SessionFactory sf = HibernateUtil.getSessionFactory(true);
		cDAO = new CustomerDAO(sf);
		sDAO = new StaffDAO(sf);
		nDAO = new NotificationDAO(sf);
	}
	
	@Test
	public void testDAOMethods() {
		//Create customer and staff
		customer = createCustomer();
		staff = createStaff();
		//Create notification
		n = new Notification("date", "text", customer, staff);
		assertTrue(nDAO.create(n), "create(notification): Failed to create notification");
		//Read notification
		n = nDAO.read(1);
		assertEquals("date", n.getDate(), "read(id): Failed to read notification date");
		assertEquals("text", n.getText(), "read(id): Failed to read notification text");
		assertEquals("Firstname", n.getCustomer().getFirstName(), "read(id): Failed to read notification customer");
		assertEquals("Firstname", n.getStaff().getFirstName(), "read(id): Failed to read notification staff");
		assertFalse(n.isRead(), "read(id): Failed to read notification read");
		//Read customers notifications
		Notification[] notifications = nDAO.readCustomersNotifications(customer);
		assertEquals(1, notifications.length, "readCustomersNotifications(customer): Failed to read all customer's notifications");
		//Update notification
		n.setRead(true);
		n.setText("Update");
		assertTrue(nDAO.update(n), "update(notification): Failed to update notification");
		n = nDAO.read(1);
		assertEquals("Update", n.getText(), "read(id): Failed to read updated notification text");
		assertTrue(n.isRead(), "read(id): Failed to read updated notification read");
		//Delete notification
		assertTrue(nDAO.delete(1), "delete(id): Failed to delete notification");
		notifications = nDAO.readCustomersNotifications(customer);
		assertEquals(0, notifications.length, "readCustomersNotifications(customer): Failed to read all customer's notifications after deleting notification");
		//Delete customer and staff
		cDAO.delete("firsur");
		sDAO.delete("firsur");
	}
	
	private Customer createCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("Firstname");
		customer.setSurname("Surname");
		customer.setSSN("SSN");
		customer.setIceNumber("ICEnumber");
		customer.setAddress("Address");
		customer.setPhoneNumber("PhoneNumber");
		customer.setPassword("Password");
		cDAO.create(customer);
		customer = cDAO.read("firsur");
		return customer;
	}
	
	private Staff createStaff() {
		Staff staff = new Staff();
		staff.setFirstName("Firstname");
		staff.setSurname("Surname");
		staff.setPhoneNumber("PhoneNumber");
		staff.setPassword("Password");
		staff.setAccessLevel("AccessLevel");
		sDAO.create(staff);
		staff = sDAO.read("firsur");
		return staff;
	}
}
