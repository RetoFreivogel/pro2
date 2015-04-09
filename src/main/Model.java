package main;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class Model extends Observable implements Observer{
	private Vector<RegelKreis> regelkreisArray;

	public Model() {
		regelkreisArray = new Vector<>(1);
		RegelKreis regelkreis = new RegelKreis(new OppeltDim(),
				new RegelStrecke(1.0, 1.71, 7.6));
		regelkreisArray.add(regelkreis);
		
		regelkreis.getRegelstrecke().getKs().addObserver(this);
		regelkreis.getRegelstrecke().getTu().addObserver(this);
		regelkreis.getRegelstrecke().getTg().addObserver(this);
	}

	public RegelKreis getRegelkreis() {
		return regelkreisArray.get(0);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		setChanged();
		notifyObservers();
	}

}
