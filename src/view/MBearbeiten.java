package view;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.Controller;

/**
 * Men� Berarbeiten der Applikation
 * @author Alex Stocker, Reto Freivogel
 *
 */
public class MBearbeiten extends JMenu implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private  String[] bearbeiten = {"R\u00FCckg\u00E4ngig","Wiederholen"};
	private JMenuItem [] mntmBearbeiten = new JMenuItem[2];
	private Controller controller;
	
	/**
	 * Erstellt ein neues Men�.
	 * @param controller Referenz auf den Controller
	 */
	public MBearbeiten(Controller controller){
		this.controller = controller;
		setText("Bearbeiten");

		for (int i = 0; i < this.bearbeiten.length; i++) {
			mntmBearbeiten[i] = new JMenuItem(this.bearbeiten[i]);
			add(mntmBearbeiten[i]);
			mntmBearbeiten[i].addActionListener(this);
		}
		int mask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
		mntmBearbeiten[0].setAccelerator(KeyStroke.getKeyStroke('Z', mask));
		mntmBearbeiten[1].setAccelerator(KeyStroke.getKeyStroke('Y', mask));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()== mntmBearbeiten[0]) {
			controller.rueckgaengig();
		}
		if (e.getSource()== mntmBearbeiten[1]) {
			controller.wiederholen();
		}
		
	
		
	}

}
