package l07.Uppgift4.client;

import javax.swing.JFrame;

public class StartClient {
	public static void main(String[] args) {
		Controller controller = new Controller("localhost", 1337);
		UI ui = new UI();
		
		controller.addUI(ui);
		ui.addController(controller);
		
		JFrame w = new JFrame();
		w.add(ui);
		w.setVisible(true);
		w.pack();
	}
}
