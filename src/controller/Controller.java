package controller;

import model.ChiensDim;
import model.Dimensionierung;
import model.ManuellDim;
import model.Model;
import model.OppeltDim;
import model.RegelKreis;
import model.RosenbergDim;
import model.ZieglerDim;
import view.View;


public class Controller {
	private Model model;
	private View view;

	public Controller(Model model) {
		this.model = model;
	}

	public void setView(View view) {
		this.view = view;
	}
	
	public void selectDim(Dimensionierung dim){
		RegelKreis kreis = model.getRegelkreis();
		
		switch(dim){
			case MANUELL:
				kreis.setDim(new ManuellDim(kreis.getRegler()));
				break;
			case PHASENGANG:
				view.setStatus("Phasengangmethode nicht unterstützt");
				break;
			case ZIEGLER:
				kreis.setDim(new ZieglerDim());				
				break;
			case CHIEN:
				kreis.setDim(new ChiensDim(ChiensDim.APERIODFUEHR));
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
	
	public void setKs(double ks){
		try{
			model.getRegelkreis().getRegelstrecke().setKs(ks);
		}catch (Exception e){
			view.setStatus(e.getMessage());
		}
	}
	
	public void setTu(double tu){
		try{
			model.getRegelkreis().getRegelstrecke().setTu(tu);
		}catch (Exception e){
			view.setStatus(e.getMessage());
		}
	}
	
	public void setTg(double tg){
		try{
			model.getRegelkreis().getRegelstrecke().setTg(tg);
		}catch (Exception e){
			view.setStatus(e.getMessage());
		}
	}
}
