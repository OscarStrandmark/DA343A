package l07;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Uppgift_3_Main {

	public static void main(String[] args) {
		Uppgift_3_Controller controller = new Uppgift_3_Controller();
		Uppgift_3_View view = new Uppgift_3_View();
		view.setController(controller);
		controller.setViewer(view);

		JFrame window = new JFrame();
		window.add(view);
		window.setSize(new Dimension(330,200));
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
