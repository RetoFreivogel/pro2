package main;

import java.awt.event.WindowAdapter;

import javax.swing.JFrame;

import com.sun.glass.events.WindowEvent;

public class PIDRechner extends JFrame{

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
		PIDRechner rechner = new PIDRechner();
		rechner.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});
		rechner.init();
		rechner.setVisible(true);
		rechner.setTitle("MVCFramework");
	}

}

