package l04;

import java.awt.Dimension;
import javax.swing.*;

public class Uppgift_3 extends Thread {

	private String[] strings;
	private long delay;
	private JLabel label;
	
	private int sleepTime = 1000;
	
	public Uppgift_3(String[] strings, JLabel label, long delay) {
		this.strings = strings;
		this.label = label;
		this.delay = delay;
	}
	
	public void run() {
		int counter = 0;
		
		while(true){
			label.setText(strings[counter % strings.length]);
			counter++;
			
			try {
				Thread.sleep(sleepTime);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				JLabel lblText = new JLabel();
				String[] texter = { "Det är två månader kvar på året", "23 * 6735 = 154905",
						"Den 28 oktober har Simone namnsdag" };
				lblText.setPreferredSize(new Dimension(400, 40));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(lblText);
				frame.pack();
				frame.setVisible(true);
				Uppgift_3 ex3 = new Uppgift_3(texter, lblText, 3000);
				ex3.start();
			}
		});
	}
}
