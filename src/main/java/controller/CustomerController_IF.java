package controller;

import model.BloodValue;
import model.Customer;

public interface CustomerController_IF {
	public abstract void loggedCustomer(Customer customer);
	public abstract Customer getCustomer();
	public abstract BloodValue[] bloodValueData();
	public abstract boolean createBloodsugar();
}
