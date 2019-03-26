package view;
/*
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

public class AdminViewTest extends ApplicationTest{
	
	Stage stage;
	@Override
	  public void start (Stage stage) throws Exception {
		this.stage = stage;
	    Parent mainNode = FXMLLoader.load(AdminView.class.getResource("/AdminView.fxml"));
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

	@Test
	public void addStaffName() {
		TextField fname = lookup("#fNameStaff").query();
		TextField surname = lookup("#sNameStaff").query();
		clickOn("#addTab");
        clickOn("#fNameStaff");
        write("firstname");
        clickOn("#sNameStaff");
        write("surname");
        
        verifyThat(fname.getText(), Matchers.is("firstname"));
        verifyThat(surname.getText(), Matchers.is("surname"));
	}
	
	@Test
	public void addStaffProfession() {
		ChoiceBox<String> profession = lookup("#profession").query();
        clickOn("#profession");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        assertTrue(profession.getItems().contains("Hoitaja"));
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
		  
}*/
