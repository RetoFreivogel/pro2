package model;

import org.apache.commons.math3.complex.Complex;

public class SchrittAntwort {
	private final double constant;
	private final Complex[] poles;
	private final Complex[] residues;

	public SchrittAntwort(Complex[] poles, Complex[] residues) {
		super();
		this.poles = poles;
		this.residues = residues;
		
		double constant = 0;
		
		for (int i = 0; i < residues.length; i++) {
			constant += residues[i].divide(poles[i]).getReal();
		}
		this.constant = -constant;
	}

	public double getY(double x) {
		double y = 0;

		for (int j = 0; j < poles.length; j++) {
			y += residues[j].multiply(poles[j].multiply(x).exp())
					.divide(poles[j]).getReal();
		}
		y += constant;
		return y;
	}

	public double getYend() {
		return getY(Double.MAX_VALUE);
	}

	public double getYmax(double delta) {
		return getY(Double.MAX_VALUE);
	}
	
	public double getTaus(double delta) {
		if(delta > 1 || delta <= 0){
			throw new IllegalArgumentException("delta muss zwischen 0 und 1 sein");
		}
		
		//Schätzwerte als Startwerte
		double sumT = 0;
		for (int i = 0; i < poles.length; i++) {
			sumT += 1/poles[i].abs();
		}		
		double tmin = -Math.log(delta) * sumT;
		double tmax = tmin * 1.2;
		
		//Überprüfen ob taus zwischen tmin und tmax liegt
		while(Math.abs(getY(tmin)-getYend()) < delta){
			tmin /= 1.2;
		}	
		while(Math.abs(getY(tmax)-getYend()) > delta){
			tmax *= 1.2;
		}
		
		//Binäre Suche nach taus
		for(int i = 0; i < 100; i++){
			double taus = (tmin + tmax)/2;
			if(Math.abs(getY(taus)-getYend()) > delta){
				tmin = taus;
			}else{
				tmax = taus;
			}
		}
		
		return (tmin + tmax)/2;
	}
}
