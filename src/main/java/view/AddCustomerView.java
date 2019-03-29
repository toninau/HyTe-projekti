package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.AdminController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Class for adding customers to database.
 * @author IdaKi
 *
 */
public class AddCustomerView extends ViewChanger implements Initializable {

	@FXML
	private Button addCustomer;
	@FXML
	private TextField hetuCust;
	@FXML
	private TextField fNameCust;
	@FXML
	private TextField sNameCust;
	@FXML
	private TextField phoneNroCust;
	@FXML
	private TextField emailCust;
	@FXML
	private TextField addressCust;
	@FXML 
	private TextField ICECust; 
	@FXML
	private Button logout;
	@FXML
	private Button toMenu;
	@FXML
	private PasswordField password;
	
	private AdminController controller;
	
	public AddCustomerView() {
		//controller = new AdminController(this);
	}
	
	/**
	 * Method for creating a customer.
	 */
	public void addCustomer() {
		//c.addCustomer();
		fNameCust.clear();
		sNameCust.clear();
		hetuCust.clear();
		phoneNroCust.clear();
		emailCust.clear();
		addressCust.clear();
		ICECust.clear();
	}
	
	/**
	 * Changes scene back to Login view.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 */
	public void logout(MouseEvent event) throws IOException {
		String fxml = "/LoginView.fxml";
		String title = "Login";
		sceneContent(fxml, event, title);
	}
	
	/**
	 * Changes scene back to Admin's menu view.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 */
	public void toMenu(MouseEvent event) throws IOException {
		String fxml = "/AdminMenuView.fxml";
		String title = "Menu";
		sceneContent(fxml, event, title);
	}
	
	/**
	 * Returns the text written in the social security number -field.
	 * @return Customer's social security number.
	 */
	public String getCustHetu() {
		return this.hetuCust.getText();
	}

	/**
	 * Returns the text written in the address -field.
	 * @return Customer's home address.
	 */
	public String getCustAddress() {
		return this.addressCust.getText();
	}

	/**
	 * Returns the text written in the in case of emergency number -field.
	 * @return Customer's ICE-number.
	 */
	public String getCustICE() {
		return this.ICECust.getText();
	}

	/**
	 * Returns the text written in the email -field.
	 * @return Customer's email address.
	 */
	public String getCustEmail() {
		return this.emailCust.getText();
	}

	/**
	 * Returns the text written in the phone number -field.
	 * @return Customer's phone number.
	 */
	public String getCustPhone() {
		return this.phoneNroCust.getText();
	}

	/**
	 * Returns the text written in the surname -field.
	 * @return Customer's surname.
	 */
	public String getCustSurname() {
		return this.sNameCust.getText();
	}

	/**
	 * Returns the text written in the first name -field.
	 * @return Customer's first name.
	 */
	public String getCustFirstname() {
		return this.fNameCust.getText();
	}
	
	/**
	 * Returns the text written in the password -field.
	 * @return Customer's password.
	 */
	public String getCustPassword() {
		return this.password.getText();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	

}
