package main;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import util.Matlab;

public class PIDRechner extends JFrame {

	private static final long serialVersionUID = 1L;

	public void init() {
		setSize(800, 600);
		Model model = new Model();
		Controller controller = new Controller(model);
		View view = new View(controller, model);
		controller.setView(view);
		model.addObserver(view);
		add(view);
	}

	public static void main(String args[]) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				PIDRechner rechner = new PIDRechner();
				rechner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				rechner.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent e) {
						super.windowClosing(e);
						Matlab.closeProxy();
					}
				});
				rechner.init();
				rechner.setVisible(true);
				rechner.setTitle("MVCFramework");
			}
		});
	}
}
