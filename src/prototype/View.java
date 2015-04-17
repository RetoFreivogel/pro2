package prototype;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import util.Chart;
import util.Matlab;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

public class View extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField tf_Ks; 
	private JTextField tf_Tg;
	private JTextField tf_Tu;
	private JTextField tf_Phrand;
	private JTextField tf_Ordn;
	private JTextField tf_Kr;
	private JTextField tf_Ymax;
	private JTextField tf_Tn;
	private JTextField tf_Tv;
	private JTextField tf_Tp;
	private JTextField tf_1;
	private JTextField tf_2;
	private JTextField tf_3;
	private JTextField tf_4;
	private JTextField tf_Tan;
	private JTextField tf_Taus;

	public View() {
		setLayout(new BorderLayout(0, 0));

		JMenuBar menuBar = new JMenuBar();
		add(menuBar, BorderLayout.NORTH);

		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);

		JMenuItem mntmNeu = new JMenuItem("Neu...");
		mnDatei.add(mntmNeu);

		JMenuItem mntmSpeichern = new JMenuItem("Speichern");
		mnDatei.add(mntmSpeichern);

		JMenuItem mntmSpeichernAls = new JMenuItem("Speichern als...");
		mnDatei.add(mntmSpeichernAls);

		JMenuItem mntmffnen = new JMenuItem("\u00D6ffnen...");
		mnDatei.add(mntmffnen);

		JMenuItem mntmNewMenuItem = new JMenuItem("Schliessen");
		mnDatei.add(mntmNewMenuItem);

		JSeparator separator_3 = new JSeparator();
		mnDatei.add(separator_3);

		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mnDatei.add(mntmBeenden);

		//Men�bereich Bearbeiten mit unterpunkten
		JMenu mnBearbeiten = new JMenu("Bearbeiten");
		menuBar.add(mnBearbeiten);

		JMenuItem mntmKopieren = new JMenuItem("Kopieren");
		mnBearbeiten.add(mntmKopieren);

		JMenuItem mntmAusschneiden = new JMenuItem("Ausschneiden");
		mnBearbeiten.add(mntmAusschneiden);

		JMenuItem mntmEinsetzen = new JMenuItem("Einsetzen");
		mnBearbeiten.add(mntmEinsetzen);

		JSeparator separator_2 = new JSeparator();
		mnBearbeiten.add(separator_2);

		JMenuItem mntmLschen = new JMenuItem("L\u00F6schen");
		mnBearbeiten.add(mntmLschen);

		JSeparator separator_1 = new JSeparator();
		mnBearbeiten.add(separator_1);

		JMenuItem mntmRckgngig = new JMenuItem("R\u00FCckg\u00E4ngig");
		mnBearbeiten.add(mntmRckgngig);

		JMenuItem mntmWiederholen = new JMenuItem("Wiederholen");
		mnBearbeiten.add(mntmWiederholen);

		// Men�bereich Optionen mit unterpunkten
		JMenu mnOptionen = new JMenu("Optionen");
		menuBar.add(mnOptionen);

		JMenuItem mntmSimulation = new JMenuItem("Simulation...");
		mnOptionen.add(mntmSimulation);

		JMenuItem mntmEinstellungen = new JMenuItem("Einstellungen");
		mnOptionen.add(mntmEinstellungen);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.WEST);

		JPanel pn_left = new JPanel();
		scrollPane.setViewportView(pn_left);
		pn_left.setLayout(new BoxLayout(pn_left, BoxLayout.Y_AXIS));

		JPanel pn_Eingabe = new JPanel();
		pn_Eingabe.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Eingabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_left.add(pn_Eingabe);
		pn_Eingabe.setLayout(new BoxLayout(pn_Eingabe, BoxLayout.Y_AXIS));

		JPanel pn_ERegelstrecke = new JPanel();
		pn_ERegelstrecke.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Regelstrecke", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pn_Eingabe.add(pn_ERegelstrecke);
		pn_ERegelstrecke.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel label = new JLabel("Definiert durch: ");
		pn_ERegelstrecke.add(label);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "KTuTg",
				"Frequenzgang" }));
		pn_ERegelstrecke.add(comboBox);

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

		JPanel pn_ERegler = new JPanel();
		pn_ERegler.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Regler", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
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

		JPanel pn_Ausgabe = new JPanel();
		pn_Ausgabe.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Ausgabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_left.add(pn_Ausgabe);
		pn_Ausgabe.setLayout(new BoxLayout(pn_Ausgabe, BoxLayout.Y_AXIS));

		JPanel pn_ARegelstrecke = new JPanel();
		pn_ARegelstrecke.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Regelstrecke", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		pn_Ausgabe.add(pn_ARegelstrecke);
		pn_ARegelstrecke.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lb_Ordn = new JLabel("Ordnung");
		pn_ARegelstrecke.add(lb_Ordn);

		this.tf_Ordn = new JTextField();
		this.tf_Ordn.setEditable(false);
		this.tf_Ordn.setText("3");
		pn_ARegelstrecke.add(this.tf_Ordn);
		this.tf_Ordn.setColumns(10);

		JLabel lb_Zeitkons = new JLabel("Zeitkonstanten");
		pn_ARegelstrecke.add(lb_Zeitkons);

		JButton btnLesen = new JButton("Lesen..");
		pn_ARegelstrecke.add(btnLesen);

		JPanel pn_ARegler = new JPanel();
		pn_ARegler.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Regler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Ausgabe.add(pn_ARegler);
		pn_ARegler.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lb_Kr = new JLabel("Kr");
		pn_ARegler.add(lb_Kr);

		this.tf_Kr = new JTextField();
		this.tf_Kr.setEditable(false);
		this.tf_Kr.setText("50");
		pn_ARegler.add(this.tf_Kr);
		this.tf_Kr.setColumns(10);

		JLabel lb_Tn = new JLabel("Tn");
		pn_ARegler.add(lb_Tn);

		this.tf_Tn = new JTextField();
		this.tf_Tn.setEditable(false);
		this.tf_Tn.setText("1");
		pn_ARegler.add(this.tf_Tn);
		this.tf_Tn.setColumns(10);

		JLabel lb_Tv = new JLabel("Tv");
		pn_ARegler.add(lb_Tv);

		this.tf_Tv = new JTextField();
		this.tf_Tv.setEditable(false);
		this.tf_Tv.setText("1");
		pn_ARegler.add(this.tf_Tv);
		this.tf_Tv.setColumns(10);

		JLabel lb_Tp = new JLabel("Tp");
		pn_ARegler.add(lb_Tp);

		this.tf_Tp = new JTextField();
		this.tf_Tp.setEditable(false);
		this.tf_Tp.setText("10");
		pn_ARegler.add(this.tf_Tp);
		this.tf_Tp.setColumns(10);

		JPanel pn_AAnalyse = new JPanel();
		pn_AAnalyse.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Analyse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Ausgabe.add(pn_AAnalyse);
		pn_AAnalyse.setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lb_Ymax = new JLabel("Ymax");
		pn_AAnalyse.add(lb_Ymax);

		this.tf_Ymax = new JTextField();
		this.tf_Ymax.setEditable(false);
		this.tf_Ymax.setText("1.25");
		pn_AAnalyse.add(this.tf_Ymax);
		this.tf_Ymax.setColumns(10);

		JLabel lb_Tan = new JLabel("Tan");
		pn_AAnalyse.add(lb_Tan);

		this.tf_Tan = new JTextField();
		this.tf_Tan.setEditable(false);
		this.tf_Tan.setText("0.75");
		pn_AAnalyse.add(this.tf_Tan);
		this.tf_Tan.setColumns(10);

		JLabel lb_Taus = new JLabel("Taus");
		pn_AAnalyse.add(lb_Taus);

		this.tf_Taus = new JTextField();
		this.tf_Taus.setEditable(false);
		this.tf_Taus.setText("1.25");
		pn_AAnalyse.add(this.tf_Taus);
		this.tf_Taus.setColumns(10);

		JLabel lb_1 = new JLabel("S |e(t)| dt");
		pn_AAnalyse.add(lb_1);

		this.tf_1 = new JTextField();
		this.tf_1.setEditable(false);
		this.tf_1.setText("1");
		pn_AAnalyse.add(this.tf_1);
		this.tf_1.setColumns(10);

		JLabel lb_2 = new JLabel("S e(t)^2 dt");
		pn_AAnalyse.add(lb_2);

		this.tf_2 = new JTextField();
		this.tf_2.setEditable(false);
		this.tf_2.setText("2");
		pn_AAnalyse.add(this.tf_2);
		this.tf_2.setColumns(10);

		JLabel lb_3 = new JLabel("S |e(t)|*t dt");
		pn_AAnalyse.add(lb_3);

		this.tf_3 = new JTextField();
		this.tf_3.setEditable(false);
		this.tf_3.setText("2");
		pn_AAnalyse.add(this.tf_3);
		this.tf_3.setColumns(10);

		JLabel lb_4 = new JLabel("S e(t)^2*t dt");
		pn_AAnalyse.add(lb_4);

		this.tf_4 = new JTextField();
		this.tf_4.setEditable(false);
		this.tf_4.setText("4");
		pn_AAnalyse.add(this.tf_4);
		this.tf_4.setColumns(10);
		
		//------Graph-----
		
		JPanel pn_Right = new JPanel();
		pn_Right.setBackground(Color.WHITE);
		add(pn_Right, BorderLayout.CENTER);
		pn_Right.setLayout(new BorderLayout(0, 0));

		JPanel panel_10 = new JPanel();
		pn_Right.add(panel_10, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel_10.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_panel_10.columnWeights = new double[] { 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gbl_panel_10.rowWeights = new double[] { 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel_10.setLayout(gbl_panel_10);

		JPanel panel_11 = new JPanel();
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.weightx = 0.5;
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridx = 0;
		gbc_panel_11.gridy = 0;
		panel_11.setBackground(Color.PINK);
		panel_10.add(panel_11, gbc_panel_11);

		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Regelkreis");
		chckbxNewCheckBox_2.setBackground(new Color(250, 128, 114));
		GridBagConstraints gbc_chckbxNewCheckBox_2 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox_2.gridx = 1;
		gbc_chckbxNewCheckBox_2.gridy = 0;
		panel_10.add(chckbxNewCheckBox_2, gbc_chckbxNewCheckBox_2);

		JPanel panel_9 = new JPanel();
		GridBagConstraints gbc_panel_9 = new GridBagConstraints();
		gbc_panel_9.weightx = 0.5;
		gbc_panel_9.fill = GridBagConstraints.BOTH;
		gbc_panel_9.gridx = 2;
		gbc_panel_9.gridy = 0;
		panel_9.setBackground(Color.GREEN);
		panel_10.add(panel_9, gbc_panel_9);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Regelstrecke");
		chckbxNewCheckBox.setBackground(new Color(154, 205, 50));
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 1;
		panel_10.setBackground(Color.BLUE);
		panel_10.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Regler");
		chckbxNewCheckBox_1.setBackground(new Color(100, 149, 237));
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox_1.gridx = 1;
		gbc_chckbxNewCheckBox_1.gridy = 2;
		panel_10.add(chckbxNewCheckBox_1, gbc_chckbxNewCheckBox_1);

		double[] output = Matlab.calcStep(new double[]{1},new double[]{1,1,2});
		Matlab.closeProxy();
		JPanel pn_chart = Chart.makePanel(output);
		pn_chart.setBackground(Color.WHITE);
		pn_Right.add(pn_chart, BorderLayout.CENTER);

		JLabel lblStatus = new JLabel("Status");
		add(lblStatus, BorderLayout.SOUTH);

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		try {
			UIManager.setLookAndFeel(
			        UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TopView");
		frame.getContentPane().add(new View());
		frame.setSize(800, 600);
		// frame.pack();
		frame.setVisible(true);
	}
}
