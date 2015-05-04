package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import controller.Controller;

public class MBearbeiten extends JMenu implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private  String[] bearbeiten = {"R\u00FCckg\u00E4ngig","Wiederholen"};
	
	public MBearbeiten(Controller controller){
		setText("Bearbeiten");
		JMenuItem [] mntmBearbeiten = new JMenuItem[6];
		for (int i = 0; i < this.bearbeiten.length; i++) {
			mntmBearbeiten[i] = new JMenuItem(this.bearbeiten[i]);
			add(mntmBearbeiten[i]);

		}
	}

	public void actionPerformed(ActionEvent e) {
	
		
	}

}
