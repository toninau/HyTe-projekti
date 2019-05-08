package model;


import dao.*;

public interface DAOManagerIF {
	abstract void create(Object obj);
	abstract boolean update(Object obj);
	abstract Object[] readAll(String obj);
	abstract Object readWithID(int id, String obj);
	abstract Object readPersonWithID(String key, String email);
	abstract CustomerDAO getCustomerDAO();
	abstract StaffDAO getStaffDAO();
	abstract PrescriptionDAO getPrescriptionDAO();
	abstract IllnessDAO getIllnessDAO();
	abstract BloodValueDAO getBloodValueDAO();
	abstract NotificationDAO getNotificationDAO();
	abstract AppointmentDAO getAppointmentDAO();
	abstract UserImageDAO getUserImageDAO();
	abstract void writeAllCustomerInformation(Customer customer);
	abstract UserImage[] readCustomerImages();
	abstract Appointment[] readCustomerAppointments();
	abstract Prescription[] readCustomerPrescriptions();
	abstract BloodValue[] readCustomerBloodValues();
	abstract void writeImageToFileDuringSession(Customer customer);
	
	abstract void writeBloodValueToFileDuringSession(Customer customer);
}
