package prototype;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
	private JTextField tf_Ordn;
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



		//---------------------Panel_Left-------------------------------

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
		pn_Eingabe.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Eingabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_left.add(pn_Eingabe);
		//---------------------Eingabe_Regelstrecke-------------------------------
		JPanel pn_ERegelstrecke = new JPanel();
		pn_ERegelstrecke.setBorder(new TitledBorder(new LineBorder(Color.GRAY),"Regelstrecke", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		
		tf_Ks = new JTextField("1",10);
		tf_Ks.setEditable(false);
		pn_ERegelstrecke.add(tf_Ks);

		JLabel lb_Tg = new JLabel("Tg");
		pn_ERegelstrecke.add(lb_Tg);

		tf_Tg = new JTextField("1",10);
		tf_Tg.setEditable(false);
		pn_ERegelstrecke.add(tf_Tg);

		JLabel lb_Tu = new JLabel("Tu");
		pn_ERegelstrecke.add(lb_Tu);

		tf_Tu = new JTextField("5",10);
		tf_Tu.setEditable(false);		
		pn_ERegelstrecke.add(tf_Tu);
		
		//---------------------Eingabe_Regler-------------------------------
		JPanel pn_ERegler = new JPanel();
		pn_ERegler.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
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

		tf_Phrand = new JTextField("20\u00B0",10);
		tf_Phrand.setEditable(false);
		pn_ERegler.add(tf_Phrand);
		
		//---------------------Ausgabe-------------------------------
		JPanel pn_Ausgabe = new JPanel();
		pn_Ausgabe.setLayout(new BoxLayout(pn_Ausgabe, BoxLayout.Y_AXIS));
		pn_Ausgabe.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Ausgabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_left.add(pn_Ausgabe);
		
		
		//---------------------Ausgabe_Regelstrecke-------------------------------
		JPanel pn_ARegelstrecke = new JPanel();
		pn_ARegelstrecke.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Regelstrecke", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Ausgabe.add(pn_ARegelstrecke);
		pn_ARegelstrecke.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lb_Ordn = new JLabel("Ordnung");
		pn_ARegelstrecke.add(lb_Ordn);

		tf_Ordn = new JTextField("3",10);
		tf_Ordn.setEditable(false);
		pn_ARegelstrecke.add(tf_Ordn);


		JLabel lb_Zeitkons = new JLabel("Zeitkonstanten");
		pn_ARegelstrecke.add(lb_Zeitkons);

		JButton bt_Zeitkonst = new JButton("Lesen..");
		pn_ARegelstrecke.add(bt_Zeitkonst);
		
		//---------------------Ausgabe_Regler-------------------------------
		JPanel pn_ARegler = new JPanel();
		pn_ARegler.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Regler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Ausgabe.add(pn_ARegler);
		pn_ARegler.setLayout(new GridLayout(0, 2, 0, 0));
		JLabel lb_Kr = new JLabel("Kr");
		pn_ARegler.add(lb_Kr);

		tf_Kr = new JTextField("50",10);
		tf_Kr.setEditable(false);
		pn_ARegler.add(tf_Kr);
	
		JLabel lb_Tn = new JLabel("Tn");
		pn_ARegler.add(lb_Tn);

		tf_Tn = new JTextField("1",10);
		tf_Tn.setEditable(false);
		pn_ARegler.add(tf_Tn);		

		JLabel lb_Tv = new JLabel("Tv");
		pn_ARegler.add(lb_Tv);

		tf_Tv = new JTextField("1", 10);
		tf_Tv.setEditable(false);
		pn_ARegler.add(tf_Tv);	

		JLabel lb_Tp = new JLabel("Tp");
		pn_ARegler.add(lb_Tp);

		tf_Tp = new JTextField("10",10);
		tf_Tp.setEditable(false);
		pn_ARegler.add(tf_Tp);
				
		
		//---------------------Ausgabe_Analyse-------------------------------
		JPanel pn_AAnalyse = new JPanel();
		pn_AAnalyse.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Analyse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Ausgabe.add(pn_AAnalyse);
		pn_AAnalyse.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lb_Ymax = new JLabel("Ymax");
		pn_AAnalyse.add(lb_Ymax);

		tf_Ymax = new JTextField();
		tf_Ymax.setEditable(false);
		tf_Ymax.setText("1.25");
		pn_AAnalyse.add(tf_Ymax);
		tf_Ymax.setColumns(10);

		JLabel lb_Tan = new JLabel("Tan");
		pn_AAnalyse.add(lb_Tan);

		tf_Tan = new JTextField();
		tf_Tan.setEditable(false);
		tf_Tan.setText("0.75");
		pn_AAnalyse.add(tf_Tan);
		tf_Tan.setColumns(10);

		JLabel lb_Taus = new JLabel("Taus");
		pn_AAnalyse.add(lb_Taus);

		tf_Taus = new JTextField("1.25",10);
		tf_Taus.setEditable(false);	
		pn_AAnalyse.add(tf_Taus);

		JLabel lb_1 = new JLabel("S |e(t)| dt");
		pn_AAnalyse.add(lb_1);

		tf_1 = new JTextField("1",10);
		tf_1.setEditable(false);
		pn_AAnalyse.add(tf_1);
		
		JLabel lb_2 = new JLabel("S e(t)^2 dt");
		pn_AAnalyse.add(lb_2);

		tf_2 = new JTextField("2",10);
		tf_2.setEditable(false);
		pn_AAnalyse.add(tf_2);

		JLabel lb_3 = new JLabel("S |e(t)|*t dt");
		pn_AAnalyse.add(lb_3);

		tf_3 = new JTextField("2",10);
		tf_3.setEditable(false);
		pn_AAnalyse.add(tf_3);

		JLabel lb_4 = new JLabel("S e(t)^2*t dt");
		pn_AAnalyse.add(lb_4);

		tf_4 = new JTextField("4",10);
		tf_4.setEditable(false);
		pn_AAnalyse.add(tf_4);
		
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

		//---------------------Status Zeile-------------------------------
		JPanel pn_Status = new JPanel();
		pn_Status.setLayout(new BorderLayout());
		JLabel lblStatus = new JLabel("Status",JLabel.LEFT);
		pn_Status.add(lblStatus,BorderLayout.WEST);
		add(pn_Status, BorderLayout.SOUTH);

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
