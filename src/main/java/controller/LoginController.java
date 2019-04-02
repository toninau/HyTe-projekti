package controller;

import model.DAOManager;
import model.DAOManager_IF;
import view.LoginView_IF;

public class LoginController implements LoginController_IF{

	DAOManager_IF daom;
	LoginView_IF view;
	
	public LoginController(LoginView_IF view) {
		this.view = view;
		daom = new DAOManager();
	}
	
	public void getUserFromDatabase() {
		
	}
	
	public void getCustomerFromDB() {
		
	}
	
	public void checkUsernameCustomer() {
		
	}
	
	public void checkPassword() {
		//SCryptUtil.check(passwd, hashed);
	}
}
