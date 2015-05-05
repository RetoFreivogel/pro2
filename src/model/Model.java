package model;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class Model extends Observable implements Observer {
	private Vector<RegelKreis> regelkreisArray;
	
	public Model() {
		regelkreisArray = new Vector<>(1);
		RegelKreis regelkreis = new RegelKreis(new OppeltDim(),
				new RegelStrecke(1.0, 1.71, 7.6));
		regelkreisArray.add(regelkreis);
		regelkreis.addObserver(this);
	}

	public RegelKreis getRegelkreis() {
		return regelkreisArray.get(0);
	}

	public Vector<RegelKreis> getAll(){
		return regelkreisArray;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (RegelKreis rk : regelkreisArray) {
			builder.append(rk).append('\n');
		}
		return builder.toString();
	}

}
