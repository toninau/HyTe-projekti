package model;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.ImageIO;

import org.hibernate.SessionFactory;

import dao.UserImageDAO;

public class ImageLoader {

	private File[] files;
	UserImageDAO userImageDAO;
	UserImage[] userImages;
	Customer customer;
	private static ImageLoader imageLoader;
	String[] imageNames;

	private ImageLoader() {
		userImageDAO = new UserImageDAO(HibernateUtil.getSessionFactory(false));
		files = new File[3];
		imageNames = new String[3];
	}

	public static synchronized ImageLoader getImageLoader() {
		if (imageLoader == null) {
			imageLoader = new ImageLoader();
		} 
		return imageLoader;
	}

	public void writeImagesToFile(Customer customer) {
		userImages = userImageDAO.readCustomerUserImages(customer);
		for (int j = 0; j < userImages.length; j++) {
			imageNames[j] = userImages[j].getImageName();
			try (FileOutputStream os = new FileOutputStream(files[j] = File.createTempFile("userimages", null))) {
				BufferedImage img = ImageIO.read(new ByteArrayInputStream(userImages[j].getImage()));
				ImageIO.write(img, "png", files[j]);
				files[j].deleteOnExit();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public UserImage[] readCustomerImages() {
		UserImage[] userImages1 = new UserImage[userImages.length];
		for (int i = 0; i < userImages.length; i++) {
			try (FileInputStream is = new FileInputStream(files[i])) {
				userImages1[i] = new UserImage();
				BufferedImage bImage = ImageIO.read(is);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				ImageIO.write(bImage, "png", bos);
				byte[] data = bos.toByteArray();
				userImages1[i].setImage(data);
				userImages1[i].setImageName(imageNames[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return userImages1;
	}
}
