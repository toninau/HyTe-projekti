package view;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.LoginController;
import controller.LoginController_IF;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Customer;
import model.DAOManager;
import model.Staff;

/**
 * 
 * Class for login view.
 *
 */

public class LoginView extends ViewChanger implements Initializable, LoginView_IF {

	@FXML private Tab staffTab;	
	@FXML private PasswordField pw;	
	@FXML private Button loginBtn;
	@FXML private TextField username;
	
	@FXML private Tab customerTab;
	@FXML private PasswordField pwAsiakas;
	@FXML private TextField usernameAsiakas;
	@FXML private Button loginBtnAsiakas;

	ResourceBundle bundle;
	private LoginController_IF c;
	
	/**
	 * Constructor for LoginView -class.
	 * Luo Data access object -managerin. 
	 */
	public LoginView() {	
		c = new LoginController(this);
	}
		
	/**
	 * Function for staff and admin's login.
	 * Fired when login button in Staff -tab is clicked.
	 * 
	 * @param event	Mouse clicked
	 * @throws IOException Loading the fxml file failed.
	 */
	@FXML
	public void loginStaff(MouseEvent event) throws IOException {
		String fxml = "";
		String title = "Login";
		if(!getUsernameStaff().equals("admin") && !getPasswordStaff().equals("admin")) {
			c.getStaffFromDatabase();
			if(c.checkLoginStaff()) {
				fxml = "/StaffView.fxml";
				title = "Staff view";
			}else {
				loginFailed("");
				fxml = "/LoginView.fxml";	
				title = "Login";
			}		
		}
		else {
			fxml = "/fxml/AdminMenuView.fxml";
			title = "Menu";
			bundle = ResourceBundle.getBundle(Bundles.ADMINMENU.getBundleName(), HyteGUI.getLocale());
		}
		sceneContent(fxml, event, title, bundle);
	}
	
	
	/**
	 * Fired when customer's login button is clicked.
	 * @param event Button clicked.
	 * @throws IOException Loading the fxml file failed.
	 */
	@FXML
	public void loginCustomer(MouseEvent event) throws IOException {
		String fxml = "/fxml/LoginView.fxml";
		String title = "Welcome!";
		if (c.checkLoginCustomer()) {
			fxml = "/fxml/AsiakasView.fxml";
			title = "Welcome!";
		}
		sceneContent(fxml, event, title, bundle);
	}

	public void loginFailed(String msg) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Login failed.");
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
	}
	/**
	 * Return the text written in the employee's user name -field.
	 * @return Employee's user name.
	 */
	public String getUsernameStaff() {
		return this.username.getText();
	}
	
	/**
	 * Return the text written in the employee's password -field.
	 * @return	Employee's password.
	 */
	public String getPasswordStaff() {
		return this.pw.getText();
	}
	
	/**
	 * Return the text written in the customer's user name -field.
	 * @return Customer's user name.
	 */
	public String getUsernameCustomer() {
		return this.usernameAsiakas.getText();
	}
	
	/**
	 * Return the text written in the customer's password -field.
	 * @return Customer's password.
	 */
	public String getPasswordCustomer() {
		return this.pwAsiakas.getText();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle("properties.LoginProperties", HyteGUI.getLocale());
		
	}
}
