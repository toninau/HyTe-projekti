package view;

import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import com.lambdaworks.crypto.SCryptUtil;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import model.HibernateUtil;
import view.admin.AddCustomerView;
import view.enums.Bundles;
import view.enums.FxmlEnum;

import static org.testfx.matcher.control.LabeledMatchers.hasText;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;


public class AddCustomerViewTest extends ApplicationTest {

	Stage stage;
	@Override
	  public void start (Stage stage) throws Exception {
		this.stage = stage;
		Locale fi = new Locale("fi", "FI");
		ResourceBundle bundle = ResourceBundle.getBundle("properties.Admin", fi);
	    Parent mainNode = FXMLLoader.load(AddCustomerView.class.getResource("/fxml/AddCustomerView.fxml"), bundle);
	    stage.setScene(new Scene(mainNode));
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
	public void addCustomerName() {
		TextField fname = lookup("#firstname").query();
		TextField surname = lookup("#surname").query();

        clickOn("#firstname");
        write("firstname");
        clickOn("#surname");
        write("surname");
        
        verifyThat(fname.getText(), Matchers.is("firstname"));
        verifyThat(surname.getText(), Matchers.is("surname"));
	}
	
	
	@Test
	public void addCustomerEmail() {
		TextField email = lookup("#email").query();
	    clickOn("#email");
	    write("example@email.com");   
	    verifyThat(email.getText(), Matchers.is("example@email.com"));
	}
	
	@Test
	public void addCustomerPhoneNumber() {
		TextField phoneNumber = lookup("#phone").query();
		clickOn("#phone");
	    write("0401234567");
        verifyThat(phoneNumber.getText(), Matchers.is("0401234567"));
	}
	
	@Test
	public void addCustomerSSN() {
		TextField ssn = lookup("#ssn").query();
		clickOn("#ssn");
	    write("000000-000A");
        verifyThat(ssn.getText(), Matchers.is("000000-000A"));
	}
	
	@Test
	public void addCustomerICENumber() {
		TextField ice = lookup("#ice").query();
		clickOn("#ice");
	    write("0504230101");
        verifyThat(ice.getText(), Matchers.is("0504230101"));
	}
	
	@Test
	public void addCustomerAddressTest() {
		TextField addr = lookup("#address").query();
		clickOn("#address");
	    write("Pihakuja 1");
        verifyThat(addr.getText(), Matchers.is("Pihakuja 1"));
	}
	
	@Test
	public void addCustomerPasswordTest() {
		TextField pw = lookup("#password").query();
		clickOn("#password");
	    write("salasana123");
	    String hashed = SCryptUtil.scrypt(pw.getText(), 16, 16, 16);
        assertTrue(SCryptUtil.check(pw.getText(), hashed));
	}
}
