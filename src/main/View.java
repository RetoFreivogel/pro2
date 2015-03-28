package main;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

public class View extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	
	Controller controller;
	Model model;
	
	public View(Controller controller, Model model){
		this.controller = controller;
		this.model = model;
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
}
