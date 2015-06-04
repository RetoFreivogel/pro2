package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.Controller;
import model.RegelStrecke;

public class RegelStreckeView extends JPanel implements PropertyChangeListener,
		FocusListener {
	private static final long serialVersionUID = 1L;

	private final Controller controller;

	private JFormattedTextField tf_Ordn;
	private final JFormattedTextField tf_ks;
	private final JFormattedTextField tf_tg;
	private final JFormattedTextField tf_tu;

	public RegelStreckeView(RegelStrecke regelstrecke, Controller controller) {
		super();
		this.controller = controller;
		
		
		setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Regelstrecke",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lb_Ks = new JLabel("Ks");
		add(lb_Ks);
		tf_ks = new JFormattedTextField(new LowercaseDecimalFormatter());
		tf_ks.addFocusListener(this);
		add(tf_ks);

		JLabel lb_Tu = new JLabel("Tu");
		add(lb_Tu);
		tf_tu = new JFormattedTextField(new LowercaseDecimalFormatter());
		tf_tu.addFocusListener(this);
		add(tf_tu);

		JLabel lb_Tg = new JLabel("Tg");
		add(lb_Tg);
		tf_tg = new JFormattedTextField(new LowercaseDecimalFormatter());
		tf_tg.addFocusListener(this);
		add(tf_tg);

		JLabel lb_Ordn = new JLabel("Ordnung");
		add(lb_Ordn);
		DecimalFormat Ordnung_format = new DecimalFormat("#0");
		tf_Ordn = new JFormattedTextField(Ordnung_format);
		tf_Ordn.addFocusListener(this);

		tf_Ordn.setEditable(false);
		add(tf_Ordn);

		/*
		 * JLabel lb_Zeitkons = new JLabel("Zeitkonstanten"); add(lb_Zeitkons);
		 * JButton bt_Zeitkonst = new JButton("Lesen.."); // TODO enable reading
		 * of the Zeitkonstanten bt_Zeitkonst.setEnabled(false);
		 * add(bt_Zeitkonst);
		 */

		enableEvents();
		update(regelstrecke);
	}

	private void enableEvents() {
		tf_ks.addPropertyChangeListener("value", this);
		tf_tu.addPropertyChangeListener("value", this);
		tf_tg.addPropertyChangeListener("value", this);
	}

	private void disableEvents() {
		tf_ks.removePropertyChangeListener("value", this);
		tf_tu.removePropertyChangeListener("value", this);
		tf_tg.removePropertyChangeListener("value", this);
	}

	public void update(RegelStrecke regelstrecke) {
		disableEvents();
		try {
			tf_ks.setValue(regelstrecke.getKs());
			tf_tu.setValue(regelstrecke.getTu());
			tf_tg.setValue(regelstrecke.getTg());
			tf_Ordn.setValue(regelstrecke.getOrdnung());
		} finally {
			enableEvents();
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (tf_ks == evt.getSource()) {
			controller.setKs(((Number) tf_ks.getValue()).doubleValue());
		} else if (tf_tu == evt.getSource()) {
			controller.setTu(((Number) tf_tu.getValue()).doubleValue());
		} else if (tf_tg == evt.getSource()) {
			controller.setTg(((Number) tf_tg.getValue()).doubleValue());
		}
	}

	@Override
	public void focusGained(final FocusEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (e.getSource() == tf_ks) {
					tf_ks.selectAll();
				} else if (e.getSource() == tf_tu) {
					tf_tu.selectAll();
				} else if (e.getSource() == tf_tg) {
					tf_tg.selectAll();
				} else if (e.getSource() == tf_Ordn) {
					tf_Ordn.selectAll();
				}
			}
		});
	}

	@Override
	public void focusLost(FocusEvent e) {
		// Do Nothing
	}
}
