package controller;

import java.io.File;
import java.util.List;

import model.Appointment;
import model.BloodValue;
import model.Customer;
import model.Notification;
import model.Prescription;
import model.UserImage;


public interface CustomerControllerIF {
	abstract Customer getLoggedCustomer();
	abstract List<BloodValue> bloodSugarData();
	abstract List<BloodValue> bloodPressureData();
	abstract boolean createBloodsugarValue();
	abstract boolean createBloodPressureValue();

	abstract Prescription[] prescriptions();
	abstract List<String> locationSuggestions();
	abstract Appointment[] customersAppointments();
	abstract void imageToDatabase(File file, int imageSlot);
	abstract UserImage[] imageFromTempFile();
	abstract void updateImage(File selectedFile, int imageSlot);
	abstract void updateMedicineTaken(Prescription p);
	abstract Notification[] getMyMessages();
}
