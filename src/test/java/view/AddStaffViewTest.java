package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import model.HibernateUtil;
import view.admin.AddCustomerView;

import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.framework.junit5.ApplicationTest;

import com.lambdaworks.crypto.SCryptUtil;

import org.testfx.api.FxToolkit;

import static org.junit.Assert.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import java.util.Locale;
import java.util.ResourceBundle;

import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;

/**
 * 
 * @author IdaKi
 *
 */

public class AddStaffViewTest extends ApplicationTest{
	
	Stage stage;
	@Override
	  public void start (Stage stage) throws Exception {
		this.stage = stage;
		Locale fi = new Locale("fi", "FI");
		ResourceBundle bundle = ResourceBundle.getBundle("properties.Admin", fi);
	    Parent mainNode = FXMLLoader.load(AddCustomerView.class.getResource("/fxml/AddStaffView.fxml"), bundle);	    stage.setScene(new Scene(mainNode));
	    stage.show();
	    stage.toFront();
	}
	
	@BeforeAll
	  public static void setUp () throws Exception {
		SessionFactory istuntotehdas = HibernateUtil.getSessionFactory(true);

		System.setProperty("testfx.robot", "glass"); 
		System.setProperty("testfx.headless", "true"); 
		System.setProperty("prism.order", "sw"); 
		System.setProperty("prism.text", "t2k"); 
		System.setProperty("java.awt.headless", "true");
	}
	
	@After
	  public void tearDown () throws Exception {
	    FxToolkit.hideStage();
	    release(new KeyCode[]{});
	    release(new MouseButton[]{});
	}
	

	@Test
	public void addStaffName() {
		TextField fname = lookup("#fNameStaff").query();
		TextField surname = lookup("#surnameStaff").query();

        clickOn("#fNameStaff");
        write("firstname");
        clickOn("#surnameStaff");
        write("surname");
        
        verifyThat(fname.getText(), Matchers.is("firstname"));
        verifyThat(surname.getText(), Matchers.is("surname"));
	}
	
	@Test
	public void addStaffProfession() {
		ComboBox<String> profession = lookup("#profession").query();
        clickOn("#profession");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        assertTrue(profession.getItems().contains("Lääkäri"));
	}
	
	@Test
	public void addStaffEmail() {
		TextField emailStaff = lookup("#emailStaff").query();
	    clickOn("#emailStaff");
	    write("example@email.com");   
	    verifyThat(emailStaff.getText(), Matchers.is("example@email.com"));
	}
	
	@Test
	public void addStaffPhoneNumber() {
		TextField phoneNumber = lookup("#phoneNroStaff").query();
		clickOn("#phoneNroStaff");
	    write("0401234567");
        verifyThat(phoneNumber.getText(), Matchers.is("0401234567"));
	}
		  
	@Test
	public void addStaffPasswordTest() {
		TextField pw = lookup("#passwordStaff").query();
		clickOn("#passwordStaff");
	    write("salasana123");
	    String hashed = SCryptUtil.scrypt(pw.getText(), 16, 16, 16);
        assertTrue(SCryptUtil.check(pw.getText(), hashed));
	}
}

