package view.admin;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import controller.AdminController;
import controller.AdminController_IF;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import model.Customer;
import view.HyteGUI;
import view.ViewChanger;
import view.enums.Bundles;

/**
 * Class for editing customers in database.
 * @author IdaKi
 *
 */
public class EditCustomerView extends ViewChanger implements Initializable, EditCustomerIF {

	@FXML
	private TextField findCustomer;
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
	private Button confirmEdit;
	@FXML
	private Button findButton;
	@FXML
	private Button remove;
	@FXML
	private Button logout;
	@FXML
	private Button toMenu;
	
	private ArrayList<String> resultSet;
	private AdminController_IF c;
	private Customer customer;
	private ResourceBundle bundle;
	
	/**
	 * 
	 */
	public EditCustomerView() {
		c = new AdminController(this);
		resultSet = new ArrayList<>();
	}
	
	/**
	 * Gets all customers from database for suggestion provider.
	 */
	public void allFromDatabase() {
		Customer[] customers = c.findCustomerAll();
        for (Customer customer : customers) {
            resultSet.add(customer.getCustomerID() + ", " + customer.getSurname() + ", " + customer.getFirstName());
        }
	}

	/**
	 * Fills the text fields with chosen customer's information.
	 */
	public void showInfo() {
		customer = c.findCustomerWithID(customer.getCustomerID());
		firstname.setText(customer.getFirstName());
		surname.setText(customer.getSurname());
		email.setText(customer.getCustomerID());
		ssn.setText(customer.getSSN());
		phone.setText(customer.getPhoneNumber());
		ice.setText(customer.getIceNumber());
		address.setText(customer.getAddress());
	}
	
	/**
	 * Updates the customer's information with the text found in text fields.
	 */
	public void updateInfo() {
		customer.setFirstName(getFirstName());
		customer.setSurname(getSurname());
		customer.setAddress(getAddress());
		customer.setIceNumber(getICE());
		customer.setPhoneNumber(getPhoneNumber());
		customer.setSSN(getSSN());
		customer.setCustomerID(getEmail());
		c.updateCustomer(customer);	
	}
	
	/**
	 * Deletes the chosen customer from database.
	 */
	public void removeCustomer() {
		if(c.removeStaffFromDatabase(getEmail())){
			clearFields();
		}else {
			alert("remove");
		}
	}
	
	public void clearFields() {
		firstname.clear();
		surname.clear();
		email.clear();
		phone.clear();
		address.clear();
		ice.clear();
		ssn.clear();	
	}
	
	public void alert(String msg) {
		String title;
		switch (msg) {
		case "remove":
			msg = bundle.getString("loginFailed.username");
			title = bundle.getString("loginFailed.title");
			break;
		case "update":
			msg = bundle.getString("loginFailed.password");
			title = bundle.getString("loginFailed.title");
			break;
		default:
			msg = "Login failed.";
			title = "Login failed";
			break;
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
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
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.ADMIN.getBundleName(), HyteGUI.getLocale());

		allFromDatabase();
		TextFields.bindAutoCompletion(findCustomer, SuggestionProvider.create(resultSet));
	}
	
	/**
	 * Returns the text written in the social security number -field.
	 * @return Customer's social security number.
	 */
	public String getSSN() {
		return this.ssn.getText();
	}

	/**
	 * Returns the text written in the address -field.
	 * @return Customer's home address.
	 */
	public String getAddress() {
		return this.address.getText();
	}

	/**
	 * Returns the text written in the in case of emergency number -field.
	 * @return Customer's ICE-number.
	 */
	public String getICE() {
		return this.ice.getText();
	}

	/**
	 * Returns the text written in the email -field.
	 * @return Customer's email address.
	 */
	public String getEmail() {
		return this.email.getText();
	}

	/**
	 * Returns the text written in the phone number -field.
	 * @return Customer's phone number.
	 */
	public String getPhoneNumber() {
		return this.phone.getText();
	}

	/**
	 * Returns the text written in the surname -field.
	 * @return Customer's surname.
	 */
	public String getSurname() {
		return this.surname.getText();
	}

	/**
	 * Returns the text written in the first name -field.
	 * @return Customer's first name.
	 */
	public String getFirstName() {
		return this.firstname.getText();
	}
	
}
