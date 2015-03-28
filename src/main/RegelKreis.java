package main;

interface ImmutableRegelKreis extends ImmutableRegelGlied{
	public ImmutableRegler getRegler();
	public ReglerDim getDim();
	public ImmutableRegelStrecke getRegelstrecke();
}

public class RegelKreis extends RegelGlied implements ImmutableRegelKreis{
	private Regler regler;
	private RegelStrecke regelstrecke;
	private ReglerDim dim;

	public RegelKreis(ReglerDim dim, RegelStrecke regelstrecke){
		this.dim = dim;
		this.regelstrecke = regelstrecke;
		auto_dim();
	}
	
	public RegelKreis(Regler regler, RegelStrecke regelstrecke){
		this.dim = new ManuellDim(regler);
		this.regelstrecke = regelstrecke;
		auto_dim();
	}	
	
	public void auto_dim(){
		regler = dim.calc(regelstrecke);
	}
	
	public void setDim(ReglerDim dim) {
		this.dim = dim;
		auto_dim();
	}
	
	public void setRegler(Regler regler) {
		dim = new ManuellDim(regler);
		auto_dim();
	}
	
	public void setRegelstrecke(RegelStrecke regelstrecke) {
		this.regelstrecke = regelstrecke;
		auto_dim();
	}

	
	public ImmutableRegler getRegler() {
		return regler;
	}

	public ReglerDim getDim() {
		return dim;
	}
	
	public ImmutableRegelStrecke getRegelstrecke() {
		return regelstrecke;
	}
}
