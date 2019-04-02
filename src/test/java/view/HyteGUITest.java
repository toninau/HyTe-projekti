package view;

/*
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

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

/**
 * 
 * @author IdaKi
 *
 */
/*
public class HyteGUITest extends ApplicationTest{

	Stage stage;
	@Override
	  public void start (Stage stage) throws Exception {
		String language = "fi";
		String country = "FI";		
		Locale currentLocale = new Locale(language, country);
		ResourceBundle bundle = ResourceBundle.getBundle(Bundles.LOGIN.getBundleName(), currentLocale);	

		this.stage = stage;
	    Parent mainNode = FXMLLoader.load(HyteGUI.class.getResource("/fxml/LoginView.fxml"), bundle);
	    stage.setScene(new Scene(mainNode));
	    stage.show();
	    stage.toFront();
	}
	
	
	
	@BeforeAll
	  public static void setUp () throws Exception {
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
    	TextField u = lookup("#usernameAsiakas").query();
        clickOn("#usernameAsiakas");
        write("name");
        assertTrue(u.getText().equals("name"));             
    }

    @Test
    public void testPasswordInput() {
    	TextField u = lookup("#pwAsiakas").query();
        clickOn("#pwAsiakas");
        write("password");
        assertTrue(u.getText().equals("password"));             
    }
    
    @Test
    public void testAdminLogin() {
    	clickOn("#staffTab");
    	clickOn("#username");
    	write("admin");
    	clickOn("#pw");
    	write("admin");
    	clickOn("#loginBtn");
    	assertTrue(stage.getTitle().equals("Menu"));
    }
    
}
*/