package controller;

import com.lambdaworks.crypto.SCryptUtil;

import model.Customer;
import model.DAOManager;
import model.DAOManager_IF;
import model.Staff;
import view.admin.AddCustomerView;
import view.admin.AddCustomerIF;
import view.admin.AddStaffView;
import view.admin.AddStaffIF;
import view.admin.EditCustomerView;
//import view.admin.EditCustomerIF;
import view.admin.EditStaffView;
//import view.admin.EditStaffIF;

public class AdminController implements AdminController_IF {

	private AddStaffIF addstaff;
	private AddCustomerIF addcustomer;
	private DAOManager_IF daoM;

	public AdminController() {
		daoM = new DAOManager();
	}

	public AdminController(AddStaffView addstaff) {

		this.addstaff = addstaff;
		if (daoM == null)
			daoM = new DAOManager();

	}

	public AdminController(AddCustomerView addcustomer) {
		this.addcustomer = addcustomer;
		if (daoM == null)
			daoM = new DAOManager();
	}

	public AdminController(EditStaffView editstaff) {
		// EditStaffIF es = editstaff;
		if (daoM == null)
			daoM = new DAOManager();
	}

	public AdminController(EditCustomerView editcustomer) {
		// EditCustomerIF ec = editcustomer;
		if (daoM == null)
			daoM = new DAOManager();
	}

	/**
	 * Method for adding an employee to database.
	 */
	public boolean addStaff() {
		Staff hkunta = new Staff();
		String etunimi = addstaff.getFirstName();
		String sukunimi = addstaff.getSurname();
		String puhnro = addstaff.getPhoneNumber();
		String email = addstaff.getEmail();
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

		String hetu = addcustomer.getSSN();
		String etunimi = addcustomer.getFirstName();
		String sukunimi = addcustomer.getSurname();
		String puhnro = addcustomer.getPhoneNumber();
		String email = addcustomer.getEmail();
		String ICE = addcustomer.getICE();
		String osoite = addcustomer.getAddress();
		String pw = encryptPassword(addcustomer.getPassword());
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
		if (daoM.update(f)) {
			return true;
		}
		return false;
	}

	public void updateCustomer(Customer customer) {
		daoM.update(customer);
	}

	public Staff[] findStaffAll() {
		return (Staff[]) daoM.readAll("staff");
	}

	public Customer[] findCustomerAll() {
		return (Customer[]) daoM.readAll("customer");
	}

	public Staff findStaffWithID(String id) {
		return (Staff) daoM.readWithEmail("staff", id);
	}

	public Customer findCustomerWithID(String id) {
		return (Customer) daoM.readWithEmail("customer", id);
	}

	public boolean removeStaffFromDatabase(String id) {
		return daoM.getStaffDAO().delete(id);
	}

	public boolean removeCustomerFromDatabase(String id) {
		return daoM.getCustomerDAO().delete(id);
	}

	public String encryptPassword(String password) {
		String originalPassword = password;
		return SCryptUtil.scrypt(originalPassword, 16, 16, 16);
	}

}
