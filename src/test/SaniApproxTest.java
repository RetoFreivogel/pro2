package test;

import static org.junit.Assert.*;

import org.junit.Test;

import util.Matlab;
import model.RegelStrecke;
import model.SaniApprox;

public class SaniApproxTest {

	@Test
	public void testCalcSani(){
		Matlab.setMocked(false);
		double tu = 1.71;
		double tg = 7.6;		
		RegelStrecke rs = new RegelStrecke(1.0, tu, tg);
		double[] Tcoeff_ref = Matlab.calcSani(rs);
		double[] Tcoeff = SaniApprox.calcSani(tu, tg);
		
		assertArrayEquals(Tcoeff_ref, Tcoeff, 0.001);
	}
	
	
	@Test
	public void testCalcSani2(){
		Matlab.setMocked(false);		
		double tu = 7;
		double tg = 12;		
		RegelStrecke rs = new RegelStrecke(1.0, tu, tg);
		double[] Tcoeff_ref = Matlab.calcSani(rs);
		double[] Tcoeff = SaniApprox.calcSani(tu, tg);
		
		assertArrayEquals(Tcoeff_ref, Tcoeff, 0.001);
	}
	
	@Test
	public void testCalcSani3(){
		Matlab.setMocked(false);		
		double tu = 2;
		double tg = 1999;		
		RegelStrecke rs = new RegelStrecke(1.0, tu, tg);
		double[] Tcoeff_ref = Matlab.calcSani(rs);
		double[] Tcoeff = SaniApprox.calcSani(tu, tg);
		
		assertArrayEquals(Tcoeff_ref, Tcoeff, 0.001);
	}
}
