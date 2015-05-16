package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

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
		
	//private Model model;
	private final Controller controller;
	
	private final RegelStreckeView pn_ERegelstrecke;
	private final AnalyseView pn_AAnalyse;
	private ReglerView[] alleRegler = new ReglerView[]{};

	private JPanel pn_Regler;

	public SidebarPanel(Model model, Controller controller){
		super();
		
		this.controller = controller;
					
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		setAlignmentY(TOP_ALIGNMENT);
		
		JPanel pn_left = new  JPanel();
		setViewportView(pn_left);
		pn_left.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 0,
				0, GridBagConstraints.NORTHWEST, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0);

		pn_ERegelstrecke = new RegelStreckeView(model.getRegelstrecke(), controller);
		pn_left.add(pn_ERegelstrecke, constraints);		
		constraints.gridy++;
		
		//---------------------Regler-------------------------------		
		pn_Regler = new JPanel();
		pn_Regler.setLayout(new GridBagLayout());
		pn_Regler.setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Regler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pn_left.add(pn_Regler, constraints);
		constraints.gridy++;
		//---------------------Eingabe_Regelstrecke-------------------------------
		

		initEingabe(model);
		
		//---------------------Ausgabe-------------------------------	
		pn_AAnalyse = new AnalyseView(model.getAlleRegelkreise().get(0), controller);
		pn_left.add(pn_AAnalyse, constraints);
		constraints.gridy++;
		constraints.weighty = 1.0;
		pn_left.add(new JPanel(), constraints);
		constraints.gridy++;
	}
	
	public void initEingabe(Model model){
		pn_Regler.removeAll();
		
		GridBagConstraints constraints = new GridBagConstraints(0, 0, 1, 1, 1,
				0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 0, 0), 0, 0);

		Vector<RegelKreis> alleKreise = model.getAlleRegelkreise();
		
		if(alleRegler.length != alleKreise.size()){
			alleRegler = new ReglerView[alleKreise.size()];
			
			int i = 0;
			for(RegelKreis rk : alleKreise){
				ReglerView rv = new ReglerView(rk, controller);
				alleRegler[i] = rv;
				i++;
				pn_Regler.add(rv, constraints);
				constraints.gridy++;
			}
		}else{
			int i = 0;
			for (RegelKreis regelKreis : alleKreise) {
				alleRegler[i].update(regelKreis);
				pn_Regler.add(alleRegler[i], constraints);
				constraints.gridy++;
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
}
