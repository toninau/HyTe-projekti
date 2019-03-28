package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import controller.AdminController;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class AddStaffView extends ViewChanger implements Initializable{
	
	@FXML
	private TextField fNameStaff;
	@FXML
	private TextField sNameStaff;
	@FXML
	private TextField phoneNroStaff;
	@FXML
	private TextField emailStaff;
	@FXML
	private ChoiceBox<String> profession;
	
	@FXML
	private Button addStaff;
	
	@FXML
	private Button logout;
	@FXML
	private Button toMenu;
	
	private AdminController c;

	/**
	 * Consturctor for AdminView. Creates a controller and a suggestionHandler.
	 */
	public AddStaffView() {
		
	}
	
	/**
	 * Metodi työntekijän luontia varten. Käynnistyy napin painalluksesta.
	 */
	public void addStaff() {
		//c.addStaff();
		fNameStaff.clear();
		sNameStaff.clear();
		emailStaff.clear();
		phoneNroStaff.clear();

	}
	
	public void logout(MouseEvent event) throws IOException {
		String fxml = "/LoginView.fxml";
		String title = "Login";
		sceneContent(fxml, event, title);
	}
	
	public void toMenu(MouseEvent event) throws IOException {
		String fxml = "/AdminMenuView.fxml";
		String title = "Menu";
		sceneContent(fxml, event, title);
	}
	
	/**
	 * Palauttaa työntekijän etunimen.
	 * 
	 * @return Työnteijän etunimi.
	 */
	public String getStaffFirstName() {
		return this.fNameStaff.getText();
	}

	/**
	 * Palauttaa työntekijän sukunimen.
	 * 
	 * @return Työntekijän sukunimi.
	 */
	public String getStaffSurname() {
		return this.sNameStaff.getText();
	}

	/**
	 * Palauttaa työntekijän puhelinnumeron.
	 * 
	 * @return Työntekijän puhelinnumero.
	 */
	public String getStaffPhone() {
		return this.phoneNroStaff.getText();
	}

	/**
	 * Palauttaa työntekijän sähköpostiosoitteen.
	 * 
	 * @return Työntekijän sähköpostiosoite.
	 */
	public String getStaffEmail() {
		return this.emailStaff.getText();
	}

	/**
	 * Palauttaa työntekijän ammatin.
	 * 
	 * @return Työntekijän ammatti.
	 */
	public String getProfession() {
		return (String) this.profession.getValue();
	}

	
	public void initialize(URL location, ResourceBundle resources) {
		profession.setValue("Lääkäri");
		profession.getItems().add("Lääkäri");
		profession.getItems().add("Hoitaja");
		profession.getItems().add("Asiakaspalvelija");

	}

}
