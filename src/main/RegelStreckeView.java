package main;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class RegelStreckeView extends JPanel implements Observer, PropertyChangeListener {
	private static final long serialVersionUID = 1L;
	
	private final RegelStrecke regelstrecke;
	private final Controller controller;
	
	private final JFormattedTextField tf_ks;
	private final JFormattedTextField tf_tg;
	private final JFormattedTextField tf_tu;
	
	public RegelStreckeView(RegelStrecke regelstrecke, Controller controller){
		super();
		this.regelstrecke = regelstrecke;
		this.controller = controller;
				
		setBorder(new TitledBorder(new LineBorder(Color.GRAY),"Regelstrecke", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lb_defd_RS = new JLabel("Definiert durch:");
		add(lb_defd_RS);
		
		
		JComboBox<String> cbbx_defd_RS = new JComboBox<>();
		cbbx_defd_RS.setModel(new DefaultComboBoxModel<>(new String[] { "KTuTg",
				"Frequenzgang" }));
		add(cbbx_defd_RS);
		
		DecimalFormat format = new DecimalFormat("###0.###");
		
		JLabel lb_Ks = new JLabel("Ks");
		add(lb_Ks);
		tf_ks = new JFormattedTextField(format);
		tf_ks.addPropertyChangeListener("value", this);
		add(tf_ks);

		JLabel lb_Tu = new JLabel("Tu");
		add(lb_Tu);
		tf_tu = new JFormattedTextField(format);
		tf_tu.addPropertyChangeListener("value", this);
		add(tf_tu);

		JLabel lb_Tg = new JLabel("Tg");
		add(lb_Tg);
		tf_tg = new JFormattedTextField(format);
		tf_tg.addPropertyChangeListener("value", this);
		add(tf_tg);
		
		regelstrecke.addObserver(this);
		update(null, null);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		tf_ks.setValue(regelstrecke.getKs());
		tf_tu.setValue(regelstrecke.getTu());
		tf_tg.setValue(regelstrecke.getTg());		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(tf_ks == evt.getSource()){
			controller.setKs(((Number)tf_ks.getValue()).doubleValue());
		}else if(tf_tu == evt.getSource()){
			controller.setTu(((Number)tf_tu.getValue()).doubleValue());
		}else if(tf_tg == evt.getSource()){
			controller.setTg(((Number)tf_tg.getValue()).doubleValue());
		}
	}
}
