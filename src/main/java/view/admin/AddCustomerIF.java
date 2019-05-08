package view.admin;

import java.util.HashMap;

public interface AddCustomerIF extends AddIF {
	void addCustomer();
	String getSSN();
	String getAddress();
	String getICE();
}
