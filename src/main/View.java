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

	Controller controller;
	private Model model;
	private JLabel statusbar;
	JFormattedTextField tf_Ks;
	private JLabel lb_Kr;

	private ActionListener al_Ks = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			Command command = new Command() {
				@Override
				public void execute(Model regelkreisModel) {
					double value = Double
							.parseDouble(View.this.tf_Ks.getText());
					regelkreisModel.getRegelkreis().getRegelstrecke()
							.setKs(value);
				}
			};
			View.this.controller.handleCommand(command);
		}
	};

	public View(Controller controller, Model model) {
		this.controller = controller;
		this.model = model;
		this.statusbar = new JLabel("Bereit");

		setLayout(new BorderLayout());
		add(this.statusbar, BorderLayout.SOUTH);
		this.tf_Ks = new JFormattedTextField(new DefaultFormatter());

		this.tf_Ks.addActionListener(this.al_Ks);

		add(this.tf_Ks, BorderLayout.NORTH);
		this.lb_Kr = new JLabel();
		add(this.lb_Kr, BorderLayout.CENTER);

		update();
	}

	public void setStatus(String message) {
		this.statusbar.setText(message);
	}

	public void update() {
		this.tf_Ks.setValue(""
				+ this.model.getRegelkreis().getRegelstrecke().getKs());
		this.lb_Kr.setText("" + this.model.getRegelkreis().getRegler().getKr());
	}

	@Override
	public void update(Observable o, Object arg) {
		update();
	}
}
