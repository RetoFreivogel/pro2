package main;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

public class Model extends Observable implements Observer{
	private Vector<RegelKreis> regelkreise;
			
	public Model(){
		regelkreise = new Vector<RegelKreis>(1);
		RegelKreis regelkreis = new RegelKreis(new OppeltDim(), new RegelStrecke(1.0, 0.1, 0.5));
		regelkreise.add(regelkreis);
		regelkreis.addObserver(this);
	}
	
	public RegelKreis getRegelkreis(){
		return regelkreise.get(0);
	}
	
	public void setRegelkreis(RegelKreis regelkreis){
		regelkreise.set(0, regelkreis);
		setChanged();
		notifyObservers();
	}

	@Override
	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers();		
	}
}
