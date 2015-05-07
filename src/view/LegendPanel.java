package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import model.Model;

import org.jfree.chart.ChartPanel;

import controller.Controller;

public class LegendPanel extends JPanel implements Observer, ActionListener {
	private static final long serialVersionUID = 1L;

	private static final AbstractButton dbKr = null;

	public JPanel pn_legend;
	public ChartPanel pn_chart;
	public Model model;
	public Controller controller;

	
	public double tf_Kr = Double.parseDouble(dbKr.getText());

	private AbstractButton dbTn;
	public double tf_Tn = Double.parseDouble(dbTn.getText());

	private AbstractButton dbTv;
	public double tf_Tv = Double.parseDouble(dbTv.getText());

	private AbstractButton dbTp;
	public double tf_Tp = Double.parseDouble(dbTp.getText());

	private JCheckBox ckbx_Graph_1;
	private JCheckBox ckbx_Graph_2;
	private JCheckBox ckbx_Graph_3;
	private JCheckBox ckbx_Graph_4;
	private JCheckBox ckbx_Graph_5;

	public LegendPanel(Model model, Controller controller) {
		super();
		this.model = model;
		this.controller = controller;
		init();
		model.addObserver(this);
	}

	private void init() {
		JPanel pn_legend = new JPanel();
		add(pn_legend);

		ckbx_Graph_1 = new JCheckBox();
		ckbx_Graph_1.setText(dbKr.getText());
		ckbx_Graph_1.setSelected(false); // checkbox.setEnabled(false);
		ckbx_Graph_1.addActionListener(this);

		GridBagConstraints gbc_ckbx_Graph_1 = new GridBagConstraints();
		gbc_ckbx_Graph_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_ckbx_Graph_1.gridx = 0;
		gbc_ckbx_Graph_1.gridy = 0;
		pn_legend.add(ckbx_Graph_1, gbc_ckbx_Graph_1);

		ckbx_Graph_2 = new JCheckBox();
		ckbx_Graph_2.setText("Graph 2");
		ckbx_Graph_2.setSelected(false); // checkbox.setEnabled(false);
		ckbx_Graph_2.addActionListener(this);

		GridBagConstraints gbc_ckbx_Graph_2 = new GridBagConstraints();
		gbc_ckbx_Graph_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_ckbx_Graph_2.gridx = 0;
		gbc_ckbx_Graph_2.gridy = 1;
		pn_legend.add(ckbx_Graph_2, gbc_ckbx_Graph_2);

		ckbx_Graph_3 = new JCheckBox();
		ckbx_Graph_3.setText("Graph 3");
		ckbx_Graph_3.setSelected(false); // checkbox.setEnabled(false);
		ckbx_Graph_3.addActionListener(this);

		GridBagConstraints gbc_ckbx_Graph_3 = new GridBagConstraints();
		gbc_ckbx_Graph_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_ckbx_Graph_3.gridx = 0;
		gbc_ckbx_Graph_3.gridy = 2;
		pn_legend.add(ckbx_Graph_3, gbc_ckbx_Graph_3);

		ckbx_Graph_4 = new JCheckBox();
		ckbx_Graph_4.setText("Graph 4");
		ckbx_Graph_4.setSelected(false); // checkbox.setEnabled(false);
		ckbx_Graph_4.addActionListener(this);

		GridBagConstraints gbc_ckbx_Graph_4 = new GridBagConstraints();
		gbc_ckbx_Graph_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_ckbx_Graph_4.gridx = 0;
		gbc_ckbx_Graph_4.gridy = 3;
		pn_legend.add(ckbx_Graph_4, gbc_ckbx_Graph_4);

		ckbx_Graph_5 = new JCheckBox();
		ckbx_Graph_5.setText("Graph 5");
		ckbx_Graph_5.setSelected(false);
		ckbx_Graph_5.addActionListener(this);

		GridBagConstraints gbc_ckbx_Graph_5 = new GridBagConstraints();
		gbc_ckbx_Graph_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_ckbx_Graph_5.gridx = 0;
		gbc_ckbx_Graph_5.gridy = 4;
		pn_legend.add(ckbx_Graph_5, gbc_ckbx_Graph_5);

	}

	public void createCheckbox(JCheckBox checkbox) {

		checkbox = new JCheckBox();
		checkbox.setText("");
		checkbox.setSelected(false);
		checkbox.addActionListener(this);

		GridBagConstraints gbc_checkbox = new GridBagConstraints();
		gbc_checkbox.fill = GridBagConstraints.HORIZONTAL;
		gbc_checkbox.gridx = 0;
		gbc_checkbox.gridy = 0;
		pn_legend.add(checkbox, gbc_checkbox);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (ckbx_Graph_1.isSelected()) {
			ckbx_Graph_1.setBackground(Color.BLUE); // (255, 79, 79)

		} else {

			ckbx_Graph_1.setBackground(null);
		}

		if (ckbx_Graph_2.isSelected()) {
			ckbx_Graph_2.setBackground(Color.CYAN);// (79, 135, 255)
		} else {
			ckbx_Graph_2.setBackground(null);
		}

		if (ckbx_Graph_3.isSelected()) {
			ckbx_Graph_3.setBackground(Color.YELLOW);// (79, 255, 79)
		} else {
			ckbx_Graph_3.setBackground(null);
		}

		if (ckbx_Graph_4.isSelected()) {
			ckbx_Graph_4.setBackground(Color.RED);// (255, 79, 255)
		} else {
			ckbx_Graph_4.setBackground(null);
		}

		if (ckbx_Graph_5.isSelected()) {
			ckbx_Graph_5.setBackground(Color.GREEN);// (255, 255, 79)
		} else {
			ckbx_Graph_5.setBackground(null);
		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {

	}

}
