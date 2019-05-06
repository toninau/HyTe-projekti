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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import view.HyteGUI;
import view.ViewChanger;
import view.enums.Bundles;

public class CustomerHelpView extends ViewChanger implements Initializable {

	@FXML
	private ImageView slideShow;
	@FXML
	private Button previousButton;
	@FXML
	private Tooltip previousTT;
	@FXML
	private Button nextButton;
	@FXML
	private Tooltip nextTT;
	@FXML
	private Button startButton;
	@FXML
	private Tooltip startTT;
	@FXML
	private Button homeButton1;
	@FXML
	private Button myHealthButton1;
	@FXML
	private Button helpButton1;
	@FXML
	private Label helpText;
	@FXML
	private Label helpCounter;
	@FXML
	private Label helpTip;

	private ResourceBundle bundle;
	private List<String> imageList;
	private List<String> textList;
	private int index = 0;

	public CustomerHelpView() {
		slideShow = new ImageView();
		imageList = new ArrayList<>();
		textList = new ArrayList<>();
	}

	/**
	 * Sets the right bundle. Calls the needed methods.
	 * 
	 * @see #fillImageList()
	 * @see #fillTextList()
	 * @see #openImage()
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
		previousTT.setText(bundle.getString("help.previous.tt"));
		nextTT.setText(bundle.getString("help.next.tt"));
		startTT.setText(bundle.getString("help.start.tt"));
		fillImageList();
		fillTextList();
		openImage();
	}

	/**
	 * Returns to the beginning.
	 * 
	 * @param event ActionEvent
	 */
	@FXML
	private void startImage(ActionEvent event) {
		index = 0;
		openImage();
	}

	/**
	 * Changes image to next image.
	 * 
	 * @param event ActionEvent
	 */
	@FXML
	private void nextImage(ActionEvent event) {
		if (index < imageList.size() - 1) {
			index++;
		} else {
			index = 0;
		}
		openImage();
	}

	/**
	 * Changes image to previous image.
	 * 
	 * @param event ActionEvent
	 */
	@FXML
	private void previousImage(ActionEvent event) {
		if (index > 0) {
			index--;
		} else {
			index = imageList.size() - 1;
		}
		openImage();
	}

	/**
	 * Sets image to ImageView. Also sets help text below image and image counter.
	 */
	private void openImage() {
		try {
			File file = new File(CustomerHelpView.class.getResource(imageList.get(index)).getFile());
			String test = file.toURI().toString();
			Image image = new Image(test);
			slideShow.setImage(image);
			helpText.setText(textList.get(index));
			int index1 = index + 1;
			helpCounter.setText(index1 + "/" + imageList.size());
			helpTip.setText(bundle.getString("help.mainTip") + " " + index1);
		} catch (Exception e) {
		}
	}

	/**
	 * Fills list (imageList) with helpful images.
	 */
	private void fillImageList() {
		for (int i = 1; i < 4; i++) {
			imageList.add("/pictures/" + i + ".png");
		}
	}

	/**
	 * Fills list (textList) with tips that are shown below images.
	 */
	private void fillTextList() {
		for (int i = 0; i < imageList.size(); i++) {
			textList.add(bundle.getString("help.tip" + i));
		}
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
