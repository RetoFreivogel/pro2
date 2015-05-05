package controller;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.Model;
import util.Matlab;
import view.View;

public class PIDRechner extends JFrame {

	private static final long serialVersionUID = 1L;

	public void init() {
		setSize(800, 600);
		Model model = new Model();
		Controller controller = new Controller(model, this);
		View view = new View(model, controller);
		controller.setView(view);
		add(view);
		view.update(null, null);
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
