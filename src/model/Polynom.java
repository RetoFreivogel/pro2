package model;

import java.util.Arrays;

import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;

public final class Polynom {
	private final double[] coeff;
	
	public Polynom(double[] coeff){
		int ordnung = coeff.length -1;
		
		//Remove trailing zeros
		while(ordnung > 0 && coeff[ordnung] == 0.0){
			ordnung--;
		}
		
		this.coeff = Arrays.copyOf(coeff, ordnung+1);
	}
	
	public static Polynom fromRoots(double[] roots){
		int n = roots.length;
		double[] coeff = new double[n + 1];
		coeff[n] = 1.0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				coeff[j] -= coeff[j+1] * roots[i];
			}
		}
		return new Polynom(coeff);
	}
	
	public double[] getCoeff(){
		return Arrays.copyOf(coeff, coeff.length);
	}
	
	public Complex[] getRoots(){
		if(coeff.length == 1)return new Complex[]{};
		
		LaguerreSolver solver = new LaguerreSolver();			
		return solver.solveAllComplex(coeff, 0.0, 1000);
	}
	
	public Complex eval(Complex x){
		Complex result = new Complex(0,0);
		
		for (int i = 0; i < coeff.length; i++) {
			result = result.add(x.pow(i).multiply(coeff[i]));
		}
		
		return result;
	}
	
	public Complex[] getResidues(){
		Complex[] roots = getRoots();
		
		Complex[] result = new Complex[roots.length];
		
		for(int i = 0; i < roots.length; i++){
			result[i] = Complex.ONE;
			for (int j = 0; j < roots.length; j++) {
				if(i != j){
					result[i] = result[i].multiply(roots[i].subtract(roots[j]));
				}
			}
		}
		return result; 
	}
	
	public Polynom mul(double scalar){
		double[] result = getCoeff();
		for (int i = 0; i < result.length; i++) {
			result[i] *= scalar;
		}
		return new Polynom(result);
	}
	
	public Polynom mul(Polynom other){
		double[] b = other.getCoeff();
		double[] result = new double[coeff.length + b.length -1];
		
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < coeff.length; j++) {
				result[i + j] += b[i] * coeff[j]; 
			}
		}
		return new Polynom(result);
	}
	
	public Polynom add(Polynom other){
		double[] b = other.getCoeff();
		double[] result = new double[Math.max(coeff.length, b.length)];
		
		for (int i = 0; i < b.length; i++) {
			result[i] += b[i];
		}
		
		for (int i = 0; i < coeff.length; i++) {
			result[i] += coeff[i];
		}
		
		return new Polynom(result);
	}
	
	public Polynom differentiate(){
		double[] result = new double[coeff.length -1];
		
		for (int i = 0; i < result.length; i++) {
			result[i] = coeff[i+1] * (i+1);
		}
		
		return new Polynom(result);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(coeff);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Polynom other = (Polynom) obj;
		if (!Arrays.equals(coeff, other.coeff))
			return false;
		return true;
	}	
}
