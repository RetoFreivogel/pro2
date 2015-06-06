package model;

import org.apache.commons.math3.complex.Complex;

public class SchrittAntwort {
	private final Complex[] poles;
	private final Complex[] residues;
	private final double constant;
	private final boolean stabil;
	private final boolean asymptotisch;
	private final double Taus;
	private final double Tymax;
	private final double Tan;
	private final double Ymax;
	private final double wmax;

	public SchrittAntwort(Complex[] poles, Complex[] residues) {
		super();
		this.poles = poles;
		this.residues = residues;
		
		constant = -getY(0);
		stabil = calcStabil();
		Taus = calcTaus(0.001);
		asymptotisch = calcAsymp();
		wmax = calcWmax();	
		Tymax = calcTymax();
		Ymax = getY(Tymax);
		Tan = calcTan();
		
	}

	public boolean isStabil() {
		return stabil;
	}
	
	public boolean isAsymptotisch() {
		return asymptotisch;
	}

	public double getTaus() {
		return Taus;
	}

	public double getTymax() {
		return Tymax;
	}

	public double getTan() {
		return Tan;
	}

	public double getYmax() {
		return Ymax;
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
		return constant;
	}

	private boolean calcStabil(){
		boolean stabil = true;
		for(Complex pole : poles){
			stabil &= pole.getReal() < 0;
		}
		return stabil;
	}
	
	private boolean calcAsymp(){
		for(Complex pole : poles){
			if(Math.abs(pole.getImaginary()) > 0){
				return false;
			}
		}
		return true;
	}
	
	private double calcTan(){
		double tmin = 0;
		double tmax = Tymax;
		
		if(Ymax < getYend()){
			return Taus;
		}

		for(int i = 0; i < 100; i++){
			double tmiddle = (tmin + tmax)/2;
			if(getY(tmiddle) < constant){
				tmin = tmiddle;
			}else{
				tmax = tmiddle;
			}
		}
		
		return (tmax + tmin)/2;
	}
	
	private double calcWmax(){
		if(asymptotisch){
			return 0;
		}
		
		double[] allw = new double[poles.length];
		double wmax = 0;
		for (int i = 0; i < poles.length; i++) {
			double w = Math.abs(poles[i].getImaginary());
			allw[i] = w;
			if(wmax < w){
				wmax = w;
			}
		}
		return wmax;
	}
	
	private double calcTymax() {
		if(asymptotisch){
			return Taus;
		}
		
		//schrittgrösse für die Suche der Anfangswerte
		double tstep = (Math.PI/2)/wmax;
		if(tstep > Taus){
			tstep = Taus;
		}
		
		//Suche nach Anfangswerten
		double tmin = 0;
		double tmax = tstep;
		while(getY(tmax) < getY(tmax + tstep) && tmin < Taus){
			tmin = tmax;
			tmax += tstep;
		}
		tmax += tstep;
		
		//Golden Section Search (von Wikipedia)
		final double gr = 0.618; //Golden Ratio
		for(int i = 0; i < 100; i++){
			double t1 = tmax - gr*(tmax-tmin);
			double t2 = tmin + gr*(tmax-tmin);
			if(getY(t1)<getY(t2)){
				tmin = t1;
			}else{
				tmax = t2;
			}
		}
		
		return (tmin + tmax)/2;
	}
		
	private double calcTaus(double delta) {
		if(delta > 1 || delta <= 0){
			throw new IllegalArgumentException("delta muss zwischen 0 und 1 sein");
		}
		
		//Schätzwerte als Startwerte
		double maxT = 0;
		for (int i = 0; i < poles.length; i++) {
			double t =  -1/poles[i].getReal();
			if(maxT < t){
				maxT = t;
			}
		}		
		double tmin = -Math.log(delta) * maxT;
		
		//Beende Suche falls Schrittantwort nicht stabil ist.
		if(!stabil){
			return tmin;
		}
		
		//Überprüfen ob taus zwischen tmin und tmax liegt
		while(Math.abs(getY(tmin)-getYend()) < delta){
			tmin /= 1.2;
		}
		double tmax = tmin * 1.2;
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
