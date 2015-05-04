package test;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import model.Polynom;
import model.TransferFunction;

import org.junit.Test;

public class TransferFunctionTest {

	@Test
	public void testPhaseAt() {
		Polynom z = new Polynom(new double[]{1});
		Polynom n = new Polynom(new double[]{1, 1});
		TransferFunction tf = new TransferFunction(z, n);
		double data[] = new double[128];
		
		for (int i = 0; i < data.length; i++) {
			data[i] = tf.phaseAt(0.0001 * (double)i);
		}
		
		JFrame frame = util.Chart.makeFrame(data, 1.28);
		frame.setVisible(true);
		frame.setSize(800, 600);
		//assertEquals(-Math.PI/2, tf.phaseAt(), 0.001);
		while(true);
	}

	@Test
	public void testAmplitudeAt() {
		Polynom z = new Polynom(new double[]{1});
		Polynom n = new Polynom(new double[]{1, 1});
		TransferFunction tf = new TransferFunction(z, n);
		
		assertEquals(1, tf.amplitudeAt(0.001), 0.001);
	}

}
