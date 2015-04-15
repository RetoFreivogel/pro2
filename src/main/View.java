package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import util.Chart;

public class View extends JPanel implements Observer{
	private static final long serialVersionUID = 1L;

	private Controller controller;
	private Model model;
	
	private JLabel statusbar;
	
	private JTextField tf_Ks;
	private JTextField tf_Tu;
	private JTextField tf_Tg;
	
	private JTextField tf_Kr;
	private JTextField tf_Tn;
	private JTextField tf_Tv;
	
	
	private JPanel sidePanel;
	private ChartPanel graph;

	public View(Controller controller, Model model) {
		this.controller = controller; 
		this.model = model;
		init();
	}
	
	public void init(){
		setLayout(new BorderLayout());

		//add(new JMenuBar(), BorderLayout.NORTH);
		
		sidePanel = new JPanel();
		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		
		tf_Ks = new JTextField();
		tf_Ks.setAction(controller.getKsAction());
		sidePanel.add(tf_Ks);
		tf_Tu = new JTextField();
		tf_Tu.setAction(controller.getTuAction());
		sidePanel.add(tf_Tu);
		tf_Tg = new JTextField();
		tf_Tg.setAction(controller.getTgAction());
		sidePanel.add(tf_Tg);
		
		tf_Kr = new JTextField();
		tf_Kr.setColumns(15);
		sidePanel.add(tf_Kr);
		tf_Tn = new JTextField();
		tf_Tn.setColumns(15);
		sidePanel.add(tf_Tn);
		tf_Tv = new JTextField();
		tf_Tv.setColumns(15);
		sidePanel.add(tf_Tv);
		
		add(sidePanel, BorderLayout.WEST);
		
		statusbar = new JLabel("Bereit");
		add(statusbar, BorderLayout.SOUTH);
		
		graph = Chart.makePanel(new double[]{0});
		graph.setBackground(Color.WHITE);
		add(graph, BorderLayout.CENTER);
		
		update(null, null);
	}

	public void setStatus(String message) {
		statusbar.setText(message);
	}

	@Override
	public void update(Observable o, Object arg) {
		double[] output = this.model.getRegelkreis()
				.schrittantwort();
		JFreeChart chart = Chart.makeChart(output);
		graph.setChart(chart);

		tf_Ks.setText("" + model.getRegelkreis().getRegelstrecke().getKs().getValue());
		tf_Tu.setText("" + model.getRegelkreis().getRegelstrecke().getTu().getValue());
		tf_Tg.setText("" + model.getRegelkreis().getRegelstrecke().getTg().getValue());
		
		tf_Kr.setText("" + this.model.getRegelkreis().getRegler().getKr());
		tf_Tn.setText("" + this.model.getRegelkreis().getRegler().getTn());
		tf_Tv.setText("" + this.model.getRegelkreis().getRegler().getTv());
	}
}

