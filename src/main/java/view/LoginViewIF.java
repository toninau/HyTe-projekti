package view;

public interface LoginViewIF {
	String getUsernameCustomer();
	String getUsernameStaff();
	String getPasswordCustomer();
	String getPasswordStaff();
	void loginFailed(String msg);
}
