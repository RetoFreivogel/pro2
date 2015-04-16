package prototype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JSeparator;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class NewView extends JPanel {
	private static final long serialVersionUID = 1L;
	
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
		JPanel panel = new  JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel pn_Eingabe = new JPanel();
		pn_Eingabe.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Eingabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(pn_Eingabe);
		
		JPanel pn_ERegelstrecke = new JPanel();
		pn_ERegelstrecke.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Regelstrecke", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Eingabe.add(pn_ERegelstrecke);
		pn_Eingabe.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lb_defd_RS = new JLabel("Definiert durch:");
		pn_ERegelstrecke.add(lb_defd_RS);
		
		JComboBox<String> cbbx_defd_RS = new JComboBox<>();
		cbbx_defd_RS.setModel(new DefaultComboBoxModel<>(new String[] { "KTuTg",
				"Frequenzgang" }));
		pn_Eingabe.add(cbbx_defd_RS);
		
		
		
		
		JPanel pn_ERegler = new JPanel();
		pn_ERegler.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Regler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Eingabe.add(pn_ERegler);
		
		JPanel pn_Ausgabe = new JPanel();
		pn_Ausgabe.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Ausgabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(pn_Ausgabe);
		
		JPanel pn_ARegelstrecke = new JPanel();
		pn_ARegelstrecke.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Regelstrecke", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Ausgabe.add(pn_ARegelstrecke);
		
		JPanel pn_ARegler = new JPanel();
		pn_ARegler.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Regler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Ausgabe.add(pn_ARegler);
		
		JPanel pn_AAnalyse = new JPanel();
		pn_AAnalyse.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Analyse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Ausgabe.add(pn_AAnalyse);
		

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
