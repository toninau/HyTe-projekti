package controller;

import model.Model;
import model.Model_IF;
import view.HyteGUI_IF;

public class Controller implements Controller_IF {
	HyteGUI_IF gui;
	Model_IF model;
	public Controller(HyteGUI_IF gui) {
		this.gui = gui;
		model = new Model();
	}

}
