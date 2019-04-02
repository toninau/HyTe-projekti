package view;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.LoginController;
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

public class LoginView extends ViewChanger implements Initializable {

	private DAOManager daom;

	@FXML private Tab staffTab;	
	@FXML private PasswordField pw;	
	@FXML private Button loginBtn;
	@FXML private TextField username;
	
	@FXML private Tab customerTab;
	@FXML private PasswordField pwAsiakas;
	@FXML private TextField usernameAsiakas;
	@FXML private Button loginBtnAsiakas;

	ResourceBundle bundle;
	LoginController c;
	/**
	 * Constructor for LoginView -class.
	 * Luo Data access object -managerin. 
	 */
	public LoginView() {	
		//daom = new DAOManager();
		c = new LoginController();
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
		Staff hkunta = new Staff();
		String fxml = "";
		String title = "Login";
		if(getUsernameStaff().equals("admin") && getPasswordStaff().equals("admin")) {
			fxml = "/AdminMenuView.fxml";
			title = "Menu";
			bundle = ResourceBundle.getBundle(Bundles.ADMINMENU.getBundleName(), HyteGUI.getLocale());

		}
		/*else if(hkunta.getHenkilökuntaID() == Integer.parseInt(getUsername())) {
			fxml = "/StaffView.fxml";
			title = "Staff view";
		}*/else {
			fxml = "/LoginView.fxml";			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Login failed.");
			alert.setHeaderText(null);
			alert.setContentText("Login failed.");
			alert.showAndWait();
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
		Customer customer = new Customer();
		String fxml = "/AsiakasView.fxml";
		String title = "Welcome!";
		sceneContent(fxml, event, title, bundle);
		if(getUsernameStaff().equals(customer.getEmail()) && getPasswordStaff().equals("asiakkaanpassword")) {
			
		}
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
