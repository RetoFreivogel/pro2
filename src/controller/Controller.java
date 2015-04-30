package controller;

import model.ChiensDim;
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
			e.printStackTrace();
		}
	}
	
	public void setTu(double tu){
		try{
			model.getRegelkreis().getRegelstrecke().setTu(tu);
		}catch (Exception e){
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setTg(double tg){
		try{
			model.getRegelkreis().getRegelstrecke().setTg(tg);
		}catch (Exception e){
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}

	public void setKr(double kr) {
		try{
			ManuellDim dim = (ManuellDim)model.getRegelkreis().getDim();
			dim.setKr(kr);
		}catch (Exception e){
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setTn(double tn) {
		try{
			ManuellDim dim = (ManuellDim)model.getRegelkreis().getDim();
			dim.setTn(tn);
		}catch (Exception e){
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setTv(double tv) {
		try{
			ManuellDim dim = (ManuellDim)model.getRegelkreis().getDim();
			dim.setTv(tv);
		}catch (Exception e){
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void setTp(double tp) {
		try{
			ManuellDim dim = (ManuellDim)model.getRegelkreis().getDim();
			dim.setTp(tp);
		}catch (Exception e){
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}

	public void selectTopo(ReglerTopologie topo) {
		try{
			RegelKreis kreis = model.getRegelkreis();
			kreis.setTopo(topo);
		}catch (Exception e){
			view.setStatus(e.getMessage());
			e.printStackTrace();
		}
	}
}
