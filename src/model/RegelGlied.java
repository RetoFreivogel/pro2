package model;

/**
 * Basis - Interface aller Regelglieder
 * 
 * @author Reto Freivogel
 *
 */
public interface RegelGlied {
	/**
	 * Erzeugt die Übertragungsfunktion dieses Regelgliedes
	 * 
	 * @return Die Übertragungsfunktion
	 */
	public TransferFunction getTranferFunction();
}
