package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import controller.Controller;
import model.RegelKreis;
import model.Regler;
import model.dimensionierung.ChiensEnum;
import model.dimensionierung.DimEnum;
import model.dimensionierung.TopoEnum;

/**
 * Ansicht zur Darstellung einer Dimesionierungsmethode und des dimensionierten Reglers.
 * @author Reto Freivogel, Alex Stocker
 *
 */
public class ReglerView extends JPanel implements PropertyChangeListener,
		ActionListener, FocusListener {
	private static final long serialVersionUID = 1L;

	private final Controller controller;
	private RegelKreis regelkreis;

	private JButton jb_copy, jb_close;
	private JFormattedTextField tf_Phrand;
	private JFormattedTextField tf_Schwingen;
	private JFormattedTextField tf_Kr;
	private JFormattedTextField tf_Tn;
	private JFormattedTextField tf_Tv;
	private JFormattedTextField tf_Tp;
	private JComboBox<DimEnum> cbbx_defd_R;
	private JComboBox<TopoEnum> cbbx_Topo;
	private JComboBox<ChiensEnum> cbbx_chiens;
	private boolean eventsEnabled = false;

	/**
	 * Erstellt eine neue Ansicht.
	 * @param kreis Der Regelkreis mit der Dimensionierungsmethode 
	 * @param controller Referenz auf den Kontroller
	 */
	public ReglerView(RegelKreis kreis, Controller controller) {
		super();
		this.controller = controller;
		this.regelkreis = kreis;

		jb_copy = new JButton(new ImageIcon("icons/copy.png"));
		jb_copy.setBackground(new Color(64, 255, 64));
		jb_copy.setPreferredSize(new Dimension(20, 20));
		jb_copy.addActionListener(this);

		jb_close = new JButton(new ImageIcon("icons/close.png"));
		jb_close.setBackground(new Color(255, 64, 64));
		jb_close.setPreferredSize(new Dimension(20, 20));
		jb_close.addActionListener(this);

		cbbx_Topo = new JComboBox<>(
				new TopoEnum[] { TopoEnum.PI, TopoEnum.PID });
		cbbx_Topo.setSelectedIndex(1);
		cbbx_Topo.addActionListener(this);

		cbbx_defd_R = new JComboBox<>(DimEnum.values());
		cbbx_defd_R.addActionListener(this);

		cbbx_chiens = new JComboBox<>(ChiensEnum.values());
		cbbx_chiens.addActionListener(this);

		tf_Phrand = new JFormattedTextField(new DegreeFormatter());
		tf_Phrand.addPropertyChangeListener("value", this);
		tf_Phrand.addFocusListener(this);
		tf_Schwingen = new JFormattedTextField(new PercentFormatter());
		tf_Schwingen.addPropertyChangeListener("value", this);
		tf_Schwingen.addFocusListener(this);
		tf_Kr = new JFormattedTextField(new LowercaseDecimalFormatter());
		tf_Kr.addPropertyChangeListener("value", this);
		tf_Kr.addFocusListener(this);
		tf_Tn = new JFormattedTextField(new LowercaseDecimalFormatter());
		tf_Tn.addPropertyChangeListener("value", this);
		tf_Tn.addFocusListener(this);
		tf_Tv = new JFormattedTextField(new LowercaseDecimalFormatter());
		tf_Tv.addPropertyChangeListener("value", this);
		tf_Tv.addFocusListener(this);
		tf_Tp = new JFormattedTextField(new LowercaseDecimalFormatter());
		tf_Tp.addPropertyChangeListener("value", this);
		tf_Tp.addFocusListener(this);

		setBorder(new TitledBorder(new LineBorder(Color.GRAY),
				kreis.toString(), TitledBorder.LEADING, TitledBorder.TOP, null,
				null));
		setLayout(new GridBagLayout());

		update(kreis);
		init();
		eventsEnabled = true;
	}

	private void init() {
		removeAll();

		GridBagConstraints constraints = new GridBagConstraints(1, 0, 1, 1, 1,
				0, GridBagConstraints.NORTHEAST, GridBagConstraints.NONE,
				new Insets(0, 0, 0, 0), 0, 0);

		add(jb_copy, constraints);
		constraints.gridx = 2;
		constraints.weightx = 0;
		add(jb_close, constraints);
		constraints.weightx = 1;
		constraints.fill = GridBagConstraints.BOTH;
		// constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridy++;
		constraints.gridx = 0;
		JLabel lb_Topo = new JLabel("Topologie");
		add(lb_Topo, constraints);
		constraints.gridx = 1;
		constraints.gridwidth = 2;
		add(cbbx_Topo, constraints);
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		constraints.gridy++;

		JLabel lb_defd_R = new JLabel("Definiert durch: ");
		add(lb_defd_R, constraints);
		constraints.gridx = 1;
		constraints.gridwidth = 2;
		add(cbbx_defd_R, constraints);
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		constraints.gridy++;

		if (DimEnum.ZELLWEGER == cbbx_defd_R.getSelectedItem()) {
			JLabel lb_Phrand = new JLabel("Phasenrand");
			add(lb_Phrand, constraints);
			constraints.gridx = 1;
			constraints.gridwidth = 2;
			add(tf_Phrand, constraints);
			constraints.gridx = 0;
			constraints.gridwidth = 1;
			constraints.gridy++;
		}

		if (DimEnum.ITERATIV == cbbx_defd_R.getSelectedItem()) {
			JLabel lb_Schwingen = new JLabel("Ueberschwingen");
			add(lb_Schwingen, constraints);
			constraints.gridx = 1;
			constraints.gridwidth = 2;
			add(tf_Schwingen, constraints);
			constraints.gridx = 0;
			constraints.gridwidth = 1;
			constraints.gridy++;
		}

		if (DimEnum.CHIENS == cbbx_defd_R.getSelectedItem()) {
			JLabel lb_chiens = new JLabel("Regelung: ");
			add(lb_chiens, constraints);
			constraints.gridx = 1;
			constraints.gridwidth = 2;
			add(cbbx_chiens, constraints);
			constraints.gridx = 0;
			constraints.gridwidth = 1;
			constraints.gridy++;
		}

		JLabel lb_Kr = new JLabel("Kr");
		add(lb_Kr, constraints);
		constraints.gridx = 1;
		constraints.gridwidth = 2;
		add(tf_Kr, constraints);
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		constraints.gridy++;

		JLabel lb_Tn = new JLabel("Tn");
		add(lb_Tn, constraints);
		constraints.gridx = 1;
		constraints.gridwidth = 2;
		add(tf_Tn, constraints);
		constraints.gridx = 0;
		constraints.gridwidth = 1;
		constraints.gridy++;

		if (TopoEnum.PID == cbbx_Topo.getSelectedItem()) {
			JLabel lb_Tv = new JLabel("Tv");
			add(lb_Tv, constraints);
			constraints.gridx = 1;
			constraints.gridwidth = 2;
			add(tf_Tv, constraints);
			constraints.gridx = 0;
			constraints.gridwidth = 1;
			constraints.gridy++;

			JLabel lb_Tp = new JLabel("Tp");
			add(lb_Tp, constraints);
			constraints.gridx = 1;
			constraints.gridwidth = 2;
			add(tf_Tp, constraints);
			constraints.gridx = 0;
			constraints.gridwidth = 1;
			constraints.gridy++;
		}

		if (DimEnum.MANUELL == cbbx_defd_R.getSelectedItem()) {
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
			} else if (event.getSource() == tf_Schwingen) {
				Number schwingen = (Number) tf_Schwingen.getValue();
				controller.setUeberschwingen(schwingen.doubleValue(),
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
				DimEnum dim = (DimEnum) cbbx_defd_R.getSelectedItem();
				controller.setTyp(dim, regelkreis.getDim());
			} else if (event.getSource() == cbbx_Topo) {
				TopoEnum topo = (TopoEnum) cbbx_Topo.getSelectedItem();
				controller.selectTopo(topo, regelkreis.getDim());
			} else if (event.getSource() == cbbx_chiens) {
				ChiensEnum chiensReg = (ChiensEnum) cbbx_chiens
						.getSelectedItem();
				controller.setVerhalten(chiensReg, regelkreis.getDim());
			} else if (event.getSource() == jb_copy) {
				controller.copyKreis(regelkreis);
			} else if (event.getSource() == jb_close) {
				controller.closeRegler(regelkreis.getDim());
			}
			init();
		}
	}

	/**
	 * Aktualisiert die Ansicht
	 * @param kreis Der Regelkreis mit den neuen Werten
	 */
	public void update(RegelKreis kreis) {
		this.regelkreis = kreis;
		Regler regler = regelkreis.getRegler();
		eventsEnabled = false;
		try {
			cbbx_Topo.setSelectedItem(regelkreis.getDim().getTopo());

			((TitledBorder) getBorder()).setTitle(kreis.toString());

			DimEnum d = regelkreis.getDim().getTyp();
			cbbx_defd_R.setSelectedItem(d);

			if (d == DimEnum.CHIENS) {
				cbbx_chiens.setSelectedItem(regelkreis.getDim().getVerhalten());
			}

			if (d == DimEnum.ZELLWEGER) {
				tf_Phrand.setValue(regelkreis.getDim().getPhasenrand());
			}

			if (d == DimEnum.ITERATIV) {
				tf_Schwingen.setValue(regelkreis.getDim().getUeberschwingen());
			}

			tf_Kr.setValue(regler.getKr());
			tf_Tn.setValue(regler.getTn());
			tf_Tv.setValue(regler.getTv());
			tf_Tp.setValue(regler.getTp());
		} finally {
			eventsEnabled = true;
		}
	}

	@Override
	public void focusGained(final FocusEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (e.getSource() == tf_Phrand) {
					tf_Phrand.selectAll();
				} else if (e.getSource() == tf_Schwingen) {
					tf_Schwingen.selectAll();
				} else if (e.getSource() == tf_Kr) {
					tf_Kr.selectAll();
				} else if (e.getSource() == tf_Tn) {
					tf_Tn.selectAll();
				} else if (e.getSource() == tf_Tv) {
					tf_Tv.selectAll();
				} else if (e.getSource() == tf_Tp) {
					tf_Tp.selectAll();
				}
			}
		});
	}

	@Override
	public void focusLost(FocusEvent e) {
		// Do Nothing
	}
}
