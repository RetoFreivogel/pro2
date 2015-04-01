package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatter;

import util.Chart;
import util.Matlab;

public class View extends JPanel implements Observer, ActionListener {
	private static final long serialVersionUID = 1L;

	private Controller controller;
	private Model model;
	private JLabel statusbar;
	private JFormattedTextField tf_Ks;
	private JLabel lb_Kr;
	private JPanel graph = null;

	public View(Controller controller, Model model) {
		this.controller = controller;
		this.model = model;
		this.statusbar = new JLabel("Bereit");

		setLayout(new BorderLayout());
		add(this.statusbar, BorderLayout.SOUTH);
		this.tf_Ks = new JFormattedTextField(new DefaultFormatter());

		this.tf_Ks.addActionListener(this);

		add(this.tf_Ks, BorderLayout.NORTH);
		this.lb_Kr = new JLabel();
		add(this.lb_Kr, BorderLayout.WEST);

		double[] output = Matlab.calcStep("[1],[1 1 2]");
		this.graph = Chart.makePanel(output);
		this.graph.setBackground(Color.WHITE);
		add(this.graph, BorderLayout.CENTER);
		
		update(null, null);
	}

	public void setStatus(String message) {
		this.statusbar.setText(message);
	}

	@Override
	public void update(Observable o, Object arg) {
		double[] output = this.model.getRegelkreis().getRegelstrecke().schrittantwort();
		remove(this.graph);
		this.graph = Chart.makePanel(output);
		this.graph.setBackground(Color.WHITE);
		add(this.graph, BorderLayout.CENTER);
		
		this.tf_Ks.setText(""
				+ this.model.getRegelkreis().getRegelstrecke().getKs());
		this.lb_Kr.setText("" + this.model.getRegelkreis().getRegler().getKr());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.tf_Ks) {
			this.controller.setKr(this.tf_Ks.getText());
		}

	}
}
