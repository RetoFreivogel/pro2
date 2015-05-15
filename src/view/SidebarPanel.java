package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.Controller;
import model.Model;
import model.RegelKreis;

public class SidebarPanel extends JScrollPane implements ActionListener{
	private static final long serialVersionUID = 1L;
		
	//TODO
	//private Model model;
	private final Controller controller;
	
	private final JButton jb_Add = new JButton("+");
	private final RegelStreckeView pn_ERegelstrecke;
	private final AnalyseView pn_AAnalyse;
	private ReglerView[] alleRegler = new ReglerView[]{};

	private JPanel pn_Eingabe;

	public SidebarPanel(Model model, Controller controller){
		super();
		
		//TODO
		this.controller = controller;
				
		jb_Add.addActionListener(this);
		
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
		initEingabe(model);
		
		//---------------------Ausgabe-------------------------------
		JPanel pn_Ausgabe = new JPanel();
		pn_Ausgabe.setLayout(new BoxLayout(pn_Ausgabe, BoxLayout.Y_AXIS));
		pn_Ausgabe.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Ausgabe", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_left.add(pn_Ausgabe);
			
		pn_AAnalyse = new AnalyseView(model.getAlleRegelkreise().get(0), controller);
		pn_Ausgabe.add(pn_AAnalyse);
		
	}
	
	public void initEingabe(Model model){
		pn_Eingabe.removeAll();
		pn_Eingabe.add(pn_ERegelstrecke);
		pn_Eingabe.add(jb_Add);
		
		Vector<RegelKreis> alleKreise = model.getAlleRegelkreise();
		
		if(alleRegler.length != alleKreise.size()){
			alleRegler = new ReglerView[alleKreise.size()];
			
			int i = 0;
			for(RegelKreis rk : alleKreise){
				ReglerView rv = new ReglerView(rk, controller);
				alleRegler[i] = rv;
				i++;
				pn_Eingabe.add(rv);
			}
		}else{
			int i = 0;
			for (RegelKreis regelKreis : alleKreise) {
				alleRegler[i].update(regelKreis);
				pn_Eingabe.add(alleRegler[i]);
				i++;
			}
		}
			
		
		try {
			getRootPane().revalidate();
			getRootPane().repaint();
		} catch (NullPointerException ex) {
		}
	}
	
	public void update(Model model) {
		pn_ERegelstrecke.update(model.getRegelstrecke());
		initEingabe(model);	
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		controller.newKreis();
		
	}
}
