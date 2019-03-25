package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.Customer;
import model.CustomerDAO;
import model.DAOManager;
import model.Staff;
import view.AdminViewController;
import view.LoginViewController;

public class Controller {
	private AdminViewController ac;
	private DAOManager daoM;
	
	public Controller(AdminViewController ac) {
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
		Staff[] kaikki = (Staff[])daoM.readAll(1);
		for (Staff staff : kaikki) {
			System.out.println(staff.getFirstName());
		}
	}
	
	public void addCustomer() {
		Customer customer = new Customer();
		String hetu = ac.getCustHetu();
		String etunimi = ac.getCustFirstname();
		String sukunimi = ac.getCustSurname();
		String puhnro = ac.getCustPhone();
		String email = ac.getCustEmail();
		String ICE = ac.getCustICE();
		String osoite = ac.getCustAddress();
		CustomerDAO ao = daoM.getCustomerDAO();
		ao.create(customer);
		String[] info = { etunimi, sukunimi, puhnro, email, hetu, ICE, osoite };

		for (String string : info) {
			if (string == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Tiedot");
				alert.setContentText("Tarkista tiedot");
				alert.showAndWait();
			}
			System.out.println(string);
		}
	}
	
	public Staff[] findStaff() {
		return (Staff[])daoM.readAll(1);
		
	}
	
}
