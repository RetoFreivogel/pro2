package test;

import static org.junit.Assert.*;

import javax.swing.JFrame;
import javax.swing.JRootPane;

import org.junit.Test;

import prototype.NewView;
import util.Matlab;

public class NewViewTest {

	@Test
	public void test() {
		Matlab.setMocked(true);
		
		JFrame frame = new JFrame();
		frame.setUndecorated(false);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("TopView_Alex");
		frame.getContentPane().add(new NewView());
		frame.setSize(800, 600);
		//frame.pack();
		frame.setVisible(true);
	}
}
