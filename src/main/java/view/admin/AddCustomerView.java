package view.admin;

import java.net.URL;
import java.util.ResourceBundle;

import controller.AdminController;
import controller.AdminControllerIF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import view.HyteGUI;
import view.ViewChanger;
import view.enums.Bundles;

/**
 * Class for adding customers to database.
 * @author IdaKi
 *
 */
public class AddCustomerView extends ViewChanger implements Initializable, AddCustomerIF {

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
	
	private AdminControllerIF controller;
	private ResourceBundle bundle;
	
	public AddCustomerView() {
		controller = new AdminController(this);
	}
	
	/**
	 * Method for creating a customer.
	 */
	public void addCustomer() {
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
	

	public void alert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Tiedot");
		alert.setContentText("Tarkista antamasi tiedot");
		alert.showAndWait();
	}
	
	
	
	public void validateAlphabetical(KeyEvent keyEvent) {
		if (keyEvent.getCode().isDigitKey() && keyEvent.getCharacter().matches("<([{\\^=$!|]})?*+.>")) {
			TextField textField = (TextField) keyEvent.getSource();
			switch(textField.getId()) {
			case "firstname":
				
				break;
			case "surname":
				
			}
		}
	}
	
	/**
	 * Checks if the given text field is empty.
	 * @param field Text field which text is checked.
	 * @return Empty string if text field is empty, otherwise the text in the text field.
	 */
	public String checkIfEmpty(TextField field) {
		if(field.getText().isEmpty()) {
			field.setPromptText("!");
			field.setStyle("-fx-prompt-text-fill: red;");
			return "";
		}
		field.setPromptText(bundle.getString(field.getId() + ".prompt"));
		field.setStyle("-fx-prompt-text-fill: #adbace;");
		return field.getText();
	}
	
	/**
	 * Returns the text written in the social security number -field.
	 * @return Customer's social security number.
	 */
	public String getSSN() {
		return checkIfEmpty(this.ssn);
	}

	/**
	 * Returns the text written in the address -field.
	 * @return Customer's home address.
	 */
	public String getAddress() {
		return checkIfEmpty(this.address);
	}

	/**
	 * Returns the text written in the in case of emergency number -field.
	 * @return Customer's ice-number.
	 */
	public String getICE() {
		return checkIfEmpty(this.ice);
	}

	/**
	 * Returns the text written in the email -field.
	 * @return Customer's email address.
	 */
	public String getEmail() {
		return checkIfEmpty(this.email);
	}

	/**
	 * Returns the text written in the phone number -field.
	 * @return Customer's phone number.
	 */
	public String getPhoneNumber() {
		return checkIfEmpty(this.phone);
	}

	/**
	 * Returns the text written in the surname -field.
	 * @return Customer's surname.
	 */
	public String getSurname() {
		return checkIfEmpty(this.surname);
	}

	/**
	 * Returns the text written in the first name -field.
	 * @return Customer's first name.
	 */
	public String getFirstName() {
		return checkIfEmpty(this.firstname);
	}
	
	/**
	 * Returns the text written in the password -field.
	 * @return Customer's password.
	 */
	public String getPassword() {
		return checkIfEmpty(this.password);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.ADMIN.getBundleName(), HyteGUI.getLocale());

		
	}
	

}
