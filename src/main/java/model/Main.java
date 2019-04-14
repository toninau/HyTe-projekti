package model;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.hibernate.SessionFactory;
import dao.AppointmentDAO;
import dao.BloodValueDAO;
import dao.CustomerDAO;
import dao.IllnessDAO;
import dao.NotificationDAO;
import dao.PrescriptionDAO;
import dao.StaffDAO;
import dao.UserImageDAO;


/**
 * Testing class
 * 
 * @author Group 3
 *
 */

public class Main {

	public static void main(String[] args) {
		// Testaus main, jota voidaan myös käyttää tietokannan luomista varten
		SessionFactory istuntotehdas = HibernateUtil.getSessionFactory(false);
		NotificationDAO ilmoitusDAO = new NotificationDAO(istuntotehdas);
		CustomerDAO asiakasDAO = new CustomerDAO(istuntotehdas);
		IllnessDAO sairausDAO = new IllnessDAO(istuntotehdas);
		StaffDAO henkilöDAO = new StaffDAO(istuntotehdas);
		AppointmentDAO varausDAO = new AppointmentDAO(istuntotehdas);
		PrescriptionDAO reseptiDAO = new PrescriptionDAO(istuntotehdas);
		BloodValueDAO veriarvoDAO = new BloodValueDAO(istuntotehdas);
		UserImageDAO imageDAO = new UserImageDAO(istuntotehdas);

		// Luo ensimmäinen henkilökunnan jäsen
		Staff staff = new Staff();
		staff.setStaffID("1");
		staff.setFirstName("test");
		staff.setSurname("tohtori");
		staff.setPassword("test");
		staff.setPhoneNumber("112");
		staff.setAccessLevel("Lääkäri");
		henkilöDAO.create(staff);

		// Ensimmäinen asiakas
		Customer customer = new Customer();
		customer.setFirstName("Jorma");
		customer.setSurname("Testi");
		customer.setSSN("123456-7890");
		customer.setIceNumber("12312145");
		customer.setAddress("Testikuja 2");
		customer.setPhoneNumber("12341235");
		customer.setCustomerID("3");
		customer.setPassword("test");
		asiakasDAO.create(customer);
		customer = asiakasDAO.read("3");

		// Kuvan laittaminen tietokantaan
		File file = new File(Main.class.getResource("/pictures/finland_flag.png").getFile());
		byte[] bFile = new byte[(int) file.length()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(bFile);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		UserImage image = new UserImage();
		image.setCustomer(customer);
		image.setImage(bFile);
		image.setImageName("test_image");
		imageDAO.create(image);
		
		 
		//Kuvan lataaminen ja näyttäminen
		//image = imageDAO.read(1); //Yksittäinen kuva id:n perusteella
		UserImage[] images = imageDAO.readCustomerUserImages(customer); //Asiakkaan kaikki kuvat
		bFile = images[0].getImage();
		BufferedImage img = null;
		try {
			img = ImageIO.read(new ByteArrayInputStream(bFile));
			ImageIcon icon = new ImageIcon(img);
			JFrame frame = new JFrame();
			frame.setLayout(new FlowLayout());
			frame.setSize(200,300);
			JLabel lbl = new JLabel();
			lbl.setIcon(icon);
			frame.add(lbl);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//Kuvan päivitys
		image = imageDAO.read(1);
		image.setImageName("päivitys testi");
		imageDAO.update(image);
		
		//Kuvan poisto tietokannasta
		//imageDAO.delete(1);
	}
}
