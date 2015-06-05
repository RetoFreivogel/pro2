package test;

import static org.junit.Assert.*;
import model.RegelKreis;
import model.RegelStrecke;
import model.SchrittAntwort;
import model.dimensionierung.DimEnum;
import model.dimensionierung.Dimensionierung;
import model.dimensionierung.TopoEnum;

import org.junit.Test;

import util.Matlab;

public class BenchmarkStepResponse {

	@Test
	public void test() {
		Dimensionierung dim = new Dimensionierung(DimEnum.OPPELT, TopoEnum.PID);
		RegelStrecke rs = new RegelStrecke(1.0, 1.5, 7.6);
		RegelKreis regelkreis = new RegelKreis(dim, rs, "");
				
		Matlab.setMocked(true);
		
		long start = 0;
		long end = 0;
		long total = 0;
		
		for(int i = 0; i < 1000; i++){
			
			start = System.nanoTime();
			rs.setTu(rs.getTu() + 0.001);
			SchrittAntwort sa = regelkreis.getTranferFunction().schrittantwort();
			double taus = sa.getTaus();
			for (int j = 0; j < 1000; j++) {
				sa.getY(taus * j / 999);
			}
			end = System.nanoTime();
			total += (end - start);
			
		}
		Matlab.setMocked(false);
		
		double mean_ms = (double)total/1000000000;
		System.out.println("Zeit Schrittantwort: " + mean_ms);

		
		//typical values have been:
		//120ms - 150ms for Matlab
		//1.7ms - 2.0ms for Mocked Residue
		assertTrue(10 > mean_ms);
	}

}
