package main;

import java.util.Observable;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;

import util.Matlab;

public class RegelStrecke extends Observable implements RegelGlied {
	private double ks, tu, tg;
	private double v;
	int n;
	private double [] ri = new double[50];

	public RegelStrecke(double ks, double tu, double tg) {
		if(ks < 0)throw new IllegalArgumentException("ks can't be negative");
		if(tu < 0)throw new IllegalArgumentException("tu can't be negative");
		if(tg < 0)throw new IllegalArgumentException("tg can't be negative");
		
		this.ks = ks;
		this.tu = tu;
		this.tg = tg;
	}

	public RegelStrecke(RegelStrecke other) {
		ks = other.getKs();
		tu = other.getTu();
		tg = other.getTg();
	}

	public double getKs() {
		return ks;
	}

	public double getTu() {
		return tu;
	}

	public double getTg() {
		return tg;
	}

	public void setKs(double ks) {
		this.ks = ks;
		setChanged();
		notifyObservers();
	}

	public void setTu(double tu) {
		this.tu = tu;
		setChanged();
		notifyObservers();
	}

	public void setTg(double tg) {
		this.tg = tg;
		setChanged();
		notifyObservers();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(ks);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tg);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(tu);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		RegelStrecke other = (RegelStrecke) obj;
		if (Double.doubleToLongBits(ks) != Double.doubleToLongBits(other.ks))
			return false;
		if (Double.doubleToLongBits(tg) != Double.doubleToLongBits(other.tg))
			return false;
		if (Double.doubleToLongBits(tu) != Double.doubleToLongBits(other.tu))
			return false;
		return true;
	}

	public double[] calcSani(){
		
		for(int i =0; i<50;i++){
			int p=1/49;
			ri[i]=p*i;
		}
		
		v = tu/tg;
		if(v>0.64173){
			
		}
		
		if(v<0.001){
			
		}
		
		if(v <= 0.103638){
			n=2;
		}
		
		if(v <= 0.218017){
			n=3;
		}
		
		if(v <= 0.319357){
			n=4;
		}
		
		if(v <= 0.410303){
			n=5;
		}
		
		if(v <= 0.410303){
			n=6;
		}
		
		if(v <= 0.5700){
			n=7;
		}
		
		if(v<=0.64173){
			n=8;
		}
		
		else{
			n=10;
		}
		
		static final SplineInterpolator interpolator = new SplineInterpolator();

		 

	     static final double[] x = { 0.00000000000000e+000, 16.0950576774209e-003,

	                28.6661664881192e-003, 39.6162441127304e-003,

	                49.6392546550365e-003, 59.1104948706751e-003,

	                68.2559002191843e-003, 77.2244322466593e-003,

	                86.1118160725877e-003, 94.9806300778381e-003,

	                103.867494606055e-003, 112.789721289430e-003,

	                121.750608015504e-003, 130.744128533721e-003,

	                139.756469447806e-003, 148.769982219454e-003,

	                157.763242283713e-003, 166.715245182820e-003,

	                175.600244165446e-003, 184.393125175265e-003,

	                193.068728132452e-003, 201.599136088098e-003,

	                209.958792041090e-003, 218.118872542195e-003,

	                226.053743230940e-003, 233.738686557713e-003,

	                241.150139101732e-003, 248.266605131661e-003,

	                255.070553513447e-003, 261.544653450614e-003,

	                267.677750465630e-003, 273.458121093045e-003,

	                278.878229075341e-003, 283.935977266567e-003,

	                288.627246672896e-003, 292.954331163085e-003,

	                296.918453877578e-003, 300.524246601810e-003,

	                303.780026398736e-003, 306.691849164696e-003,

	                309.270214028114e-003, 311.523876473965e-003,

	                313.465938278298e-003, 315.107796182504e-003,

	                316.460262400822e-003, 317.537660810227e-003,

	                318.353469886297e-003, 318.919666670065e-003,

	                319.249764579088e-003, 319.356722376953e-003 };

	 

	     static final double[] y = { 0.00000000000000e+000, 20.4081632653061e-003,

	                40.8163265306122e-003, 61.2244897959184e-003,

	                81.6326530612245e-003, 102.040816326531e-003,

	                122.448979591837e-003, 142.857142857143e-003,

	                163.265306122449e-003, 183.673469387755e-003,

	                204.081632653061e-003, 224.489795918367e-003,

	                244.897959183673e-003, 265.306122448980e-003,

	                285.714285714286e-003, 306.122448979592e-003,

	                326.530612244898e-003, 346.938775510204e-003,

	                367.346938775510e-003, 387.755102040816e-003,

	                408.163265306122e-003, 428.571428571429e-003,

	                448.979591836735e-003, 469.387755102041e-003,

	                489.795918367347e-003, 510.204081632653e-003,

	                530.612244897959e-003, 551.020408163265e-003,

	                571.428571428571e-003, 591.836734693878e-003,

	                612.244897959184e-003, 632.653061224490e-003,

	                653.061224489796e-003, 673.469387755102e-003,

	                693.877551020408e-003, 714.285714285714e-003,

	                734.693877551020e-003, 755.102040816327e-003,

	                775.510204081633e-003, 795.918367346939e-003,

	                816.326530612245e-003, 836.734693877551e-003,

	                857.142857142857e-003, 877.551020408163e-003,

	                897.959183673469e-003, 918.367346938776e-003,

	                938.775510204082e-003, 959.183673469388e-003,

	                979.591836734694e-003, 1.00000000000000e+000 };

	 

	     public static double spline(double[] x, double[] y, double v) {

	          PolynomialSplineFunction f = interpolator.interpolate(x, y);

	          return f.value(v);

	     }

	 

	     public static void main(String[] args) {

	          // Matlab - Test - Code:

	          // clear all; close all; clc;

	          // load('p2_sani_tu_tg');

	          //

	          // tu=1.71;

	          // tg=7.6;

	          // v=tu/tg;

	          // n = 4;

	          //

	          // ri=linspace(0,1,50);

	          // r=spline(Tu_Tg(n,:),ri,v);

	 

	          double v = 0.225;

	          double r = spline(x, y, v);

	          System.out.println("Wert r: " + r);

	          System.out.println("Differenz zu Matlab: "

	                     + (487.049670730899e-003 - r));

	     }

	 

	}
		
		
		double[] Tcoeffs = new double[]{0.408, 0.838, 1.72, 3.532};

		return Tcoeffs;
		
	
}
	
	@Override
	public TransferFunction getTranferFunction() {
		double[] Tcoeffs = calcSani();
			
		double Tprodukt = 1.0;
		for (int i = 0; i < Tcoeffs.length; i++) {
			Tprodukt *= Tcoeffs[i];
		}
		
		for (int i = 0; i < Tcoeffs.length; i++) {
			Tcoeffs[i] = -1 / Tcoeffs[i];
		}
		Polynom nenner = Polynom.fromRoots(Tcoeffs);
		
		nenner = nenner.mul(Tprodukt);
		
		return new TransferFunction(Polynom.fromCoeff(new double[]{ks}), nenner);	
	}
}
