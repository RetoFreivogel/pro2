package prototype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSeparator;

public class NewView extends JPanel {
	private  String[] datei = {"Neu...","Speichern","Speichern als...","\u00D6ffnen...","Schliessen","Beenden"};
	private  String[] bearbeiten = {"Kopieren","Ausschneiden","Einsetzen","L\u00F6schen","R\u00FCckg\u00E4ngig","Wiederholen"};
	private  String[] optionen = {"Simulation...","Einstellungen"};
	
	
	public NewView(){
		this.setLayout(new BorderLayout());
		setLayout(new BorderLayout(0,0));
		
		// Menuleiste
		JMenuBar menuBar = new JMenuBar();
		add(menuBar,BorderLayout.NORTH);
		// Untermenu Datei
		JMenu mnDatei = new JMenu("Datei"); 
		menuBar.add(mnDatei);
		JMenuItem [] mntmDatei = new JMenuItem[6];
		for (int i = 0; i < this.datei.length; i++) {
			 mntmDatei[i] = new JMenuItem(this.datei[i]);
			mnDatei.add(mntmDatei[i]);
			if (i==4) {	
				JSeparator separator = new JSeparator();
				mnDatei.add(separator);
			}
		}
		// Untermenu Bearbeiten
		JMenu mnBearbeiten = new JMenu("Bearbeiten"); 
		menuBar.add(mnBearbeiten);
		JMenuItem [] mntmBearbeiten = new JMenuItem[6];
		for (int i = 0; i < this.bearbeiten.length; i++) {
			mntmBearbeiten[i] = new JMenuItem(this.bearbeiten[i]);
			mnBearbeiten.add(mntmBearbeiten[i]);
			if (i==2||i==3) {	
				JSeparator separator = new JSeparator();
				mnBearbeiten.add(separator);

			}
		}
		// Untermenu Optionen
		JMenu mnOptionen = new JMenu("Optionen"); 
		menuBar.add(mnOptionen);
		for (int i = 0; i < this.optionen.length; i++) {
			JMenuItem mntmNeu = new JMenuItem(this.bearbeiten[i]);
			mnOptionen.add(mntmNeu);

		}

		// Panel
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TopView_Alex");
		frame.getContentPane().add(new NewView());
		frame.setSize(800, 600);
		//frame.pack();
		frame.setVisible(true);

	}

}
