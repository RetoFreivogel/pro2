package test;

import static org.junit.Assert.*;

import model.Polynom;
import model.TransferFunction;

import org.junit.Test;

public class TransferFunctionTest {

	@Test
	public void testPhaseAt() {
		Polynom z = new Polynom(new double[]{1});
		Polynom n = new Polynom(new double[]{1, 1});
		TransferFunction tf = new TransferFunction(z, n);
		
		assertEquals(-Math.PI/4, tf.phaseAt(1), 0.001);
	}

	@Test
	public void testAmplitudeAt() {
		Polynom z = new Polynom(new double[]{1});
		Polynom n = new Polynom(new double[]{1, 1});
		TransferFunction tf = new TransferFunction(z, n);
		
		assertEquals(1, tf.amplitudeAt(0.001), 0.001);
		assertEquals(0.5, tf.amplitudeAt(1), 0.001);
	}

}
