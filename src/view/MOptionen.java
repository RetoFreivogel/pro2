package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import controller.Controller;

public class MOptionen extends JMenu implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private String[] optionen = {"Simulation...","Einstellungen"};
	private JMenuItem [] mntmOptionen = new JMenuItem[2];
	private Controller controller;
	
	public  MOptionen(Controller controller){
		this.controller = controller;
		setText("Optionen");

		for (int i = 0; i < this.optionen.length; i++) {
			mntmOptionen[i] = new JMenuItem(this.optionen[i]);
			add(mntmOptionen[i]);
			mntmOptionen[i].addActionListener(this);
		}
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== mntmOptionen[0]) {
			controller.simulation();
		}
		if (e.getSource()== mntmOptionen[0]) {
			controller.einstellung();
		}
		
	}
}
