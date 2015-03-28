package main;

interface ImmutableRegler extends ImmutableRegelGlied{
	public double getKr();
	public double getTn();
	public double getTv();
}

public class Regler extends RegelGlied implements ImmutableRegler{
	private double Kr, Tn, Tv;

	public Regler(double kr, double tn, double tv){
		this.Kr = kr;
		this.Tn = tn;
		this.Tv = tv;
	}
	
	public Regler(ImmutableRegler regler){
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
	}

	public void setTn(double tn) {
		Tn = tn;
	}

	public void setTv(double tv) {
		Tv = tv;
	}
}
