package controller;

import javafx.collections.ObservableList;
import model.*;

public interface StaffController_IF {
    ObservableList<Appointment> allAppointments();
    void loggedStaff(Staff staff);
    Staff getLoggedStaff();
    boolean sendNotification(Notification notification);
    ObservableList<Customer> getStaffCustomers();
    ObservableList<Appointment> getCustomersAppointments(Customer customer);
    boolean addAppointment(Appointment appointment);
    boolean saveAppointment(Appointment appointment);
    ObservableList<Prescription> getCustomersPrescriptions(Customer customer);
    boolean savePrescription(Prescription prescription);



}
