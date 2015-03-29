package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatter;

public class View extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;

	private Controller controller;
	private Model model;
	private JLabel statusbar;
	private JFormattedTextField tf_Ks;
	private JLabel lb_Kr;

	private ActionListener al_Ks = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Command command = new Command() {
				public void execute(Model model) {
					double value = Double.parseDouble(tf_Ks.getText());
					model.getRegelkreis().getRegelstrecke().setKs(value);
				}
			};
			controller.handleCommand(command);
		}
	};

	public View(Controller controller, Model model) {
		this.controller = controller;
		this.model = model;
		statusbar = new JLabel("Bereit");

		setLayout(new BorderLayout());
		add(statusbar, BorderLayout.SOUTH);
		tf_Ks = new JFormattedTextField(new DefaultFormatter());

		tf_Ks.addActionListener(al_Ks);

		add(tf_Ks, BorderLayout.NORTH);
		lb_Kr = new JLabel();
		add(lb_Kr, BorderLayout.CENTER);

		update();
	}

	public void setStatus(String message) {
		statusbar.setText(message);
	}

	public void update() {
		tf_Ks.setValue("" + model.getRegelkreis().getRegelstrecke().getKs());
		lb_Kr.setText("" + model.getRegelkreis().getRegler().getKr());
	}

	@Override
	public void update(Observable o, Object arg) {
		update();
	}
}
