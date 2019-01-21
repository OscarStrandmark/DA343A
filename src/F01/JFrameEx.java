package F01;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.function.Consumer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class JFrameEx {
	private JLabel label = new JLabel();
	private JFrame frame = new JFrame();
	
	private void showFrame() {
		frame.setTitle("En instans av JFrame");
		frame.setLocation(200, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(label);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void action() {
		label.setText("DA343A");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.BLUE); 
		label.setFont(new Font("SansSerif",Font.PLAIN,48));
		label.setPreferredSize(new Dimension(222,58));
		showFrame();		
		System.out.println(label.getPreferredSize());
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
			    JFrameEx prog = new JFrameEx();
			    prog.action();
			}
		});
	}
}
