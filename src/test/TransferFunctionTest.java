package test;

import static org.junit.Assert.*;

import java.util.Random;

import model.Polynom;
import model.TransferFunction;

import org.apache.commons.math3.complex.Complex;
import org.junit.Ignore;
import org.junit.Test;

public class TransferFunctionTest {

	@Test
	public void testPhaseAt() {
		Polynom z = new Polynom(new double[] { 1 });
		Polynom n = new Polynom(new double[] { 1, 1 });
		TransferFunction tf = new TransferFunction(z, n);

		assertEquals(0, tf.phaseAt(0), 0.001);
		assertEquals(-Math.PI / 4, tf.phaseAt(1), 0.001);
		assertEquals(-Math.PI / 2, tf.phaseAt(10000), 0.001);
	}

	@Test
	public void testAmplitudeAt() {
		Polynom z = new Polynom(new double[] { 1 });
		Polynom n = new Polynom(new double[] { 1, 1 });
		TransferFunction tf = new TransferFunction(z, n);

		assertEquals(1, tf.amplitudeAt(0.001), 0.001);
		assertEquals(Math.sqrt(0.5), tf.amplitudeAt(1), 0.001);
		assertEquals(0, tf.amplitudeAt(1000), 0.001);
	}

	@Ignore
	@Test
	public void testFeedBackLoop() {
		/*
		 * überprüft wie viele komplexe nullstullen beim anwenden von
		 * feedbackloop entstehen wenn es auf UF ohne komplexe nullstellen
		 * angewendet wird;
		 */
		Random rand = new Random();

		int nenner_ordnung = rand.nextInt(32) + 1;
		int zaehler_ordnung = rand.nextInt(nenner_ordnung) + 1;

		double[] n_roots = new double[nenner_ordnung];
		double[] z_roots = new double[zaehler_ordnung];
		for (int j = 0; j < n_roots.length; j++) {
			n_roots[j] = -rand.nextDouble();
		}
		for (int j = 0; j < z_roots.length; j++) {
			z_roots[j] = -rand.nextDouble();
		}

		Polynom nenner = Polynom.fromRoots(n_roots);
		Polynom zaehler = Polynom.fromRoots(n_roots);

		TransferFunction tf = new TransferFunction(zaehler, nenner);
		TransferFunction tf2 = tf.feedback_loop();
		
		Complex[] ordnung2 = tf2.getNenner().getRoots();
		
		int cnt = 0;
		for(Complex c : ordnung2){
			if(Math.abs(c.getImaginary()) > 0.001){
				cnt++;
			}
		}
		System.out.println(cnt);
	}

}
