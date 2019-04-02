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
	private String email;
	private String password;
	private Customer customer;
	private Staff staff;
	
	public LoginController(LoginView_IF view) {
		this.view = view;
		daom = new DAOManager();
	}
	
	public void getCustomerFromDatabase() {
		email = view.getUsernameCustomer();
		customer = (Customer) daom.readWithEmail("customer", email);
	}
	
	public boolean checkLoginCustomer() {	
		password = view.getPasswordCustomer();
		String pwFromDB = customer.getPassword();
		String emailFromDB = customer.getEmail();
		if(emailFromDB.equals(email) && SCryptUtil.check(password, pwFromDB)  ) {
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public void getStaffFromDatabase() {
		email = view.getUsernameStaff();
		staff = (Staff) daom.readWithEmail("staff", email);
	}

	@Override
	public boolean checkLoginStaff() {
		password = view.getPasswordStaff();
		String pwFromDB = staff.getPassword();
		String emailFromDB = staff.getEmail();
		if(emailFromDB.equals(email) && SCryptUtil.check(password, pwFromDB)  ) {
			return true;
		}else {
			return false;
		}
	}
}
