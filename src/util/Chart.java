package util;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chart {
	public static JFreeChart makeChart(double[] data) {
		XYSeries ser1 = new XYSeries("Test");
		for (int i = 0; i < data.length; i++) {
			ser1.add(i, data[i]);
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(ser1);

		// create a chart...
		return ChartFactory.createXYLineChart("", // Title
				"", // Xaxis
				"", // Yaxis
				dataset, PlotOrientation.VERTICAL, false, // legend?
				false, // tooltips?
				false // URLs?
				);
	}

	public static JPanel makePanel(double[] data) {
		JFreeChart chart = makeChart(data);
		return new ChartPanel(chart);
	}

	public static JFrame makeFrame(double[] data) {
		JFreeChart chart = makeChart(data);
		return new ChartFrame("", chart);
	}
}
