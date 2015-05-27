package model;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Observable;
import java.util.Vector;

public class Model extends Observable implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private final Vector<AbstractDim> alleDim = new Vector<>(0, 1);
	private RegelStrecke regelstrecke;

	public Model() {
		regelstrecke = new RegelStrecke(1.0, 1.71, 7.6);
		alleDim.add(new ZellwegerDim(45, ReglerTopologie.PID, "Zellweger"));
	}
		
	public Vector<RegelKreis> getAlleRegelkreise() {
		Vector<RegelKreis> kreise = new Vector<>(alleDim.size());
		for(AbstractDim dim : alleDim){
			kreise.add(new RegelKreis(dim, regelstrecke));
		}
		return kreise;
	}
	
	public Iterator<AbstractDim> getAlleDim(int index) {
		return alleDim.iterator();
	}

	public void replaceDim(AbstractDim old_dim, AbstractDim new_dim) {
		int index = alleDim.indexOf(old_dim);
		alleDim.remove(index);
		alleDim.insertElementAt(new_dim, index);
		setChanged();
		notifyObservers();
	}

	public RegelStrecke getRegelstrecke() {
		return regelstrecke;
	}

	public void setRegelstrecke(RegelStrecke regelstrecke) {
		this.regelstrecke = regelstrecke;
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Model\nalleDim: ");
		builder.append(alleDim);
		builder.append("\nregelstrecke: ");
		builder.append(regelstrecke);
		return builder.toString();
	}
	
	public void removeDim(AbstractDim dim) {
		if(alleDim.size() == 1){
			throw new IllegalStateException("Letzter Regler kann nicht entfernt werden.");
		}
		alleDim.remove(dim);
		setChanged();
		notifyObservers();
	}

	public void copyDim(AbstractDim dim) {
		alleDim.add(dim.setName(dim.getName()+ " Kopie"));
		setChanged();
		notifyObservers();
	}
}
