package controller;

import model.DAOManager_IF;
import model.Staff;
import view.staff.StaffAppointmentView;
import view.staff.StaffHomeView;
import view.staff.StaffNotificationView;
import view.staff.StaffPrescriptionView;

public class StaffController {
    private StaffHomeView homeView;
    private StaffAppointmentView appointmentView;
    private StaffNotificationView notificationView;
    private StaffPrescriptionView prescriptionView;
    private static Staff staff;
    private DAOManager_IF daom;

}
