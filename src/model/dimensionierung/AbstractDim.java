package model.dimensionierung;

import java.io.Serializable;

import util.Copyable;
import model.RegelStrecke;
import model.Regler;

/**
 * Basisklasse für alle Dimensionierungen
 * 
 * @author Reto Freivogel
 *
 */
abstract class AbstractDim implements Serializable, Copyable {
	private static final long serialVersionUID = 1L;
	private final TopoEnum topo;

	protected AbstractDim(TopoEnum topo) {
		this.topo = topo;
	}

	/**
	 * Gibt die Topologie des zu dimensionierenden Reglers zurück.
	 * 
	 * @return Die Topologie
	 */
	TopoEnum getTopo() {
		return topo;
	}

	/**
	 * Gibt den Typ der Dimensionierung zurück
	 * @return Der Typ
	 */
	abstract DimEnum getTyp();

	/**
	 * Erstellt eine neue Dimensionierung mit einer neuen Topologie.s
	 * @param topo Die neue Topologie
	 * @return Eine neue Dimensionierung
	 */
	abstract AbstractDim setTopo(TopoEnum topo);

	/**
	 * Berechnet einen Regler für eine gegebene Regelstrecke. 
	 * @param regelstrecke Die vorgegebene Strecke
	 * @return Der dimensionierte Regler
	 */
	abstract Regler calc(RegelStrecke regelstrecke);
}
