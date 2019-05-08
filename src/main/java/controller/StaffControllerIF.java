package controller;

import javafx.collections.ObservableList;
import model.*;

public interface StaffControllerIF {
    ObservableList<Appointment> allAppointments();
    Staff getLoggedStaff();
    boolean sendNotification(Notification notification);
    ObservableList<Customer> getStaffCustomers();
    ObservableList<Appointment> getCustomersAppointments(Customer customer);
    boolean addAppointment(Appointment appointment);
    boolean saveAppointment(Appointment appointment);
    ObservableList<Prescription> getCustomersPrescriptions(Customer customer);
    boolean savePrescription(Prescription prescription);



}
