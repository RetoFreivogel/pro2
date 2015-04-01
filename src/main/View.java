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

		setLayout(new BorderLayout());

		statusbar = new JLabel("Bereit");
		add(statusbar, BorderLayout.SOUTH);

		tf_Ks = new JFormattedTextField(new DefaultFormatter());
		tf_Ks.addActionListener(this);
		add(tf_Ks, BorderLayout.NORTH);

		lb_Kr = new JLabel();
		add(lb_Kr, BorderLayout.WEST);

		update(null, null);
	}

	public void setStatus(String message) {
		statusbar.setText(message);
	}

	@Override
	public void update(Observable o, Object arg) {
		double[] output = this.model.getRegelkreis().getRegelstrecke()
				.schrittantwort();
		if(graph!=null)remove(graph);
		graph = Chart.makePanel(output);
		graph.setBackground(Color.WHITE);
		add(graph, BorderLayout.CENTER);

		tf_Ks.setText("" + model.getRegelkreis().getRegelstrecke().getKs());
		lb_Kr.setText("" + this.model.getRegelkreis().getRegler().getKr());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tf_Ks) {
			controller.setKr(tf_Ks.getText());
		}

	}
}
