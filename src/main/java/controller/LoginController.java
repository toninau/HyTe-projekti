package controller;

import com.lambdaworks.crypto.SCryptUtil;

import model.Customer;
import model.DAOManager;
import model.DAOManagerIF;
import model.Staff;
import view.LoginViewIF;

public class LoginController implements LoginControllerIF {

	private DAOManagerIF daom;
	private LoginViewIF view;

	private boolean success = false;

	public LoginController(LoginViewIF view) {
		this.view = view;
		daom = new DAOManager();
	}

	public Customer getCustomerFromDatabase(String email) {
		return (Customer) daom.readWithEmail("customer", email);
	}

	public boolean checkLoginInfo(String pw, String pwDB, String username, String usernameDB) {
		if(pwDB!=null) {
			if(checkUsername(username, usernameDB)) {
				if(checkPassword(pw, pwDB)) {
					return true;
				}else {
					view.loginFailed("password");
				}
			}else {
				view.loginFailed("user");
			}
		}else {
			view.loginFailed("user");
		}
		return false;
	}
	
	public boolean checkLoginCustomer(String email, String password) {
		success = false;
		Customer customer = getCustomerFromDatabase(email);
		String pwFromDB = customer.getPassword();
		String emailFromDB = customer.getCustomerID();
		if (checkLoginInfo(password, pwFromDB, email, emailFromDB)){
			CustomerController.loggedCustomer(customer);
			daom.writeAllCustomerInformation(customer);
			success = true;
		}
		return success;
	}
	


	public Staff getStaffFromDatabase(String email) {
		return (Staff) daom.readWithEmail("staff", email);
	}

	@Override
	public boolean checkLoginStaff(String email, String password) {
		success = false;
		Staff staff = getStaffFromDatabase(email);
		String pwFromDB = staff.getPassword();
		String emailFromDB = staff.getStaffID();
		
		if (checkLoginInfo(password, pwFromDB, email, emailFromDB)){
			StaffController.loggedStaff(staff);
			success = true;
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
