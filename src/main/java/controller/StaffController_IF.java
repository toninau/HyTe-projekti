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

    ObservableList<Appointment> allAppointments();
    void sendNotification(String data, Customer customer);

    String getDailyHappenings();


}
