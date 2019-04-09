package controller;

import model.BloodValue;
import model.Customer;
import model.Prescription;

public interface CustomerController_IF {
	public abstract void loggedCustomer(Customer customer);
	public abstract Customer getCustomer();
	public abstract BloodValue[] bloodValueData();
	public abstract boolean createBloodsugar();
	public abstract Prescription[] prescriptions();
}
