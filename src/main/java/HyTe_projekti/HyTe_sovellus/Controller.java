package HyTe_projekti.HyTe_sovellus;

public class Controller implements Controller_IF {
	HyteGUI_IF gui;
	Model_IF model;
	public Controller(HyteGUI_IF gui) {
		this.gui = gui;
		model = new Model();
	}

}
