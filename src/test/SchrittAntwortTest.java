package test;

import static org.junit.Assert.*;
import model.ManuellDim;
import model.OppeltDim;
import model.RegelKreis;
import model.RegelStrecke;
import model.ReglerTopologie;
import model.SchrittAntwort;

import org.junit.Test;

import util.Matlab;

public class SchrittAntwortTest {

	
	@Test
	public void testPID() {
		OppeltDim dim = new OppeltDim();
		RegelStrecke rs = new RegelStrecke(1.0, 1.71, 7.6);
		RegelKreis kreis = new RegelKreis(dim, rs);
		SchrittAntwort sw = kreis.getTranferFunction().schrittantwort();
		
		double[][] output = Matlab.calcStep(kreis);
		double[] y_ref = output[0];
		double[] t = output[1];
 		
		double[] y = new double[t.length];
		for (int i = 0; i < y.length; i++) {
			y[i] = sw.getY(t[i]);
		}
		assertArrayEquals(y_ref, y, 0.001);
	}

	
	@Test
	public void testP() {
		OppeltDim dim = new OppeltDim();
		RegelStrecke rs = new RegelStrecke(1.0, 1.71, 7.6);
		RegelKreis kreis = new RegelKreis(dim, rs);
		kreis.setTopo(ReglerTopologie.P);
		ManuellDim dim2 = new ManuellDim(kreis.getRegler());
		dim2.setKr(2);
		kreis.setDim(dim2);
		
		SchrittAntwort sw = kreis.getTranferFunction().schrittantwort();
		
		double[][] output = Matlab.calcStep(kreis);
		double[] y_ref = output[0];
		double[] t = output[1];
 		
		double[] y = new double[t.length];
		for (int i = 0; i < y.length; i++) {
			y[i] = sw.getY(t[i]);
		}
		
		assertArrayEquals(y_ref, y, 0.001);
	}
}
