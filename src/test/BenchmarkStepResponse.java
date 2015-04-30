package test;

import static org.junit.Assert.*;
import model.OppeltDim;
import model.RegelKreis;
import model.RegelStrecke;

import org.junit.Test;

import util.Matlab;

public class BenchmarkStepResponse {

	@Test
	public void test() {
		OppeltDim dim = new OppeltDim();
		RegelStrecke rs = new RegelStrecke(1.0, 1.5, 7.6);
		RegelKreis regelkreis = new RegelKreis(dim, rs);
				
		Matlab.setMocked(true);
		
		long start = 0;
		long end = 0;
		long total = 0;
		
		for(int i = 0; i < 1000; i++){
			
			start = System.nanoTime();
			rs.setTu(rs.getTu() + 0.001);
			regelkreis.getTranferFunction().schrittantwort();
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
