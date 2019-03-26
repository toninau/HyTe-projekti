package view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class CustomerView extends ViewChanger {
	
	@FXML Text city;
	@FXML Text weather;
	@FXML TextArea notification;
	@FXML Text welcome;
	@FXML Text celcius;
	@FXML Button logout;
	
	public void setCelcius() {
		this.celcius.setText(null);
	}
	
	public void logout(MouseEvent event) throws IOException {
		String fxml = "/LoginView.fxml";
		String title  = "Login";
		sceneContent(fxml, event, title);
	}
	
}
