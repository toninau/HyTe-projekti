package controller;

import model.Customer;

public interface CustomerController_IF {
	public abstract void loggedCustomer(Customer customer);
	public abstract Customer getCustomer();
}
