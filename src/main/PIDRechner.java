package main;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PIDRechner extends JFrame {

	private static final long serialVersionUID = 1L;

	public void init() {
		setSize(400, 300);
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
				rechner.init();
				rechner.setVisible(true);
				rechner.setTitle("MVCFramework");
			}
		});
	}
}
