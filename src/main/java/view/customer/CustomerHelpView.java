package view.customer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.ViewChanger;

public class CustomerHelpView extends ViewChanger implements Initializable {
	
	@FXML private ImageView slideShow;
	@FXML private Button previousButton;
	@FXML private Button nextButton;
	@FXML private Button startButton;
	@FXML private Button homeButton1;
	@FXML private Button myHealthButton1;
	@FXML private Button helpButton1;
    @FXML private Label helpText;
	
	private List<String> imageList;
	private List<String> textList;
	private int index = 0;

	public CustomerHelpView() {
		slideShow = new ImageView();
		imageList = new ArrayList<>();
		textList = new ArrayList<>();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fillImageList();
		fillTextList();
		openImage();
	}
	
	@FXML
	void startImage(ActionEvent event) {
		index = 0;
		openImage();
	}

	@FXML
	void nextImage(ActionEvent event) {
		if (index < imageList.size()-1) {
			index++;
			openImage();
		}
	}
	
	@FXML
	void previousImage(ActionEvent event) {
		if (index > 0) {
			index--;
			openImage();
		}
	}
	
	private void openImage() {
		try {
			File file = new File(CustomerHelpView.class.getResource(imageList.get(index)).getFile());
			String test = file.toURI().toString();
			Image image = new Image(test);
			slideShow.setImage(image);
			helpText.setText(textList.get(index));
		} catch (Exception e) {}
	}

	private void fillImageList() {
		imageList.add("/pictures/finland_flag.png");
		imageList.add("/pictures/france_flag.png");
		imageList.add("/pictures/happy.png");
	}
	
	private void fillTextList() {
		textList.add("tämä tekee jotakin");
		textList.add("tämä tekee toista asiaa");
		textList.add("tämä kolmatta");
	}
	

	/**
	 * Fired when Home button is clicked.
	 * 
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerHome(Event);
	 */
	public void toHome(MouseEvent event) throws IOException {
		toCustomerHome(event);
	}

	/**
	 * Fired when Calendar button is clicked.
	 * 
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerCalendar(Event);
	 */
	public void toCalendar(MouseEvent event) throws IOException {
		toCustomerCalendar(event);
	}

	/**
	 * Fired when Help button is clicked.
	 * 
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerHelp(Event);
	 */
	public void toHelp(MouseEvent event) throws IOException {
		toCustomerHelp(event);
	}

	/**
	 * Fired when Health button is clicked.
	 * 
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerHealth(Event);
	 */
	public void toHealth(MouseEvent event) throws IOException {
		toCustomerHealth(event);
	}

	/**
	 * Fired when logout button is clicked.
	 * 
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#logoutForAll(Event);
	 */
	public void logout(MouseEvent event) throws IOException {
		logoutForAll(event);
	}

}
