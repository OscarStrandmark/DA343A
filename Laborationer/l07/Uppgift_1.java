package l07;
import javax.swing.JFrame;


public class Uppgift_1 {

	public static void main(String[] args) {

		JFrame w = new JFrame();
		w.add(new Uppgift_1_UI());
		w.setVisible(true);
		w.setSize(300, 100);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
