package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

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

	public int j;

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
			model.getRegelkreis().getRegelstrecke().setKs(ks);
			view.setStatus("Bereit");
		} catch (Exception e) {
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setTu(double tu) {
		try {
			model.getRegelkreis().getRegelstrecke().setTu(tu);
			view.setStatus("Bereit");
		} catch (Exception e) {
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setTg(double tg) {
		try {
			model.getRegelkreis().getRegelstrecke().setTg(tg);
			view.setStatus("Bereit");
		} catch (Exception e) {
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setKr(double kr) {
		try {
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
		String txt = "Ks: 1,Tu: 2.8,Tg: 19";
		if (file != null && file.exists() == true) {
			System.out.println("haaalt");
			String[] zeilen = txt.split("[,]+");
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
			String txt = "Ks: 1,Tu: 1.67,Tg: 6";
			if (file.exists() == true) {
				System.out.println("haaalt");
				String[] zeilen = txt.split("[,]+");
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
				String[] zeilen = txt.split("[,]+");
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
				 
					sc.skip("Ks: ");
					str[0] = Double.toString(sc.nextDouble());
					sc.nextLine();
					sc.skip("Tu: ");
					str[1] = Double.toString(sc.nextDouble());
					sc.nextLine();
					sc.skip("Tg: ");
					str[2] = Double.toString(sc.nextDouble());

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(str[0]);
			System.out.println(str[1]);
			System.out.println(str[2]);
		}	

	}
	
	public void beenden(){
		frame.dispose();
	}

	public void rueckgaengig() {

	}

	public void wiederholen() {

	}

	public void einstellung() {

	}

	public void simulation() {

	}
}
