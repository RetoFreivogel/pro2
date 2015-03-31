package main;

import java.util.Observable;

import util.Matlab;

public class RegelGlied extends Observable {
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
		for (int i = 0; i < this.zaehler.length; i++) {
			polyString += this.zaehler[i] + " ";
		}
		polyString += "],[";
		for (int i = 0; i < this.nenner.length; i++) {
			polyString += this.nenner[i] + " ";
		}
		polyString += "]";

		double[] antwort = Matlab.calcStep(polyString);

		return antwort;
	}

	public double[] getPolyZaehler() {
		return this.zaehler;
	}

	public double[] getPolyNenner() {
		return this.nenner;
	}

	public void setPolyZaehler(double[] polyZaehler) {
		this.zaehler = polyZaehler;
		setChanged();
		notifyObservers();
	}

	protected void silentSetPolyZaehler(double[] polyZaehler) {
		this.zaehler = polyZaehler;
	}

	public void setPolyNenner(double[] polyNenner) {
		this.nenner = polyNenner;
		setChanged();
		notifyObservers();
	}

	protected void silentSetPolyNenner(double[] polyNenner) {
		this.nenner = polyNenner;
	}
}
