package controller;

import model.*;

import java.io.File;
import java.util.List;

public interface CustomerController_IF {
	void loggedCustomer(Customer customer);
	Customer getLoggedCustomer();
	BloodValue[] bloodValueData();
	boolean createBloodsugar();
	Prescription[] prescriptions();
	List<String> locationSuggestions();
	Appointment[] customersAppointments();
	void imageToDatabase(File file, int imageSlot);
	UserImage[] imageFromDatabase();
	void updateImage(File selectedFile, int imageSlot);
	void updateMedicineTaken(Prescription p);
}
