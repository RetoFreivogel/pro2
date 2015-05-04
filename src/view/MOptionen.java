package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import controller.Controller;

public class MOptionen extends JMenu implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private  String[] optionen = {"Simulation...","Einstellungen"};
	
	public  MOptionen(Controller controller){
		setText("Bearbeiten");
		for (int i = 0; i < this.optionen.length; i++) {
			JMenuItem mntmNeu = new JMenuItem(this.optionen[i]);
			add(mntmNeu);			
		}
		
	}
	public void einstellung(){
		
	}
	public void simulation(){
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	
		
	}
}
