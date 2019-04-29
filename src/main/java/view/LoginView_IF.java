package view;

public interface LoginView_IF {
	String getUsernameCustomer();
	String getUsernameStaff();
	String getPasswordCustomer();
	String getPasswordStaff();
	void loginFailed(String msg);
}
