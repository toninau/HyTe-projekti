package controller;

import model.Customer;
import model.Staff;

public interface AdminController_IF {
	public abstract void addStaff();
	public abstract void addCustomer();
	public abstract Staff[] findStaffAll();
	public abstract Customer[] findCustomerAll();
	public abstract Customer findCustomerWithID(int id);
	public abstract Staff findStaffWithID(int id);
	public abstract void updateStaff(Staff staff);
	public abstract void updateCustomer(Customer customer);
}
