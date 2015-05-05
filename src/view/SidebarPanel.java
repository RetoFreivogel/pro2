package view;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.Controller;
import model.Model;
import model.RegelKreis;

public class SidebarPanel extends JScrollPane{
	private static final long serialVersionUID = 1L;
		
	//TODO
	//private Model model;
	//private Controller controller;
	
	
	private final ReglerView pn_ERegler;
	private final RegelStreckeView pn_ERegelstrecke;
	private final AnalyseView pn_AAnalyse;

	public SidebarPanel(Model model, Controller controller){
		super();
		
		//TODO
		//this.model = model;
		//this.controller = controller;

		
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
		pn_ERegelstrecke = new RegelStreckeView(model.getRegelkreis().getRegelstrecke(), controller);
		pn_Eingabe.add(pn_ERegelstrecke);
		
		//---------------------Eingabe_Regler-------------------------------
		pn_ERegler = new ReglerView(model.getRegelkreis(), controller);
		pn_Eingabe.add(pn_ERegler);
		
		//---------------------Ausgabe-------------------------------
		JPanel pn_Ausgabe = new JPanel();
		pn_Ausgabe.setLayout(new BoxLayout(pn_Ausgabe, BoxLayout.Y_AXIS));
		pn_Ausgabe.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Ausgabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_left.add(pn_Ausgabe);
			
		//---------------------Ausgabe_Regelstrecke-------------------------------
		
		//---------------------Ausgabe_Regler-------------------------------
		
		//---------------------Ausgabe_Analyse-------------------------------
		pn_AAnalyse = new AnalyseView(model.getRegelkreis(), controller);
		pn_Ausgabe.add(pn_AAnalyse);
	}
	
	public void update(RegelKreis regelkreis) {
	}

	public void setModel(Model model) {
		//this.model = model;
		pn_ERegelstrecke.setRegelstrecke(model.getRegelkreis().getRegelstrecke());
		pn_ERegler.setReglerkreis(model.getRegelkreis());
		pn_AAnalyse.setRegelkreis(model.getRegelkreis());
	}
}
