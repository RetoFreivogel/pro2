package main;

import java.util.Observable;
import java.util.Vector;

public class Model extends Observable{
	private Vector<RegelKreis> regelkreisArray;

	public Model() {
		this.regelkreisArray = new Vector<>(1);
		RegelKreis regelkreis = new RegelKreis(new OppeltDim(),
				new RegelStrecke(1.0, 0.1, 0.5));
		this.regelkreisArray.add(regelkreis);
	}

	public RegelKreis getRegelkreis() {
		return this.regelkreisArray.get(0);
	}

	public void setRegelkreis(RegelKreis regelkreis) {
		this.regelkreisArray.set(0, regelkreis);
		setChanged();
		notifyObservers();
	}
}
