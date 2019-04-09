package view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import controller.CustomerController;
import controller.CustomerController_IF;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.BloodValue;

public class CustomerHealthView extends ViewChanger implements Initializable {
	@FXML
	Button homeButton;
	@FXML
	Button calendarButton;
	@FXML
	Button helpButton;
	@FXML
	Button myHealthButton;
	@FXML
	Button sendblood;
	@FXML
	TextField bloodsugar;
	@FXML
	TextField lowPressure;
	@FXML
	TextField highPressure;
	@FXML
	DatePicker datePicker;
	@FXML
	LineChart<String, Double> bloodSugarChart;
	@FXML
	NumberAxis yAxis;
	@FXML
	CategoryAxis xAxis;
	@FXML
	LineChart<String, Integer> bloodPressureChart;
	@FXML
	NumberAxis yAxisPressure;
	@FXML
	CategoryAxis xAxisPressure;
	
	ResourceBundle bundle;
	
	Series<String, Double> seriesBloodSugar;
	Series<String, Integer> seriesHighPressure;
	Series<String, Integer> seriesLowPressure;

	private CustomerController_IF controller;
	
	public CustomerHealthView() {
		controller = new CustomerController(this);
	}
	
	public void createBlood() {
		if(controller.createBloodsugar()) {
			updateBloodSugarChart();
			updateBloodPressureChart();
		}
	}
	
	public void showBloodsugarChart() {
		xAxis.setLabel(bundle.getString("health.sugarchart.category"));
		yAxis.setLabel(bundle.getString("health.sugarchart.value"));
		bloodSugarChart.setTitle(bundle.getString("health.sugarchart.title"));
		seriesBloodSugar = new XYChart.Series<String, Double>();
		seriesBloodSugar.setName(bundle.getString("health.sugarchart.title"));
		updateBloodSugarChart();	
	}
	

	public void updateBloodSugarChart() {
		BloodValue[] a = controller.bloodValueData();
		bloodSugarChart.getData().clear();
		for (BloodValue bloodValue : a) {
	        seriesBloodSugar.getData().add(new XYChart.Data<String, Double>(bloodValue.getDate(), bloodValue.getBloodsugar()));
		}
		bloodSugarChart.getData().add(seriesBloodSugar);
	}
	
	public void showBloodpressureChart() {
		xAxisPressure.setLabel(bundle.getString("health.pressurechart.category"));
		yAxisPressure.setLabel(bundle.getString("health.pressurechart.value"));
		bloodPressureChart.setTitle(bundle.getString("health.pressurechart.title"));
		seriesHighPressure = new XYChart.Series<String, Integer>();
		seriesLowPressure = new XYChart.Series<String, Integer>();
		seriesHighPressure.setName("yläpaine");
		seriesLowPressure.setName("alapaine");
		updateBloodPressureChart();	
	}
	
	public void updateBloodPressureChart() {
		BloodValue[] a = controller.bloodValueData();
		bloodPressureChart.getData().clear();
		for (BloodValue bloodValue : a) {
	        seriesHighPressure.getData().add(new Data<String, Integer>(bloodValue.getDate(), bloodValue.getHighPressure()));
	        seriesLowPressure.getData().add(new Data<String, Integer>(bloodValue.getDate(), bloodValue.getLowPressure()));
		}
		bloodPressureChart.getData().add(seriesHighPressure);
		bloodPressureChart.getData().add(seriesLowPressure);

	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
		showBloodsugarChart();
		showBloodpressureChart();
		
	}
	
	/**
	 * Fired when Home button is clicked.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerHome(Event);
	 */
	public void toHome(MouseEvent event) throws IOException {
		toCustomerHome(event);
	}

	/**
	 * Fired when Calendar button is clicked.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerCalendar(Event);
	 */
	public void toCalendar(MouseEvent event) throws IOException {
		toCustomerCalendar(event);
	}

	/**
	 * Fired when Help button is clicked.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerHelp(Event);
	 */
	public void toHelp(MouseEvent event) throws IOException {
		toCustomerHelp(event);
	}

	/**
	 * Fired when Health button is clicked.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#toCustomerHealth(Event);
	 */
	public void toHealth(MouseEvent event) throws IOException {
		toCustomerHealth(event);
	}

	/**
	 * Fired when logout button is clicked.
	 * @param event Mouse clicked.
	 * @throws IOException Loading fxml file failed.
	 * @see view.ViewChanger#logoutForAll(Event);
	 */
	public void logout(MouseEvent event) throws IOException {
		logoutForAll(event);
	}

	public String getTime() {
		return LocalDate.now().toString();
	}
	public String getDate() {
		LocalDate date = datePicker.getValue();
		return date.toString();
	}
	public int getHighPressure() {
		return Integer.parseInt(highPressure.getText());
	}
	public int getLowPressure() {
		return Integer.parseInt(lowPressure.getText());
	}
	public double getBloodsugar() {
		return Double.parseDouble(bloodsugar.getText());
	}
}
