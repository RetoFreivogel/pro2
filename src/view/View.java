package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import model.Model;
import model.SchrittAntwort;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import controller.Controller;
import util.Chart;

public class View extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	private SidebarPanel sidebarPanel;
	private JLabel lblStatus;
	private ChartPanel pn_chart;
	
	private Model model;
	private Controller controller;

	public View(Model model, Controller controller) {
		super();
		this.model = model;
		this.controller = controller;
		init();
		model.addObserver(this);
	}
	
	
	private void init(){
		this.setLayout(new BorderLayout());
		setLayout(new BorderLayout(0, 0));

		// Menuleiste
		JMenuBar menuBar = new JMenuBar();
		add(menuBar, BorderLayout.NORTH);
		// Untermenu Datei
		MDatei mnDatei = new MDatei(controller);
		menuBar.add(mnDatei);
		// Untermenu Bearbeiten
		MBearbeiten mnBearbeiten = new MBearbeiten(controller);
		menuBar.add(mnBearbeiten);

		// Untermenu Optionen
		MOptionen mnOptionen = new MOptionen(controller);
		menuBar.add(mnOptionen);

		// ---------------------Panel_Left-------------------------------
		sidebarPanel = new SidebarPanel(model, controller);
		add(sidebarPanel, BorderLayout.WEST);

		// ---------------------Panel_Right-------------------------------
		JPanel pn_Right = new JPanel();
		pn_Right.setBackground(Color.WHITE);
		add(pn_Right, BorderLayout.CENTER);
		pn_Right.setLayout(new BorderLayout(0, 0));

		// ---------------------Panel_Optionen-------------------------------
		JPanel pn_Optionen = new JPanel();
		pn_Right.add(pn_Optionen, BorderLayout.SOUTH);

		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Regelkreis");
		chckbxNewCheckBox_2.setSelected(true);
		chckbxNewCheckBox_2.setEnabled(false);
		chckbxNewCheckBox_2.setBackground(new Color(250, 128, 114));
		GridBagConstraints gbc_chckbxNewCheckBox_2 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox_2.gridx = 1;
		gbc_chckbxNewCheckBox_2.gridy = 0;
		pn_Optionen.add(chckbxNewCheckBox_2, gbc_chckbxNewCheckBox_2);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Regelstrecke");
		chckbxNewCheckBox.setBackground(new Color(154, 205, 50));
		chckbxNewCheckBox.setEnabled(false);
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox.gridx = 1;
		gbc_chckbxNewCheckBox.gridy = 1;
		pn_Optionen.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Regler");
		chckbxNewCheckBox_1.setBackground(new Color(100, 149, 237));
		chckbxNewCheckBox_1.setEnabled(false);
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxNewCheckBox_1.gridx = 1;
		gbc_chckbxNewCheckBox_1.gridy = 2;
		pn_Optionen.add(chckbxNewCheckBox_1, gbc_chckbxNewCheckBox_1);

		// ---------------------Graph-------------------------------
		double[] output = new double[] {};
		pn_chart = Chart.makePanel(output, 1.0);
		pn_chart.setBackground(Color.WHITE);
		pn_Right.add(pn_chart, BorderLayout.CENTER);

		// ---------------------Status Zeile-------------------------------
		JPanel pn_Status = new JPanel();
		pn_Status.setLayout(new BorderLayout());
		lblStatus = new JLabel("Status", JLabel.LEFT);
		pn_Status.add(lblStatus, BorderLayout.WEST);
		add(pn_Status, BorderLayout.SOUTH);

	}

	public void setStatus(String message) {
		lblStatus.setText(message);
	}

	@Override
	public void update(Observable o, Object arg) {
		SchrittAntwort sw = model.getRegelkreis().getTranferFunction()
				.schrittantwort();
		double maxX = sw.getTaus(0.001);

		double[] x = new double[256];
		for (int i = 0; i < x.length; i++) {
			x[i] = sw.getY((double) i * maxX / 255);
		}
		JFreeChart chart = Chart.makeChart(x, maxX);
		pn_chart.setChart(chart);
		sidebarPanel.update(model.getRegelkreis());
	}

	public void setModel(Model model) {
		this.model.deleteObserver(this);
		this.model = model;
		sidebarPanel.setModel(model);
		this.model.addObserver(this);
		update(null, null);
	}
}
