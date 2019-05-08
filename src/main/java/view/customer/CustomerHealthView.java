package view.customer;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import controller.CustomerController;
import controller.CustomerControllerIF;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
import model.BloodValue;
import model.Prescription;
import view.HyteGUI;
import view.ViewChanger;
import view.enums.Bundles;

public class CustomerHealthView extends ViewChanger implements Initializable {
	@FXML
	private Button homeButton;
	@FXML
	private Button calendarButton;
	@FXML
	private Button helpButton;
	@FXML
	private Button myHealthButton;
	@FXML
	private Button sendblood;
	@FXML
	private TextField bloodsugar;
	@FXML
	private TextField lowPressure;
	@FXML
	private TextField highPressure;
	@FXML
	private DatePicker datePicker;
	@FXML
	private LineChart<String, Double> bloodSugarChart;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private LineChart<String, Integer> bloodPressureChart;
	@FXML
	private NumberAxis yAxisPressure;
	@FXML
	private CategoryAxis xAxisPressure;
	@FXML
	private TableView<Prescription> prescriptonsTable;
	@FXML
	private TableColumn<Prescription, String> medicineName;
	@FXML
	private TableColumn<Prescription, String> medicineDosage;
	@FXML
	private TableColumn<Prescription, String> medicineDescription;
	@FXML
	private TableColumn<Prescription, String> medicineTime;
	@FXML
	private TableColumn<Prescription, Void> medicineRenew;
	@FXML
	private Label prescriptionInfoLabel;
	@FXML
	private ImageView highPressureInputWarning;
	@FXML
	private ImageView lowPressureInputWarning;
	@FXML
	private ImageView bloodSugarInputWarning;

	private ResourceBundle bundle;

	private Series<String, Double> seriesBloodSugar;
	private Series<String, Integer> seriesHighPressure;
	private Series<String, Integer> seriesLowPressure;

	private CustomerControllerIF controller;

	public CustomerHealthView() {
		controller = new CustomerController(this);
	}

	public void createBloodSugarValue() {
		if (controller.createBloodsugarValue()) {
			updateBloodSugarChart();
			bloodsugar.clear();
		}
	}

	public void createBloodPressureValue() {
		if (controller.createBloodPressureValue()) {
			updateBloodPressureChart();
			lowPressure.clear();
			highPressure.clear();
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
		List<BloodValue> a = controller.bloodSugarData();
		bloodSugarChart.getData().clear();
		for (BloodValue bloodValue : a) {
			seriesBloodSugar.getData()
					.add(new XYChart.Data<String, Double>(bloodValue.getDate(), bloodValue.getBloodsugar()));
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
		List<BloodValue> a = controller.bloodPressureData();
		bloodPressureChart.getData().clear();
		for (BloodValue bloodValue : a) {
			seriesHighPressure.getData()
					.add(new Data<String, Integer>(bloodValue.getDate(), bloodValue.getHighPressure()));
			seriesLowPressure.getData()
					.add(new Data<String, Integer>(bloodValue.getDate(), bloodValue.getLowPressure()));
		}
		bloodPressureChart.getData().add(seriesHighPressure);
		bloodPressureChart.getData().add(seriesLowPressure);
	}

	/**
	 * Populates the table with an observable list and sets the columns. Add's a
	 * button so the customer can send a renew request of their prescriptions.
	 * 
	 * @see #prescriptionsList()
	 * @see #addButtonToTable()
	 */
	@SuppressWarnings("unchecked")
	public void populateTableView() {
		prescriptonsTable.getColumns().clear();
		medicineName.setCellValueFactory(new PropertyValueFactory<Prescription, String>("prescriptionName"));
		medicineDosage.setCellValueFactory(new PropertyValueFactory<Prescription, String>("dosage"));
		medicineDescription.setCellValueFactory(new PropertyValueFactory<Prescription, String>("prescriptionGuide"));
		medicineTime.setCellValueFactory(new PropertyValueFactory<Prescription, String>("timeToTake"));
		medicineRenew.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

		prescriptonsTable.setRowFactory(tv -> {
			TableRow<Prescription> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty()) {
					Prescription rowData = row.getItem();
					prescriptionInfoLabel.setText(rowData.toStringAllInfo());
				}
			});
			return row;
		});

