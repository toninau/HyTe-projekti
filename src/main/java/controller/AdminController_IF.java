package controller;

import model.Customer;
import model.Staff;

public interface AdminController_IF {
	public abstract boolean addStaff();
	public abstract boolean addCustomer();
	public abstract Staff[] findStaffAll();
	public abstract Customer[] findCustomerAll();
	public abstract Customer findCustomerWithID(String id);
	public abstract Staff findStaffWithID(String id);
	public abstract boolean updateStaff(Staff staff);
	public abstract void updateCustomer(Customer customer);
}
