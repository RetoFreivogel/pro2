package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.ChiensDim;
import model.ChiensRegelung;
import model.Dimensionierung;
import model.ManuellDim;
import model.Model;
import model.OppeltDim;
import model.RegelKreis;
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

	private final JFileChooser jfcLaden = new JFileChooser(new File(".//"));
	private File file;
	private File theDirectory = new File(".//");

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

	public void selectDim(Dimensionierung dim) {
		RegelKreis kreis = model.getRegelkreis();
		modelChanged();

		switch (dim) {
		case MANUELL:
			kreis.setDim(new ManuellDim(kreis.getRegler()));
			break;
		case PHASENGANG:
			kreis.setDim(new ZellwegerDim(Math.PI/4));
			break;
		case ZIEGLER:
			kreis.setDim(new ZieglerDim());
			break;
		case CHIENS:
			kreis.setDim(new ChiensDim(ChiensDim.APERIODSTOER));
			break;
		case OPPELT:
			kreis.setDim(new OppeltDim());
			break;
		case ROSENBERG:
			kreis.setDim(new RosenbergDim());
			break;
		}
	}

	public void selectChiensRegelung(ChiensRegelung chiensReg) {
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
		ChiensDim dim = (ChiensDim) model.getRegelkreis().getDim();
		dim.setJ(j);
		return;
	}

	public void setKs(double ks) {
		try {
			modelChanged();
			model.getRegelkreis().getRegelstrecke().setKs(ks);
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setTu(double tu) {
		try {
			modelChanged();
			model.getRegelkreis().getRegelstrecke().setTu(tu);
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setTg(double tg) {
		try {
			modelChanged();
			model.getRegelkreis().getRegelstrecke().setTg(tg);
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setKr(double kr) {
		try {
			modelChanged();
			ManuellDim dim = (ManuellDim) model.getRegelkreis().getDim();
			dim.setKr(kr);
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setPhasenrand(double phasenrand) {
		try {
			modelChanged();
			ZellwegerDim dim = (ZellwegerDim) model.getRegelkreis().getDim();
			dim.setPhasenrand(phasenrand);
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setTn(double tn) {
		try {
			modelChanged();
			ManuellDim dim = (ManuellDim) model.getRegelkreis().getDim();
			dim.setTn(tn);
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setTv(double tv) {
		try {
			modelChanged();
			ManuellDim dim = (ManuellDim) model.getRegelkreis().getDim();
			dim.setTv(tv);
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setTp(double tp) {
		try {
			modelChanged();
			ManuellDim dim = (ManuellDim) model.getRegelkreis().getDim();
			dim.setTp(tp);
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void selectTopo(ReglerTopologie topo) {
		try {
			modelChanged();
			RegelKreis kreis = model.getRegelkreis();
			kreis.setTopo(topo);
			view.clearError();
		} catch (Exception e) {
			view.displayError(e.getMessage());
			e.printStackTrace();
		}
	}

	public void neu() {
		jfcLaden.setSelectedFile(null);
		jfcLaden.setCurrentDirectory(theDirectory);
		view.setModel(new Model());
		
	}

	public void speichern() {
		System.out.println(model);
		file = jfcLaden.getSelectedFile();
		String txt = model.toString();
		
		if (file != null && file.exists() == true) {
			String[] zeilen = txt.split("[\n]+");
			try {
				PrintWriter ausgabeDatei = new PrintWriter(new FileWriter(file,
						false));
				for (int i = 0; i < zeilen.length; i++) {
					ausgabeDatei.println(zeilen[i]);
				}

				ausgabeDatei.close();
			} catch (IOException exc) {
				System.err.println("Dateifehler: " + exc.toString());

			}
		} else {
			speichernals();
		}
	}

	public void speichernals() {
		int returnVal = jfcLaden.showSaveDialog(frame);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = jfcLaden.getSelectedFile();
			String txt = model.toString();

			if (file.exists() == true) {
				System.out.println(txt);
				String[] zeilen = txt.split("[\n]+");
				try {
					PrintWriter ausgabeDatei = new PrintWriter(new FileWriter(
							file, false));
					for (int i = 0; i < zeilen.length; i++) {
						ausgabeDatei.println(zeilen[i]);
					}

					ausgabeDatei.close();
				} catch (IOException exc) {
					System.err.println("Dateifehler: " + exc.toString());

				}
				
			} else {
				String[] zeilen = txt.split("[\n]+");
				try {
					PrintWriter ausgabeDatei = new PrintWriter(new FileWriter(
							file, false));
					for (int i = 0; i < zeilen.length; i++) {
						ausgabeDatei.println(zeilen[i]);

					}

					ausgabeDatei.close();
				} catch (IOException exc) {
					System.err.println("Dateifehler: " + exc.toString());

				}
			}

		}

	}

	public void oeffnen() {

		int returnVal = jfcLaden.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			file = jfcLaden.getSelectedFile();
			System.out.println(file.getAbsolutePath());

			Scanner sc;

			try {
				sc = new Scanner(file);
				view.setModel(new Model(sc));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException | NoSuchElementException e) {
				JOptionPane.showMessageDialog(frame,
						"Datei konnte nicht gelesen werden");
			}
		}

	}

	private void modelChanged() {
		undone_changes.clear();
		changes.addElement(new Model(model));
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

	public String setString(String[] str) {

		return null;
	}
}
