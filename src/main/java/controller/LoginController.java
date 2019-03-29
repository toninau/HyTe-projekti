package controller;

import model.DAOManager;

public class LoginController {

	DAOManager daom;
	
	public LoginController() {
		daom = new DAOManager();
	}
	
	public void getUserFromDatabase() {
		
	}
	
	public void checkUsername() {
		
	}
	
	public void checkPassword() {
		//SCryptUtil.check(passwd, hashed);
	}
}
