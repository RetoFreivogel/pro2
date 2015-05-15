package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
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
		ActionListener {
	private static final long serialVersionUID = 1L;
	private static final DecimalFormat format = new DecimalFormat("##0.000");

	private final Controller controller;
	private RegelKreis regelkreis;

	private JButton jb_close;
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

		jb_close = new JButton("");
		jb_close.setBackground(new Color(255, 64, 64));
		jb_close.setPreferredSize(new Dimension(15, 15));
		jb_close.addActionListener(this);
		
		cbbx_Topo = new JComboBox<>(new ReglerTopologie[] { ReglerTopologie.PI,
				ReglerTopologie.PID });
		cbbx_Topo.setSelectedIndex(1);
		cbbx_Topo.addActionListener(this);

		cbbx_defd_R = new JComboBox<>(Dimensionierung.values());
		cbbx_defd_R.addActionListener(this);

		cbbx_chiens = new JComboBox<>(ChiensRegelung.values());
		cbbx_chiens.addActionListener(this);

		tf_Phrand = new JFormattedTextField(format);
		tf_Phrand.addPropertyChangeListener("value", this);
		tf_Kr = new JFormattedTextField(format);
		tf_Kr.addPropertyChangeListener("value", this);
		tf_Tn = new JFormattedTextField(format);
		tf_Tn.addPropertyChangeListener("value", this);
		tf_Tv = new JFormattedTextField(format);
		tf_Tv.addPropertyChangeListener("value", this);
		tf_Tp = new JFormattedTextField(format);
		tf_Tp.addPropertyChangeListener("value", this);

		setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Regler",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridBagLayout());

		update(kreis);
		init();
		eventsEnabled = true;
	}

	private void init() {
		removeAll();

		GridBagConstraints constraints = new GridBagConstraints(1, 0, 1, 1, 1,
				0, GridBagConstraints.NORTHEAST,
				GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0);

		add(jb_close, constraints);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridy++;
		constraints.gridx = 0;
		JLabel lb_Topo = new JLabel("Topologie");
		add(lb_Topo, constraints);
		constraints.gridx = 1;
		add(cbbx_Topo, constraints);
		constraints.gridx = 0;
		constraints.gridy++;

		JLabel lb_defd_R = new JLabel("Definiert durch: ");
		add(lb_defd_R, constraints);
		constraints.gridx = 1;
		add(cbbx_defd_R, constraints);
		constraints.gridx = 0;
		constraints.gridy++;

		if (Dimensionierung.PHASENGANG == cbbx_defd_R.getSelectedItem()) {
			JLabel lb_Phrand = new JLabel("Phasenrand");
			add(lb_Phrand, constraints);
			constraints.gridx = 1;
			add(tf_Phrand, constraints);
			constraints.gridx = 0;
			constraints.gridy++;
		}

		if (Dimensionierung.CHIENS == cbbx_defd_R.getSelectedItem()) {
			JLabel lb_chiens = new JLabel("Regelung: ");
			add(lb_chiens, constraints);
			constraints.gridx = 1;
			add(cbbx_chiens, constraints);
			constraints.gridx = 0;
			constraints.gridy++;
		}

		JLabel lb_Kr = new JLabel("Kr");
		add(lb_Kr, constraints);
		constraints.gridx = 1;
		add(tf_Kr, constraints);
		constraints.gridx = 0;
		constraints.gridy++;

		JLabel lb_Tn = new JLabel("Tn");
		add(lb_Tn, constraints);
		constraints.gridx = 1;
		add(tf_Tn, constraints);
		constraints.gridx = 0;
		constraints.gridy++;

		if (ReglerTopologie.PID == cbbx_Topo.getSelectedItem()) {
			JLabel lb_Tv = new JLabel("Tv");
			add(lb_Tv, constraints);
			constraints.gridx = 1;
			add(tf_Tv, constraints);
			constraints.gridx = 0;
			constraints.gridy++;

			JLabel lb_Tp = new JLabel("Tp");
			add(lb_Tp, constraints);
			constraints.gridx = 1;
			add(tf_Tp, constraints);
			constraints.gridx = 0;
			constraints.gridy++;
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

		try {
			getRootPane().revalidate();
			getRootPane().repaint();
		} catch (NullPointerException ex) {
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (eventsEnabled) {
			if (event.getSource() == tf_Phrand) {
				Number phrand = (Number) tf_Phrand.getValue();
				controller.setPhasenrand(phrand.doubleValue(),
						regelkreis.getDim());
			} else if (event.getSource() == tf_Kr) {
				controller.setKr(((Number) tf_Kr.getValue()).doubleValue(),
						regelkreis.getDim());
			} else if (event.getSource() == tf_Tn) {
				controller.setTn(((Number) tf_Tn.getValue()).doubleValue(),
						regelkreis.getDim());
			} else if (event.getSource() == tf_Tv) {
				controller.setTv(((Number) tf_Tv.getValue()).doubleValue(),
						regelkreis.getDim());
			} else if (event.getSource() == tf_Tp) {
				controller.setTp(((Number) tf_Tp.getValue()).doubleValue(),
						regelkreis.getDim());
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (eventsEnabled) {
			if (event.getSource() == cbbx_defd_R) {
				Dimensionierung dim = (Dimensionierung) cbbx_defd_R
						.getSelectedItem();
				controller.selectDim(dim, regelkreis.getDim());
			} else if (event.getSource() == cbbx_Topo) {
				ReglerTopologie topo = (ReglerTopologie) cbbx_Topo
						.getSelectedItem();
				controller.selectTopo(topo, regelkreis.getDim());
			} else if (event.getSource() == cbbx_chiens) {
				ChiensRegelung chiensReg = (ChiensRegelung) cbbx_chiens
						.getSelectedItem();
				controller.selectChiensRegelung(chiensReg,
						(ChiensDim) regelkreis.getDim());
			} else if (event.getSource() == jb_close) {
				controller.closeRegler(regelkreis.getDim());
			}
			init();
		}
	}

	public void update(RegelKreis kreis) {
		this.regelkreis = kreis;
		Regler regler = regelkreis.getRegler();
		eventsEnabled = false;
		try {
			cbbx_Topo.setSelectedItem(regelkreis.getDim().getTopo());

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

			if (d == Dimensionierung.PHASENGANG) {
				tf_Phrand.setValue(((ZellwegerDim) regelkreis.getDim())
						.getPhasenrand());
			}

			tf_Kr.setValue(regler.getKr());
			tf_Tn.setValue(regler.getTn());
			tf_Tv.setValue(regler.getTv());
			tf_Tp.setValue(regler.getTp());
		} finally {
			eventsEnabled = true;
		}
	}
}
