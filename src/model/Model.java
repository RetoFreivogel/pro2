package model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Vector;

import util.Copyable;
import model.dimensionierung.Dimensionierung;

/**
 * Das Model enthält die Regelstrecke und alle Reglerdimensionierungen die auf die Regelstrecke angewendet werden.
 * @author Reto Freivogel
 *
 */
public class Model extends Observable implements Serializable, Copyable{
	private static final long serialVersionUID = 1L;
	
	private final Vector<Dimensionierung> alleDim = new Vector<>(0, 1);
	private RegelStrecke regelstrecke;
		
	/**
	 * Erzeugt ein neues Model
	 * @param regelstrecke Die Regelstrecke des neuen Models
	 * @param dims Die Dimensionierungen des neuen Models
	 */
	public Model(RegelStrecke regelstrecke, Dimensionierung[] dims){
		if(dims.length == 0){
			throw new IllegalArgumentException();
		}
		this.regelstrecke = (RegelStrecke) regelstrecke.copy();
		for (int i = 0; i < dims.length; i++) {
			alleDim.addElement((Dimensionierung) dims[i].copy());
		}
	}
	
	/**
	 * Erzeugt und gibt alle Regelkreise zurück
	 * @return Ein Vector der alle Regelkreise enthält. Die Regelkreise haben alle die gleiche Regelstrecke 
	 */
	public Vector<RegelKreis> getAlleRegelkreise() {
		Vector<RegelKreis> kreise = new Vector<>(alleDim.size());
		
		for (int i = 0; i < alleDim.size(); i++) {
			kreise.add(new RegelKreis(alleDim.get(i), regelstrecke, "" + (i+1)));
		}
		return kreise;
	}
	
	/**
	 * Ersetzt eine Dimensionierung durch eine neuere.
	 * Ruft alle Observer auf.
	 * @param old_dim Die alte zu ersetzende Dimensionierung
	 * @param new_dim Die neue Dimensionierung
	 */
	public void replaceDim(Dimensionierung old_dim, Dimensionierung new_dim) {
		int index = alleDim.indexOf(old_dim);
		alleDim.remove(index);
		alleDim.insertElementAt(new_dim, index);
		setChanged();
		notifyObservers();
	}

	/**
	 * Gibt die Regelstrecke zurück
	 * @return Die Regelstrecke
	 */
	public RegelStrecke getRegelstrecke() {
		return regelstrecke;
	}

	/**
	 * Setzt die Regelstrecke auf einen neuen Wert
	 * Ruft alle Observer auf
	 * @param regelstrecke Die neue Regelstrecke
	 */
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
	
	/**
	 * Entfernt eine Dimensionierung
	 * Ruft alle Oberver auf.
	 * @param dim Die zu enternende Dimensionierung
	 */
	public void removeDim(Dimensionierung dim) {
		if(alleDim.size() == 1){
			throw new IllegalStateException("Letzter Regler kann nicht entfernt werden.");
		}
		alleDim.remove(dim);
		setChanged();
		notifyObservers();
	}

	/**
	 * Kopiert eine Dimensionierung
	 * Ruft alle Observer auf
	 * @param dim Die zu kopierende Dimensionierung 
	 */
	public void copyDim(Dimensionierung dim) {
		alleDim.add((Dimensionierung) dim.copy());
		setChanged();
		notifyObservers();
	}

	@Override
	public Object copy() {
		Dimensionierung[] dims = new Dimensionierung[alleDim.size()];
		alleDim.copyInto(dims);
		return new Model(regelstrecke, dims);
	}
}
