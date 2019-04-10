package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import controller.AdminController;
import controller.AdminController_IF;
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
public class AddCustomerView extends ViewChanger implements Initializable, AddCustomerView_IF {

	@FXML
	private Button addCustomer;
	@FXML
	private TextField ssn;
	@FXML
	private TextField firstname;
	@FXML
	private TextField surname;
	@FXML
	private TextField phone;
	@FXML
	private TextField email;
	@FXML
	private TextField address;
	@FXML 
	private TextField ice; 
	@FXML
	private Button logout;
	@FXML
	private Button toMenu;
	@FXML
	private PasswordField password;
	
	private AdminController_IF controller;
	private HashMap<String, String> customerInfo;
	private ResourceBundle bundle;
	
	public AddCustomerView() {
		controller = new AdminController(this);
		customerInfo = new HashMap<>();
	}
	
	/**
	 * Method for creating a customer.
	 */
	public void addCustomer() {
		//createInfoMap();
		if(controller.addCustomer()) {
			firstname.clear();
			surname.clear();
			ssn.clear();
			phone.clear();
			email.clear();
			address.clear();
			ice.clear();
			password.clear();
		}
	}
	
	/**
	 * Changes scene back to Login view.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 */
	public void logout(MouseEvent event) throws IOException {
		logoutForAll(event);
	}
	
	/**
	 * Changes scene back to Admin's menu view.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 */
	public void toMenu(MouseEvent event) throws IOException {
		toAdminMenu(event);
	}
	
	public void createInfoMap() {
		customerInfo.put("ssn", getCustHetu());
		customerInfo.put("firstname", getCustFirstname());
		customerInfo.put("surname", getCustSurname());
		customerInfo.put("email", getCustEmail());
		customerInfo.put("phone", getCustPhone());
		customerInfo.put("ice", getCustICE());
		customerInfo.put("address", getCustAddress());
		customerInfo.put("password", getCustPassword());
	}
	
	public String checkIfEmpty(TextField f) {
		if(f.getText().isEmpty()) {
			f.setPromptText("Arvo puuttuu");
			f.setStyle("-fx-prompt-text-fill: red;");
			return "";
		}
		f.setPromptText(bundle.getString(f.getId() + ".prompt"));
		f.setStyle("-fx-prompt-text-fill: default;");
		return f.getText();
	}
	
	/**
	 * Returns the text written in the social security number -field.
	 * @return Customer's social security number.
	 */
	public String getCustHetu() {
		return checkIfEmpty(this.ssn);
	}

	/**
	 * Returns the text written in the address -field.
	 * @return Customer's home address.
	 */
	public String getCustAddress() {
		return checkIfEmpty(this.address);
	}

	/**
	 * Returns the text written in the in case of emergency number -field.
	 * @return Customer's ice-number.
	 */
	public String getCustICE() {
		return checkIfEmpty(this.ice);
	}

	/**
	 * Returns the text written in the email -field.
	 * @return Customer's email address.
	 */
	public String getCustEmail() {
		return checkIfEmpty(this.email);
	}

	/**
	 * Returns the text written in the phone number -field.
	 * @return Customer's phone number.
	 */
	public String getCustPhone() {
		return checkIfEmpty(this.phone);
	}

	/**
	 * Returns the text written in the surname -field.
	 * @return Customer's surname.
	 */
	public String getCustSurname() {
		return checkIfEmpty(this.surname);
	}

	/**
	 * Returns the text written in the first name -field.
	 * @return Customer's first name.
	 */
	public String getCustFirstname() {
		return checkIfEmpty(this.firstname);
	}
	
	/**
	 * Returns the text written in the password -field.
	 * @return Customer's password.
	 */
	public String getCustPassword() {
		return checkIfEmpty(this.password);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.ADMIN.getBundleName(), HyteGUI.getLocale());

		
	}
	

}
