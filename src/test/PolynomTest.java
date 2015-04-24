package test;

import static org.junit.Assert.*;
import main.Polynom;

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

}
