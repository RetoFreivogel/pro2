package controller;


import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.Model;
import model.RegelStrecke;
import model.dimensionierung.DimEnum;
import model.dimensionierung.Dimensionierung;
import model.dimensionierung.TopoEnum;
import view.View;

public class PIDRechner extends JFrame {

	private static final long serialVersionUID = 1L;

	public void init() {
		setPreferredSize(new Dimension(800, 600));
		RegelStrecke regelstrecke = new RegelStrecke(1.0, 1.71, 7.6);
		Dimensionierung[] dim = new Dimensionierung[]{new Dimensionierung(DimEnum.PHASENGANG, TopoEnum.PID)};
		Model model = new Model(regelstrecke, dim);
		Controller controller = new Controller(model, this);
		View view = new View(model, controller, this);
		controller.setView(view);
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
					}
				});
				rechner.init();
				rechner.setTitle("Regler Dimensionierungs Tool ONE");
				rechner.pack();
				rechner.setVisible(true);
			}
		});
	}
}
