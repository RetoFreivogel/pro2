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

public class View extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
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
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0};
		gbl_panel.rowHeights = new int[] {0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Regeltrecke", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.anchor = GridBagConstraints.NORTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {0, 0, 0};
		gbl_panel_1.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblStrecke = new JLabel("Definiert durch: ");
		GridBagConstraints gbc_lblStrecke = new GridBagConstraints();
		gbc_lblStrecke.fill = GridBagConstraints.BOTH;
		gbc_lblStrecke.gridx = 0;
		gbc_lblStrecke.gridy = 0;
		panel_1.add(lblStrecke, gbc_lblStrecke);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Ks, Tu, Tg", "Frequenzgang"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.weightx = 1.0;
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel_1.add(comboBox, gbc_comboBox);
		
		JLabel lblKs = new JLabel("Ks");
		GridBagConstraints gbc_lblKs = new GridBagConstraints();
		gbc_lblKs.fill = GridBagConstraints.BOTH;
		gbc_lblKs.gridx = 0;
		gbc_lblKs.gridy = 1;
		panel_1.add(lblKs, gbc_lblKs);
		
		textField = new JTextField();
		textField.setText("1");
		textField.setColumns(10);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.weightx = 1.0;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel_1.add(textField, gbc_textField);
		
		JLabel lblTg = new JLabel("Tg");
		GridBagConstraints gbc_lblTg = new GridBagConstraints();
		gbc_lblTg.fill = GridBagConstraints.BOTH;
		gbc_lblTg.gridx = 0;
		gbc_lblTg.gridy = 2;
		panel_1.add(lblTg, gbc_lblTg);
		
		textField_1 = new JTextField();
		textField_1.setText("1");
		textField_1.setColumns(10);
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.weightx = 1.0;
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panel_1.add(textField_1, gbc_textField_1);
		
		JLabel lblTu = new JLabel("Tu");
		GridBagConstraints gbc_lblTu = new GridBagConstraints();
		gbc_lblTu.fill = GridBagConstraints.BOTH;
		gbc_lblTu.gridx = 0;
		gbc_lblTu.gridy = 3;
		panel_1.add(lblTu, gbc_lblTu);
		
		textField_2 = new JTextField();
		textField_2.setText("5");
		textField_2.setColumns(10);
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		panel_1.add(textField_2, gbc_textField_2);
		
		JLabel lblOrdnung = new JLabel("Ordnung");
		GridBagConstraints gbc_lblOrdnung = new GridBagConstraints();
		gbc_lblOrdnung.fill = GridBagConstraints.BOTH;
		gbc_lblOrdnung.gridx = 0;
		gbc_lblOrdnung.gridy = 4;
		panel_1.add(lblOrdnung, gbc_lblOrdnung);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setText("3");
		textField_3.setColumns(10);
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.BOTH;
		gbc_textField_3.gridx = 1;
		gbc_textField_3.gridy = 4;
		panel_1.add(textField_3, gbc_textField_3);
		
		JLabel lblWerte = new JLabel("Werte");
		GridBagConstraints gbc_lblWerte = new GridBagConstraints();
		gbc_lblWerte.fill = GridBagConstraints.BOTH;
		gbc_lblWerte.gridx = 0;
		gbc_lblWerte.gridy = 5;
		panel_1.add(lblWerte, gbc_lblWerte);
		
		JButton btnBearbeiten = new JButton("Lesen...");
		GridBagConstraints gbc_btnBearbeiten = new GridBagConstraints();
		gbc_btnBearbeiten.fill = GridBagConstraints.BOTH;
		gbc_btnBearbeiten.gridx = 1;
		gbc_btnBearbeiten.gridy = 5;
		panel_1.add(btnBearbeiten, gbc_btnBearbeiten);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "PID Regler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.anchor = GridBagConstraints.NORTH;
		gbc_panel_2.weightx = 1.0;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] {0, 0, 0};
		gbl_panel_2.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblRegler = new JLabel("Definiert durch: ");
		GridBagConstraints gbc_lblRegler = new GridBagConstraints();
		gbc_lblRegler.fill = GridBagConstraints.BOTH;
		gbc_lblRegler.gridx = 0;
		gbc_lblRegler.gridy = 0;
		panel_2.add(lblRegler, gbc_lblRegler);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Phasengangmethode", "Manuell"}));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.weightx = 1.0;
		gbc_comboBox_1.anchor = GridBagConstraints.WEST;
		gbc_comboBox_1.fill = GridBagConstraints.VERTICAL;
		gbc_comboBox_1.gridx = 1;
		gbc_comboBox_1.gridy = 0;
		panel_2.add(comboBox_1, gbc_comboBox_1);
		
		JLabel lblberschwingen = new JLabel("\u00DCberschwingen");
		GridBagConstraints gbc_lblberschwingen = new GridBagConstraints();
		gbc_lblberschwingen.fill = GridBagConstraints.BOTH;
		gbc_lblberschwingen.gridx = 0;
		gbc_lblberschwingen.gridy = 1;
		panel_2.add(lblberschwingen, gbc_lblberschwingen);
		
		textField_4 = new JTextField();
		textField_4.setText("10%");
		textField_4.setColumns(10);
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.fill = GridBagConstraints.BOTH;
		gbc_textField_4.gridx = 1;
		gbc_textField_4.gridy = 1;
		panel_2.add(textField_4, gbc_textField_4);
		
		JLabel lblP = new JLabel("P");
		GridBagConstraints gbc_lblP = new GridBagConstraints();
		gbc_lblP.fill = GridBagConstraints.BOTH;
		gbc_lblP.gridx = 0;
		gbc_lblP.gridy = 2;
		panel_2.add(lblP, gbc_lblP);
		
		textField_5 = new JTextField();
		textField_5.setText("50");
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		GridBagConstraints gbc_textField_5 = new GridBagConstraints();
		gbc_textField_5.fill = GridBagConstraints.BOTH;
		gbc_textField_5.gridx = 1;
		gbc_textField_5.gridy = 2;
		panel_2.add(textField_5, gbc_textField_5);
		
		JLabel lblI = new JLabel("I");
		GridBagConstraints gbc_lblI = new GridBagConstraints();
		gbc_lblI.fill = GridBagConstraints.BOTH;
		gbc_lblI.gridx = 0;
		gbc_lblI.gridy = 3;
		panel_2.add(lblI, gbc_lblI);
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setText("1");
		textField_6.setColumns(10);
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.fill = GridBagConstraints.BOTH;
		gbc_textField_6.gridx = 1;
		gbc_textField_6.gridy = 3;
		panel_2.add(textField_6, gbc_textField_6);
		
		JLabel lblD = new JLabel("D");
		GridBagConstraints gbc_lblD = new GridBagConstraints();
		gbc_lblD.fill = GridBagConstraints.BOTH;
		gbc_lblD.gridx = 0;
		gbc_lblD.gridy = 4;
		panel_2.add(lblD, gbc_lblD);
		
		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setText("5");
		textField_7.setColumns(10);
		GridBagConstraints gbc_textField_7 = new GridBagConstraints();
		gbc_textField_7.fill = GridBagConstraints.BOTH;
		gbc_textField_7.gridx = 1;
		gbc_textField_7.gridy = 4;
		panel_2.add(textField_7, gbc_textField_7);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Analyse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_5.anchor = GridBagConstraints.NORTH;
		gbc_panel_5.weightx = 1.0;
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 2;
		panel.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] {0};
		gbl_panel_5.rowHeights = new int[] {0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{0.0};
		gbl_panel_5.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Maximum 1. \u00DCberschwingung", "Fl\u00E4che Fehler", "Fl\u00E4che Fehler quadriert", "Fl\u00E4che Fehler zeitbewertet", "Fl\u00E4che Fehler quadriert zeitbewertet"}));
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.weightx = 1.0;
		gbc_comboBox_2.fill = GridBagConstraints.BOTH;
		gbc_comboBox_2.gridx = 0;
		gbc_comboBox_2.gridy = 0;
		panel_5.add(comboBox_2, gbc_comboBox_2);
		
		textField_8 = new JTextField();
		textField_8.setText("0");
		GridBagConstraints gbc_textField_8 = new GridBagConstraints();
		gbc_textField_8.fill = GridBagConstraints.BOTH;
		gbc_textField_8.gridx = 0;
		gbc_textField_8.gridy = 1;
		panel_5.add(textField_8, gbc_textField_8);
		textField_8.setColumns(10);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		splitPane.setLeftComponent(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		splitPane.setRightComponent(panel_4);
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
