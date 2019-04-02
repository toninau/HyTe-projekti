package controller;

import com.lambdaworks.crypto.SCryptUtil;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Customer;
import model.DAOManager;
import model.DAOManager_IF;
import model.Staff;
import view.AddCustomerView;
import view.AddStaffView;
import view.AddStaffView_IF;
import view.EditCustomerView;
import view.EditStaffView;

public class AdminController implements AdminController_IF {
	
	private AddStaffView_IF addstaff;
	private AddCustomerView addcustomer;
	private EditStaffView editstaff;
	private EditCustomerView editcustomer;
	private DAOManager_IF daoM;
	

	public AdminController(AddStaffView addstaff) {
		this.addstaff = addstaff;
		daoM  = new DAOManager();
	}
	public AdminController(AddCustomerView addcustomer) {
		this.addcustomer = addcustomer;
		daoM = new DAOManager();
	}
	public AdminController(EditStaffView editstaff) {
		this.editstaff = editstaff;
		daoM  = new DAOManager();
	}
	public AdminController(EditCustomerView editcustomer) {
		this.editcustomer = editcustomer;
		daoM = new DAOManager();
	}
	
	/**
	 * Method for adding an employee to database.
	 */
	public void addStaff() {
		Staff hkunta = new Staff();
		String etunimi = addstaff.getStaffFirstName();
		String sukunimi = addstaff.getStaffSurname();
		String puhnro = addstaff.getStaffPhone();
		String email = addstaff.getStaffEmail();
		String ammatti = addstaff.getProfession();
		String pw = encryptPassword(addstaff.getPassword());

		String[] info = { etunimi, sukunimi, puhnro, email, ammatti, pw };
		boolean onnistui = true;
		for (String string : info) {
			if (string.isEmpty()) {
				onnistui = false;
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Tiedot");
				alert.setContentText("Tarkista tiedot");
				alert.showAndWait();
				break;
			}
		}	
		if (onnistui) {
			hkunta.setFirstName(etunimi);
			hkunta.setSurname(sukunimi);
			hkunta.setPhoneNumber(puhnro);
			hkunta.setEmail(email);
			hkunta.setAccessLevel(ammatti);
			hkunta.setPassword(pw);
			daoM.create(hkunta);
		}
	}
	
	public void addCustomer() {
		Customer customer = new Customer();
		boolean success = true;
		String hetu = addcustomer.getCustHetu();
		String etunimi = addcustomer.getCustFirstname();
		String sukunimi = addcustomer.getCustSurname();
		String puhnro = addcustomer.getCustPhone();
		String email = addcustomer.getCustEmail();
		String ICE = addcustomer.getCustICE();
		String osoite = addcustomer.getCustAddress();

		
		String[] info = { etunimi, sukunimi, puhnro, email, hetu, ICE, osoite };

		for (String string : info) {
			if (string == null) {
				success = false;
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Tiedot");
				alert.setContentText("Tarkista tiedot");
				alert.showAndWait();
			}
			System.out.println(string);
		}
		
		if (success) {
			customer.setFirstName(etunimi);
			customer.setSurname(sukunimi);
			customer.setPhoneNumber(puhnro);
			customer.setEmail(email);
			customer.setAddress(osoite);
			customer.setIceNumber(ICE);
			customer.setSSN(hetu);
			daoM.create(customer);
		}
	}
	
	public void updateStaff(Staff f) {
		daoM.update(f);
	}
	
	public void updateCustomer(Customer customer) {
		daoM.update(customer);
	}
	
	public Staff[] findStaffAll() {
		return (Staff[])daoM.readAll("staff");
	}
	
	public Customer[] findCustomerAll() {
		return(Customer[]) daoM.readAll("customer");
	}
	
	public Staff findStaffWithID(int id) {
		return (Staff)daoM.readWithID(id, "staff");
	}
	
	public Customer findCustomerWithID(int id) {
		return (Customer)daoM.readWithID(id, "customer");
	}
	
	public String generateUsername(String firstname, String surname) {
		String username = firstname.substring(0, 2) + surname.substring(0, 2);
		
		return username;
	}
	
	public String encryptPassword(String password) {
		String originalPassword = password;
		//SCryptUtil.check(passwd, hashed);
		return SCryptUtil.scrypt(originalPassword, 16, 16, 16);
	}


	
}
