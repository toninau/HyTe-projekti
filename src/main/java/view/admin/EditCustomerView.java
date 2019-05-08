package view.admin;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import controller.AdminController;
import controller.AdminControllerIF;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Customer;
import view.ViewChanger;

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
	private AdminControllerIF c;
	private Customer customer;
	
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
        for (Customer cust : customers) {
            resultSet.add(cust.getCustomerID() + ", " + cust.getSurname() + ", " + cust.getFirstName());
        }
	}

	/**
	 * Fills the text fields with chosen customer's information.
	 */
	public void showInfo() {
		String [] split = findCustomer.getText().split(",");	
		String before = split[0];
		customer = c.findCustomerWithID(before);
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
	public void remove() {
		if(c.removeCustomerFromDatabase(getEmail())){
			clearFields();
		}
	}
	
	/**
	 * Clears the fields.
	 */
	public void clearFields() {
		firstname.clear();
		surname.clear();
		email.clear();
		phone.clear();
		address.clear();
		ice.clear();
		ssn.clear();	
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
