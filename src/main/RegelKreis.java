package main;

import java.util.Observable;
import java.util.Observer;

public class RegelKreis extends RegelGlied implements Observer {
	private Regler regler;
	private RegelStrecke regelstrecke;
	private ReglerDim dim;

	public RegelKreis(ReglerDim dim, RegelStrecke regelstrecke) {
		this.dim = dim;
		this.dim.addObserver(this);
		this.regelstrecke = regelstrecke;
		this.regelstrecke.addObserver(this);
		this.regler = this.dim.calc(regelstrecke);
		this.regler.addObserver(this);
	}

	public RegelKreis(Regler regler, RegelStrecke regelstrecke) {
		this.dim = new ManuellDim(regler);
		this.dim.addObserver(this);
		this.regelstrecke = regelstrecke;
		this.regelstrecke.addObserver(this);
		this.regler = this.dim.calc(regelstrecke);
		this.regler.addObserver(this);
	}

	public void auto_dim() {
		this.regler.deleteObserver(this);
		this.regler = this.dim.calc(this.regelstrecke);
		this.regler.addObserver(this);
		setChanged();
		notifyObservers();
	}

	public void setDim(ReglerDim dim) {
		dim.deleteObserver(this);
		this.dim = dim;
		dim.addObserver(this);
		auto_dim();
	}

	public void setRegler(Regler regler) {
		setDim(new ManuellDim(regler));
	}

	public void setRegelstrecke(RegelStrecke regelstrecke) {
		regelstrecke.deleteObserver(this);
		this.regelstrecke = regelstrecke;
		regelstrecke.addObserver(this);
		auto_dim();
	}

	public Regler getRegler() {
		return this.regler;
	}

	public ReglerDim getDim() {
		return this.dim;
	}

	public RegelStrecke getRegelstrecke() {
		return this.regelstrecke;
	}

	@Override
	public void update(Observable obs, Object arg) {
		if (obs == this.regler) {
			setRegler(this.regler);
		} else {
			auto_dim();
		}
	}
}
