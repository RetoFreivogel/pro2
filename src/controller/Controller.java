package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;


import model.ChiensDim;
import model.ChiensRegelung;
import model.Dimensionierung;
import model.ManuellDim;
import model.Model;
import model.OppeltDim;
import model.RegelKreis;
import model.ReglerTopologie;
import model.RosenbergDim;
import model.ZieglerDim;
import view.View;

public class Controller {
	private Model model;
	private View view;
	private JFrame frame;
	
	private Vector<Model> changes = new Vector<Model>(0, 1);
	private Vector<Model> undone_changes = new Vector<Model>(0, 1);

	private final JFileChooser jfcLaden = new JFileChooser(new File(".//"));
	
	public Controller(Model model, JFrame frame) {
		this.model = model;
		this.frame = frame;
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
			view.setStatus("Phasengangmethode nicht unterstützt");
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
		default:
			view.setStatus("Interner Fehler");
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
		ChiensDim dim = (ChiensDim)model.getRegelkreis().getDim();
		dim.setJ(j);
		return;
	}

	public void setKs(double ks) {
		try {
			modelChanged();
			model.getRegelkreis().getRegelstrecke().setKs(ks);
			view.setStatus("Bereit");
		} catch (Exception e) {
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setTu(double tu) {
		try {
			modelChanged();
			model.getRegelkreis().getRegelstrecke().setTu(tu);
			view.setStatus("Bereit");
		} catch (Exception e) {
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setTg(double tg) {
		try {
			modelChanged();
			model.getRegelkreis().getRegelstrecke().setTg(tg);
			view.setStatus("Bereit");
		} catch (Exception e) {
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setKr(double kr) {
		try {
			modelChanged();
			ManuellDim dim = (ManuellDim) model.getRegelkreis().getDim();
			dim.setKr(kr);
			view.setStatus("Bereit");
		} catch (Exception e) {
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setTn(double tn) {
		try {
			modelChanged();
			ManuellDim dim = (ManuellDim) model.getRegelkreis().getDim();
			dim.setTn(tn);
			view.setStatus("Bereit");
		} catch (Exception e) {
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setTv(double tv) {
		try {
			modelChanged();
			ManuellDim dim = (ManuellDim) model.getRegelkreis().getDim();
			dim.setTv(tv);
			view.setStatus("Bereit");
		} catch (Exception e) {
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setTp(double tp) {
		try {
			modelChanged();
			ManuellDim dim = (ManuellDim) model.getRegelkreis().getDim();
			dim.setTp(tp);
			view.setStatus("Bereit");
		} catch (Exception e) {
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}

	public void selectTopo(ReglerTopologie topo) {
		try {
			modelChanged();
			RegelKreis kreis = model.getRegelkreis();
			kreis.setTopo(topo);
			view.setStatus("Bereit");
	} catch (Exception e) {
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}

	public void neu() {

	}

	public void speichern(){
		System.out.println(model);
		File file = jfcLaden.getSelectedFile();
		//Hier sollte der String vom Model sein
		String txt = model.toString();
		if (file != null && file.exists() == true) {		
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
		}else
		{
			speichernals();
		}
	}
	public void speichernals(){
		int returnVal = jfcLaden.showSaveDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = jfcLaden.getSelectedFile();
			//Hier sollte der String vom Model sein
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
	
	public void oeffnen(){
		
		int returnVal = jfcLaden.showOpenDialog(frame);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = jfcLaden.getSelectedFile();
			System.out.println(file.getAbsolutePath());
			
			Scanner sc;

			String [] str = new String [3];;
			try {
				sc = new Scanner(file);		
				view.setModel(new Model(sc));
//				sc.skip("Ks: ");
//					str[0] = Double.toString(sc.nextDouble());
//					sc.nextLine();
//					sc.skip("Tu: ");
//					str[1] = Double.toString(sc.nextDouble());
//					sc.nextLine();
//					sc.skip("Tg: ");
//					str[2] = Double.toString(sc.nextDouble());
//					String string = "Öffnen"+"\n"+str[0]+"\n"+ str[1]+"\n"+str[2]+"\n"+"Fertig";
//					str[3] = Double.toString(sc.nextDouble());
//					str[4] = Double.toString(sc.nextDouble());
//					str[5] = Double.toString(sc.nextDouble());
//					str[6] = Double.toString(sc.nextDouble());
//					str[7] = Double.toString(sc.nextDouble());
					
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	

	}
	
	private void modelChanged(){
		undone_changes.clear();
		changes.addElement(new Model(model));
	}
		
	public void beenden(){
		frame.dispose();
	}

	public void rueckgaengig() {
		if(changes.isEmpty())return;
		Model recall = changes.lastElement();
		changes.removeElement(recall);
		undone_changes.addElement(model);
		model = recall;
		view.setModel(model);
	}

	public void wiederholen() {
		if(undone_changes.isEmpty())return;
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
	public String setString(String[] str){
		
		
		return null;
	}
}
