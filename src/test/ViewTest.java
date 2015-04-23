package test;

import static org.junit.Assert.*;

import javax.swing.JFrame;
import javax.swing.JRootPane;

import main.View;

import org.junit.Test;

import util.Matlab;

public class ViewTest {

	@Test
	public void test() {
		Matlab.setMocked(true);
		
		JFrame frame = new JFrame();
		frame.setUndecorated(false);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TopView_Alex");
		frame.getContentPane().add(new View(null, null));
		frame.setSize(800, 600);
		//frame.pack();
		frame.setVisible(true);
		
		assertNotNull(frame);
	}
}
