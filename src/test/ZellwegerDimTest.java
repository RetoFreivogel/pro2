package test;

import static org.junit.Assert.*;
import model.Polynom;
import model.TransferFunction;
import model.ZellwegerDim;

import org.junit.Test;

public class ZellwegerDimTest {

	@Test
	public void testSearchPhase() {
		Polynom z = new Polynom(new double[]{1});
		Polynom n = new Polynom(new double[]{1, 1, 1});
		TransferFunction tf = new TransferFunction(z, n);
		
		assertEquals(-Math.PI/2, tf.phaseAt(1), 0.001);

		ZellwegerDim dim = new ZellwegerDim(0);
		
		assertEquals(1, dim.searchPhase(tf, -Math.PI/2), 0.001);
	}

	
	@Test
	public void testPID(){
		
	}
}