		ObservableList<Prescription> list = prescriptionsList();
		prescriptonsTable.setItems(list);
		prescriptonsTable.getColumns().addAll(medicineName, medicineDosage, medicineDescription, medicineTime);
		addButtonToTable();
	}

	/**
	 * Adds all the customer's prescription to an observable list.
	 * 
	 * @return Observable list of the customer's prescriptions.
	 * @see controller.CustomerController#prescriptions()
	 */
	public ObservableList<Prescription> prescriptionsList() {
		ObservableList<Prescription> data = FXCollections.observableArrayList();
		Prescription[] a = controller.prescriptions();
		for (Prescription prescription : a) {
			data.add(prescription);
		}
		return data;
	}

	/**
	 * Adds a button to the renew column of the prescriptions table. Sets an event
	 * handler to the button.
	 * 
	 * @see #sendRenewRequest(Prescription)
	 */
	private void addButtonToTable() {
		Callback<TableColumn<Prescription, Void>, TableCell<Prescription, Void>> cellFactory = new Callback<TableColumn<Prescription, Void>, TableCell<Prescription, Void>>() {
			@Override
			public TableCell<Prescription, Void> call(final TableColumn<Prescription, Void> param) {
				final TableCell<Prescription, Void> cell = new TableCell<Prescription, Void>() {
					private final Button btn = new Button("Uusi resepti");
					{
						btn.setOnAction((ActionEvent event) -> {
							Prescription p = getTableView().getItems().get(getIndex());
							sendRenewRequest(p);
							if (p.isRenewPrescription()) {
								btn.setDisable(true);
							} else {
								btn.setDisable(false);
							}
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btn);
						}
					}
				};
				return cell;
			}
		};
		medicineRenew.setCellFactory(cellFactory);
		prescriptonsTable.getColumns().add(medicineRenew);
	}

	/**
	 * Sets the renew request true.
	 * 
	 * @param prescription The prescription to be renewed.
	 * @see model.Prescription#setRenewPrescription(boolean)
	 */
	public void sendRenewRequest(Prescription p) {
		p.setRenewPrescription(true);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bundle = ResourceBundle.getBundle(Bundles.CUSTOMER.getBundleName(), HyteGUI.getLocale());
		showBloodsugarChart();
		showBloodpressureChart();
		populateTableView();
	}

	public String getTime() {
		return LocalTime.now().truncatedTo(ChronoUnit.MINUTES).toString();
	}

	public String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.YYYY");
		LocalDate localDate = LocalDate.now();
		return dtf.format(localDate);
	}

	public void validateNumeric(KeyEvent keyEvent) {
		if (keyEvent.getCode().isLetterKey()) {
			TextField textField = (TextField) keyEvent.getSource();
			if (textField.getId().equals(lowPressure.getId())) {
				lowPressureInputWarning.setVisible(true);
			} else if (textField.getId().equals(highPressure.getId())) {
				highPressureInputWarning.setVisible(true);
			} else if (textField.getId().equals(bloodsugar.getId())) {
				bloodSugarInputWarning.setVisible(true);
			}
		} else if (keyEvent.getCode().isDigitKey() || keyEvent.getCode().equals(KeyCode.COMMA)
				|| keyEvent.getCode().equals(KeyCode.PERIOD)) {
			lowPressureInputWarning.setVisible(false);
			highPressureInputWarning.setVisible(false);
			bloodSugarInputWarning.setVisible(false);
		}
	}

	public int getHighPressure() {
		if (highPressure.getText().isEmpty()) {
			highPressureInputWarning.setVisible(true);
			return -1;
		} else {
			return Integer.parseInt(highPressure.getText());

		}
	}

	public int getLowPressure() {
		if (lowPressure.getText().isEmpty()) {
			lowPressureInputWarning.setVisible(true);
			return -1;
		} else {
			return Integer.parseInt(lowPressure.getText());

		}
	}

	public double getBloodsugar() {
		double value;
		if(bloodsugar.getText().isEmpty()) {
			bloodSugarInputWarning.setVisible(true);
			return -1;
		}else {
			if (bloodsugar.getText().contains(",")) {
				value = Double.parseDouble(bloodsugar.getText().replaceAll(",", "."));
			} else {
				value = Double.parseDouble(bloodsugar.getText());
			}
			return value;
		}

	}
}
