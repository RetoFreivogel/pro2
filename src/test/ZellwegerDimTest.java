package test;

import static org.junit.Assert.*;
import model.Polynom;
import model.RegelStrecke;
import model.Regler;
import model.TransferFunction;
import model.ZellwegerDim;

import org.junit.Test;

public class ZellwegerDimTest {

	@Test
	public void testSearchPhase() {
		Polynom z = new Polynom(new double[]{1});
		Polynom n = new Polynom(new double[]{1, 1, 1});
		TransferFunction tf = new TransferFunction(z, n);
		ZellwegerDim dim = new ZellwegerDim(0);
		
		assertEquals(-Math.PI/2, tf.phaseAt(1), 0.001);
		assertEquals(1, dim.searchPhase(tf, -Math.PI/2), 0.001);
	}

	
	@Test
	public void testPID(){
		RegelStrecke rs = new RegelStrecke(1.0, 1.71, 7.6);
		ZellwegerDim dim = new ZellwegerDim(Math.PI/4);

		Regler r = dim.calc(rs, null);
		
		assertEquals(3.9447, r.getTn(), 0.04);
		assertEquals(0.8556, r.getTv(), 0.01);
		assertEquals(0.1598, r.getTp(), 0.002);
		assertEquals(2.3034, r.getKr(), 0.02);
	}
}
