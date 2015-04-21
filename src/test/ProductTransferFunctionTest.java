package test;

import static org.junit.Assert.*;
import main.ProductTransferFunction;

import org.junit.Test;

public class ProductTransferFunctionTest{

	
	
	@Test
	public void test() {
		ProductTransferFunction ptf = new ProductTransferFunction(){
			@Override
			public double[] getNennerFactors() {
				return new double[]{1, 1, 1, 1};
			}

			@Override
			public double[] getZaehlerFactors() {
				return new double[]{1, 1, 1};
			}

			@Override
			public double getBaseFactor() {
				return 1;
			}
			
		};
		
		assertArrayEquals(new double[]{1, 4, 6, 4, 1}, ptf.getPolyNenner(), 0);
		assertArrayEquals(new double[]{1, 3, 3, 1}, ptf.getPolyZaehler(), 0);
	}

}
