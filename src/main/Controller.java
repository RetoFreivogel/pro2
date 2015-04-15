package main;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.JTextField;

public class Controller {
	private Model model;
	private View view;

	public Controller(Model model) {
		this.model = model;
	}

	public void setView(View view) {
		this.view = view;
		ActionMap actionmap = view.getActionMap();
		actionmap.put("SET_KS", new SetDoubleAction("KS", model.getRegelkreis()
				.getRegelstrecke().getKs()));
		actionmap.put("SET_TU", new SetDoubleAction("TU", model.getRegelkreis()
				.getRegelstrecke().getTu()));
		actionmap.put("SET_TG", new SetDoubleAction("TG", model.getRegelkreis()
				.getRegelstrecke().getTg()));
	}

	/*
	 * public AbstractAction getKsAction() { return new SetDoubleAction("KS",
	 * model.getRegelkreis() .getRegelstrecke().getKs()); }
	 * 
	 * public AbstractAction getTuAction() { return new SetDoubleAction("TU",
	 * model.getRegelkreis() .getRegelstrecke().getTu()); }
	 * 
	 * public AbstractAction getTgAction() { return new SetDoubleAction("TG",
	 * model.getRegelkreis() .getRegelstrecke().getTg()); }
	 */

	private class SetDoubleAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		private final String command_string;
		private final ObservableDouble value;

		public SetDoubleAction(String name, ObservableDouble value) {
			super(name);
			this.value = value;
			command_string = "SET_" + name + "_COMMAND";
			putValue(AbstractAction.ACTION_COMMAND_KEY, command_string);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() != command_string) {
				return;
			}
			if (!(e.getSource() instanceof JTextField)) {
				return;
			}
			JTextField textfield = (JTextField) e.getSource();

			try {
				value.setValue(Double.parseDouble(textfield.getText()));
				textfield.setBackground(Color.WHITE);
			} catch (Exception ex) {
				textfield.setBackground(Color.RED);
				view.setStatus(ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
}
