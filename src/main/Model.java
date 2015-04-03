package main;

import java.util.Observable;
import java.util.Vector;

public class Model extends Observable {
	private Vector<RegelKreis> regelkreisArray;

	public Model() {
		regelkreisArray = new Vector<>(1);
		RegelKreis regelkreis = new RegelKreis(new OppeltDim(),
				new RegelStrecke(1.0, 1.71, 7.6));
		regelkreisArray.add(regelkreis);
	}

	public RegelKreis getRegelkreis() {
		return regelkreisArray.get(0);
	}

	public void setRegelkreis(RegelKreis regelkreis) {
		regelkreisArray.set(0, regelkreis);
		setChanged();
		notifyObservers();
	}
}
