package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import model.Model;
import model.SchrittAntwort;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph extends JPanel implements Observer, ActionListener {
	private static final long serialVersionUID = 1L;

	private Model model;

	private final Vector<JCheckBox> ckbx_Graph;
	private final JPanel pn_legend;
	private ChartPanel pn_chart;
	private XYItemRenderer renderer;

	public Graph(Model model) {
		super();
		this.model = model;

		setLayout(new BorderLayout());

		ckbx_Graph = new Vector<>(model.getAlleRegelkreise().size(), 1);
		
		pn_legend = new JPanel();
		pn_legend.setBackground(null);
		add(pn_legend, BorderLayout.SOUTH);

		XYSeriesCollection dataset = new XYSeriesCollection();
		JFreeChart chart = ChartFactory.createXYLineChart("", "", "", dataset,
				PlotOrientation.VERTICAL, false, false, false);
		renderer = ((XYPlot) chart.getPlot()).getRenderer();

		pn_chart = new ChartPanel(chart);
		add(pn_chart);
		
		model.addObserver(this);
		update(null, null);
	}

	private void init() {
		pn_legend.removeAll();
		ckbx_Graph.clear();
		XYSeriesCollection dataset = new XYSeriesCollection();

		JFreeChart chart = pn_chart.getChart();
		XYPlot plot = (XYPlot) chart.getPlot();
		
		final int n = model.getAlleRegelkreise().size();
		for (int i = 0; i < n; i++) {
			JCheckBox cb = new JCheckBox();
			cb.setText("Graph " + (i+1));
			cb.setSelected(true);
			Color color = Color.getHSBColor((float) i * 37 / 256, 1, 1);
			cb.setBackground(color);
			//cb.setOpaque(false);
			cb.addActionListener(this);
			pn_legend.add(cb);
			ckbx_Graph.add(cb);

			SchrittAntwort sa = model.getAlleRegelkreise().get(i)
					.getTranferFunction().schrittantwort();
			double Tend = sa.getTaus(0.001);
			XYSeries ser = new XYSeries(i);
			for (int j = 0; j < 100; j++) {
				double t = (double) j * Tend / 100;
				double y = sa.getY(t);
				ser.add(t, y);
			}
			dataset.addSeries(ser);
			renderer.setSeriesPaint(i, color);
		}
		plot.setDataset(dataset);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int n = ckbx_Graph.size();
		for (int i = 0; i < n; i++) {
			JCheckBox cb = ckbx_Graph.get(i);
			cb.setOpaque(cb.isSelected());
			renderer.setSeriesVisible(i, cb.isSelected());
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		init();
	}

}
