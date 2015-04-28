package test;

import static org.junit.Assert.*;
import main.OppeltDim;
import main.RegelKreis;
import main.RegelStrecke;

import org.junit.Test;

import util.Matlab;

public class BenchmarkStepResponse {

	@Test
	public void test() {
		OppeltDim dim = new OppeltDim();
		RegelStrecke rs = new RegelStrecke(1.0, 1.71, 7.6);
		RegelKreis regelkreis = new RegelKreis(dim, rs);
				
		Matlab.setMocked(true);
		
		long start = 0;
		long end = 0;
		long total = 0;
		
		for(int i = 0; i < 100; i++){
			
			start = System.nanoTime();
			rs.setKs(rs.getKs() + 0.01);
			regelkreis.getTranferFunction().schrittantwort();
			end = System.nanoTime();
			
			total += (end - start);
			
		}
		Matlab.setMocked(false);
		
		double mean_ms = (double)total/100000000;
		
		//typical values have been:
		//120ms - 150ms for Matlab
		//1.7ms - 2.0ms for Mocked Residue
		assertTrue(100 > mean_ms);
	}

}
