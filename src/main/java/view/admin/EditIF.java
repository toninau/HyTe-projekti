package view.admin;

public interface EditIF {
	public abstract void allFromDatabase();
	public abstract void showInfo();
	public abstract void updateInfo();
	public abstract String getFirstName();
	public abstract String getSurname();
	public abstract String getEmail();
	public abstract String getPhoneNumber();
}
