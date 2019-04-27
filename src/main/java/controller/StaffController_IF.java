package controller;

import javafx.collections.ObservableList;
import model.Appointment;
import model.Customer;
import model.Staff;

public interface StaffController_IF {
    void loggedStaff(Staff staff);

    Staff getLoggedStaff();

    ObservableList<Customer> getStaffCustomers();

    Appointment[] customersAppointments();

    abstract ObservableList<Appointment> allAppointments();
    boolean sendNotification(String data);
}
