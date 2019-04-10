package view;

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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Customer;

/**
 * Class for editing customers in database.
 * @author IdaKi
 *
 */
public class EditCustomerView extends ViewChanger implements Initializable, EditCustomerView_IF {

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
	private Button logout;
	@FXML
	private Button toMenu;
	
	private ArrayList<String> resultSet;
	private AdminController_IF c;
	Customer customer;
	
	public EditCustomerView() {
		c = new AdminController(this);
		resultSet = new ArrayList<>();
	}
	
	/**
	 * Gets all customers from database.
	 */
	public void allCustomers() {
		Customer[] customers = c.findCustomerAll();
        for (Customer customer : customers) {
            resultSet.add(customer.getCustomerID() + ", " + customer.getSurname() + ", " + customer.getFirstName());
        }
	}

	public void showCustomerInfo() {
		//String [] split = findCustomer.getText().split(",");
		//int before = Integer.parseInt(split[0]);
		customer = c.findCustomerWithID(customer.getCustomerID());
		
		firstname.setText(customer.getFirstName());
		surname.setText(customer.getSurname());
		email.setText(customer.getCustomerID());
		ssn.setText(customer.getSSN());
		phone.setText(customer.getPhoneNumber());
		ice.setText(customer.getIceNumber());
		address.setText(customer.getAddress());
	}
	
	public void updateCustomerInfo() {
		customer.setFirstName(getCustFirstname());
		customer.setSurname(getCustSurname());
		customer.setAddress(getCustAddress());
		customer.setIceNumber(getCustICE());
		customer.setPhoneNumber(getCustPhone());
		customer.setSSN(getCustHetu());
		customer.setCustomerID(getCustEmail());
		c.updateCustomer(customer);	
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
		allCustomers();
		TextFields.bindAutoCompletion(findCustomer, SuggestionProvider.create(resultSet));
	}
	
	/**
	 * Returns the text written in the social security number -field.
	 * @return Customer's social security number.
	 */
	public String getCustHetu() {
		return this.ssn.getText();
	}

	/**
	 * Returns the text written in the address -field.
	 * @return Customer's home address.
	 */
	public String getCustAddress() {
		return this.address.getText();
	}

	/**
	 * Returns the text written in the in case of emergency number -field.
	 * @return Customer's ICE-number.
	 */
	public String getCustICE() {
		return this.ice.getText();
	}

	/**
	 * Returns the text written in the email -field.
	 * @return Customer's email address.
	 */
	public String getCustEmail() {
		return this.email.getText();
	}

	/**
	 * Returns the text written in the phone number -field.
	 * @return Customer's phone number.
	 */
	public String getCustPhone() {
		return this.phone.getText();
	}

	/**
	 * Returns the text written in the surname -field.
	 * @return Customer's surname.
	 */
	public String getCustSurname() {
		return this.surname.getText();
	}

	/**
	 * Returns the text written in the first name -field.
	 * @return Customer's first name.
	 */
	public String getCustFirstname() {
		return this.firstname.getText();
	}
	
}
