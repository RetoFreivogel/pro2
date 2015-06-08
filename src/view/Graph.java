package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import model.Model;
import model.RegelKreis;
import model.SchrittAntwort;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.event.AxisChangeEvent;
import org.jfree.chart.event.AxisChangeListener;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Graph extends JPanel implements ActionListener, AxisChangeListener {
	private static final long serialVersionUID = 1L;

	private Model model;

	private final Vector<JCheckBox> ckbx_Graph;
	private final JPanel pn_legend;
	private ChartPanel pn_chart;
	private XYItemRenderer renderer;
	private ValueAxis xAxis;

	public Graph(Model model) {
		super();
		this.model = model;

		setLayout(new BorderLayout());

		ckbx_Graph = new Vector<>(0, 1);

		pn_legend = new JPanel();
		pn_legend.setBackground(null);
		add(pn_legend, BorderLayout.SOUTH);

		XYSeriesCollection dataset = new XYSeriesCollection();
		JFreeChart chart = ChartFactory.createXYLineChart("", "", "", dataset,
				PlotOrientation.VERTICAL, false, false, false);
		XYPlot plot = chart.getXYPlot();
		renderer = plot.getRenderer();
		xAxis = plot.getDomainAxis();
		xAxis.setRange(new Range(0, calcTmax()), false, false);
		xAxis.addChangeListener(this);

		plot.setBackgroundPaint(new Color(255, 255, 255));
		plot.setDomainGridlinePaint(new Color(196, 196, 196));
		plot.setRangeGridlinePaint(new Color(196, 196, 196));

		pn_chart = new ChartPanel(chart);
		add(pn_chart, BorderLayout.CENTER);

		initCheckboxes();
		initRenderer();
		initDataset();
	}

	private double calcTmax() {
		double tmax = 0;
		for (RegelKreis rk : model.getAlleRegelkreise()) {
			SchrittAntwort sa = rk.getTranferFunction().schrittantwort();
			double taus = sa.getTaus();
			if (tmax < taus) {
				tmax = taus;
			}
		}
		return tmax;
	}

	private Color getSeriesColor(int num) {
		return Color
				.getHSBColor((float) num * 3 / 20, (float) 0.8, (float) 0.9);
	}

	private void initCheckboxes() {

		Vector<RegelKreis> alleKreise = model.getAlleRegelkreise();
		int n = alleKreise.size();
		
		while(ckbx_Graph.size() < n){
			JCheckBox cb = new JCheckBox();
			cb.setSelected(true);
			Color color = getSeriesColor(ckbx_Graph.size());
			cb.setBackground(color);
			cb.addActionListener(this);
			ckbx_Graph.add(cb);
		}
		
		while(ckbx_Graph.size() > n){
			ckbx_Graph.remove(ckbx_Graph.size() -1);
		}
					
		pn_legend.removeAll();
		for(int i = 0; i < n; i++){
			RegelKreis rk = alleKreise.get(i);
			JCheckBox cb = ckbx_Graph.get(i);
			cb.setText(rk.toString());
			cb.setOpaque(cb.isSelected());
			renderer.setSeriesVisible(3 * i + 0, cb.isSelected());
			renderer.setSeriesVisible(3 * i + 1, cb.isSelected());
			renderer.setSeriesVisible(3 * i + 2, cb.isSelected());
			pn_legend.add(cb);			
		}		
		revalidate();
		repaint();
	}

	private void initRenderer(){
		
		BasicStroke lineStroke = new BasicStroke(1.5f);
		BasicStroke dashedStroke = new BasicStroke(1f,
				BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1.0f,
				new float[] { 6.0f, 6.0f }, 0.0f);
		
		for (int i = 0; i < model.getAlleRegelkreise().size(); i++) {
			Color color = getSeriesColor(i);
			renderer.setSeriesPaint(3 * i, color);
			renderer.setSeriesStroke(3 * i, lineStroke);
			
			renderer.setSeriesPaint(3 * i + 1, color);
			renderer.setSeriesStroke(3 * i + 1, dashedStroke);

			renderer.setSeriesPaint(3 * i + 2, color);
			renderer.setSeriesStroke(3 * i + 2, dashedStroke);
		}
	}
	
	private void initDataset(){

		XYSeriesCollection dataset = new XYSeriesCollection();

		if (xAxis.isAutoRange()) {
			xAxis.setRange(new Range(0, calcTmax()), false, false);
		}
		double tstart = xAxis.getRange().getLowerBound();
		double tend = xAxis.getRange().getUpperBound();

		int i = 0;
		for (RegelKreis rk: model.getAlleRegelkreise()) {

			SchrittAntwort sa = rk.getTranferFunction().schrittantwort();
			XYSeries ser = new XYSeries(3 * i);
			for (int j = 0; j < 300; j++) {
				double t = tstart + (tend - tstart) * j / 299;
				double y = sa.getY(t);
				ser.add(t, y);
			}

			dataset.addSeries(ser);

			XYSeries maxlabel = new XYSeries(3 * i + 1, false);
			maxlabel.add(sa.getTymax(), 0);
			maxlabel.add(sa.getTymax(), sa.getYmax());
			maxlabel.add(0, sa.getYmax());
			dataset.addSeries(maxlabel);

			XYSeries tauslabel = new XYSeries(3 * i + 2, false);
			tauslabel.add(sa.getTaus(), 0);
			tauslabel.add(sa.getTaus(), sa.getY(sa.getTaus()));
			dataset.addSeries(tauslabel);
			
			i++;
		}

		XYPlot plot = pn_chart.getChart().getXYPlot();
		plot.setDataset(dataset);
		if (xAxis.isAutoRange()) {
			xAxis.setRange(new Range(0, calcTmax()), false, false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		initCheckboxes();
	}

	public void update(Model model) {
		this.model = model;
		initCheckboxes();
		initRenderer();
		initDataset();
	}

	@Override
	public void axisChanged(AxisChangeEvent e) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				initDataset();
			}
		});

	}
}
