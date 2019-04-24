package controller;

import model.Appointment;
import model.Customer;
import model.Staff;

public interface StaffController_IF {
    void loggedStaff(Staff staff);

    Staff getLoggedStaff();

    Customer[] getStaffCustomers();

    Appointment[] customersAppointments();

    boolean sendNotification(String data);
}
