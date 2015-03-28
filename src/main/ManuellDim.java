package main;


public class ManuellDim extends ReglerDim{
	Regler regler;

	public ManuellDim(double kr, double tn, double tv){
		this.regler = new Regler(kr, tn, tv);
	}
	
	public ManuellDim(Regler regler) {
		this.regler = regler;
	}
	
	public ManuellDim(ManuellDim dim) {
		this.regler = dim.getRegler();
	}

	public Regler calc(RegelStrecke regelstrecke) {
		return regler;
	}

	public Regler getRegler() {
		return regler;
	}
}
