package test;

import static org.junit.Assert.*;
import main.OppeltDim;
import main.RegelKreis;
import main.RegelStrecke;

import org.junit.Ignore;
import org.junit.Test;

import util.Matlab;

public class BenchmarkStepResponse {

	@Ignore
	@Test
	public void test() {
		OppeltDim dim = new OppeltDim();
		RegelStrecke rs = new RegelStrecke(1.0, 1.71, 7.6);
		RegelKreis regelkreis = new RegelKreis(dim, rs);
				
		Matlab.getProxy();
		
		long start = 0;
		long end = 0;
		
		for(int i = 0; i < 50; i++){
			
			start = System.nanoTime();
			rs.setKs(rs.getKs() + 0.01);
			regelkreis.getTranferFunction().schrittantwort();
			end = System.nanoTime();
			System.out.println((float)(end-start)/1000000);
		}

		Matlab.closeProxy();
		
		//typical values have been 120ms - 150ms
		assertTrue(200 > (end-start)/1000000);
	}

}
