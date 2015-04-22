package main;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class SidebarPanel extends JScrollPane{
	private static final long serialVersionUID = 1L;
		
	private JTextField tf_Ks; 
	private JTextField tf_Tg;
	private JTextField tf_Tu;
	private JTextField tf_Phrand;
	private JTextField tf_Ordn;
	private JTextField tf_Kr;
	private JTextField tf_Tn;
	private JTextField tf_Tv;
	private JTextField tf_Tp;
	private JTextField tf_Ymax;
	private JTextField tf_Tan;
	private JTextField tf_Taus;
	private JTextField tf_E;
	private JTextField tf_E2;
	private JTextField tf_Et;
	private JTextField tf_E2t;
	
	JComboBox<String> cbbx_defd_R;

	public SidebarPanel(){
		super();

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
		JPanel pn_ERegelstrecke = new JPanel();
		pn_ERegelstrecke.setBorder(new TitledBorder(new LineBorder(Color.GRAY),"Regelstrecke", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Eingabe.add(pn_ERegelstrecke);
		pn_ERegelstrecke.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lb_defd_RS = new JLabel("Definiert durch:");
		pn_ERegelstrecke.add(lb_defd_RS);
		
		
		JComboBox<String> cbbx_defd_RS = new JComboBox<>();
		cbbx_defd_RS.setModel(new DefaultComboBoxModel<>(new String[] { "KTuTg",
				"Frequenzgang" }));
		pn_ERegelstrecke.add(cbbx_defd_RS);
		
		JLabel lb_Ks = new JLabel("Ks");
		pn_ERegelstrecke.add(lb_Ks);
		
		tf_Ks = new JTextField(10);
		pn_ERegelstrecke.add(tf_Ks);

		JLabel lb_Tg = new JLabel("Tg");
		pn_ERegelstrecke.add(lb_Tg);

		tf_Tg = new JTextField(10);
		pn_ERegelstrecke.add(tf_Tg);

		JLabel lb_Tu = new JLabel("Tu");
		pn_ERegelstrecke.add(lb_Tu);

		tf_Tu = new JTextField(10);
		pn_ERegelstrecke.add(tf_Tu);
		
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

		tf_Phrand = new JTextField(10);
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

		tf_Ordn = new JTextField(10);
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

		tf_Kr = new JTextField(10);
		pn_ARegler.add(tf_Kr);

		JLabel lb_Tn = new JLabel("Tn");
		pn_ARegler.add(lb_Tn);

		tf_Tn = new JTextField(10);
		pn_ARegler.add(tf_Tn);		

		JLabel lb_Tv = new JLabel("Tv");
		pn_ARegler.add(lb_Tv);

		tf_Tv = new JTextField(10);
		pn_ARegler.add(tf_Tv);	

		JLabel lb_Tp = new JLabel("Tp");
		pn_ARegler.add(lb_Tp);

		tf_Tp = new JTextField(10);
		pn_ARegler.add(tf_Tp);
				
		
		//---------------------Ausgabe_Analyse-------------------------------
		JPanel pn_AAnalyse = new JPanel();
		pn_AAnalyse.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Analyse", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_Ausgabe.add(pn_AAnalyse);
		pn_AAnalyse.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lb_Ymax = new JLabel("Ymax");
		pn_AAnalyse.add(lb_Ymax);

		tf_Ymax = new JTextField(10);
		pn_AAnalyse.add(tf_Ymax);

		JLabel lb_Tan = new JLabel("Tan");
		pn_AAnalyse.add(lb_Tan);

		tf_Tan = new JTextField(10);		
		pn_AAnalyse.add(tf_Tan);

		JLabel lb_Taus = new JLabel("Taus");
		pn_AAnalyse.add(lb_Taus);

		tf_Taus = new JTextField(10);
		pn_AAnalyse.add(tf_Taus);

		JLabel lb_1 = new JLabel("S |e(t)| dt");
		pn_AAnalyse.add(lb_1);

		tf_E = new JTextField(10);
		pn_AAnalyse.add(tf_E);
		
		JLabel lb_2 = new JLabel("S e(t)^2 dt");
		pn_AAnalyse.add(lb_2);

		tf_E2 = new JTextField(10);
		pn_AAnalyse.add(tf_E2);

		JLabel lb_3 = new JLabel("S |e(t)|*t dt");
		pn_AAnalyse.add(lb_3);

		tf_Et = new JTextField(10);
		pn_AAnalyse.add(tf_Et);

		JLabel lb_4 = new JLabel("S e(t)^2*t dt");
		pn_AAnalyse.add(lb_4);

		tf_E2t = new JTextField(10);
		pn_AAnalyse.add(tf_E2t);
	}

	public void registerActions(ActionMap actionmap){
		tf_Ks.setAction(actionmap.get("SET_KS"));
		tf_Tu.setAction(actionmap.get("SET_TU"));
		tf_Tg.setAction(actionmap.get("SET_TG"));
		cbbx_defd_R.setAction(actionmap.get("SELECT_DIM"));
		
	}
	
	public void update(RegelKreis regelkreis) {
		RegelStrecke regelstrecke = regelkreis.getRegelstrecke();
		tf_Ks.setText("" + regelstrecke.getKs());		
		tf_Tu.setText("" + regelstrecke.getTu());		
		tf_Tg.setText("" + regelstrecke.getTg());
		
		Regler regler = regelkreis.getRegler();
		tf_Kr.setText("" + regler.getKr());
		tf_Tn.setText("" + regler.getTn());
		tf_Tv.setText("" + regler.getTv());
		tf_Tp.setText("" + regler.getTp());
	}
}
