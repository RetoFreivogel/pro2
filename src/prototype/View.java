package prototype;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JCheckBox;
import java.awt.Component;
import javax.swing.UIManager;

public class View extends JPanel{
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTextField textField_3;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
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
		
		JMenu mnOptionen = new JMenu("Optionen");
		menuBar.add(mnOptionen);
		
		JMenuItem mntmSimulation = new JMenuItem("Simulation...");
		mnOptionen.add(mntmSimulation);
		
		JMenuItem mntmEinstellungen = new JMenuItem("Einstellungen");
		mnOptionen.add(mntmEinstellungen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Eingabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Regelstrecke", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label = new JLabel("Definiert durch: ");
		panel_2.add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"KTuTg", "Frequenzgang"}));
		panel_2.add(comboBox);
		
		JLabel label_1 = new JLabel("Ks");
		panel_2.add(label_1);
		
		textField = new JTextField();
		textField.setText("1");
		textField.setColumns(10);
		panel_2.add(textField);
		
		JLabel label_2 = new JLabel("Tg");
		panel_2.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setText("1");
		textField_1.setColumns(10);
		panel_2.add(textField_1);
		
		JLabel label_3 = new JLabel("Tu");
		panel_2.add(label_3);
		
		textField_2 = new JTextField();
		textField_2.setText("5");
		textField_2.setColumns(10);
		panel_2.add(textField_2);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Regler", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel label_6 = new JLabel("Topologie");
		panel_3.add(label_6);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"PI", "PID"}));
		comboBox_2.setSelectedIndex(1);
		panel_3.add(comboBox_2);
		
		JLabel label_4 = new JLabel("Definiert durch: ");
		panel_3.add(label_4);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Manuell", "Phasengang", "Ziegler", "Chien", "Oppelt", "Rosenberg", "Tsumme"}));
		comboBox_1.setSelectedIndex(1);
		panel_3.add(comboBox_1);
		
		JLabel label_5 = new JLabel("Phasenrand");
		panel_3.add(label_5);
		
		textField_4 = new JTextField();
		textField_4.setText("20\u00B0");
		textField_4.setColumns(10);
		panel_3.add(textField_4);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Ausgabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Regelstrecke", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.add(panel_5);
		panel_5.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblN = new JLabel("Ordnung");
		panel_5.add(lblN);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setText("3");
		panel_5.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblTn = new JLabel("Zeitkonstanten");
		panel_5.add(lblTn);
		
		JButton btnLesen = new JButton("Lesen..");
		panel_5.add(btnLesen);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Regler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.add(panel_6);
		panel_6.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblKr = new JLabel("Kr");
		panel_6.add(lblKr);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setText("50");
		panel_6.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblTn_1 = new JLabel("Tn");
		panel_6.add(lblTn_1);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setText("1");
		panel_6.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblTv = new JLabel("Tv");
		panel_6.add(lblTv);
		
		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setText("1");
		panel_6.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblTp = new JLabel("Tp");
		panel_6.add(lblTp);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setText("10");
		panel_6.add(textField_9);
		textField_9.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Analyse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblYmax = new JLabel("Ymax");
		panel_7.add(lblYmax);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setText("1.25");
		panel_7.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblTan = new JLabel("Tan");
		panel_7.add(lblTan);
		
		textField_14 = new JTextField();
		textField_14.setEditable(false);
		textField_14.setText("0.75");
		panel_7.add(textField_14);
		textField_14.setColumns(10);
		
		JLabel lblTaus = new JLabel("Taus");
		panel_7.add(lblTaus);
		
		textField_15 = new JTextField();
		textField_15.setEditable(false);
		textField_15.setText("1.25");
		panel_7.add(textField_15);
		textField_15.setColumns(10);
		
		JLabel lblSetDt = new JLabel("S |e(t)| dt");
		panel_7.add(lblSetDt);
		
		textField_10 = new JTextField();
		textField_10.setEditable(false);
		textField_10.setText("1");
		panel_7.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblSEtDt = new JLabel("S e(t)^2 dt");
		panel_7.add(lblSEtDt);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setText("2");
		panel_7.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblSettDt = new JLabel("S |e(t)|*t dt");
		panel_7.add(lblSettDt);
		
		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setText("2");
		panel_7.add(textField_12);
		textField_12.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("S e(t)^2*t dt");
		panel_7.add(lblNewLabel);
		
		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setText("4");
		panel_7.add(textField_13);
		textField_13.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_10 = new JPanel();
		panel_8.add(panel_10, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[] {0, 0, 0, 0};
		gbl_panel_10.rowHeights = new int[] {0, 0, 0, 0};
		gbl_panel_10.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		
		JPanel panel_11 = new JPanel();
		GridBagConstraints gbc_panel_11 = new GridBagConstraints();
		gbc_panel_11.weightx = 0.5;
		gbc_panel_11.fill = GridBagConstraints.BOTH;
		gbc_panel_11.gridx = 0;
		gbc_panel_11.gridy = 0;
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
		panel_10.add(panel_9, gbc_panel_9);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Regelstrecke");
		chckbxNewCheckBox.setBackground(new Color(154, 205, 50));
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 1;
		panel_10.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Regler");
		chckbxNewCheckBox_1.setBackground(new Color(100, 149, 237));
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox_1.gridx = 1;
		gbc_chckbxNewCheckBox_1.gridy = 2;
		panel_10.add(chckbxNewCheckBox_1, gbc_chckbxNewCheckBox_1);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(Color.WHITE);
		panel_8.add(panel_12, BorderLayout.CENTER);
		
		JLabel lblStatus = new JLabel("Status");
		add(lblStatus, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TopView");
		frame.getContentPane().add(new View());
		frame.setSize(800, 600);
		//frame.pack();
		frame.setVisible(true);
	}
}
