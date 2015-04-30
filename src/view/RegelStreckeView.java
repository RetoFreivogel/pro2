package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.Controller;
import model.RegelStrecke;

public class RegelStreckeView extends JPanel implements Observer,
		PropertyChangeListener {
	private static final long serialVersionUID = 1L;

	private final RegelStrecke regelstrecke;
	private final Controller controller;

	private JFormattedTextField tf_Ordn;
	private final JFormattedTextField tf_ks;
	private final JFormattedTextField tf_tg;
	private final JFormattedTextField tf_tu;

	public RegelStreckeView(RegelStrecke regelstrecke, Controller controller) {
		super();
		this.regelstrecke = regelstrecke;
		this.controller = controller;

		DecimalFormat format = new DecimalFormat("###0.###");
		
		setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Regelstrecke",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lb_defd_RS = new JLabel("Definiert durch:");
		add(lb_defd_RS);
		JComboBox<String> cbbx_defd_RS = new JComboBox<>();
		cbbx_defd_RS.setModel(new DefaultComboBoxModel<>(
				new String[] { "KTuTg", "frequenzgang"})); 
		// TODO add definition through the Time coefficents	
		cbbx_defd_RS.setEnabled(false);		
		add(cbbx_defd_RS);	

		JLabel lb_Ks = new JLabel("Ks");
		add(lb_Ks);
		tf_ks = new JFormattedTextField(format);
		add(tf_ks);

		JLabel lb_Tu = new JLabel("Tu");
		add(lb_Tu);
		tf_tu = new JFormattedTextField(format);
		add(tf_tu);

		JLabel lb_Tg = new JLabel("Tg");
		add(lb_Tg);
		tf_tg = new JFormattedTextField(format);
		add(tf_tg);

		JLabel lb_Ordn = new JLabel("Ordnung");
		add(lb_Ordn);
		DecimalFormat Ordnung_format = new DecimalFormat("#0");
		tf_Ordn = new JFormattedTextField(Ordnung_format);
		tf_Ordn.setEditable(false);
		add(tf_Ordn);

		JLabel lb_Zeitkons = new JLabel("Zeitkonstanten");
		add(lb_Zeitkons);
		JButton bt_Zeitkonst = new JButton("Lesen..");
		//TODO enable reading of the Zeitkonstanten
		bt_Zeitkonst.setEnabled(false);
		add(bt_Zeitkonst);

		regelstrecke.addObserver(this);
		enableEvents();
		update(null, null);
	}

	private void enableEvents(){
		tf_ks.addPropertyChangeListener("value", this);
		tf_tu.addPropertyChangeListener("value", this);
		tf_tg.addPropertyChangeListener("value", this);		
	}
	
	private void disableEvents(){
		tf_ks.removePropertyChangeListener("value", this);
		tf_tu.removePropertyChangeListener("value", this);
		tf_tg.removePropertyChangeListener("value", this);				
	}

	
	@Override
	public void update(Observable arg0, Object arg1) {
		disableEvents();
		tf_ks.setValue(regelstrecke.getKs());
		tf_tu.setValue(regelstrecke.getTu());
		tf_tg.setValue(regelstrecke.getTg());
		tf_Ordn.setValue(regelstrecke.getOrdnung());
		enableEvents();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO verify that this isnt needed
		// if(!"value".equals(evt.getPropertyName()))return;

		if (tf_ks == evt.getSource()) {
			controller.setKs(((Number) tf_ks.getValue()).doubleValue());
		} else if (tf_tu == evt.getSource()) {
			controller.setTu(((Number) tf_tu.getValue()).doubleValue());
		} else if (tf_tg == evt.getSource()) {
			controller.setTg(((Number) tf_tg.getValue()).doubleValue());
		}
	}
}
