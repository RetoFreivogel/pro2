package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.Controller;
import model.RegelKreis;
import model.SchrittAntwort;

/**
 * Ansicht zur Darstellung der Eigenschaften einer Schrittantwort
 * @author Reto Freivogel, Alex Stocker
 *
 */
public class AnalyseView extends JPanel implements FocusListener{
	private static final long serialVersionUID = 1L;

	private JFormattedTextField tf_Tymax;
	private JFormattedTextField tf_Ymax;
	private JFormattedTextField tf_Tan;
	private JFormattedTextField tf_Taus;
	
	/**
	 * Erstellt eine neue Ansicht
	 * @param regelkreis Der Regelkreis von dem die Schrittantwort gebildet wird.
	 * @param controller Referenz auf den Controller der Applikation
	 */
	public AnalyseView(RegelKreis regelkreis, Controller controller) {
		super();
		
		setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Analyse",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lb_Tymax = new JLabel("Tymax");
		add(lb_Tymax);
		tf_Tymax = new JFormattedTextField(new LowercaseDecimalFormatter());
		tf_Tymax.setEditable(false);
		tf_Tymax.addFocusListener(this);		
		add(tf_Tymax);
		
		JLabel lb_Ymax = new JLabel("Ymax");
		add(lb_Ymax);
		tf_Ymax = new JFormattedTextField(new LowercaseDecimalFormatter());
		tf_Ymax.setEditable(false);
		tf_Ymax.addFocusListener(this);		
		add(tf_Ymax);

		JLabel lb_Tan = new JLabel("Tan");
		add(lb_Tan);
		tf_Tan = new JFormattedTextField(new LowercaseDecimalFormatter());
		tf_Tan.setEditable(false);
		tf_Tan.addFocusListener(this);		
		add(tf_Tan);

		JLabel lb_Taus = new JLabel("Taus 0.1%");
		add(lb_Taus);
		tf_Taus = new JFormattedTextField(new LowercaseDecimalFormatter());
		tf_Taus.setEditable(false);
		tf_Taus.addFocusListener(this);		
		add(tf_Taus);
		
		update(regelkreis);
	}

	/**
	 * Aktualisiert die Ansicht
	 * @param regelkreis Der Regelkreis mit den neuen Werten
	 */
	public void update(RegelKreis regelkreis) {
		SchrittAntwort sa = regelkreis.getTranferFunction().schrittantwort();
		tf_Tymax.setValue(sa.getTymax());		
		tf_Taus.setValue(sa.getTaus());
		tf_Tan.setValue(sa.getTan());
		tf_Ymax.setValue(sa.getYmax());
	}

	
	@Override
	public void focusGained(final FocusEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (e.getSource() == tf_Tymax) {
					tf_Tymax.selectAll();
				} else if (e.getSource() == tf_Ymax) {
					tf_Ymax.selectAll();
				} else if (e.getSource() == tf_Tan) {
					tf_Tan.selectAll();
				} else if (e.getSource() == tf_Taus) {
					tf_Taus.selectAll();
				}
			}
		});
	}

	@Override
	public void focusLost(FocusEvent e) {
		// Do Nothing
	}
}
