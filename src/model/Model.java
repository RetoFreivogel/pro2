package model;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Vector;

public class Model extends Observable implements Observer {
	private Vector<RegelKreis> regelkreisArray;

	public Model(Scanner sc) {
		regelkreisArray = new Vector<>(1);
		RegelStrecke rs = new RegelStrecke(sc);
		AbstractDim dim = AbstractDim.fromScanner(sc);
		RegelKreis regelkreis = new RegelKreis(dim, rs);
		regelkreisArray.add(regelkreis);
		regelkreis.addObserver(this);
	}

	public Model() {
		regelkreisArray = new Vector<>(1);
		RegelKreis regelkreis = new RegelKreis(new OppeltDim(),
				new RegelStrecke(1.0, 1.71, 7.6));
		regelkreisArray.add(regelkreis);
		regelkreis.addObserver(this);
	}
	
	public Model(Model other){
		regelkreisArray = new Vector<>(1);
		RegelKreis regelkreis = new RegelKreis(other.getRegelkreis());
		regelkreisArray.add(regelkreis);
		regelkreis.addObserver(this);
	}

	public RegelKreis getRegelkreis() {
		return regelkreisArray.get(0);
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
