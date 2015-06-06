package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import model.Model;
import model.RegelKreis;
import model.SchrittAntwort;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	private final Vector<JCheckBox> ckbx_Graph;
	private final JPanel pn_legend;
	private ChartPanel pn_chart;
	private XYItemRenderer renderer;

	public Graph(Model model) {
		super();

		setLayout(new BorderLayout());

		ckbx_Graph = new Vector<>(model.getAlleRegelkreise().size(), 1);

		pn_legend = new JPanel();
		pn_legend.setBackground(null);
		add(pn_legend, BorderLayout.SOUTH);

		XYSeriesCollection dataset = new XYSeriesCollection();
		JFreeChart chart = ChartFactory.createXYLineChart("", "", "", dataset,
				PlotOrientation.VERTICAL, false, false, false);
		XYPlot plot = (XYPlot) chart.getPlot();
		renderer = plot.getRenderer();
		plot.setBackgroundPaint(new Color(255, 255, 255));
		plot.setDomainGridlinePaint(new Color(196, 196, 196));
		plot.setRangeGridlinePaint(new Color(196, 196, 196));

		pn_chart = new ChartPanel(chart);
		add(pn_chart);

		update(model);
	}

	private void init(Model model) {
		pn_legend.removeAll();
		ckbx_Graph.clear();
		XYSeriesCollection dataset = new XYSeriesCollection();

		JFreeChart chart = pn_chart.getChart();
		XYPlot plot = (XYPlot) chart.getPlot();

		double tmax = 0;
		for (RegelKreis rk : model.getAlleRegelkreise()) {
			SchrittAntwort sa = rk.getTranferFunction().schrittantwort();
			double taus = sa.getTaus();
			if (tmax < taus) {
				tmax = taus;
			}
		}

		final int n = model.getAlleRegelkreise().size();
		for (int i = 0; i < n; i++) {
			RegelKreis rk = model.getAlleRegelkreise().get(i);

			JCheckBox cb = new JCheckBox();
			cb.setText(rk.toString());
			cb.setSelected(true);
			Color color = Color.getHSBColor((float) i * 3 / 20, (float) 0.8,
					(float) 0.9);
			cb.setBackground(color);
			cb.addActionListener(this);
			pn_legend.add(cb);
			ckbx_Graph.add(cb);

			SchrittAntwort sa = rk.getTranferFunction().schrittantwort();
			XYSeries ser = new XYSeries(3 * i);
			for (int j = 0; j < 300; j++) {
				double t = (double) j * tmax / 299;
				double y = sa.getY(t);
				ser.add(t, y);
			}
			dataset.addSeries(ser);
			renderer.setSeriesPaint(3 * i, color);
			renderer.setSeriesStroke(3 * i, new BasicStroke(1.5f));

			
			XYSeries maxlabel = new XYSeries(3*i + 1, false);
			maxlabel.add(sa.getTymax(), 0);
			maxlabel.add(sa.getTymax(), sa.getYmax());
			maxlabel.add(0, sa.getYmax());
			dataset.addSeries(maxlabel);
			renderer.setSeriesPaint(3 * i + 1, color);
			renderer.setSeriesStroke(3 * i + 1, new BasicStroke(1f,
					BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,
					new float[] { 6.0f, 6.0f }, 0.0f));
			
			XYSeries tauslabel = new XYSeries(3*i + 2, false);
			tauslabel.add(sa.getTaus(), 0);
			tauslabel.add(sa.getTaus(), sa.getY(sa.getTaus()));
			dataset.addSeries(tauslabel);
			renderer.setSeriesPaint(3 * i + 2, color);
			renderer.setSeriesStroke(3 * i + 2, new BasicStroke(1f,
					BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,
					new float[] { 6.0f, 6.0f }, 0.0f));

		}
		plot.setDataset(dataset);
		try {
			getRootPane().revalidate();
			getRootPane().repaint();
		} catch (NullPointerException ex) {
		}
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

	public void update(Model model) {
		init(model);
	}
}
