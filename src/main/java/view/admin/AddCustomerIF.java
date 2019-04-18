package view.admin;

public interface AddCustomerIF extends AddIF {
	public abstract void addCustomer();
	public abstract String getSSN();
	public abstract String getAddress();
	public abstract String getICE();
}
