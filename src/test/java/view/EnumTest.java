package view;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import view.enums.Bundles;
import view.enums.FxmlEnum;

public class EnumTest {
	
	/**
	 * Test method for getting login constant from fxml enum.
	 */
	@Test
	public void getLoginFxmlTest() {
		assertTrue(FxmlEnum.LOGIN.getFxml().equals("/fxml/LoginView.fxml"));
	}
	
	/**
	 * Test method for getting add customer constant from fxml enum.
	 */
	@Test
	public void getAddCustomerViewFxmlTest() {
		assertTrue(FxmlEnum.ADDCUSTOMER.getFxml().equals("/fxml/AddCustomerView.fxml"));
	}
	
	/**
	 * Test method for getting add staff constant from fxml enum.
	 */
	@Test
	public void getAddStaffViewFxmlTest() {
		assertTrue(FxmlEnum.ADDSTAFF.getFxml().equals("/fxml/AddStaffView.fxml"));
	}
	
	/**
	 * Test method for getting edit customer constant from fxml enum.
	 */
	@Test
	public void getEditCustomerViewFxmlTest() {
		assertTrue(FxmlEnum.EDITCUSTOMER.getFxml().equals("/fxml/EditCustomerView.fxml"));
	}
	
	/**
	 * Test method for getting customer home constant from fxml enum.
	 */
	@Test
	public void getCustomerHomeViewFxmlTest() {
		assertTrue(FxmlEnum.CUSTOMERHOME.getFxml().equals("/fxml/CustomerHomeView.fxml"));
	}
	
	/**
	 * Test method for getting customer calendar constant from fxml enum.
	 */
	@Test
	public void getCalendarViewFxmlTest() {
		assertTrue(FxmlEnum.CUSTOMERCALENDAR.getFxml().equals("/fxml/CalendarView.fxml"));
	}
	
	/**
	 * Test method for getting customer help constant from fxml enum.
	 */
	@Test
	public void getCustomerHelpViewFxmlTest() {
		assertTrue(FxmlEnum.CUSTOMERHELP.getFxml().equals("/fxml/CustomerHelpView.fxml"));
	}
	
	/**
	 * Test method for getting customer health constant from fxml enum.
	 */
	@Test
	public void getCustomerHealthViewFxmlTest() {
		assertTrue(FxmlEnum.CUSTOMERHEALTH.getFxml().equals("/fxml/CustomerHealthView.fxml"));
	}
	
	/**
	 * Test method for getting staff home constant from fxml enum.
	 */
	@Test
	public void getStaffHomeViewFxmlTest() {
		assertTrue(FxmlEnum.STAFFHOME.getFxml().equals("/fxml/StaffHomeView.fxml"));
	}
	
	/**
	 * Test method for getting staff appointment constant from fxml enum.
	 */
	@Test
	public void getStaffAppointmentViewFxmlTest() {
		assertTrue(FxmlEnum.STAFFAPPOINTMENT.getFxml().equals("/fxml/StaffAppointmentView.fxml"));
	}
	
	/**
	 * Test method for getting admin properties constant from bundles enum.
	 */
	@Test
	public void getAdminBundleTest() {
		assertTrue(Bundles.ADMIN.getBundleName().equals("properties.Admin"));
	}
	
	/**
	 * Test method for getting login properties constant from bundles enum.
	 */
	@Test
	public void getLoginBundleTest() {
		assertTrue(Bundles.LOGIN.getBundleName().equals("properties.LoginProperties"));
	}
	
	/**
	 * Test method for getting customer properties constant from bundles enum.
	 */
	@Test
	public void getCustomerBundleTest() {
		assertTrue(Bundles.CUSTOMER.getBundleName().equals("properties.Customer"));
	}
	
	/**
	 * Test method for getting staff properties constant from bundles enum.
	 */
	@Test
	public void getStaffBundleTest() {
		assertTrue(Bundles.STAFF.getBundleName().equals("properties.Staff"));
	}
}
