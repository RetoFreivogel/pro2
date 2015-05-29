package test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.RegelKreis;
import model.RegelStrecke;
import model.SchrittAntwort;
import model.dimensionierung.DimEnum;
import model.dimensionierung.Dimensionierung;
import model.dimensionierung.TopoEnum;

import org.junit.Test;

import util.Matlab;

public class SchrittAntwortTest {

	
	@Test
	public void testPID() {
		Matlab.setMocked(false);
		Dimensionierung dim = new Dimensionierung(DimEnum.OPPELT, TopoEnum.PID);
		RegelStrecke rs = new RegelStrecke(1.0, 1.71, 7.6);
		RegelKreis kreis = new RegelKreis(dim, rs);
		SchrittAntwort sw = kreis.getTranferFunction().schrittantwort();
		
		double[][] output;
 
		try{
			FileInputStream stream = new FileInputStream("schrittantwort1.dat");
			ObjectInputStream ausgabe = new ObjectInputStream(stream);

			output = (double[][]) ausgabe.readObject();
			ausgabe.close();
			stream.close();
		}catch(Exception e){
			output = Matlab.calcStep(kreis);		
			try {
				FileOutputStream stream = new FileOutputStream("schrittantwort1.dat");
				ObjectOutputStream ausgabe = new ObjectOutputStream(stream);
				ausgabe.writeObject(output);
				ausgabe.close();
				stream.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}		
		}	
		double[] y_ref = output[0];
		double[] t = output[1];
 		
		double[] y = new double[t.length];
		for (int i = 0; i < y.length; i++) {
			y[i] = sw.getY(t[i]);
		}
		assertArrayEquals(y_ref, y, 0.001);
	}
}
