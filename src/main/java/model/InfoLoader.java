package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.hibernate.SessionFactory;

import dao.AppointmentDAO;
import dao.PrescriptionDAO;

public class InfoLoader {

	private File fileP, fileA;
	Appointment[] appointments;

	public InfoLoader() {

	}

	public void writeAppointmentsToFile(Customer customer) {
		AppointmentDAO aDAO = new AppointmentDAO(HibernateUtil.getSessionFactory(false));
		appointments = aDAO.readCustomerAppointments(customer);
		writeToFile(appointments, fileA);
	}

	public void writePrescriptionsToFile(Customer customer) {
		PrescriptionDAO pDAO = new PrescriptionDAO(HibernateUtil.getSessionFactory(false));
		Prescription[] p = pDAO.readCustomersPrescriptions(customer);
		writeToFile(p, fileP);
	}

	public void writeToFile(Object obj, File f) {
		try (FileOutputStream os = new FileOutputStream(f = File.createTempFile("info", null));
				ObjectOutputStream objectOut = new ObjectOutputStream(os)) {
			if(obj instanceof Appointment[])
				fileA = f;
			else if(obj instanceof Prescription[])
				fileP = f;
			objectOut.writeObject(obj);
			f.deleteOnExit();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Appointment[] readAppointmentsFromFile() {
		boolean done = true;
		Appointment[] app = null;

		ArrayList<Appointment[]> a = new ArrayList<Appointment[]>();
		try (FileInputStream is = new FileInputStream(fileA); ObjectInputStream objectIn = new ObjectInputStream(is)) {
			while(done) {
				Object obj = null;
				try {
					obj = objectIn.readObject();
				}catch(ClassNotFoundException e) {
					
				}
				if(obj != null) {
					a.add((Appointment[]) obj);
				}else {
					done = false;
				}
			}			

		} catch (IOException e) {
			e.getMessage();
		}
		for (int i = 0; i < a.size(); i++) {
			app = a.get(i);
		}
		return app;
	}

}
