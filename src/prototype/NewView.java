package prototype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSeparator;

import util.Chart;
import util.Matlab;

public class NewView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private  String[] datei = {"Neu...","Speichern","Speichern als...","\u00D6ffnen...","Schliessen","Beenden"};
	private  String[] bearbeiten = {"Kopieren","Ausschneiden","Einsetzen","L\u00F6schen","R\u00FCckg\u00E4ngig","Wiederholen"};
	private  String[] optionen = {"Simulation...","Einstellungen"};
	
	private SidebarPanel sidebarPanel;
	
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

		//---------------------Panel_Left-------------------------------
		sidebarPanel = new SidebarPanel();
		add(sidebarPanel, BorderLayout.WEST);
		
		//---------------------Panel_Right-------------------------------
		JPanel pn_Right = new JPanel();
		pn_Right.setBackground(Color.WHITE);
		add(pn_Right, BorderLayout.CENTER);
		pn_Right.setLayout(new BorderLayout(0, 0));	
		
		//---------------------Panel_Optionen-------------------------------
		JPanel pn_Optionen = new JPanel();
		pn_Right.add(pn_Optionen, BorderLayout.SOUTH);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Regelkreis");
		chckbxNewCheckBox_2.setBackground(new Color(250, 128, 114));
		GridBagConstraints gbc_chckbxNewCheckBox_2 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox_2.gridx = 1;
		gbc_chckbxNewCheckBox_2.gridy = 0;
		pn_Optionen.add(chckbxNewCheckBox_2, gbc_chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Regelstrecke");
		chckbxNewCheckBox.setBackground(new Color(154, 205, 50));
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 1;
		pn_Optionen.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Regler");
		chckbxNewCheckBox_1.setBackground(new Color(100, 149, 237));
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox_1.gridx = 1;
		gbc_chckbxNewCheckBox_1.gridy = 2;
		pn_Optionen.add(chckbxNewCheckBox_1, gbc_chckbxNewCheckBox_1);
		
		//---------------------Graph-------------------------------
		double[] output = Matlab.calcStep(new double[]{1},new double[]{1,1,2});
		Matlab.closeProxy();
		JPanel pn_chart = Chart.makePanel(output);
		pn_chart.setBackground(Color.WHITE);
		pn_Right.add(pn_chart, BorderLayout.CENTER);
		
		
		//---------------------Status Zeile-------------------------------
		JPanel pn_Status = new JPanel();
		pn_Status.setLayout(new BorderLayout());
		JLabel lblStatus = new JLabel("Status",JLabel.LEFT);
		pn_Status.add(lblStatus,BorderLayout.WEST);
		add(pn_Status, BorderLayout.SOUTH);

	}
	


}
