package controller;

import org.apache.commons.lang3.SerializationUtils;

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

import model.AbstractDim;
import model.ChiensDim;
import model.ChiensRegelung;
import model.Dimensionierung;
import model.ManuellDim;
import model.Model;
import model.OppeltDim;
import model.ReglerTopologie;
import model.RosenbergDim;
import model.ZellwegerDim;
import model.ZieglerDim;
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

	public void selectDim(Dimensionierung dim, AbstractDim old_dim) {
		modelChanged();
		
		AbstractDim new_dim = null;
		switch (dim) {
		case MANUELL:
			new_dim =  new ManuellDim(old_dim.calc(model.getRegelstrecke()));
			break;
		case PHASENGANG:
			new_dim = new ZellwegerDim(45, old_dim.getTopo());
			break;
		case ZIEGLER:
			new_dim = new ZieglerDim(old_dim.getTopo());
			break;
		case CHIENS:
			new_dim = new ChiensDim(ChiensDim.APERIODSTOER, old_dim.getTopo());
			break;
		case OPPELT:
			new_dim = new OppeltDim(old_dim.getTopo());
			break;
		case ROSENBERG:
			new_dim = new RosenbergDim(old_dim.getTopo());
			break;
		}
		model.replaceDim(old_dim, new_dim);
	}

	public void selectChiensRegelung(ChiensRegelung chiensReg, ChiensDim dim) {
		int j = 0;
		modelChanged();

		switch (chiensReg) {
		case APERIODSTOER:
			j = 0;
			break;
		case APERIODFUEHR:
			j = 1;
			break;
		case ZWANZIGSTOER:
			j = 2;
			break;
		case ZWANZIGFUEHR:
			j = 3;
			break;
		}	
		model.replaceDim(dim, dim.setJ(j));
		return;
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

	public void setKr(double kr, AbstractDim dim) {
		try {
			modelChanged();
			ManuellDim mdim = (ManuellDim) dim;
			model.replaceDim(dim, mdim.setKr(kr));
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setPhasenrand(double phasenrand, AbstractDim dim) {
		try {
			modelChanged();
			ZellwegerDim zdim = (ZellwegerDim) dim;
			model.replaceDim(dim, zdim.setPhasenrand(phasenrand));
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setTn(double tn, AbstractDim dim) {
		try {
			modelChanged();
			ManuellDim mdim = (ManuellDim) dim;
			model.replaceDim(dim, mdim.setTn(tn));
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setTv(double tv, AbstractDim dim) {
		try {
			modelChanged();
			ManuellDim mdim = (ManuellDim) dim;
			model.replaceDim(dim, mdim.setTv(tv));
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setTp(double tp, AbstractDim dim) {
		try {
			modelChanged();
			ManuellDim mdim = (ManuellDim) dim;
			model.replaceDim(dim, mdim.setTp(tp));
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void selectTopo(ReglerTopologie topo, AbstractDim dim) {
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
		view.setModel(new Model());
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
		Model copy = SerializationUtils.roundtrip(model);
		changes.addElement(copy);
	}

	public void beenden() {
		frame.dispose();
	}

	public void rueckgaengig() {
		if (changes.isEmpty())
			return;
		Model recall = changes.lastElement();
		changes.removeElement(recall);
		undone_changes.addElement(model);
		model = recall;
		view.setModel(model);
	}

	public void wiederholen() {
		if (undone_changes.isEmpty())
			return;
		Model recall = undone_changes.lastElement();
		undone_changes.removeElement(recall);
		changes.addElement(model);
		model = recall;
		view.setModel(model);
	}

	public void einstellung() {

	}

	public void simulation() {

	}

	public void closeRegler(AbstractDim dim) {
		modelChanged();
		try {
			model.removeDim(dim);
		} catch (IllegalStateException e) {
			changes.remove(changes.lastElement());
		}
	}

	public void newKreis() {
		modelChanged();
		model.newDim();
	}
}
