package controller;

import com.lambdaworks.crypto.SCryptUtil;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import kotlin.reflect.jvm.internal.impl.util.CheckResult.SuccessCheck;
import model.Customer;
import model.CustomerDAO;
import model.DAOManager;
import model.Staff;
import view.AdminView;
import view.LoginView;

public class AdminController {
	private AdminView ac;
	private DAOManager daoM;
	
	public AdminController(AdminView ac) {
		this.ac = ac;
		daoM  = new DAOManager();
	}
	
	public void addStaff() {
		Staff hkunta = new Staff();
		String etunimi = ac.getStaffFirstName();
		String sukunimi = ac.getStaffSurname();
		String puhnro = ac.getStaffPhone();
		String email = ac.getStaffEmail();
		String ammatti = ac.getProfession();
		String[] info = { etunimi, sukunimi, puhnro, email, ammatti };
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
			System.out.println(string);
		}

		if (onnistui) {
			hkunta.setFirstName(etunimi);
			hkunta.setSurname(sukunimi);
			hkunta.setPhoneNumber(puhnro);
			hkunta.setEmail(email);
			hkunta.setAccessLevel(ammatti);
			daoM.create(hkunta);
		}
		Staff[] kaikki = (Staff[])daoM.readAll("staff");
		for (Staff staff : kaikki) {
			System.out.println(staff.getFirstName());
		}
	}
	
	public void addCustomer() {
		Customer customer = new Customer();
		boolean success = true;
		String hetu = ac.getCustHetu();
		String etunimi = ac.getCustFirstname();
		String sukunimi = ac.getCustSurname();
		String puhnro = ac.getCustPhone();
		String email = ac.getCustEmail();
		String ICE = ac.getCustICE();
		String osoite = ac.getCustAddress();

		
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
	
	public Staff[] findStaffAll() {
		return (Staff[])daoM.readAll("staff");
		
	}
	
	public Customer[] findCustomerAll() {
		return(Customer[]) daoM.readAll("customer");
	}
	
	public Staff findStaffWithID(int id) {
		return (Staff)daoM.readWithID(id, "staff");
	}
	
	public void encryptPassword(String password) {
		String originalPassword = password;
		String encryptedPassword = SCryptUtil.scrypt(originalPassword, 16, 16, 16);
	}
	
}
