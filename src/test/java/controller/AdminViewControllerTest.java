package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import view.HyteGUI;

import org.junit.After;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.framework.junit5.ApplicationTest;

import org.testfx.api.FxToolkit;

import static org.junit.Assert.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.hamcrest.Matchers;

public class AdminViewControllerTest extends ApplicationTest{
	
	AdminViewController ac;
	Stage stage;
	@Override
	  public void start (Stage stage) throws Exception {
		this.stage = stage;
	    Parent mainNode = FXMLLoader.load(HyteGUI.class.getResource("/AdminView.fxml"));
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
	public void tabTest() {
		Button but = lookup("#find").query();
		Button b = lookup("#addStaff").query();
		clickOn("#editTab");
		verifyThat(but, hasText("Etsi"));
		clickOn("#addTab");
		verifyThat(b, hasText("Lisää työntekijä"));
	}
	
	/*@Test
	public void addStaffTest() {
		ac = new AdminViewController();
		clickOn("#addTab");
        clickOn("#fNameStaff");
        write("firstname");
        clickOn("#sNameStaff");
        write("surname");
        clickOn("#phoneNroStaff");
        write("0401234567");
        clickOn("#emailStaff");
        write("example@email.com");
        clickOn("#profession");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        //clickOn("#addStaff");
       
        String fname = ac.getStaffFirstName();
        verifyThat(fname, Matchers.is("firstname"));
        verifyThat(ac.getStaffSurname(), Matchers.is("surname"));
        verifyThat(ac.getStaffPhone(), Matchers.is("0401234567"));
        verifyThat(ac.getStaffEmail(), Matchers.is("example@email.com"));
        verifyThat(ac.getProfession(), Matchers.is("Hoitaja"));

       	assertTrue(ac.getStaffFirstName().equals("firstname"));
       	assertTrue(ac.getStaffSurname().equals("surname"));
       	assertTrue(ac.getStaffPhone().equals("0401234567"));
       	assertTrue(ac.getStaffEmail().equals("example@email.com"));
       	assertTrue(ac.getProfession().equals("Hoitaja"));
	}*/
		  
}
