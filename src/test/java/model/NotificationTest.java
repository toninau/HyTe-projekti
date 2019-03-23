package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class NotificationTest {
	private Notification notification;
	private Staff staffmember;
	private Customer customer;

	@BeforeEach
	public void setNotification() {
		notification = new Notification();
		staffmember = new Staff();
		customer = new Customer();
	}

	@Test
	public void testEmptyConstructor() {
		try {
			notification = new Notification();
		} catch (Exception e) {
			fail("Failed to create notification-object");
		}
	}

	@Test
	public void testConstructor() {
		customer = new Customer();
		staffmember = new Staff();
		try {
			notification = new Notification("12.12.1212", "testi-notification", customer, staffmember);
		} catch (Exception e) {
			fail("Failed to create notification-object");
		}
	}

	@Test
	public void testGetSetID() {
		notification.setNotificationID(12);
		assertEquals(12, notification.getNotificationID(), "Failed to set and get notificationID");
	}

	@Test
	public void testGetSetRead() {
		notification.setRead(true);
		assertEquals(true, notification.isRead(), "Failed to set and get notification's read status");
	}

	@Test
	public void testGetSetDate() {
		notification.setDate("12.12.2000");
		assertEquals("12.12.2000", notification.getDate(), "Failed to set and get notification's date");
	}

	@Test
	public void testGetSetText() {
		notification.setText("testi");
		assertEquals("testi", notification.getText(), "Failed to set and get notification's text");
	}

	@Test
	public void testGetSetCustomer() {
		customer.setCustomerID(5);
		notification.setCustomer(customer);
		assertEquals(5, notification.getCustomer().getCustomerID(), "Failed to set and get notification's customer");
	}

	@Test
	public void testGetSetStaff() {
		staffmember.setStaffID(5);
		notification.setStaff(staffmember);
		assertEquals(5, notification.getStaff().getStaffID(), "Failed to set and get notification's staff member");
	}
}
