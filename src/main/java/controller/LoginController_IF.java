package controller;

import model.Customer;
import model.Staff;

public interface LoginController_IF {

	boolean checkLoginCustomer(String username, String password);
	Customer getCustomerFromDatabase(String email);
	Staff getStaffFromDatabase(String email);
	boolean checkLoginStaff(String username, String password);
}
