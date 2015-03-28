package main;

import java.util.Observable;
import java.util.Vector;

public class Model extends Observable{
	private Vector<RegelKreis> regelkreise;
			
	public Model(){
		regelkreise = new Vector<RegelKreis>(1);
		regelkreise.add(new RegelKreis(new OppeltDim(), new RegelStrecke(1, 0.1, 0.5)));
	}
	
	public RegelKreis getRegelkreis(){
		return regelkreise.get(0);
	}
	
	public void setRegelkreis(RegelKreis regelkreis){
		regelkreise.set(0, regelkreis);
		setChanged();
		notifyObservers();
	}
}
