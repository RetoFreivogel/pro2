package model;

/**
 * Basis - Interface aller Regelglieder
 * 
 * @author Reto Freivogel
 *
 */
public interface RegelGlied {
	/**
	 * Erzeugt die �bertragungsfunktion dieses Regelgliedes
	 * 
	 * @return Die �bertragungsfunktion
	 */
	public TransferFunction getTranferFunction();
}
