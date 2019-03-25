package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class CustomerView {
	
	@FXML Text city;
	@FXML Text weather;
	@FXML TextArea notification;
	@FXML Text welcome;
	@FXML Text celcius;
	
	public void setCelcius() {
		this.celcius.setText(null);
	}
	
}
