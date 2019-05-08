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
import view.HyteGUI;
import view.ViewChanger;
import view.enums.Bundles;

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
	ResourceBundle bundle;
	
	public EditStaffView() {
		c = new AdminController(this);
		resultSet = new ArrayList<>();
	}
	
	/**
	 * Generates an ArrayList for the suggestion provider.
	 */
	public void allFromDatabase() {
		Staff[] staffs = c.findStaffAll();
        for (Staff staff : staffs) {
            resultSet.add(staff.getStaffID() + ", " + staff.getSurname() + ", " + staff.getFirstName());
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
		ObservableList<Customer> customerList = FXCollections.observableArrayList();
		Customer[] staffCustomers = c.findCustomerAll();
		StaffControllerIF staffController = new StaffController();
		StaffController.loggedStaff(staff);
		ObservableList<Customer> StaffCustomerList = staffController.getStaffCustomers();
		for (Customer customer : staffCustomers) {
			for (Customer c : StaffCustomerList) {
				if (!c.getFirstName().equals(customer.getFirstName())) {
					customerList.add(customer);
				}
			}
		}
		if (StaffCustomerList!=null) {
			adminStaffCustomerListView.getItems().addAll(StaffCustomerList);
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
		}else {
			alert("update");

		}
	}
	
	/**
	 * Deletes the chosen employee from database.
	 */
	public void removeStaff() {
		if(c.removeStaffFromDatabase(getEmail())){
			clearFields();
		}else {
			alert("remove");
		}
	}
	
	public void clearFields() {
		firstName.clear();
		surname.clear();
		email.clear();
		phoneNumber.clear();
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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.ADMIN.getBundleName(), HyteGUI.getLocale());
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
