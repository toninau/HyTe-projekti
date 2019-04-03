package controller;

import model.Customer;
import model.Staff;

public interface LoginController_IF {

	public abstract boolean checkLoginCustomer();
	public abstract Customer getCustomerFromDatabase(String email);
	public abstract Staff getStaffFromDatabase(String email);
	public abstract boolean checkLoginStaff();
}
