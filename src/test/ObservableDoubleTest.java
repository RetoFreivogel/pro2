package test;

import static org.junit.Assert.*;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import main.ObservableDouble;

import org.junit.Test;

public class ObservableDoubleTest {

	@Test
	public void copyMakesEqualObject() {
		Random r = new Random();
		ObservableDouble od = new ObservableDouble(r.nextDouble());
		
		ObservableDouble copy_od = new ObservableDouble(od);
		
		if(od == copy_od){
			fail("Didn't create new instance");
		}
		assertEquals(od, copy_od);
	}
	
	@Test
	public void callObservers() {
		Random r = new Random();
		ObservableDouble od = new ObservableDouble(r.nextDouble());
		MockObserver obs = new MockObserver();
		od.addObserver(obs);
		
		od.setValue(r.nextDouble());
		
		if(!obs.gotCalled){
			fail("Observer didnt get Called");
		}
	}
	
	@Test
	public void equalsDisregardsObservers() {
		Random r = new Random();
		ObservableDouble od = new ObservableDouble(r.nextDouble());
		ObservableDouble copy_od = new ObservableDouble(od);
		MockObserver obs = new MockObserver();

		copy_od.addObserver(obs);

		assertEquals(od, copy_od);
	}

}

class MockObserver implements Observer{
	public boolean gotCalled = false;
	
	@Override
	public void update(Observable arg0, Object arg1) {
		gotCalled = true;
		
	}
}

