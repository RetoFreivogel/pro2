package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import model.Model;
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
	private LegendPanel pn_legend;

	private Model model;
	private Controller controller;

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

	private void init() {
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

		// ---------------------Graph-------------------------------
		double[] output = new double[] {};
		pn_chart = Chart.makePanel(output, 1.0);
		pn_chart.setBackground(Color.WHITE);
		pn_Right.add(pn_chart, BorderLayout.CENTER);

		// ---------------------Legend-------------------------------
		pn_legend = new LegendPanel(model, controller);
		pn_legend.setBackground(null);
		pn_Right.add(pn_legend, BorderLayout.SOUTH);

		// ---------------------Status Zeile-------------------------------
		JPanel pn_Status = new JPanel();
		pn_Status.setLayout(new BorderLayout());
		lblStatus = new JLabel("Status", JLabel.LEFT);
		pn_Status.add(lblStatus, BorderLayout.WEST);
		add(pn_Status, BorderLayout.SOUTH);

		model.addObserver(this);
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
