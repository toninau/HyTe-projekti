package controller;

import model.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public interface CustomerController_IF {
	abstract void loggedCustomer(Customer customer);
	abstract Customer getLoggedCustomer();
	abstract ArrayList<BloodValue> bloodSugarData();
	abstract ArrayList<BloodValue> bloodPressureData();

	abstract boolean createBloodsugarValue();
	abstract boolean createBloodPressureValue();
	
	abstract Prescription[] prescriptions();
	abstract List<String> locationSuggestions();
	abstract Appointment[] customersAppointments();
	abstract void imageToDatabase(File file, int imageSlot);
	abstract UserImage[] imageFromTempFile();
	abstract void updateImage(File selectedFile, int imageSlot);
	abstract void updateMedicineTaken(Prescription p);
}
