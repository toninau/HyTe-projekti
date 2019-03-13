package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Asiakas;
import model.AsiakasAccessObject;
import model.Henkilökunta;
import model.HenkilökuntaAccessObject;
import view.HyteGUI_IF;

public class AdminViewController  implements Initializable  {

	@FXML Tab editTab;	
	@FXML Button logout;
	
	@FXML Tab addTab;
	@FXML TextField fNameStaff; 
	@FXML TextField sNameStaff; 
	@FXML TextField phoneNroStaff; 
	@FXML TextField emailStaff;
	@FXML ChoiceBox<String> profession;
	@FXML Button addCustomer; 
	
	@FXML Button addStaff;
	@FXML TextField hetuCust; 
	@FXML TextField fNameCust; 
	@FXML TextField sNameCust; 
	@FXML TextField phoneNroCust; 
	@FXML TextField emailCust; 
	@FXML TextField addressCust; 
	@FXML TextField ICECust; 

	@FXML Button find;
	@FXML Button findCustomer;
	@FXML TextField staffID;
	@FXML TextField customerID;
	
<<<<<<< HEAD
	private AsiakasAccessObject accessObject;
=======
	private HenkilökuntaAccessObject hDAO;
>>>>>>> branch 'dev' of https://github.com/toninau/HyTe-projekti/
	
	public AdminViewController() {
		hDAO = new HenkilökuntaAccessObject();
	}

	@FXML
	public void init() throws IOException {	
	}
	
	
	public void addCustomer() {
		Asiakas asiakas = new Asiakas();
		String hetu = getCustHetu();
		String etunimi = getCustFirstname();
		String sukunimi = getCustSurname();
		String puhnro = getCustPhone();
		String email = getCustEmail();
		String ICE = getCustICE();
		String osoite = getCustAddress();
		accessObject.createAsiakas(asiakas);
		String[] info = {etunimi, sukunimi, puhnro, email, hetu,
				ICE, osoite};
		
		for (String string : info) {
			if(string == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Tiedot");
				alert.setContentText("Tarkista tiedot");
				alert.showAndWait();
			}
			System.out.println(string);
		}
		
	}
	
	public void addStaff() {
		Henkilökunta hkunta = new Henkilökunta();
		String etunimi = getStaffFirstName();
		String sukunimi = getStaffSurname();
		String puhnro = getStaffPhone();
		String email = getStaffEmail();
		String ammatti = getProfession();
		String[] info = {etunimi, sukunimi, puhnro, email, ammatti};
		boolean onnistui = true;
		for (String string : info) {
			if(string.isEmpty()) {
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
<<<<<<< HEAD
			
		/*hkunta.setEtunimi(etunimi);
		hkunta.setSukunimi(sukunimi);
		hkunta.setPuhnumero(puhnro);
		hkunta.setSposti(email);
		hkunta.setOikeus(ammatti);*/
=======
		if (onnistui) {
			hkunta.setEtunimi(etunimi);
			hkunta.setSukunimi(sukunimi);
			hkunta.setPuhnumero(puhnro);
			hkunta.setSposti(email);
			hkunta.setOikeus(ammatti);
			hDAO.createHenkilökunta(hkunta);
		}
		Henkilökunta [] kaikki = hDAO.readAll();
		for (Henkilökunta henkilökunta : kaikki) {
			System.out.println(henkilökunta.getEtunimi());
		}
>>>>>>> branch 'dev' of https://github.com/toninau/HyTe-projekti/
	}
		

		
	public void findStaff() {
		Henkilökunta henk = new Henkilökunta();	
		if(henk.getHenkilökuntaID() != getStaffID()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Tiedot");
			alert.setContentText("Tarkista tiedot");
			alert.showAndWait();
		}else {
			String[] tiedot = {henk.getEtunimi(), henk.getSukunimi(),
					henk.getPuhnumero(), henk.getSposti(), henk.getOikeus()};
				for (String string : tiedot) {
					System.out.println(string);
				}
		}
	}
	
	public void findCustomer() {
		Asiakas asiakas = new Asiakas();	
		if(asiakas.getAsiakasID() != getCustomerID()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Tiedot");
			alert.setContentText("Tarkista tiedot");
			alert.showAndWait();
		}else {
			String[] tiedot = {asiakas.getEtunimi(), asiakas.getSukunimi(),
				asiakas.getHetu(), asiakas.getKotiosoite(), asiakas.getPuhnumero(),
				asiakas.getIcenumero(), asiakas.getSposti()};
					
			for (String string : tiedot) {
				System.out.println(string);
			}
		}	
	}
	
	@FXML
	public void logout() {
		
	}
	public String getCustHetu() {
		return this.hetuCust.getText();
	}
	public String getCustAddress() {
		return this.addressCust.getText();
	}
	public String getCustICE() {
		return this.ICECust.getText();
	}
	public String getCustEmail() {
		return this.emailCust.getText();
	}
	public String getCustPhone() {
		return this.phoneNroCust.getText();
	}
	public String getCustSurname() {
		return this.sNameCust.getText();
	}
	public String getCustFirstname() {
		return this.fNameCust.getText();
	}	
	public int getCustomerID() {
		return Integer.parseInt(this.customerID.getText());
	}
	public int getStaffID() {
		return Integer.parseInt(this.staffID.getText());
	}	
	public String getStaffFirstName() {
		return this.fNameStaff.getText();
	}
	public String getStaffSurname() {
		return this.sNameStaff.getText();
	}
	public String getStaffPhone() {
		return this.phoneNroStaff.getText();
	}
	public String getStaffEmail() {
		return this.emailStaff.getText();
	}
	public String getProfession() {
		return (String)this.profession.getValue();
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		profession.setValue("Lääkäri");
		profession.getItems().add("Lääkäri");
		profession.getItems().add("Hoitaja");
		profession.getItems().add("Asiakaspalvelija");
	}
	
}
