package model;

import java.util.Observable;

/**
 * Basisklasse f�r alle Reglerdimensionierungen.
 * @author Reto
 */
public abstract class AbstractDim extends Observable {
	
	/**
	 * Funktion zur Berechnung eines neuen Reglers.
	 * @param regelstrecke Die regelstrecke, welche der generierte Regler ansteuern soll.
	 * @param topo Die gew�nschte Topologie des Reglers
	 * @return
	 */
	public abstract Regler calc(RegelStrecke regelstrecke, ReglerTopologie topo);

	@Override
	public String toString() {
		return "";
	}
	
	
}
