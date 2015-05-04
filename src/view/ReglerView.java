package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.Controller;
import model.ChiensRegelung;
import model.Dimensionierung;
import model.RegelKreis;
import model.Regler;
import model.ReglerTopologie;

public class ReglerView extends JPanel implements PropertyChangeListener,
		Observer, ActionListener {
	private static final long serialVersionUID = 1L;
	private static final DecimalFormat format = new DecimalFormat("###0.###");

	private final Controller controller;
	private final RegelKreis kreis;

	JLabel lb_Phrand;
	private JFormattedTextField tf_Phrand;
	private JFormattedTextField tf_Kr;
	private JFormattedTextField tf_Tn;
	private JFormattedTextField tf_Tv;
	private JFormattedTextField tf_Tp;
	JComboBox<Dimensionierung> cbbx_defd_R;
	JComboBox<ReglerTopologie> cbbx_Topo;
	JComboBox<ChiensRegelung> cbbx_chiens;

	public ReglerView(RegelKreis kreis, Controller controller) {
		super();
		this.controller = controller;
		this.kreis = kreis;


		setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Regler",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lb_Topo = new JLabel("Topologie");
		add(lb_Topo);
		cbbx_Topo = new JComboBox<>(ReglerTopologie.values());
		cbbx_Topo.setSelectedIndex(2);
		cbbx_Topo.addActionListener(this);
		add(cbbx_Topo);

		JLabel lb_defd_R = new JLabel("Definiert durch: ");
		add(lb_defd_R);
		cbbx_defd_R = new JComboBox<>(Dimensionierung.values());
		cbbx_defd_R.setSelectedIndex(3);
		cbbx_defd_R.addActionListener(this);
		add(cbbx_defd_R);
		
		JLabel lb_chiens = new JLabel("Regelung");
		add(lb_chiens);
		cbbx_chiens = new JComboBox<>(ChiensRegelung.values());
		cbbx_chiens.setSelectedIndex(4);
		cbbx_chiens.addActionListener(this);
		add(cbbx_chiens);

		JLabel lb_Kr = new JLabel("Kr");
		add(lb_Kr);
		tf_Kr = new JFormattedTextField(format);
		add(tf_Kr);

		JLabel lb_Tn = new JLabel("Tn");
		add(lb_Tn);
		tf_Tn = new JFormattedTextField(format);
		add(tf_Tn);

		JLabel lb_Tv = new JLabel("Tv");
		add(lb_Tv);
		tf_Tv = new JFormattedTextField(format);
		add(tf_Tv);

		JLabel lb_Tp = new JLabel("Tp");
		add(lb_Tp);
		tf_Tp = new JFormattedTextField(format);
		add(tf_Tp);

		autoDim();
		enableEvents();
		update(null, null);
		kreis.addObserver(this);
	}

	private void enableEvents() {
		tf_Kr.addPropertyChangeListener("value", this);
		tf_Tn.addPropertyChangeListener("value", this);
		tf_Tv.addPropertyChangeListener("value", this);
		tf_Tp.addPropertyChangeListener("value", this);
	}

	private void disableEvents() {
		tf_Kr.removePropertyChangeListener("value", this);
		tf_Tn.removePropertyChangeListener("value", this);
		tf_Tv.removePropertyChangeListener("value", this);
		tf_Tp.removePropertyChangeListener("value", this);
	}

	private void manuellDim() {
		tf_Kr.setEditable(true);
		tf_Tn.setEditable(true);
		tf_Tv.setEditable(true);
		tf_Tp.setEditable(true);
	}

	private void autoDim() {
		tf_Kr.setEditable(false);
		tf_Tn.setEditable(false);
		tf_Tv.setEditable(false);
		tf_Tp.setEditable(false);
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getSource() == tf_Kr) {
			controller.setKr(((Number) tf_Kr.getValue()).doubleValue());
		} else if (event.getSource() == tf_Tn) {
			controller.setTn(((Number) tf_Tn.getValue()).doubleValue());
		} else if (event.getSource() == tf_Tv) {
			controller.setTv(((Number) tf_Tv.getValue()).doubleValue());
		} else if (event.getSource() == tf_Tp) {
			controller.setTp(((Number) tf_Tp.getValue()).doubleValue());
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == cbbx_defd_R) {
			Dimensionierung dim = (Dimensionierung) cbbx_defd_R
					.getSelectedItem();
			switch(dim){
			case PHASENGANG:
				enablePhrand();
				break;
			case OPPELT:
				disablePhrand();
				break;
			case Chiens:
				enableChiens();
				break;
			}
			if (dim == Dimensionierung.MANUELL) {
				manuellDim();
			} else {
				autoDim();
			}
			controller.selectDim(dim);
		} else if (event.getSource() == cbbx_Topo) {
			ReglerTopologie topo = (ReglerTopologie) cbbx_Topo
					.getSelectedItem();
			controller.selectTopo(topo);
		}
	}

	private void enablePhrand() {
		if(tf_Phrand == null){
			lb_Phrand = new JLabel("Phasenrand");
			add(lb_Phrand);

			tf_Phrand = new JFormattedTextField(format);
			add(tf_Phrand);
		}
	}

	private void disablePhrand() {
		if(tf_Phrand != null){
			remove(lb_Phrand);
			lb_Phrand = null;
			
			remove(tf_Phrand);
			tf_Phrand = null;
			revalidate();
			repaint();
		}
	}
	
	private void enableChiens() {
		if(tf_Phrand == null){
			lb_Phrand = new JLabel("Phasenrand");
			add(lb_Phrand);

			tf_Phrand = new JFormattedTextField(format);
			add(tf_Phrand);
		}
	}

	private void disableChiens() {
		if(tf_Phrand != null){
			remove(lb_Phrand);
			lb_Phrand = null;
			
			remove(tf_Phrand);
			tf_Phrand = null;
			revalidate();
			repaint();
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		Regler regler = kreis.getRegler();
		disableEvents();
		tf_Kr.setValue(regler.getKr());
		tf_Tn.setValue(regler.getTn());
		tf_Tv.setValue(regler.getTv());
		tf_Tp.setValue(regler.getTp());
		enableEvents();
	}

}
