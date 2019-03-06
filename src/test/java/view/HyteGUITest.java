package view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.framework.junit5.ApplicationTest;

import org.testfx.api.FxToolkit;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class HyteGUITest extends ApplicationTest{

	private HyteGUI gui;
	
	@Override
	  public void start (Stage stage) throws Exception {
	    Parent mainNode = FXMLLoader.load(HyteGUI.class.getResource("/Skene.fxml"));
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
    public void testInput() {
        clickOn("#username");
        write("name");
       
    }

    
    @Test
    public void testButton() {
    	Label label = (Label)GuiTest.find("#label");
        clickOn("#loginBtn");
        verifyThat(label, hasText("Welcome"));
    }
    
}
