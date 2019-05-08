package controller;

import com.lambdaworks.crypto.SCryptUtil;
import model.Customer;
import model.DAOManager;
import model.DAOManagerIF;
import model.Staff;
import view.admin.*;

/**
 * Controller class for admin's views. 
 * @author IdaKi
 *
 */
public class AdminController implements AdminControllerIF {

	private AddStaffIF addstaff;
	private AddCustomerIF addcustomer;
	private DAOManagerIF daoM;

	/**
	 * Empty constructor.
	 */
	public AdminController() {
		daoM = new DAOManager();
	}

	/**
	 * Constructor for add staff view.
	 * @see model.DAOManager#DAOManager()
	 * @param addstaff AddStaffView.
	 */
	public AdminController(AddStaffView addstaff) {
		this.addstaff = addstaff;
		if (daoM == null)
			daoM = new DAOManager();
	}

	/**
	 * Constructor for add customer view.
	 * @see model.DAOManager#DAOManager()
	 * @param addcustomer AddCustomerView.
	 */
	public AdminController(AddCustomerView addcustomer) {
		this.addcustomer = addcustomer;
		if (daoM == null)
			daoM = new DAOManager();
	}

	/**
	 * Constructor for edit staff view.
	 * @see model.DAOManager#DAOManager()
	 * @param editstaff EditStaffView.
	 */
	public AdminController(EditStaffView editstaff) {
		if (daoM == null)
			daoM = new DAOManager();
	}

	/**
	 * Constructor for edit customer view.
	 * @see model.DAOManager#DAOManager()
	 * @param editcustomer EditCustomerView.
	 */
	public AdminController(EditCustomerView editcustomer) {
		if (daoM == null)
			daoM = new DAOManager();
	}

	/**
	 * Method for adding an employee to database.
	 * @return <code> true </code> if creating the staff member succeeded. <br>
	 * 			<code> false </code> if creating the staff member failed.
	 * @see dao.StaffDAO#create(Staff)
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
			hkunta.setAccessLevel(ammatti);
			hkunta.setPassword(pw);
			daoM.create(hkunta);
		}
		return success;
	}

	/**
	 * Method for adding a customer to database.
	 * @return <code> true </code> if creating the customer succeeded. <br>
	 * 			<code> false </code> if creating the customer failed.
	 * @see dao.CustomerDAO#create(Customer)
	 */
	public boolean addCustomer() {
		Customer customer = new Customer();
		boolean success = true;

		String hetu = addcustomer.getSSN();
		String etunimi = addcustomer.getFirstName();
		String sukunimi = addcustomer.getSurname();
		String puhnro = addcustomer.getPhoneNumber();
		String email = addcustomer.getEmail();
		String ice = addcustomer.getICE();
		String osoite = addcustomer.getAddress();
		String pw = encryptPassword(addcustomer.getPassword());
		String[] info = { etunimi, sukunimi, puhnro, email, hetu, ice, osoite, pw };
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
			customer.setAddress(osoite);
			customer.setIceNumber(ice);
			customer.setSSN(hetu);
			customer.setPassword(pw);
			daoM.create(customer);
		}
		return success;
	}

	/**
	 * Method for updating the information of an employee.
	 * 
	 * @return <code> true </code> if updating the employee succeeded. <br>
	 * <code> false </code> if updating the employee failed.
	 */
	public boolean updateStaff(Staff f) {
		return daoM.update(f);
	}

	/**
	 * Method for adding a customer to an employee.
	 * 
	 * @return <code> true </code> if adding the customer succeeded. <br>
	 *         <code> false </code> if adding the customer failed.
	 */
	public boolean addCustomerToStaff(Customer customer, Staff staff) {
		return daoM.getStaffDAO().addCustomerToStaff(customer, staff);
	}

	/**
	 * Method for updating customer in database.
	 * 
	 * @see model.DAOManager#update(Object)
	 * @see dao.CustomerDAO#update(Customer)
	 */
	public void updateCustomer(Customer customer) {
		daoM.update(customer);
	}

	/**
	 * Method for reading all staff members from database.
	 * 
	 * @return An array of staff members.
	 * @see model.DAOManager#readAll(String)
	 * @see dao.StaffDAO#readAll()
	 */
	public Staff[] findStaffAll() {
		return (Staff[]) daoM.readAll("staff");
	}

	/**
	 * Method for reading all customers from database.
	 * 
	 * @return An array of customers.
	 * @see model.DAOManager#readAll(String)
	 * @see dao.CustomerDAO#readAll()
	 */
	public Customer[] findCustomerAll() {
		return (Customer[]) daoM.readAll("customer");
	}

	/**
	 * Reads a staff member with their id.
	 * 
	 * @param id Staff member's id.
	 * @return Staff member found from database with given id.
	 * @see model.DAOManager#readWithID(int, String)
	 */
	public Staff findStaffWithID(String id) {
		return (Staff) daoM.readPersonWithID("staff", id);
	}

	/**
	 * Reads a customer with their id.
	 * 
	 * @param id Customer's id.
	 * @return Customer found from database with given id.
	 * @see model.DAOManager#readWithID(int, String)
	 */
	public Customer findCustomerWithID(String id) {
		return (Customer) daoM.readPersonWithID("customer", id);
	}

	/**
	 * Removes the chosen staff member.
	 * 
	 * @param id The id of the staff member to be deleted.
	 * @return <code> true </code> if the deletion is successful. <br>
	 *         <code>false</code> if the deletion is not successful
	 * @see dao.StaffDAO#delete(String)
	 * 
	 */
	public boolean removeStaffFromDatabase(String id) {
		return daoM.getStaffDAO().delete(id);
	}

	/**
	 * Removes the chosen customer.
	 * 
	 * @param id The id of the customer to be deleted.
	 * @return <code> true </code> if the deletion is successful. <br>
	 *         <code>false</code> if the deletion is not successful
	 * @see dao.CustomerDAO#delete(String)
	 * 
	 */
	public boolean removeCustomerFromDatabase(String id) {
		return daoM.getCustomerDAO().delete(id);
	}

	/**
	 * Encrypts the password using SCryptUtil.
	 * 
	 * @param password Password to be encrypted.
	 * @return Encrypted password.
	 * @see SCryptUtil#scrypt(String, int, int, int)
	 */
	public String encryptPassword(String password) {
		String originalPassword = password;
		return SCryptUtil.scrypt(originalPassword, 16, 16, 16);
	}

}
