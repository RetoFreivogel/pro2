package main;


import javax.swing.JFrame;

import util.Chart;
import util.Matlab;


public class RegelGlied {
	private double[] zaehler;
	private double[] nenner;
	
	public static void main(String[] argv){
		double[] z = {1.0};
		double[] n = {1.0, 1.0, 16.0}; 
		RegelGlied rg = new RegelGlied(z, n);
		double[] schritt = rg.schrittantwort();
		
		JFrame frame = Chart.makeFrame(schritt);
		frame.pack();
		frame.setVisible(true);
		Matlab.closeProxy();	
	}
	
	public RegelGlied(){
		this.zaehler = new double[]{1.0};
		this.nenner = new double[]{1.0};
	}
	
	public RegelGlied(double[] zaehler, double[] nenner){
		this.zaehler = zaehler;
		this.nenner = nenner;
	}
	
	public double[] schrittantwort(){
		String polyString = "[";
		for (int i = 0; i < zaehler.length; i++) {
			polyString += zaehler[i] + " ";
		}
		polyString += "],[";
		for (int i = 0; i < nenner.length; i++) {
			polyString += nenner[i] + " ";
		}
		polyString += "]";
		
		double[] antwort = Matlab.calcStep(polyString);
		
		return antwort;
	}
	
	
	public double[] getPolyZaehler() {
		return zaehler;
	}
	public void setPolyZaehler(double[] polyZaehler) {
		zaehler = polyZaehler;
	}
	public double[] getPolyNenner() {
		return nenner;
	}
	public void setPolyNenner(double[] polyNenner) {
		nenner = polyNenner;
	}
}
