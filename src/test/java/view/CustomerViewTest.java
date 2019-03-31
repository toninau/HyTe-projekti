/*
package view;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

public class CustomerViewTest extends ApplicationTest{
	
	Stage stage;
	@Override
	  public void start (Stage stage) throws Exception {
		this.stage = stage;
	    Parent mainNode = FXMLLoader.load(CustomerView.class.getResource("/CustomerView.fxml"));
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
	public void sendBloodValues() {
		
	}
	
	@Test
	public void weatherCall() {
		
	}
}
*/