package view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import model.HibernateUtil;
import view.enums.Bundles;

import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.framework.junit5.ApplicationTest;

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

public class LoginViewTest extends ApplicationTest{
	
	Stage stage;
	@Override
	  public void start (Stage stage) throws Exception {		
		Locale fi = new Locale("fi", "FI");
		ResourceBundle bundle = ResourceBundle.getBundle("properties.LoginProperties", fi);	
		this.stage = stage;
	    Parent mainNode = FXMLLoader.load(LoginView.class.getResource("/fxml/LoginView.fxml"), bundle);
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
    public void testNameInput() {
    	TextField u = lookup("#usernameAsiakasLogin").query();
        clickOn("#usernameAsiakasLogin");
        write("name");
        assertTrue(u.getText().equals("name"));  
    }

    @Test
    public void testPasswordInput() {
    	TextField u = lookup("#pwAsiakasLogin").query();
        clickOn("#pwAsiakasLogin");
        write("password");
        assertTrue(u.getText().equals("password"));             
    }
    
    
    
    @Test
    public void testAdminLogin() {
    	clickOn("#staffTab");
    	clickOn("#usernameLogin");
    	write("admin");
    	clickOn("#pwLogin");
    	write("admin");
    	clickOn("#loginBtn");
    	assertTrue(stage.getTitle().equals("Menu"));
    }
    
    
}
