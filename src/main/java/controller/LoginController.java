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

	private Staff staff;
	
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
		System.out.println(pwFromDB);
		if(pwFromDB != null) {
			if(checkUsername(email, emailFromDB) && checkPassword(password, pwFromDB))
				
				return true;
			else
				return false;
		}
		view.loginFailed("Usernamenotfound");
		return false;
	}
	
	public boolean checkPassword(String password, String pwFromDB) {
		return SCryptUtil.check(password, pwFromDB);
	}
	
	public boolean checkUsername(String email, String emailFromDB) {
		return emailFromDB.equals(email);
	}
	
	@Override
	public void getStaffFromDatabase() {
		String email = view.getUsernameStaff();
		staff = (Staff) daom.readWithEmail("staff", email);
	}

	@Override
	public boolean checkLoginStaff() {
		String password = view.getPasswordStaff();
		String email = view.getUsernameStaff();
		String pwFromDB = staff.getPassword();
		String emailFromDB = staff.getStaffID();
		if(emailFromDB.equals(email) && SCryptUtil.check(password, pwFromDB)  ) {
			return true;
		}else {
			return false;
		}
	}
}
