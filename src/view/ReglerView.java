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
import model.ChiensDim;
import model.ChiensRegelung;
import model.Dimensionierung;
import model.ManuellDim;
import model.OppeltDim;
import model.RegelKreis;
import model.Regler;
import model.ReglerTopologie;
import model.RosenbergDim;
import model.ZellwegerDim;
import model.ZieglerDim;

public class ReglerView extends JPanel implements PropertyChangeListener,
		Observer, ActionListener {
	private static final long serialVersionUID = 1L;
	private static final DecimalFormat format = new DecimalFormat("###0.###");

	private final Controller controller;
	private RegelKreis regelkreis;

	private JFormattedTextField tf_Phrand;
	private JFormattedTextField tf_Kr;
	private JFormattedTextField tf_Tn;
	private JFormattedTextField tf_Tv;
	private JFormattedTextField tf_Tp;
	private JComboBox<Dimensionierung> cbbx_defd_R;
	private JComboBox<ReglerTopologie> cbbx_Topo;
	private JComboBox<ChiensRegelung> cbbx_chiens;
	private boolean eventsEnabled = false;

	public ReglerView(RegelKreis kreis, Controller controller) {
		super();
		this.controller = controller;
		this.regelkreis = kreis;
		cbbx_Topo = new JComboBox<>(new ReglerTopologie[] { ReglerTopologie.PI,
				ReglerTopologie.PID });
		cbbx_Topo.setSelectedIndex(1);
		cbbx_Topo.addActionListener(this);

		cbbx_defd_R = new JComboBox<>(Dimensionierung.values());
		cbbx_defd_R.addActionListener(this);

		tf_Phrand = new JFormattedTextField(format);
		cbbx_chiens = new JComboBox<>(ChiensRegelung.values());
		cbbx_chiens.addActionListener(this);

		tf_Kr = new JFormattedTextField(format);
		tf_Kr.addPropertyChangeListener("value", this);
		tf_Tn = new JFormattedTextField(format);
		tf_Tn.addPropertyChangeListener("value", this);
		tf_Tv = new JFormattedTextField(format);
		tf_Tv.addPropertyChangeListener("value", this);
		tf_Tp = new JFormattedTextField(format);
		tf_Tp.addPropertyChangeListener("value", this);

		init();
		kreis.addObserver(this);
		update(null, null);
		eventsEnabled = true;
	}

	private void init() {
		removeAll();
		setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Regler",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 0, 0));

		JLabel lb_Topo = new JLabel("Topologie");
		add(lb_Topo);
		add(cbbx_Topo);

		JLabel lb_defd_R = new JLabel("Definiert durch: ");
		add(lb_defd_R);
		add(cbbx_defd_R);

		if (Dimensionierung.PHASENGANG == cbbx_defd_R.getSelectedItem()) {
			JLabel lb_Phrand = new JLabel("Phasenrand");
			add(lb_Phrand);
			add(tf_Phrand);
		}

		if (Dimensionierung.CHIENS == cbbx_defd_R.getSelectedItem()) {
			JLabel lb_chiens = new JLabel("Regelung: ");
			add(lb_chiens);
			add(cbbx_chiens);
		}

		JLabel lb_Kr = new JLabel("Kr");
		add(lb_Kr);
		add(tf_Kr);

		JLabel lb_Tn = new JLabel("Tn");
		add(lb_Tn);
		add(tf_Tn);

		if (ReglerTopologie.PID == cbbx_Topo.getSelectedItem()) {
			JLabel lb_Tv = new JLabel("Tv");
			add(lb_Tv);
			add(tf_Tv);

			JLabel lb_Tp = new JLabel("Tp");
			add(lb_Tp);
			add(tf_Tp);
		}

		if (Dimensionierung.MANUELL == cbbx_defd_R.getSelectedItem()) {
			tf_Kr.setEditable(true);
			tf_Tn.setEditable(true);
			tf_Tv.setEditable(true);
			tf_Tp.setEditable(true);
		} else {
			tf_Kr.setEditable(false);
			tf_Tn.setEditable(false);
			tf_Tv.setEditable(false);
			tf_Tp.setEditable(false);
		}

		revalidate();
		repaint();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (eventsEnabled) {
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
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (eventsEnabled) {
			if (event.getSource() == cbbx_defd_R) {
				Dimensionierung dim = (Dimensionierung) cbbx_defd_R
						.getSelectedItem();
				controller.selectDim(dim);
			} else if (event.getSource() == cbbx_Topo) {
				ReglerTopologie topo = (ReglerTopologie) cbbx_Topo
						.getSelectedItem();
				controller.selectTopo(topo);
			} else if (event.getSource() == cbbx_chiens) {
				ChiensRegelung chiensReg = (ChiensRegelung) cbbx_chiens
						.getSelectedItem();
				controller.selectChiensRegelung(chiensReg);
			}
			init();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		Regler regler = regelkreis.getRegler();
		eventsEnabled = false;
		try {
			cbbx_Topo.setSelectedItem(regelkreis.getTopo());

			Dimensionierung d;
			if (regelkreis.getDim() instanceof ChiensDim) {
				d = Dimensionierung.CHIENS;
			} else if (regelkreis.getDim() instanceof ManuellDim) {
				d = Dimensionierung.MANUELL;
			} else if (regelkreis.getDim() instanceof OppeltDim) {
				d = Dimensionierung.OPPELT;
			} else if (regelkreis.getDim() instanceof RosenbergDim) {
				d = Dimensionierung.ROSENBERG;
			} else if (regelkreis.getDim() instanceof ZellwegerDim) {
				d = Dimensionierung.PHASENGANG;
			} else if (regelkreis.getDim() instanceof ZieglerDim) {
				d = Dimensionierung.ZIEGLER;
			} else {
				throw new RuntimeException("Internal Error");
			}
			cbbx_defd_R.setSelectedItem(d);

			if (d == Dimensionierung.CHIENS) {
				cbbx_chiens.setSelectedItem(((ChiensDim) regelkreis.getDim())
						.getJ());
			}

			tf_Kr.setValue(regler.getKr());
			tf_Tn.setValue(regler.getTn());
			tf_Tv.setValue(regler.getTv());
			tf_Tp.setValue(regler.getTp());
		} finally {
			eventsEnabled = true;
		}
	}

	public void setReglerkreis(RegelKreis regelkreis) {
		this.regelkreis.deleteObserver(this);
		this.regelkreis = regelkreis;
		this.regelkreis.addObserver(this);
		update(null, null);
	}

}
