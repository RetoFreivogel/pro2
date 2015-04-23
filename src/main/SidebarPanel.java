package main;

import java.awt.Color;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class SidebarPanel extends JScrollPane{
	private static final long serialVersionUID = 1L;
		
	private final Model model;
	private final Controller controller;
	
	private JFormattedTextField tf_Phrand;
	private JFormattedTextField tf_Ordn;
	private JFormattedTextField tf_Kr;
	private JFormattedTextField tf_Tn;
	private JFormattedTextField tf_Tv;
	private JFormattedTextField tf_Tp;
	private JFormattedTextField tf_Ymax;
	private JFormattedTextField tf_Tan;
	private JFormattedTextField tf_Taus;
	private JFormattedTextField tf_E;
	private JFormattedTextField tf_E2;
	private JFormattedTextField tf_Et;
	private JFormattedTextField tf_E2t;
	
	JComboBox<String> cbbx_defd_R;

	public SidebarPanel(Model model, Controller controller){
		super();
		this.model = model;
		this.controller = controller;

		DecimalFormat format = new DecimalFormat("###0.###");
		
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel pn_left = new  JPanel();
		setViewportView(pn_left);
		pn_left.setLayout(new BoxLayout(pn_left, BoxLayout.Y_AXIS));
		
		//---------------------Eingabe-------------------------------		
		JPanel pn_Eingabe = new JPanel();
		pn_Eingabe.setLayout(new BoxLayout(pn_Eingabe, BoxLayout.Y_AXIS));
		pn_Eingabe.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Eingabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_left.add(pn_Eingabe);
		//---------------------Eingabe_Regelstrecke-------------------------------
		
		//TODO
		JPanel pn_ERegelstrecke = new RegelStreckeView(model.getRegelkreis().getRegelstrecke(), controller);
		pn_Eingabe.add(pn_ERegelstrecke);
		
		//---------------------Eingabe_Regler-------------------------------
		JPanel pn_ERegler = new JPanel();
		pn_ERegler.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Regler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Eingabe.add(pn_ERegler);
		pn_ERegler.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lb_Topo = new JLabel("Topologie");
		pn_ERegler.add(lb_Topo);

		JComboBox<String> cbbx_Topo = new JComboBox<>();
		cbbx_Topo.setModel(new DefaultComboBoxModel<>(new String[] { "PI",
				"PID" }));
		cbbx_Topo.setSelectedIndex(1);
		pn_ERegler.add(cbbx_Topo);

		JLabel lb_defd_R = new JLabel("Definiert durch: ");
		pn_ERegler.add(lb_defd_R);

		cbbx_defd_R = new JComboBox<>();
		cbbx_defd_R.setModel(new DefaultComboBoxModel<>(new String[] {
				"Manuell", "Phasengang", "Ziegler", "Chien", "Oppelt",
				"Rosenberg", "Tsumme" }));
		cbbx_defd_R.setSelectedIndex(1);
		pn_ERegler.add(cbbx_defd_R);

		JLabel lb_Phrand = new JLabel("Phasenrand");
		pn_ERegler.add(lb_Phrand);

		tf_Phrand = new JFormattedTextField(format);
		pn_ERegler.add(tf_Phrand);
		
		//---------------------Ausgabe-------------------------------
		JPanel pn_Ausgabe = new JPanel();
		pn_Ausgabe.setLayout(new BoxLayout(pn_Ausgabe, BoxLayout.Y_AXIS));
		pn_Ausgabe.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Ausgabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_left.add(pn_Ausgabe);
		
		
		//---------------------Ausgabe_Regelstrecke-------------------------------
		JPanel pn_ARegelstrecke = new JPanel();
		pn_ARegelstrecke.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Regelstrecke", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Ausgabe.add(pn_ARegelstrecke);
		pn_ARegelstrecke.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lb_Ordn = new JLabel("Ordnung");
		pn_ARegelstrecke.add(lb_Ordn);

		tf_Ordn = new JFormattedTextField(format);
		pn_ARegelstrecke.add(tf_Ordn);


		JLabel lb_Zeitkons = new JLabel("Zeitkonstanten");
		pn_ARegelstrecke.add(lb_Zeitkons);

		JButton bt_Zeitkonst = new JButton("Lesen..");
		pn_ARegelstrecke.add(bt_Zeitkonst);
		
		//---------------------Ausgabe_Regler-------------------------------
		JPanel pn_ARegler = new JPanel();
		pn_ARegler.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Regler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Ausgabe.add(pn_ARegler);
		pn_ARegler.setLayout(new GridLayout(0, 2, 0, 0));
		
		
		JLabel lb_Kr = new JLabel("Kr");
		pn_ARegler.add(lb_Kr);
		tf_Kr = new JFormattedTextField(format);
		tf_Kr.setEditable(false);
		pn_ARegler.add(tf_Kr);

		JLabel lb_Tn = new JLabel("Tn");
		pn_ARegler.add(lb_Tn);
		tf_Tn = new JFormattedTextField(format);
		tf_Tn.setEditable(false);
		pn_ARegler.add(tf_Tn);		

		JLabel lb_Tv = new JLabel("Tv");
		pn_ARegler.add(lb_Tv);
		tf_Tv = new JFormattedTextField(format);
		tf_Tv.setEditable(false);
		pn_ARegler.add(tf_Tv);	

		JLabel lb_Tp = new JLabel("Tp");
		pn_ARegler.add(lb_Tp);
		tf_Tp = new JFormattedTextField(format);
		tf_Tp.setEditable(false);
		pn_ARegler.add(tf_Tp);
				
		
		//---------------------Ausgabe_Analyse-------------------------------
		JPanel pn_AAnalyse = new JPanel();
		pn_AAnalyse.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Analyse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Ausgabe.add(pn_AAnalyse);
		pn_AAnalyse.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lb_Ymax = new JLabel("Ymax");
		pn_AAnalyse.add(lb_Ymax);
		tf_Ymax = new JFormattedTextField(format);
		tf_Ymax.setEditable(false);
		pn_AAnalyse.add(tf_Ymax);

		JLabel lb_Tan = new JLabel("Tan");
		pn_AAnalyse.add(lb_Tan);
		tf_Tan = new JFormattedTextField(format);		
		tf_Tan.setEditable(false);
		pn_AAnalyse.add(tf_Tan);

		JLabel lb_Taus = new JLabel("Taus");
		pn_AAnalyse.add(lb_Taus);
		tf_Taus = new JFormattedTextField(format);
		tf_Taus.setEditable(false);
		pn_AAnalyse.add(tf_Taus);

		JLabel lb_1 = new JLabel("S |e(t)| dt");
		pn_AAnalyse.add(lb_1);
		tf_E = new JFormattedTextField(format);
		tf_E.setEditable(false);
		pn_AAnalyse.add(tf_E);
		
		JLabel lb_2 = new JLabel("S e(t)^2 dt");
		pn_AAnalyse.add(lb_2);
		tf_E2 = new JFormattedTextField(format);
		tf_E2.setEditable(false);
		pn_AAnalyse.add(tf_E2);

		JLabel lb_3 = new JLabel("S |e(t)|*t dt");
		pn_AAnalyse.add(lb_3);
		tf_Et = new JFormattedTextField(format);
		tf_Et.setEditable(false);
		pn_AAnalyse.add(tf_Et);

		JLabel lb_4 = new JLabel("S e(t)^2*t dt");
		pn_AAnalyse.add(lb_4);
		tf_E2t = new JFormattedTextField(format);
		tf_E2t.setEditable(false);
		pn_AAnalyse.add(tf_E2t);
	}
	
	public void update(RegelKreis regelkreis) {
		Regler regler = regelkreis.getRegler();
		tf_Kr.setValue(regler.getKr());
		tf_Tn.setValue(regler.getTn());
		tf_Tv.setValue(regler.getTv());
		tf_Tp.setValue(regler.getTp());
	}
}
