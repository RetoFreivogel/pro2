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
	
	public MDatei(Controller controller){
		setText("Datei");
		JMenuItem [] mntmDatei = new JMenuItem[6];
		for (int i = 0; i < this.datei.length; i++) {
			 mntmDatei[i] = new JMenuItem(this.datei[i]);
			add(mntmDatei[i]);
			if (i==3) {	
				JSeparator separator = new JSeparator();
				add(separator);
			}
		}
	}
	
	public void neu(){
		
	}
	public void speichern(){
		
	}
	public void speichernals(){
		
	}
	public void oeffnen(){
		
	}
	public void beenden(){
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
	
		
	}

}
