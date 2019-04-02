package view;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;

public class AdminMenuView extends ViewChanger implements Initializable {
	
	@FXML
	Button addStaffButton;	
	@FXML
	Button addCustomerButton;
	@FXML
	Button editStaffButton;
	@FXML
	Button editCustomerButton;
		
	ResourceBundle bundle;
	
	public void changeToAddStaff(MouseEvent event) throws IOException {
		String fxml = "/fxml/AddStaffView.fxml";
		String title  = "Lisää henkilökuntaa";
		sceneContent(fxml, event, title, bundle);
	}
	
	public void changeToEditStaff(MouseEvent event) throws IOException {
		String fxml = "/fxml/EditStaffView.fxml";
		String title  = "Muokkaa henkilökuntaa";
		sceneContent(fxml, event, title, bundle);
	}
	
	public void changeToAddCustomer(MouseEvent event) throws IOException {
		String fxml = "/fxml/AddCustomerView.fxml";
		String title  = "Lisää asiakkaita";
		sceneContent(fxml, event, title, bundle);
	}
	
	public void changeToEditCustomer (MouseEvent event) throws IOException {
		bundle = ResourceBundle.getBundle(Bundles.ADMINMENU.getBundleName(), HyteGUI.getLocale());

		String fxml = "/fxml/EditCustomerView.fxml";
		String title  = "Muokkaa asiakkaita";
		sceneContent(fxml, event, title, bundle);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Tooltip tt1 = new Tooltip("Add staff member");
		addStaffButton.setTooltip(tt1);
		
		bundle = ResourceBundle.getBundle(Bundles.ADMINMENU.getBundleName(), HyteGUI.getLocale());
	}
	

	
	
}
