package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Model;
import model.RegelKreis;
import model.RegelStrecke;
import model.dimensionierung.ChiensEnum;
import model.dimensionierung.DimEnum;
import model.dimensionierung.Dimensionierung;
import model.dimensionierung.TopoEnum;
import view.View;

/**
 * 
 * Der Controller des MVC-Patterns. Besitzt Methoden für die Modifikation des Modells.
 * Vor jeder Änderung macht er sich eine Kopie des Modells um die Änderungen rückgängig
 * machen zu können.
 * 
 * @author Reto Freivogel
 */
public class Controller {
	private Model model;
	private View view;
	private JFrame frame;

	private Vector<Model> changes = new Vector<Model>(0, 1);
	private Vector<Model> undone_changes = new Vector<Model>(0, 1);

	private final JFileChooser jfcLaden = new JFileChooser();

	/**
	 * Erstellt einen neuen Controller. Später muss noch das View gesetzt
	 * werden.
	 * 
	 * @param model
	 *            Das Modell das der Controller verwalten soll.
	 * @param frame
	 *            Das Frame des GUI.
	 */
	public Controller(Model model, JFrame frame) {
		this.model = model;
		this.frame = frame;
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"TEXT FILES", "txt", "text");
		jfcLaden.setFileFilter(filter);
	}

	/**
	 * Setzt das View
	 * 
	 * @param view
	 *            Das neue View
	 */
	public void setView(View view) {
		this.view = view;
	}

	/**
	 * Setzt den Typ einer Dimensionierung im Modell, Diese Modifikation kann
	 * rückgängig gemacht werden.
	 * 
	 * @param typ
	 *            Der neue Typ.
	 * @param old_dim
	 *            Die Dimensionierung die verändert werden soll.
	 */
	public void setTyp(DimEnum typ, Dimensionierung old_dim) {
		modelChanged();

		Dimensionierung new_dim = old_dim.setTyp(typ, model.getRegelstrecke());

		model.replaceDim(old_dim, new_dim);
	}

	/**
	 * Setzt das Verhalten einer Chiens-Dimensionierung Diese Modifikation kann
	 * rückgängig gemacht werden.
	 * 
	 * @param verhalten
	 *            Das neue Verhalten.
	 * @param old_dim
	 *            Die Dimesnionierung die verändert werden soll.
	 */
	public void setVerhalten(ChiensEnum verhalten, Dimensionierung old_dim) {
		modelChanged();

		Dimensionierung new_dim = old_dim.setVerhalten(verhalten);

		model.replaceDim(old_dim, new_dim);
	}

	/**
	 * Setzt den Parameter Ks der Regelstrecke Diese Modifikation kann
	 * rückgängig gemacht werden.
	 * 
	 * @param ks
	 *            Der neue Wert für Ks
	 */
	public void setKs(double ks) {
		try {
			modelChanged();
			model.setRegelstrecke(model.getRegelstrecke().setKs(ks));
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Setzt den Parameter Tu der Regelstrecke Diese Modifikation kann
	 * rückgängig gemacht werden.
	 * 
	 * @param tu
	 *            Der neue Wert für Tu
	 */
	public void setTu(double tu) {
		try {
			modelChanged();
			model.setRegelstrecke(model.getRegelstrecke().setTu(tu));
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Setzt den Parameter Tg der Regelstrecke Diese Modifikation kann
	 * rückgängig gemacht werden.
	 * 
	 * @param tg
	 *            Der neue Wert für Tg
	 */
	public void setTg(double tg) {
		try {
			modelChanged();
			model.setRegelstrecke(model.getRegelstrecke().setTg(tg));
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Setzt den Phasenrand einer Phasengang - Dimensionierung Diese
	 * Modifikation kann rückgängig gemacht werden.
	 * 
	 * @param phasenrand
	 *            Der neue Phasenrand
	 * @param dim
	 *            Die Dimensionierung die verändert werden soll
	 */
	public void setPhasenrand(double phasenrand, Dimensionierung dim) {
		try {
			modelChanged();
			model.replaceDim(dim, dim.setPhasenrand(phasenrand));
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Setzt das Ueberschwingen einer iterativen Dimensionierung Diese
	 * Modifikation kann rückgängig gemacht werden.
	 * 
	 * @param ueberschwingen
	 *            Das neue Ueberschwingen
	 * @param dim
	 *            Die Dimensionierung die verändert werden soll
	 */
	public void setUeberschwingen(double ueberschwingen, Dimensionierung dim) {
		try {
			modelChanged();
			model.replaceDim(dim, dim.setUeberschwingen(ueberschwingen));
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Setzt den Parameter Kr einer manuellen Dimensionierung Diese Modifikation
	 * kann rückgängig gemacht werden.
	 * 
	 * @param kr
	 *            Der neue Wert für Kr
	 * @param dim
	 *            Die Dimensionierung die verändert werden soll
	 */
	public void setKr(double kr, Dimensionierung dim) {
		try {
			modelChanged();
			model.replaceDim(dim, dim.setKr(kr));
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Setzt den Parameter Tn einer manuellen Dimensionierung Diese Modifikation
	 * kann rückgängig gemacht werden.
	 * 
	 * @param tn
	 *            Der neue Wert für Tn
	 * @param dim
	 *            Die Dimensionierung die verändert werden soll
	 */
	public void setTn(double tn, Dimensionierung dim) {
		try {
			modelChanged();
			model.replaceDim(dim, dim.setTn(tn));
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Setzt den Parameter Tv einer manuellen Dimensionierung Diese Modifikation
	 * kann rückgängig gemacht werden.
	 * 
	 * @param tv
	 *            Der neue Wert für Tv
	 * @param dim
	 *            Die Dimensionierung die verändert werden soll
	 */
	public void setTv(double tv, Dimensionierung dim) {
		try {
			modelChanged();
			model.replaceDim(dim, dim.setTv(tv));
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Setzt den Parameter Tp einer manuellen Dimensionierung Diese Modifikation
	 * kann rückgängig gemacht werden.
	 * 
	 * @param tp
	 *            Der neue Wert für Tp
	 * @param dim
	 *            Die Dimensionierung die verändert werden soll
	 */
	public void setTp(double tp, Dimensionierung dim) {
		try {
			modelChanged();
			model.replaceDim(dim, dim.setTp(tp));
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Setzt die Topologie einer Dimensionierung Diese Modifikation kann
	 * rückgängig gemacht werden.
	 * 
	 * @param topo
	 *            Die neue Topologie
	 * @param dim
	 *            Die Dimensionierung die verändert werden soll
	 */
	public void selectTopo(TopoEnum topo, Dimensionierung dim) {
		try {
			modelChanged();
			model.replaceDim(dim, dim.setTopo(topo));
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Erstellt ein neues Model Diese Modifikation kann rückgängig gemacht
	 * werden.
	 */
	public void neu() {
		modelChanged();
		jfcLaden.setSelectedFile(null);
		RegelStrecke regelstrecke = new RegelStrecke(1.0, 1.71, 7.6);
		Dimensionierung[] dim = new Dimensionierung[] { new Dimensionierung(
				DimEnum.ZELLWEGER, TopoEnum.PID) };

		view.setModel(new Model(regelstrecke, dim));
	}

	/**
	 * Speichert das Model in einer vorher gewählten Datei, ist keine Datei
	 * gewählt öffnet es einen Auswahldialog.
	 */
	public void speichern() {
		File file = jfcLaden.getSelectedFile();

		if (file == null || !file.exists()) {
			speichernals();
			return;
		}

		try {
			FileOutputStream stream = new FileOutputStream(file);
			ObjectOutputStream ausgabe = new ObjectOutputStream(stream);

			ausgabe.writeObject(model);
			ausgabe.close();
			stream.close();
		} catch (IOException exc) {
			System.err.println("Dateifehler: " + exc.toString());
		}

	}

	/**
	 * Öffnet einen Dialog, um die Datei zu wählen in die gespeichert werden
	 * soll. Speichert das Model bei Bestätigung des Dialoges
	 */
	public void speichernals() {
		int returnVal = jfcLaden.showSaveDialog(frame);

		if (returnVal != JFileChooser.APPROVE_OPTION) {
			return;
		}

		File file = jfcLaden.getSelectedFile();
		if (file == null) {
			return;
		}

		try {
			FileOutputStream stream = new FileOutputStream(file);
			ObjectOutputStream ausgabe = new ObjectOutputStream(stream);

			ausgabe.writeObject(model);
			ausgabe.close();
			stream.close();
		} catch (IOException exc) {
			System.err.println("Dateifehler: " + exc.toString());
		}
	}

	/**
	 * Öffnet einen Dialog, um die Datei zu wählen die geladen werden soll. Lädt
	 * dann das Model bei Bestätigung des Dialoges Diese Modifikation kann
	 * rückgängig gemacht werden.
	 */
	public void oeffnen() {

		int returnVal = jfcLaden.showOpenDialog(frame);
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File file = jfcLaden.getSelectedFile();
		if (file == null || !file.exists()) {
			return;
		}

		try {
			FileInputStream stream = new FileInputStream(file);
			ObjectInputStream ausgabe = new ObjectInputStream(stream);

			Model temp_model = (Model) ausgabe.readObject();
			modelChanged();
			model = temp_model;
			view.setModel(model);
			ausgabe.close();
			stream.close();
		} catch (IOException exc) {
			view.displayError("Datei nicht gefunden.");
		} catch (ClassNotFoundException e) {
			view.displayError("Datei hat nicht das korrekte Format");
		}

	}

	private void modelChanged() {
		undone_changes.clear();
		changes.addElement((Model) model.copy());
	}

	/**
	 * Schliesst das Fenster
	 */
	public void beenden() {
		frame.dispose();
	}

	/**
	 * Macht die letzte Modifikation rückgängig
	 */
	public void rueckgaengig() {
		if (changes.isEmpty()) {
			return;
		}
		Model recall = changes.lastElement();
		undone_changes.addElement(model);
		changes.removeElement(recall);
		model = recall;
		view.setModel(recall);
	}

	/**
	 * Wiederholt die letzte Modifikation
	 */
	public void wiederholen() {
		if (undone_changes.isEmpty())
			return;
		Model recall = undone_changes.lastElement();
		changes.addElement(model);
		undone_changes.removeElement(recall);
		model = recall;
		view.setModel(model);
	}

	/**
	 * Entfernt eine Dimensionierung Diese Modifikation kann rückgängig gemacht
	 * werden.
	 * 
	 * @param dim
	 *            Die Dimensionierung die entfernt werden soll
	 */
	public void closeRegler(Dimensionierung dim) {
		modelChanged();
		try {
			model.removeDim(dim);
		} catch (IllegalStateException e) {
			changes.remove(changes.lastElement());
		}
	}

	/**
	 * Erstellt eine neue Dimensionierung indem es eine andere kopiert Diese
	 * Modifikation kann rückgängig gemacht werden.
	 * 
	 * @param kreis
	 *            Die Dimensionierung die kopiert werden soll.
	 */
	public void copyKreis(RegelKreis kreis) {
		modelChanged();
		model.copyDim(kreis.getDim());
	}
}
