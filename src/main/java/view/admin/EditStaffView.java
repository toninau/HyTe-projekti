package view.admin;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import controller.AdminController;
import controller.AdminControllerIF;
import controller.StaffController;
import controller.StaffControllerIF;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Customer;
import model.Staff;
import view.ViewChanger;

/**
 * Class for editing staff members in database.
 * @author IdaKi
 *
 */
public class EditStaffView extends ViewChanger implements Initializable, EditStaffIF {

	@FXML 
	private Button search;
	@FXML
	private TextField searchStaff;
	@FXML 
	private Button save;
	@FXML
	private Button remove;
	@FXML
	private TextField firstName;
	@FXML
	private TextField surname;
	@FXML
	private TextField email;
	@FXML
	private TextField phoneNumber;
	@FXML
	private Button logout;
	@FXML
	private Button toMenu;
	@FXML
	private Button addCustomerButton;
	@FXML
	private ListView<Customer> adminCustomerListView;
	@FXML
	private ListView<Customer> adminStaffCustomerListView;


	private ArrayList<String> resultSet;

	private AdminControllerIF c;
	private Staff staff;
	
	public EditStaffView() {
		c = new AdminController(this);
		resultSet = new ArrayList<>();
	}
	
	/**
	 * Generates an ArrayList for the suggestion provider.
	 */
	public void allFromDatabase() {
		Staff[] staffs = c.findStaffAll();
        for (Staff s : staffs) {
            resultSet.add(s.getStaffID() + ", " + s.getSurname() + ", " + s.getFirstName());
        }
	}
	
	/**
	 * Shows chosen employee's information from the database in the text fields.
	 */
	public void showInfo() {
		adminCustomerListView.getItems().clear();
		adminStaffCustomerListView.getItems().clear();
		String [] split = searchStaff.getText().split(",");
        String before = split[0];
		staff = c.findStaffWithID(before);
		firstName.setText(staff.getFirstName());
		surname.setText(staff.getSurname());
		email.setText(staff.getStaffID());
		phoneNumber.setText(staff.getPhoneNumber());
		
		populateCustomerListView();
		populateStaffsCustomerListView();
	}
	
	/**
	 * Populates the list view with the staff member's customers.
	 */
	public void populateStaffsCustomerListView() {
		StaffControllerIF staffController = new StaffController();
		StaffController.loggedStaff(staff);
		ObservableList<Customer> staffCustomerList = staffController.getStaffCustomers();

		if (staffCustomerList!=null) {
			adminStaffCustomerListView.getItems().addAll(staffCustomerList);
			adminStaffCustomerListView.setCellFactory(param -> new ListCell<Customer>() {
				@Override
				protected void updateItem(Customer item, boolean empty) {
					super.updateItem(item, empty);
					if (empty || item == null) {
						setText(null);
					} else {
						setText(item.getSurname() + ", " + item.getFirstName());
					}
				}
			});
		}
	}
	
	/**
	 * Populates the list view with all customers from database.
	 */
	public void populateCustomerListView() {
		Customer[] customers = c.findCustomerAll();
		ObservableList<Customer> customerList = FXCollections.observableArrayList(customers);
		adminCustomerListView.getItems().addAll(customerList);
		adminCustomerListView.setCellFactory(param -> new ListCell<Customer>() {
			@Override
			protected void updateItem(Customer item, boolean empty) {
				super.updateItem(item, empty);
				if (empty || item == null) {
					setText(null);
				} else {
					setText(item.getSurname() + ", " + item.getFirstName());
				}
			}
		});
	}
	
	/**
	 * Updates the employee's information according to the text in the text fields.
	 */
	public void updateInfo() {
		staff.setFirstName(getFirstName());
		staff.setSurname(getSurname());
		staff.setStaffID(getEmail());
		staff.setPhoneNumber(getPhoneNumber());
		if(c.updateStaff(staff)) {
			clearFields();
		}
	}
	
	/**
	 * Deletes the chosen employee from database.
	 */
	public void removeStaff() {
		if(c.removeStaffFromDatabase(getEmail())){
			clearFields();
		}
	}
	
	public void clearFields() {
		firstName.clear();
		surname.clear();
		email.clear();
		phoneNumber.clear();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		allFromDatabase();
		TextFields.bindAutoCompletion(searchStaff, SuggestionProvider.create(resultSet));	
	}

	@FXML
	public void addCustomerToStaff() {
		Customer customer = adminCustomerListView.getSelectionModel().getSelectedItem();
		if (c.addCustomerToStaff(customer, staff)) {
			Alert a = new Alert(AlertType.CONFIRMATION);
			a.setTitle("Success");
			a.setContentText("Succesfully added the customer");
			a.show();
			showInfo();
		}
	}
	
	
	/**
	 * @return 
	 */
	public String getFirstName() {
		return this.firstName.getText();
	}
	
	/**
	 * 
	 */
	public String getSurname() {
		return this.surname.getText();
	}
	
	public String getEmail() {
		return this.email.getText();
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber.getText();
	}
	
	
	
}
