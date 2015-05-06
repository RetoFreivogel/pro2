package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import model.Model;
import model.RegelKreis;
import model.SchrittAntwort;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import controller.Controller;
import util.Chart;

public class View extends JPanel implements Observer, ActionListener {
	private static final long serialVersionUID = 1L;

	private SidebarPanel sidebarPanel;
	private JLabel lblStatus;
	private ChartPanel pn_chart;
	
	private Model model;
	private Controller controller;

	private JCheckBox ckbx_Graph_1;
	/*
	 * private AbstractButton ckbx_Graph_2; private AbstractButton ckbx_Graph_3;
	 * private AbstractButton ckbx_Graph_4; private AbstractButton ckbx_Graph_5;
	 */
	public String Kr;
	public String Tn;
	public String Tv;
	public String Tp;

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

		// ---------------------Panel_Legend---------------------------------
		JPanel pn_legend = new JPanel();
		pn_Right.add(pn_legend, BorderLayout.SOUTH);

		ckbx_Graph_1 = new JCheckBox();
		ckbx_Graph_1.setText("Graph 1");
		ckbx_Graph_1.setSelected(false);
		// checkbox.setEnabled(false);
		ckbx_Graph_1.addActionListener(this);

		GridBagConstraints gbc_ckbx_Graph_1 = new GridBagConstraints();
		gbc_ckbx_Graph_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_ckbx_Graph_1.gridx = 0;
		gbc_ckbx_Graph_1.gridy = 0;
		pn_legend.add(ckbx_Graph_1, gbc_ckbx_Graph_1);

		/*
		 * ckbx_Graph_2 = new JCheckBox(); ckbx_Graph_2.setText("Graph 2");
		 * ckbx_Graph_2.setSelected(false); // checkbox.setEnabled(false);
		 * ckbx_Graph_2.addActionListener(this);
		 * 
		 * GridBagConstraints gbc_ckbx_Graph_2 = new GridBagConstraints();
		 * gbc_ckbx_Graph_2.fill = GridBagConstraints.HORIZONTAL;
		 * gbc_ckbx_Graph_2.gridx = 0; gbc_ckbx_Graph_2.gridy = 1;
		 * pn_legend.add(ckbx_Graph_2, gbc_ckbx_Graph_2);
		 * 
		 * ckbx_Graph_3 = new JCheckBox(); ckbx_Graph_3.setText("Graph 3");
		 * ckbx_Graph_3.setSelected(false); // checkbox.setEnabled(false);
		 * ckbx_Graph_3.addActionListener(this);
		 * 
		 * GridBagConstraints gbc_ckbx_Graph_3 = new GridBagConstraints();
		 * gbc_ckbx_Graph_3.fill = GridBagConstraints.HORIZONTAL;
		 * gbc_ckbx_Graph_3.gridx = 0; gbc_ckbx_Graph_3.gridy = 2;
		 * pn_legend.add(ckbx_Graph_3, gbc_ckbx_Graph_3);
		 * 
		 * ckbx_Graph_4 = new JCheckBox(); ckbx_Graph_4.setText("Graph 4");
		 * ckbx_Graph_4.setSelected(false); // checkbox.setEnabled(false);
		 * ckbx_Graph_4.addActionListener(this);
		 * 
		 * GridBagConstraints gbc_ckbx_Graph_4 = new GridBagConstraints();
		 * gbc_ckbx_Graph_4.fill = GridBagConstraints.HORIZONTAL;
		 * gbc_ckbx_Graph_4.gridx = 0; gbc_ckbx_Graph_4.gridy = 3;
		 * pn_legend.add(ckbx_Graph_4, gbc_ckbx_Graph_4);
		 * 
		 * ckbx_Graph_5 = new JCheckBox(); ckbx_Graph_5.setText("Graph 5");
		 * ckbx_Graph_5.setSelected(false); // checkbox.setEnabled(false);
		 * ckbx_Graph_5.addActionListener(this);
		 * 
		 * GridBagConstraints gbc_ckbx_Graph_5 = new GridBagConstraints();
		 * gbc_ckbx_Graph_5.fill = GridBagConstraints.HORIZONTAL;
		 * gbc_ckbx_Graph_5.gridx = 0; gbc_ckbx_Graph_5.gridy = 4;
		 * pn_legend.add(ckbx_Graph_5, gbc_ckbx_Graph_5);
		 */


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

		model.addObserver(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (ckbx_Graph_1.isSelected()) {
			ckbx_Graph_1.setBackground(Color.BLUE); // (255, 79, 79)
		} else {
			
			ckbx_Graph_1.setBackground(null);
		}
		/*
		 * if (ckbx_Graph_2.isSelected()) {
		 * ckbx_Graph_2.setBackground(Color.CYAN);//(79, 135, 255) } else {
		 * ckbx_Graph_2.setBackground(null); }
		 * 
		 * if (ckbx_Graph_3.isSelected()) {
		 * ckbx_Graph_3.setBackground(Color.YELLOW);//(79, 255, 79) } else {
		 * ckbx_Graph_3.setBackground(null); }
		 * 
		 * if (ckbx_Graph_4.isSelected()) {
		 * ckbx_Graph_4.setBackground(Color.RED);//(255, 79, 255) } else {
		 * ckbx_Graph_4.setBackground(null); }
		 * 
		 * if (ckbx_Graph_5.isSelected()) {
		 * ckbx_Graph_5.setBackground(Color.GREEN);//(255, 255, 79) } else {
		 * ckbx_Graph_5.setBackground(null); }
		 */
	}

	public String getKr() {
		return Kr;
	}

	public void setKr(String Kr) {
			this.Kr = Kr;
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
