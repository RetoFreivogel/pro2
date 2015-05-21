package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.Controller;
import model.RegelKreis;
import model.SchrittAntwort;

public class AnalyseView extends JPanel{
	private static final long serialVersionUID = 1L;

	private JFormattedTextField tf_Tymax;
	private JFormattedTextField tf_Ymax;
	private JFormattedTextField tf_Tan;
	private JFormattedTextField tf_Taus;

	public AnalyseView(RegelKreis regelkreis, Controller controller) {
		super();

		DecimalFormat format = new DecimalFormat("###0.###");

		setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Analyse",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lb_Tymax = new JLabel("Tymax");
		add(lb_Tymax);
		tf_Tymax = new JFormattedTextField(format);
		tf_Tymax.setEditable(false);
		add(tf_Tymax);
		
		JLabel lb_Ymax = new JLabel("Ymax");
		add(lb_Ymax);
		tf_Ymax = new JFormattedTextField(format);
		tf_Ymax.setEditable(false);
		add(tf_Ymax);

		JLabel lb_Tan = new JLabel("Tan");
		add(lb_Tan);
		tf_Tan = new JFormattedTextField(format);
		tf_Tan.setEditable(false);
		add(tf_Tan);

		JLabel lb_Taus = new JLabel("Taus");
		add(lb_Taus);
		tf_Taus = new JFormattedTextField(format);
		tf_Taus.setEditable(false);
		add(tf_Taus);
		
		update(regelkreis);
	}

	public void update(RegelKreis regelkreis) {
		SchrittAntwort sa = regelkreis.getTranferFunction().schrittantwort();
		tf_Tymax.setValue(sa.getTymax());		
		tf_Taus.setValue(sa.getTaus());
		tf_Tan.setValue(sa.getTan());
		tf_Ymax.setValue(sa.getYmax());
	}

}
