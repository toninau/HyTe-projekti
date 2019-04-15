package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

public class StaffHomeView extends ViewChanger implements Initializable{

	@FXML ListView dailyNews;
	@FXML ListView StaffCustomers;
	@FXML Label welcomeText;
	
	@FXML
	private Button logout;
	
	public void logout(MouseEvent event) throws IOException {
		logoutForAll(event);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
