package main;

public class Regler extends RegelGlied{
	private double Kr, Tn, Tv;

	public Regler(double kr, double tn, double tv){
		this.Kr = kr;
		this.Tn = tn;
		this.Tv = tv;
	}
	
	public Regler(Regler regler){
		this.Kr = regler.getKr();
		this.Tn = regler.getTn();
		this.Tv = regler.getTv();
	}
	
	public double getKr() {
		return Kr;
	}

	public double getTn() {
		return Tn;
	}

	public double getTv() {
		return Tv;
	}
	
	public void setKr(double kr) {
		Kr = kr;
		setChanged();
		notifyObservers();
	}

	public void setTn(double tn) {
		Tn = tn;
		setChanged();
		notifyObservers();
	}

	public void setTv(double tv) {
		Tv = tv;
		setChanged();
		notifyObservers();
	}
	
	public boolean equals(Object other){
		if(this == other)return true;
		if(!(other instanceof Regler))return false;
		Regler regler = (Regler)other;
		
		return 	this.Kr == regler.getKr() &&
				this.Tn == regler.getTn() &&
				this.Tv == regler.getTv();
	}
}
