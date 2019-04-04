package controller;

import com.lambdaworks.crypto.SCryptUtil;

import model.Customer;
import model.DAOManager;
import model.DAOManager_IF;
import model.Staff;
import view.LoginView_IF;

public class LoginController implements LoginController_IF{

	private DAOManager_IF daom;
	private LoginView_IF view;
	private boolean success = false;
	
	public LoginController(LoginView_IF view) {
		this.view = view;
		daom = new DAOManager();
	}
	
	public Customer getCustomerFromDatabase(String email) {
		return (Customer) daom.readWithEmail("customer", email);
	}
	
	public boolean checkLoginCustomer() {	
		String email = view.getUsernameCustomer();
		String password = view.getPasswordCustomer();
		Customer customer = getCustomerFromDatabase(email);
		String pwFromDB = customer.getPassword();
		String emailFromDB = customer.getCustomerID();

		if(pwFromDB != null) {
			if(checkUsername(email, emailFromDB) && checkPassword(password, pwFromDB))
				return true;
			else
				return false;
		}else {
			view.loginFailed("Usernamenotfound");
		}

		return false;
	}
	
	public Staff getStaffFromDatabase(String email) {
		return (Staff) daom.readWithEmail("staff", email);
	}
	
	@Override
	public boolean checkLoginStaff() {
		success = false;
		String password = view.getPasswordStaff();
		String email = view.getUsernameStaff();
		Staff staff = getStaffFromDatabase(email);
		String pwFromDB = staff.getPassword();
		String emailFromDB = staff.getStaffID();
		
		if(pwFromDB != null) {
			if(checkUsername(email, emailFromDB)) {
				if(checkPassword(password, pwFromDB)) {
					success = true;
				}else {
					success = false;
					view.loginFailed("password");
				} 
			}else {
				success = false;
				view.loginFailed("user");
			}
		}else {
			success = false;
			view.loginFailed("user");
		}

		return success;
	}

	public boolean checkPassword(String password, String pwFromDB) {
		return SCryptUtil.check(password, pwFromDB);
	}
	
	public boolean checkUsername(String email, String emailFromDB) {
		return emailFromDB.equals(email);
	}
	

}
