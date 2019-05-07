package dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Customer;
import model.HibernateUtil;
import model.Prescription;
import model.Staff;

public class PrescriptionDAOTest {
	private Staff staff;
	private Customer customer;
	private Prescription prescription;
	private StaffDAO sDAO;
	private CustomerDAO cDAO;
	private PrescriptionDAO pDAO;
	SessionFactory sf;
	
	@BeforeEach
	public void setTest() {
		sf = HibernateUtil.getSessionFactory(true);
		cDAO = new CustomerDAO(sf);
		sDAO = new StaffDAO(sf);
		pDAO = new PrescriptionDAO(sf);
	}
	
	
	@Test
	public void testDAOMethods() {
		//Create customer and staff
		customer = createCustomer();
		staff = createStaff();
		//Create prescription
		prescription = new Prescription();
		prescription.setCustomer(customer);
		prescription.setStaff(staff);
		prescription.setDosage("Dosage");
		prescription.setEndDate("12.12.2000");
		prescription.setPrescriptionGuide("Guide");
		prescription.setPrescriptionName("Name");
		prescription.setRenewPrescription(true);
		prescription.setStartDate("12.10.2000");
		prescription.setTimeToTake("TimetoTake");
		assertTrue(pDAO.create(prescription), "create(prescription): Failed to create prescription");
		//Read prescription
		prescription = pDAO.read(1);
		assertEquals("Dosage", prescription.getDosage(), "read(id): Failed to read prescription dosage");
		//Read all customer's prescriptions
		Prescription[] prescriptions = pDAO.readCustomersPrescriptions(customer);
		assertEquals(1, prescriptions.length, "readCustomersPrescriptions(customer): Failed to customer's prescriptions");
		//Read all staff member's prescriptions
		prescriptions = pDAO.readStaffsPrescriptions(staff);
		assertEquals(1, prescriptions.length, "readStaffsPrescriptions(staff): Failed to staff member's prescriptions");
		//Delete prescription
		assertTrue(pDAO.delete(1), "delete(id): Failed to delete prescription");
		prescriptions = pDAO.readCustomersPrescriptions(customer);
		assertEquals(0, prescriptions.length, "readCustomersPrescriptions(customer): Failed to customer's prescriptions");
		//Read all staff member's prescriptions
		prescriptions = pDAO.readStaffsPrescriptions(staff);
		assertEquals(0, prescriptions.length, "readStaffsPrescriptions(staff): Failed to staff member's prescriptions");
		//Delete customer and staff
		cDAO.delete("presur");
		sDAO.delete("presur");
	}
	
	private Customer createCustomer() {
		Customer customer = new Customer();
		customer.setFirstName("PrescriptionFirstName");
		customer.setSurname("Surname");
		customer.setSSN("SSN");
		customer.setIceNumber("ICEnumber");
		customer.setAddress("Address");
		customer.setPhoneNumber("PhoneNumber");
		customer.setPassword("Password");
		cDAO.create(customer);
		customer = cDAO.read("presur");
		return customer;
	}
	
	private Staff createStaff() {
		Staff staff = new Staff();
		staff.setFirstName("PrescriptionFirstName");
		staff.setSurname("Surname");
		staff.setPhoneNumber("PhoneNumber");
		staff.setPassword("Password");
		staff.setAccessLevel("AccessLevel");
		sDAO.create(staff);
		staff = sDAO.read("presur");
		return staff;
	}
}
