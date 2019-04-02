package controller;

public interface LoginController_IF {

	public abstract boolean checkLoginCustomer();
	public abstract void getCustomerFromDatabase();
	public abstract void getStaffFromDatabase();
	public abstract boolean checkLoginStaff();
}
