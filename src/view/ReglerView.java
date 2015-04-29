package view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.Controller;
import model.Regler;

public class ReglerView extends JPanel implements PropertyChangeListener, ActionListener {
	private static final long serialVersionUID = 1L;

	//private final Controller controller;
	
	private JFormattedTextField tf_Phrand;
	private JFormattedTextField tf_Kr;
	private JFormattedTextField tf_Tn;
	private JFormattedTextField tf_Tv;
	private JFormattedTextField tf_Tp;
	JComboBox<String> cbbx_defd_R;

	public ReglerView(Regler regler, Controller controller) {
		super();
		//this.controller = controller;

		DecimalFormat format = new DecimalFormat("###0.###");
		
		setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				"Regler", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lb_Topo = new JLabel("Topologie");
		add(lb_Topo);
		JComboBox<String> cbbx_Topo = new JComboBox<>();
		cbbx_Topo.setModel(new DefaultComboBoxModel<>(new String[] { "PI",
				"PID" }));
		cbbx_Topo.setSelectedIndex(1);
		//TODO add selection of Topology
		cbbx_Topo.setEnabled(false);
		add(cbbx_Topo);

		JLabel lb_defd_R = new JLabel("Definiert durch: ");
		add(lb_defd_R);
		cbbx_defd_R = new JComboBox<>();
		cbbx_defd_R.setModel(new DefaultComboBoxModel<>(new String[] {
				"Manuell", "Phasengang", "Ziegler", "Chien", "Oppelt",
				"Rosenberg", "Tsumme" }));
		cbbx_defd_R.setSelectedIndex(4);
		cbbx_defd_R.addActionListener(this);
		//TODO add selection of Definition
		cbbx_defd_R.setEnabled(false);
		add(cbbx_defd_R);

		JLabel lb_Phrand = new JLabel("Phasenrand");
		lb_Phrand.setVisible(false);
		add(lb_Phrand);
		tf_Phrand = new JFormattedTextField(format);
		tf_Phrand.setVisible(false);
		add(tf_Phrand);
		
		JLabel lb_Kr = new JLabel("Kr");
		add(lb_Kr);
		tf_Kr = new JFormattedTextField(format);
		tf_Kr.setEditable(false);
		add(tf_Kr);

		JLabel lb_Tn = new JLabel("Tn");
		add(lb_Tn);
		tf_Tn = new JFormattedTextField(format);
		tf_Tn.setEditable(false);
		add(tf_Tn);		

		JLabel lb_Tv = new JLabel("Tv");
		add(lb_Tv);
		tf_Tv = new JFormattedTextField(format);
		tf_Tv.setEditable(false);
		add(tf_Tv);	

		JLabel lb_Tp = new JLabel("Tp");
		add(lb_Tp);
		tf_Tp = new JFormattedTextField(format);
		tf_Tp.setEditable(false);
		add(tf_Tp);
		

		
		setRegler(regler);
	}

	public void setRegler(Regler regler) {
		tf_Kr.setValue(regler.getKr());
		tf_Tn.setValue(regler.getTn());
		tf_Tv.setValue(regler.getTv());
		tf_Tp.setValue(regler.getTp());
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		try{
			JComboBox<?> box = (JComboBox<?>)event.getSource();
			box.getSelectedIndex();
		}catch(ClassCastException ex){
			
		}
		// TODO Auto-generated method stub
		
	}

}
