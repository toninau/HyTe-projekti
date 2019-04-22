package controller;

import java.io.File;
import java.util.List;

import model.Appointment;
import model.BloodValue;
import model.Customer;
import model.Prescription;
import model.UserImage;

public interface CustomerController_IF {
	public abstract void loggedCustomer(Customer customer);
	public abstract Customer getCustomer();
	public abstract BloodValue[] bloodValueData();
	public abstract boolean createBloodsugar();
	public abstract Prescription[] prescriptions();
	public abstract List<String> locationSuggestions();
	public abstract Appointment[] customersAppointments();
	public abstract void imageToDatabase(File file, int imageSlot);
	public abstract UserImage[] imageFromDatabase();
	public abstract void updateImage(File selectedFile, int imageSlot);
}
