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
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class NewView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private  String[] datei = {"Neu...","Speichern","Speichern als...","\u00D6ffnen...","Schliessen","Beenden"};
	private  String[] bearbeiten = {"Kopieren","Ausschneiden","Einsetzen","L\u00F6schen","R\u00FCckg\u00E4ngig","Wiederholen"};
	private  String[] optionen = {"Simulation...","Einstellungen"};
	
	private JTextField tf_Ks; 
	private JTextField tf_Tg;
	private JTextField tf_Tu;
	private JTextField tf_Phrand;
	private JTextField tf_Kr;
	private JTextField tf_Tn;
	private JTextField tf_Tv;
	private JTextField tf_Tp;
	private JTextField tf_Ymax;
	private JTextField tf_Tan;
	private JTextField tf_Taus;
	private JTextField tf_1;
	private JTextField tf_2;
	private JTextField tf_3;
	private JTextField tf_4;
	private JTextField tf_5;
	
	
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



		// Panel Left
		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.WEST);
		
		JPanel pn_left = new  JPanel();
		scrollPane.setViewportView(pn_left);
		pn_left.setLayout(new BoxLayout(pn_left, BoxLayout.Y_AXIS));
		
		//---------------------Eingabe-------------------------------		
		JPanel pn_Eingabe = new JPanel();
		pn_Eingabe.setLayout(new BoxLayout(pn_Eingabe, BoxLayout.Y_AXIS));
		pn_Eingabe.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Eingabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_left.add(pn_Eingabe);
		//---------------------Eingabe_Regelstrecke-------------------------------
		JPanel pn_ERegelstrecke = new JPanel();
		pn_ERegelstrecke.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Regelstrecke", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Eingabe.add(pn_ERegelstrecke);
		pn_ERegelstrecke.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lb_defd_RS = new JLabel("Definiert durch:");
		pn_ERegelstrecke.add(lb_defd_RS);
		
		
		JComboBox<String> cbbx_defd_RS = new JComboBox<>();
		cbbx_defd_RS.setModel(new DefaultComboBoxModel<>(new String[] { "KTuTg",
				"Frequenzgang" }));
		pn_ERegelstrecke.add(cbbx_defd_RS);
		
		JLabel lb_Ks = new JLabel("Ks");
		pn_ERegelstrecke.add(lb_Ks);
		
		this.tf_Ks = new JTextField();	
		this.tf_Ks.setText("1");
		this.tf_Ks.setColumns(10);
		pn_ERegelstrecke.add(this.tf_Ks);

		JLabel lb_Tg = new JLabel("Tg");
		pn_ERegelstrecke.add(lb_Tg);

		this.tf_Tg = new JTextField();
		this.tf_Tg.setText("1");
		this.tf_Tg.setColumns(10);
		pn_ERegelstrecke.add(this.tf_Tg);

		JLabel lb_Tu = new JLabel("Tu");
		pn_ERegelstrecke.add(lb_Tu);

		this.tf_Tu = new JTextField();
		this.tf_Tu.setText("5");
		this.tf_Tu.setColumns(10);
		
		pn_ERegelstrecke.add(this.tf_Tu);
		
		//---------------------Eingabe_Regler-------------------------------
		JPanel pn_ERegler = new JPanel();
		pn_ERegler.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Regler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Eingabe.add(pn_ERegler);
		pn_ERegler.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lb_Topo = new JLabel("Topologie");
		pn_ERegler.add(lb_Topo);

		JComboBox<String> cbbx_Topo = new JComboBox<>();
		cbbx_Topo.setModel(new DefaultComboBoxModel<>(new String[] { "PI",
				"PID" }));
		cbbx_Topo.setSelectedIndex(1);
		pn_ERegler.add(cbbx_Topo);

		JLabel lb_defd_R = new JLabel("Definiert durch: ");
		pn_ERegler.add(lb_defd_R);

		JComboBox<String> cbbx_defd_R = new JComboBox<>();
		cbbx_defd_R.setModel(new DefaultComboBoxModel<>(new String[] {
				"Manuell", "Phasengang", "Ziegler", "Chien", "Oppelt",
				"Rosenberg", "Tsumme" }));
		cbbx_defd_R.setSelectedIndex(1);
		pn_ERegler.add(cbbx_defd_R);

		JLabel lb_Phrand = new JLabel("Phasenrand");
		pn_ERegler.add(lb_Phrand);

		this.tf_Phrand = new JTextField();
		this.tf_Phrand.setText("20\u00B0");
		this.tf_Phrand.setColumns(10);
		pn_ERegler.add(this.tf_Phrand);
		
		
		//---------------------Ausgabe-------------------------------
		JPanel pn_Ausgabe = new JPanel();
		pn_Ausgabe.setLayout(new BoxLayout(pn_Ausgabe, BoxLayout.Y_AXIS));
		pn_Ausgabe.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Ausgabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_left.add(pn_Ausgabe);
		
		//---------------------Ausgabe_Regelstrecke-------------------------------
		JPanel pn_ARegelstrecke = new JPanel();
		pn_ARegelstrecke.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Regelstrecke", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Ausgabe.add(pn_ARegelstrecke);
		pn_ARegelstrecke.setLayout(new GridLayout(0, 2, 0, 0));
		
		//---------------------Ausgabe_Regler-------------------------------
		JPanel pn_ARegler = new JPanel();
		pn_ARegler.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Regler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Ausgabe.add(pn_ARegler);
		pn_ARegler.setLayout(new GridLayout(0, 2, 0, 0));
		
		//---------------------Ausgabe_Analyse-------------------------------
		JPanel pn_AAnalyse = new JPanel();
		pn_AAnalyse.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Analyse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Ausgabe.add(pn_AAnalyse);
		pn_AAnalyse.setLayout(new GridLayout(0, 2, 0, 0));


	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setUndecorated(false);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TopView_Alex");
		frame.getContentPane().add(new NewView());
		frame.setSize(800, 600);
		//frame.pack();
		frame.setVisible(true);

	}

}
