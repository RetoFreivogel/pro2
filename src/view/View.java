package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import model.Model;
import controller.Controller;

/**
 * Das View der Applikation
 * @author Reto Freivogel, Alex Stocker
 *
 */
public class View extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;

	private SidebarPanel sidebarPanel;
	private JLabel lblStatus;
	private Graph pn_graph;
	private Model model;

	private Controller controller;
	
	/**
	 * Erstellt ein neues View
	 * @param model Das Model mit den Werten
	 * @param controller Referenz auf den Controller zur übergabe von Befehlen
	 * @param frame Der umgebende Frame der das View enthalten soll.
	 */
	public View(Model model, Controller controller, JFrame frame) {
		super();
		this.controller = controller;
		this.model = model;
		init(frame);
	}

	private void init(JFrame frame) {
		setLayout(new BorderLayout());

		// Menuleiste
		JMenuBar menuBar = new JMenuBar();
		// Untermenu Datei
		MDatei mnDatei = new MDatei(controller);
		menuBar.add(mnDatei);
		// Untermenu Bearbeiten
		MBearbeiten mnBearbeiten = new MBearbeiten(controller);
		menuBar.add(mnBearbeiten);

		// ---------------------Panel_Left-------------------------------
		sidebarPanel = new SidebarPanel(model, controller);
		add(sidebarPanel, BorderLayout.WEST);

		// ---------------------Panel_Right-------------------------------
		pn_graph = new Graph(model);
		add(pn_graph, BorderLayout.CENTER);

		// ---------------------Status Row-------------------------------
		lblStatus = new JLabel("");
		add(lblStatus, BorderLayout.SOUTH);
		
		model.addObserver(this);
		
		frame.setContentPane(this);
		frame.setJMenuBar(menuBar);
	}

	/**
	 * Zeigt eine Fehlermeldung in der Statuszeile
	 * @param message Die Meldung
	 */
	public void displayError(String message) {
		lblStatus.setText(message);
		lblStatus.setOpaque(true);
		lblStatus.setBackground(new Color(240, 120, 120));
	}
	
	/**
	 * Löscht die Fehlermeldung und versteckt die Statuszeile 
	 */
	public void clearError() {
		lblStatus.setText("");
		lblStatus.setBackground(new Color(240, 240, 240));
	}

	@Override
	public void update(Observable o, Object arg) {
		sidebarPanel.update(model);
		pn_graph.update(model);
	}

	/**
	 * Wechselt das Model auf eine andere Instanz
	 * @param model Das neue Model
	 */
	public void setModel(Model model) {
		this.model.deleteObserver(this);
		this.model = model;
		this.model.addObserver(this);
		update(null, null);
	}
}
