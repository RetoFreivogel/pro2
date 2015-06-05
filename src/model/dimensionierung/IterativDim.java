package model.dimensionierung;

import model.RegelKreis;
import model.RegelStrecke;
import model.Regler;

class IterativDim extends AbstractDim {
	private static final long serialVersionUID = 1L;
	
	private final double ueberschwingen;
	
	IterativDim(double ueberschwingen, TopoEnum topo){
		super(topo);
		this.ueberschwingen = ueberschwingen;	
	}
	
	double getUeberschwingen(){
		return ueberschwingen;
	}
	
	IterativDim setUeberschwingen(double ueberschwingen){
		return new IterativDim(ueberschwingen, getTopo());
	}

	@Override
	public IterativDim copy() {
		return new IterativDim(ueberschwingen, getTopo());
	}

	@Override
	DimEnum getTyp() {
		return DimEnum.ITERATIV;
	}

	@Override
	IterativDim setTopo(TopoEnum topo) {
		return new IterativDim(ueberschwingen, topo);
	}

	@Override
	Regler calc(RegelStrecke regelstrecke) {
		double minPhasenrand = 0;
		double maxPhasenrand = 90;
		double phasenrand = 45;
		Dimensionierung dim = new Dimensionierung(DimEnum.ZELLWEGER, getTopo());

		for(int i = 0; i < 100; i++){
			phasenrand = (minPhasenrand + maxPhasenrand)/2;
			dim = dim.setPhasenrand(phasenrand);
			RegelKreis kreis = new RegelKreis(dim, regelstrecke, "");
			double ymax = kreis.getTranferFunction().schrittantwort().getYmax();
			double schwingen = (ymax-1)*100;
			
			if(schwingen <= ueberschwingen){
				maxPhasenrand = phasenrand;
			}else{
				minPhasenrand = phasenrand;
			}
		}
		return dim.setPhasenrand(phasenrand).calc(regelstrecke);
	}

}
