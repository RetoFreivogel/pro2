package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import controller.Controller;


public class MDatei extends JMenu implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private  String[] datei = {"Neu...","Speichern","Speichern als...","\u00D6ffnen...","Beenden"};
	private JMenuItem [] mntmDatei = new JMenuItem[5];
	private Controller controller;

	
	public MDatei(Controller controller){
		this.controller = controller;
		setText("Datei");

		for (int i = 0; i < this.datei.length; i++) {
			 mntmDatei[i] = new JMenuItem(this.datei[i]);
			add(mntmDatei[i]);
			mntmDatei[i].addActionListener(this);
			if (i==3) {	
				JSeparator separator = new JSeparator();
				add(separator);
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== mntmDatei[0]) {
			controller.neu();
		}
		if (e.getSource()== mntmDatei[1]) {
			controller.speichern();
		}
		if (e.getSource()== mntmDatei[2]) {
			controller.speichernals();
		}
		if (e.getSource()== mntmDatei[3]) {
			controller.oeffnen();
		}
		if (e.getSource()== mntmDatei[4]) {
			controller.beenden();
		}
		
	}

}
