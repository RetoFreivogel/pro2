package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import model.Model;

import controller.Controller;

public class View extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private SidebarPanel sidebarPanel;
	private JLabel lblStatus;

	private Model model;
	private Controller controller;

	public String Kr;
	public String Tn;
	public String Tv;
	public String Tp;

	private Graph pn_graph;

	public View(Model model, Controller controller) {
		super();
		this.model = model;
		this.controller = controller;
		init();
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
		pn_graph = new Graph(model);
		add(pn_graph, BorderLayout.CENTER);

		// ---------------------Status Row-------------------------------
		lblStatus = new JLabel("");
		add(lblStatus, BorderLayout.SOUTH);

	}

	public void displayError(String message) {
		lblStatus.setText(message);
		lblStatus.setOpaque(true);
		lblStatus.setBackground(new Color(240, 120, 120));
	}
	
	public void clearError() {
		lblStatus.setText("");
		lblStatus.setBackground(new Color(240, 240, 240));
	}

	public void setModel(Model model) {
		this.model = model;
		sidebarPanel.setModel(model);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
}
