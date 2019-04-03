package controller;

import model.Customer;

public interface LoginController_IF {

	public abstract boolean checkLoginCustomer();
	public abstract Customer getCustomerFromDatabase(String email);
	public abstract void getStaffFromDatabase();
	public abstract boolean checkLoginStaff();
}
