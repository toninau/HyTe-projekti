package view;

public interface LoginView_IF {
	public abstract String getUsernameCustomer();
	public abstract String getUsernameStaff();
	public abstract String getPasswordCustomer();
	public abstract String getPasswordStaff();
	public abstract void loginFailed(String msg);
}
