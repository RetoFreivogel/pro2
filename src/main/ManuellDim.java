package main;

interface ImmutableManuellDim extends ReglerDim{
	public ImmutableRegler getRegler();
}

public class ManuellDim implements ImmutableManuellDim{
	Regler regler;

	public ManuellDim(ImmutableRegler regler) {
		this.regler = new Regler(regler);
	}
	
	public ManuellDim(ImmutableManuellDim dim) {
		this.regler = new Regler(dim.getRegler());
	}

	public Regler calc(ImmutableRegelStrecke regelstrecke) {
		return regler;
	}

	public ImmutableRegler getRegler() {
		return regler;
	}
}
