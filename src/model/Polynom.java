package model;

import java.util.Arrays;

import org.apache.commons.math3.analysis.solvers.LaguerreSolver;
import org.apache.commons.math3.complex.Complex;

/**
 * Abstraktion für ein Polynom mit reellen Coeffizienten. Ist Immutable
 * 
 * @author Reto
 *
 */
public final class Polynom {
	private final double[] coeff;
	private Complex[] roots;

	/**
	 * Erstellt ein Polynom mit den angegebenen Koeffizienten
	 * 
	 * @param coeff
	 *            Die Koeffizienten des Polynoms nach aufsteigendem Exponent.
	 */
	public Polynom(double[] coeff) {
		int ordnung = coeff.length - 1;

		// Remove trailing zeros
		while (ordnung > 0 && coeff[ordnung] == 0.0) {
			ordnung--;
		}

		this.coeff = Arrays.copyOf(coeff, ordnung + 1);
	}

	/**
	 * Erstellt ein Polynom aus den gegebenen reellen Wurzeln
	 * 
	 * @param roots
	 *            Die Wurzeln eines Polynoms
	 * @return Ein neues Polynom
	 */
	public static Polynom fromRoots(double[] roots) {
		int n = roots.length;
		double[] coeff = new double[n + 1];
		coeff[n] = 1.0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				coeff[j] -= coeff[j + 1] * roots[i];
			}
		}
		return new Polynom(coeff);
	}

	/**
	 * Gibt die Koeffizienten nach aufsteigendem Exponent zurück
	 * 
	 * @return die Koeffizienten dieses Polynoms
	 */
	public double[] getCoeff() {
		return Arrays.copyOf(coeff, coeff.length);
	}

	private Complex[] calcRoots() {
		if (coeff.length == 1)
			return new Complex[] {};

		double[] c = Arrays.copyOf(coeff, coeff.length);
		int ordnung = c.length - 1;
		double faktor = Math.pow(c[ordnung], 1.0 / ordnung);
		for (int i = 0; i < c.length; i++) {
			c[i] /= Math.pow(faktor, i);
		}

		LaguerreSolver solver = new LaguerreSolver();
		Complex[] roots = solver.solveAllComplex(c, 0.0, 1000);

		for (int i = 0; i < roots.length; i++) {
			roots[i] = roots[i].divide(faktor);
		}

		return roots;
	}

	/**
	 * Berechnet und gibt die Wurzeln des Polynoms zurück
	 * 
	 * @return Die ermittelten Wurzeln
	 */
	public Complex[] getRoots() {
		// Zwischenspeicher zur Optimierung

		if (roots == null) {
			roots = calcRoots();
		}
		return Arrays.copyOf(roots, roots.length);
	}

	/**
	 * Evaluiert das Polynom mit der gegebenen komplexen zahl x.
	 * 
	 * @param x
	 *            Eine komplexe zahl
	 * @return Eine komplexe Zahl
	 */
	public Complex eval(Complex x) {
		Complex result = new Complex(0, 0);

		for (int i = 0; i < coeff.length; i++) {
			result = result.add(x.pow(i).multiply(coeff[i]));
		}

		return result;
	}

	/**
	 * Berechnet die Residueen des Terms 1 / (Dieses Polynom) mit dem
	 * Residuenkalkül.
	 * 
	 * @return Die komplexen Residuen. Die Reihenfolge der Zahlen entspricht der
	 *         Reihenfolge der Wurzeln.
	 */
	public Complex[] getResidues() {
		Complex[] roots = getRoots();

		Complex[] result = new Complex[roots.length];

		for (int i = 0; i < roots.length; i++) {
			result[i] = Complex.ONE;
			for (int j = 0; j < roots.length; j++) {
				if (i != j) {
					result[i] = result[i].multiply(roots[i].subtract(roots[j]));
				}
			}
		}
		return result;
	}

	/**
	 * Multipliziert dieses Polynom mit einem Scalar.
	 * 
	 * @param scalar
	 *            Die Zahl mit der multipliziert werden soll
	 * @return Das Produkt
	 */
	public Polynom mul(double scalar) {
		double[] result = getCoeff();
		for (int i = 0; i < result.length; i++) {
			result[i] *= scalar;
		}
		return new Polynom(result);
	}

	/**
	 * Multipliziert dieses Polynom mit einem anderen.
	 * 
	 * @param other
	 *            Das andere Polynom
	 * @return Eine neues Polynom welches das Produkt der beiden Polynome ist.
	 */
	public Polynom mul(Polynom other) {
		double[] b = other.getCoeff();
		double[] result = new double[coeff.length + b.length - 1];

		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < coeff.length; j++) {
				result[i + j] += b[i] * coeff[j];
			}
		}
		return new Polynom(result);
	}

	/**
	 * Addiert dieses Polynom zu einem anderen.
	 * @param other Das andere Polynom
	 * @return Ein neues Polynom das die Summe der beiden anderen ist.
	 */
	public Polynom add(Polynom other) {
		double[] b = other.getCoeff();
		double[] result = new double[Math.max(coeff.length, b.length)];

		for (int i = 0; i < b.length; i++) {
			result[i] += b[i];
		}

		for (int i = 0; i < coeff.length; i++) {
			result[i] += coeff[i];
		}

		return new Polynom(result);
	}
}
