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

public class Controller {
	private Model model;
	private View view;
	private JFrame frame;

	private Vector<Model> changes = new Vector<Model>(0, 1);
	private Vector<Model> undone_changes = new Vector<Model>(0, 1);

	private final JFileChooser jfcLaden = new JFileChooser();

	public Controller(Model model, JFrame frame) {
		this.model = model;
		this.frame = frame;
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"TEXT FILES", "txt", "text");
		jfcLaden.setFileFilter(filter);
	}

	public void setView(View view) {
		this.view = view;
	}

	public void setTyp(DimEnum typ, Dimensionierung old_dim) {
		modelChanged();
		
		Dimensionierung new_dim = old_dim.setTyp(typ, model.getRegelstrecke());
		
		model.replaceDim(old_dim, new_dim);
	}

	public void setVerhalten(ChiensEnum verhalten, Dimensionierung old_dim) {
		modelChanged();

		Dimensionierung new_dim = old_dim.setVerhalten(verhalten);
		
		model.replaceDim(old_dim, new_dim);
	}

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

	public void neu() {
		jfcLaden.setSelectedFile(null);
		RegelStrecke regelstrecke = new RegelStrecke(1.0, 1.71, 7.6);
		Dimensionierung[] dim = new Dimensionierung[]{new Dimensionierung(DimEnum.ZELLWEGER, TopoEnum.PID)};

		view.setModel(new Model(regelstrecke, dim));
	}

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

	public void oeffnen() {

		int returnVal = jfcLaden.showOpenDialog(frame);
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			return;
		}
		File file = jfcLaden.getSelectedFile();
		if(file == null || !file.exists()){
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
			System.err.println("Dateifehler: " + exc.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void modelChanged() {
		undone_changes.clear();
		changes.addElement((Model) model.copy());
	}

	public void beenden() {
		frame.dispose();
	}

	public void rueckgaengig() {
		if (changes.isEmpty()){
			return;
		}
		Model recall = changes.lastElement();
		undone_changes.addElement(model);
		changes.removeElement(recall);
		model = recall;
		view.setModel(recall);
	}

	public void wiederholen() {
		if (undone_changes.isEmpty())
			return;
		Model recall = undone_changes.lastElement();
		changes.addElement(model);
		undone_changes.removeElement(recall);
		model = recall;
		view.setModel(model);
	}

	public void closeRegler(Dimensionierung dim) {
		modelChanged();
		try {
			model.removeDim(dim);
		} catch (IllegalStateException e) {
			changes.remove(changes.lastElement());
		}
	}

	public void copyKreis(RegelKreis kreis) {
		modelChanged();
		model.copyDim(kreis.getDim());
	}
}
