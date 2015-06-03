package test;

import static org.junit.Assert.*;
import model.Polynom;

import org.apache.commons.math3.complex.Complex;
import org.junit.Test;

public class PolynomTest {

	@Test
	public void testRoots() {
		double[] roots_ref = new double[]{1, 2, 3};
		
		Polynom polynom = Polynom.fromRoots(roots_ref);
		
		Complex[] c_roots = polynom.getRoots();
		
		double[] roots = new double[c_roots.length];
		for (int i = 0; i < c_roots.length; i++) {
			roots[i] = c_roots[i].getReal();
		}
		
		assertArrayEquals(roots_ref, roots, 0.000001);
	}
	
	@Test
	public void testRoots2() {
		double[] roots_ref = new double[]{3, 5, 7, 11, 13};
		
		Polynom polynom = Polynom.fromRoots(roots_ref);
		
		Complex[] c_roots = polynom.getRoots();
		
		double[] roots = new double[c_roots.length];
		for (int i = 0; i < c_roots.length; i++) {
			roots[i] = c_roots[i].getReal();
		}
		
		assertArrayEquals(roots_ref, roots, 0.000001);
	}
	
	@Test
	public void testRoots3() {

		Polynom polynom_ref = new Polynom(new double[]{
				0.9999999999999998,
				8.170184572071912,
				29.029197022814632,
				58.58523380695153,
				73.45244289618864,
				58.58523380695152,
				29.029197022814625,
				8.17018457207191,
				0.9999999999999998});
		
		Complex[] c_roots = polynom_ref.getRoots();
		double[] roots = new double[c_roots.length];
		for (int i = 0; i < roots.length; i++) {
			roots[i] = c_roots[i].getReal();
		}
		Polynom polynom = Polynom.fromRoots(roots);
		
		assertArrayEquals(polynom_ref.getCoeff(), polynom.getCoeff(), 0.01);
		
	}
}
