package model;

import java.util.Observable;
import java.util.Observer;

public class RegelKreis extends Observable implements RegelGlied, Observer {
	public RegelStrecke regelstrecke;
	private AbstractDim dim;
	private ReglerTopologie topo;

	public RegelKreis(AbstractDim dim, RegelStrecke regelstrecke) {
		this.dim = dim;
		this.regelstrecke = regelstrecke;
		this.topo = ReglerTopologie.PID;

		this.dim.addObserver(this);
		this.regelstrecke.addObserver(this);
	}

	public RegelKreis(RegelKreis other) {
		this.dim = other.getDim().makeCopy();
		this.regelstrecke = new RegelStrecke(other.getRegelstrecke());
		this.topo = other.getTopo();
		this.dim.addObserver(this);
		this.regelstrecke.addObserver(this);
	}

	public Regler getRegler() {
		return dim.calc(regelstrecke, topo);
	}

	public AbstractDim getDim() {
		return dim;
	}

	public void setDim(AbstractDim dim) {
		this.dim.deleteObserver(this);
		this.dim = dim;
		this.dim.addObserver(this);
		setChanged();
		notifyObservers();
	}

	public RegelStrecke getRegelstrecke() {
		return regelstrecke;
	}

	public void setRegelstrecke(RegelStrecke regelstrecke) {
		this.regelstrecke.deleteObserver(this);
		this.regelstrecke = regelstrecke;
		this.regelstrecke.addObserver(this);
		setChanged();
		notifyObservers();

	}

	public ReglerTopologie getTopo() {
		return topo;
	}

	public void setTopo(ReglerTopologie topo) {
		this.topo = topo;
		setChanged();
		notifyObservers();
	}

	@Override
	public TransferFunction getTranferFunction() {
		TransferFunction tf_s = regelstrecke.getTranferFunction();
		TransferFunction tf_r = getRegler().getTranferFunction();

		TransferFunction tf_k = tf_s.conv(tf_r);
		tf_k = tf_k.feedback_loop();

		return tf_k;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(regelstrecke);
		builder.append(dim);
		builder.append("topo: ");
		builder.append(topo);
		builder.append('\n');
		return builder.toString();
	}
}
