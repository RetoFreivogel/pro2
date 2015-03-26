package prototype;

import javax.swing.JPanel;

import matlabcontrol.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MatlabCall {
	public static JPanel makePanel() {
		
		// Create a proxy, which we will use to control MATLAB
		MatlabProxyFactoryOptions options = new MatlabProxyFactoryOptions.Builder()
				.setHidden(true).build();
		MatlabProxyFactory factory = new MatlabProxyFactory(options);

		// Display 'hello world' just like when using the demo
		double[] output = {};
		try {
			MatlabProxy proxy = factory.getProxy();
			output = (double[]) proxy
					.returningEval("step([1],[1 1 2])", 1)[0];
					proxy.exit();
		} catch (MatlabInvocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MatlabConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		// create a dataset...
		XYSeries ser1 = new XYSeries("Test");
		for (int i = 0; i < output.length; i++) {
			ser1.add((double)i, output[i]);
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(ser1);

		// create a chart...
		JFreeChart chart = ChartFactory.createXYLineChart(
				"", //Title
				"", //Xaxis
				"", //Yaxis
				dataset,
				PlotOrientation.VERTICAL,
				false, // legend?
				false, // tooltips?
				false // URLs?
				);
		// create and display a frame...
		return new ChartPanel(chart);
	}
}
