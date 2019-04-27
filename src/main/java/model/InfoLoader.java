package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.hibernate.SessionFactory;

import dao.AppointmentDAO;
import dao.PrescriptionDAO;

public class InfoLoader {

	private File fileP, fileA;
	Appointment[] appointments;
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
	
	
	public Object[] readFromFile(File f) {
		boolean done = false;
		Object[] list = null;
		ArrayList<Object[]> arrayList = new ArrayList<>();
		try (FileInputStream is = new FileInputStream(f); ObjectInputStream objectIn = new ObjectInputStream(is)) {
			while(!done) {
				Object obj = null;
				try {
					obj = objectIn.readObject();
				}catch(ClassNotFoundException e) {
					
				}
				if(obj != null) {
					arrayList.add((Object[]) obj);
				}else {
					done = false;
				}
			}			
		} catch (IOException e) {
			e.getMessage();
		}
		for (int i = 0; i < arrayList.size(); i++) {
			list = arrayList.get(i);
		}
		return list;	
	}
	
	public Appointment[] readAppointmentsFromFile() {
		return (Appointment[])readFromFile(fileA);
	}
	
	public Prescription[] readPrescriptionsFromFile() {
		return(Prescription[])readFromFile(fileP);
	}

/*	public Appointment[] readAppointmentsFromFile() {
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
	}*/

}
