package view;

import java.awt.Button;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class AdminMenuView extends ViewChanger {
	
	@FXML
	Button addStaffButton;
	
	@FXML
	Button addCustomerButton;
	
	@FXML
	Button editStaffButton;
	
	@FXML
	Button editCustomerButton;
	
	
	public void changeToAddStaff(MouseEvent event) throws IOException {
		String fxml = "/AddStaffView.fxml";
		String title  = "Lisää henkilökuntaa";
		sceneContent(fxml, event, title);
	}
	
	public void changeToEditStaff(MouseEvent event) throws IOException {
		String fxml = "/EditStaffView.fxml";
		String title  = "Muokkaa henkilökuntaa";
		sceneContent(fxml, event, title);
	}
	
	public void changeToAddCustomer(MouseEvent event) throws IOException {
		String fxml = "/AddCustomerView.fxml";
		String title  = "Lisää asiakkaita";
		sceneContent(fxml, event, title);
	}
	
	public void changeToEditCustomer (MouseEvent event) throws IOException {
		String fxml = "/EditCustomerView.fxml";
		String title  = "Muokkaa asiakkaita";
		sceneContent(fxml, event, title);
	}
	

	
	
}
