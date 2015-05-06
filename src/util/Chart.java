package util;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chart {
	
	public static JFreeChart makeChart(double[] data, double maxX) {
		XYSeries ser1 = new XYSeries("Test");
		for (int i = 0; i < data.length; i++) {
			double x = (double)i * maxX / data.length;
			ser1.add(x, data[i]);
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(ser1);

		// create a chart...
		return ChartFactory.createXYLineChart("", // Title
				"", // Xaxis
				"", // Yaxis
				dataset, PlotOrientation.VERTICAL,
				true, // legend?
				false, // tooltips?
				false // URLs?
				);
	}

	public static ChartPanel makePanel(double[] data, double maxX) {
		JFreeChart chart = makeChart(data, maxX);
		return new ChartPanel(chart);
	}

	public static JFrame makeFrame(double[] data, double maxX) {
		JFreeChart chart = makeChart(data, maxX);
		return new ChartFrame("", chart);
	}
}
