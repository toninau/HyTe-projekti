package model;

import java.io.File;

import dao.*;

public interface DAOManager_IF {
	void create(Object obj);
	boolean update(Object obj);
	Object[] readAll(String obj);
	Object readWithID(int id, String obj);
	Object readWithEmail(String key, String email);
	CustomerDAO getCustomerDAO();
	StaffDAO getStaffDAO();
	PrescriptionDAO getPrescriptionDAO();
	IllnessDAO getIllnessDAO();
	BloodValueDAO getBloodValueDAO();
	NotificationDAO getNotificationDAO();
	AppointmentDAO getAppointmentDAO();
	UserImageDAO getUserImageDAO();
	void writeAllCustomerInformation(Customer customer);
	UserImage[] readCustomerImages();
	Appointment[] readCustomerAppointments();
	Prescription[] readCustomerPrescriptions();
	
	void writeImageToFileDuringSession(Customer customer);
}
