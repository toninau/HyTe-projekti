package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;

import dao.AppointmentDAO;
import dao.BloodValueDAO;
import dao.PrescriptionDAO;

public class InfoLoader {

	private static final Logger log = Logger.getLogger(InfoLoader.class.getName());

	private File fileP, fileA, fileB;
	private Appointment[] appointments;
	static InfoLoader infoloader;

	private InfoLoader() {
	}

	public static synchronized InfoLoader getInfoLoader() {
		if (infoloader == null) {
			infoloader = new InfoLoader();
		}
		return infoloader;
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
	
	public void writeBloodvaluesToFile(Customer customer) {
		BloodValueDAO bDAO = new BloodValueDAO(HibernateUtil.getSessionFactory(false));
		BloodValue[] b = bDAO.readCustomerBloodvalues(customer);
		writeToFile(b, fileB);
	}

	public void writeToFile(Object obj, File f) {
		File file = f;
		try (FileOutputStream os = new FileOutputStream(file = File.createTempFile("info", null));
				ObjectOutputStream objectOut = new ObjectOutputStream(os)) {
			if (obj instanceof Appointment[])
				fileA = file;
			else if (obj instanceof Prescription[])
				fileP = file;
			else if(obj instanceof BloodValue[])
				fileB = file;
			objectOut.writeObject(obj);
			file.deleteOnExit();

		} catch (IOException e) {
		}
	}

	public Object[] readFromFile(File f) {
		boolean done = false;
		Object[] list = null;
		ArrayList<Object[]> arrayList = new ArrayList<>();

		try (FileInputStream is = new FileInputStream(f); ObjectInputStream objectIn = new ObjectInputStream(is)) {
			while (!done) {
				Object obj = null;
				try {
					obj = objectIn.readObject();
				} catch (ClassNotFoundException e) {
					log.severe("Class not found.");
				}
				if (obj != null) {
					arrayList.add((Object[]) obj);
				}
			}
		} catch (IOException e) {
		}
		for (int i = 0; i < arrayList.size(); i++) {
			list = arrayList.get(i);
		}
		return list;
	}

	public Appointment[] readAppointmentsFromFile() {
		return (Appointment[]) readFromFile(fileA);
	}

	public Prescription[] readPrescriptionsFromFile() {
		return (Prescription[]) readFromFile(fileP);
	}
	
	public BloodValue[] readBloodValuesFromFile() {	
		return (BloodValue[]) readFromFile(fileB);
	}

}
