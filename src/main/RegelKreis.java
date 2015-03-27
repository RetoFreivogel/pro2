package main;

public class RegelKreis extends RegelGlied {
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
	
	public ReglerDim getDim() {
		return dim;
	}

	public void setDim(ReglerDim dim) {
		this.dim = dim;
		auto_dim();
	}
	
	public Regler getRegler() {
		return regler;
	}
	
	public void setRegler(Regler regler) {
		dim = new ManuellDim(regler);
		auto_dim();
	}

	public RegelStrecke getRegelstrecke() {
		return regelstrecke;
	}

	public void setRegelstrecke(RegelStrecke regelstrecke) {
		this.regelstrecke = regelstrecke;
		auto_dim();
	}
}
