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
	private Model model;
	private final Controller controller;
	
	private final RegelStreckeView pn_ERegelstrecke;
	private final AnalyseView pn_AAnalyse;

	private JPanel pn_Eingabe;

	public SidebarPanel(Model model, Controller controller){
		super();
		
		//TODO
		this.model = model;
		this.controller = controller;

		
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JPanel pn_left = new  JPanel();
		setViewportView(pn_left);
		pn_left.setLayout(new BoxLayout(pn_left, BoxLayout.Y_AXIS));
		
		//---------------------Eingabe-------------------------------		
		pn_Eingabe = new JPanel();
		pn_Eingabe.setLayout(new BoxLayout(pn_Eingabe, BoxLayout.Y_AXIS));
		pn_Eingabe.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Eingabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_left.add(pn_Eingabe);
		//---------------------Eingabe_Regelstrecke-------------------------------
		
		pn_ERegelstrecke = new RegelStreckeView(model.getRegelstrecke(), controller);
		initEingabe();
		
		//---------------------Ausgabe-------------------------------
		JPanel pn_Ausgabe = new JPanel();
		pn_Ausgabe.setLayout(new BoxLayout(pn_Ausgabe, BoxLayout.Y_AXIS));
		pn_Ausgabe.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Ausgabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_left.add(pn_Ausgabe);
			
		pn_AAnalyse = new AnalyseView(model.getAlleRegelkreise().get(0), controller);
		pn_Ausgabe.add(pn_AAnalyse);
		
	}
	
	public void initEingabe(){
		pn_Eingabe.removeAll();
		pn_Eingabe.add(pn_ERegelstrecke);
		
		for(RegelKreis rk : model.getAlleRegelkreise()){	
			pn_Eingabe.add(new ReglerView(rk, controller));
		}
		try {
			getRootPane().revalidate();
			getRootPane().repaint();
		} catch (NullPointerException ex) {
		}
	}
	
	public void setModel(Model model) {
		this.model = model;
		pn_ERegelstrecke.setRegelstrecke(model.getRegelstrecke());
		pn_AAnalyse.setRegelkreis(model.getAlleRegelkreise().get(0));
		initEingabe();
	}
}
