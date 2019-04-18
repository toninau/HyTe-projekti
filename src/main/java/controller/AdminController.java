package controller;


import com.lambdaworks.crypto.SCryptUtil;

import model.Customer;
import model.DAOManager;
import model.DAOManager_IF;
import model.Staff;
import view.AddCustomerView;
import view.AddCustomerView_IF;
import view.AddStaffView;
import view.AddStaffView_IF;
import view.EditCustomerView;
import view.EditCustomerView_IF;
import view.EditStaffView;
import view.EditStaffView_IF;

public class AdminController implements AdminController_IF {
	
	private AddStaffView_IF addstaff;
	private AddCustomerView_IF addcustomer;
	private EditStaffView_IF editstaff;
	private EditCustomerView_IF editcustomer;
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
	public boolean addStaff() {
		Staff hkunta = new Staff();
		String etunimi = addstaff.getStaffFirstName();
		String sukunimi = addstaff.getStaffSurname();
		String puhnro = addstaff.getStaffPhone();
		String email = addstaff.getStaffEmail();
		String ammatti = addstaff.getProfession();
		String pw = encryptPassword(addstaff.getPassword());
		
		String[] info = { etunimi, sukunimi, puhnro, email, ammatti, pw };
		boolean success = true;
		for (String string : info) {
			if (string.isEmpty()) {
				success = false;
				addstaff.alert();
				break;
			}
		}	
		if (success) {
			hkunta.setFirstName(etunimi);
			hkunta.setSurname(sukunimi);
			hkunta.setPhoneNumber(puhnro);
			hkunta.setStaffID(email);
			hkunta.setAccessLevel(ammatti);
			hkunta.setPassword(pw);
			daoM.create(hkunta);
		}
		return success;
	}
	
	/**
	 * Method for adding a customer to database.
	 */
	public boolean addCustomer() {
		Customer customer = new Customer();
		boolean success = true;
		
		String hetu = addcustomer.getCustHetu();
		String etunimi = addcustomer.getCustFirstname();
		String sukunimi = addcustomer.getCustSurname();
		String puhnro = addcustomer.getCustPhone();
		String email = addcustomer.getCustEmail();
		String ICE = addcustomer.getCustICE();
		String osoite = addcustomer.getCustAddress();
		String pw = encryptPassword(addcustomer.getCustPassword());
		String[] info = { etunimi, sukunimi, puhnro, email, hetu, ICE, osoite, pw };
		for (String string : info) {
			if (string.isEmpty()) {
				success = false;
				addcustomer.alert();
				break;
			}
		}
		if (success) {
			customer.setFirstName(etunimi);
			customer.setSurname(sukunimi);
			customer.setPhoneNumber(puhnro);
			customer.setCustomerID(email);
			customer.setAddress(osoite);
			customer.setIceNumber(ICE);
			customer.setSSN(hetu);
			customer.setPassword(pw);
			daoM.create(customer);
		}
		return success;
	}
	

	
	/**
	 * Method for updating the information of an employee.
	 * 
	 */
	public boolean updateStaff(Staff f) {
		if(daoM.update(f)) {
			return true;
		}
		return false;
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
	
	public Staff findStaffWithID(String id) {
		return (Staff)daoM.readWithEmail("staff", id);
	}
	
	public Customer findCustomerWithID(String id) {
		return (Customer)daoM.readWithEmail("customer",id );
	}
	
	public boolean checkEmailValidity(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}
	
	public String encryptPassword(String password) {
		String originalPassword = password;
		return SCryptUtil.scrypt(originalPassword, 16, 16, 16);
	}

	
}
