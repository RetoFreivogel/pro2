package main;

import java.util.Observable;

import util.Matlab;

public class RegelGlied extends Observable{
	private double[] zaehler;
	private double[] nenner;
	
	public RegelGlied(double[] zaehler, double[] nenner) {
		this.zaehler = zaehler;
		this.nenner = nenner;
	}

	public RegelGlied() {
		this.zaehler = new double[] { 1.0 };
		this.nenner = new double[] { 1.0 };
	}

	public double[] schrittantwort() {
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

	public double[] getPolyNenner() {
		return nenner;
	}
	
	public void setPolyZaehler(double[] polyZaehler) {
		zaehler = polyZaehler;
		setChanged();
		notifyObservers();
	}
	
	protected void silentSetPolyZaehler(double[] polyZaehler) {
		zaehler = polyZaehler;
	}

	public void setPolyNenner(double[] polyNenner) {
		nenner = polyNenner;
		setChanged();
		notifyObservers();
	}
	
	protected void silentSetPolyNenner(double[] polyNenner) {
		nenner = polyNenner;
	}	
}
