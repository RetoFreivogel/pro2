package main;

public final class ManuellDim extends ReglerDim {
	private final Regler regler;

	public ManuellDim(double kr, double tn, double tv) {
		this.regler = new Regler(kr, tn, tv);
	}

	public ManuellDim(Regler regler) {
		this.regler = regler;
	}

	@Override
	public ReglerDim makeCopy() {
		return this;
	}

	@Override
	public Regler calc(RegelStrecke regelstrecke) {
		return this.regler;
	}

	public Regler getRegler() {
		return this.regler;
	}
}
