package main;

import java.util.Observable;
import java.util.Observer;

public class RegelKreis extends Observable implements RegelGlied, Observer {
	private RegelStrecke regelstrecke;
	private ReglerDim dim;

	public RegelKreis(ReglerDim dim, RegelStrecke regelstrecke) {
		this.dim = dim;
		this.regelstrecke = regelstrecke;
		
		this.dim.addObserver(this);
		this.regelstrecke.addObserver(this);
	}

	public Regler getRegler() {
		return dim.calc(regelstrecke);
	}

	public ReglerDim getDim() {
		return dim;
	}
	
	public void setDim(ReglerDim dim) {
		this.dim.deleteObserver(this);
		this.dim = dim;
		this.dim.addObserver(this);
	}

	public RegelStrecke getRegelstrecke() {
		return regelstrecke;
	}

	public void setRegelstrecke(RegelStrecke regelstrecke) {
		this.regelstrecke.deleteObserver(this);
		this.regelstrecke = regelstrecke;
		this.regelstrecke.addObserver(this);
	}

	@Override
	public TransferFunction getTranferFunction() {
		TransferFunction tf_s = regelstrecke.getTranferFunction();
		TransferFunction tf_r = dim.calc(regelstrecke).getTranferFunction();
		
		TransferFunction tf_k = tf_s.conv(tf_r);
		tf_k = tf_k.feedback_loop();
		
		return tf_k;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		setChanged();
		notifyObservers();
	}
}
