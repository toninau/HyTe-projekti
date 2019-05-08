package view;


import static org.junit.Assert.assertTrue;

import java.util.Locale;
import java.util.ResourceBundle;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import model.HibernateUtil;

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
		SessionFactory istuntotehdas = HibernateUtil.getSessionFactory(false);
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
    
}
