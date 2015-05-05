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

public class AnalyseView extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JFormattedTextField tf_Ymax;
	private JFormattedTextField tf_Tan;
	private JFormattedTextField tf_Taus;
	private JFormattedTextField tf_E;
	private JFormattedTextField tf_E2;
	private JFormattedTextField tf_Et;
	private JFormattedTextField tf_E2t;

	//private RegelKreis regelkreis;
	
	public AnalyseView(RegelKreis regelkreis, Controller controller){
		super();
		//this.regelkreis = regelkreis;
		
		DecimalFormat format = new DecimalFormat("###0.###");
		
		setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Analyse", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		
		setLayout(new GridLayout(0, 2, 0, 0));
		
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

		JLabel lb_1 = new JLabel("S |e(t)| dt");
		add(lb_1);
		tf_E = new JFormattedTextField(format);
		tf_E.setEditable(false);
		add(tf_E);
		
		JLabel lb_2 = new JLabel("S e(t)^2 dt");
		add(lb_2);
		tf_E2 = new JFormattedTextField(format);
		tf_E2.setEditable(false);
		add(tf_E2);

		JLabel lb_3 = new JLabel("S |e(t)|*t dt");
		add(lb_3);
		tf_Et = new JFormattedTextField(format);
		tf_Et.setEditable(false);
		add(tf_Et);

		JLabel lb_4 = new JLabel("S e(t)^2*t dt");
		add(lb_4);
		tf_E2t = new JFormattedTextField(format);
		tf_E2t.setEditable(false);
		add(tf_E2t);
	}


	public void setRegelkreis(RegelKreis regelkreis) {
		//this.regelkreis = regelkreis;
	}

}
