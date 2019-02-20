package l04;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TIController {
	private StartStopListener startStopListener = null;
	private TIPanel panel;
	
	private void showFrame(TIPanel panel) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	public TIController(TwoIconLabel til) {
		panel = new TIPanel(this,til);
		showFrame(panel);
		if(til instanceof StartStopListener) {
			this.startStopListener = (StartStopListener)til;
		    panel.enableStart(true);
		    panel.enableStop(false);
		} else {
			til.start();
		    panel.enableStart(false);
		    panel.enableStop(false);
		}
	}

	public void start() {
	    panel.enableStart(false);
	    panel.enableStop(true);
		startStopListener.start();
	}

	public void stop() {
	    panel.enableStart(true);
	    panel.enableStop(false);
		startStopListener.stop();
	}

	public static void main(String[] args) {
    	SwingUtilities.invokeLater( new Runnable() {
    		public void run() {
//    			TwoIcon1 til = new TwoIcon1(new ImageIcon("src\\l04\\orkanen.jpg"), new ImageIcon("src\\l04\\stationen.jpg"),300,200,1000);
//    			TwoIcon2 til = new TwoIcon2(new ImageIcon("src\\l04\\orkanen.jpg"), new ImageIcon("src\\l04\\stationen.jpg"),300,200,1000);
//    			TwoIcon3 til = new TwoIcon3(new ImageIcon("src\\l04\\orkanen.jpg"), new ImageIcon("src\\l04\\stationen.jpg"),300,200,1000);
    			TwoIcon4 til = new TwoIcon4(new ImageIcon("src\\l04\\orkanen.jpg"), new ImageIcon("src\\l04\\stationen.jpg"),300,200,1000);
//    			TwoIcon5 til = new TwoIcon5(new ImageIcon("src\\l04\\orkanen.jpg"), new ImageIcon("src\\l04\\stationen.jpg"),300,200,1000);
//  			TwoIcon6 til = new TwoIcon6(new ImageIcon("src\\l04\\orkanen.jpg"), new ImageIcon("src\\l04\\stationen.jpg"),300,200,1000);
    			
    			new TIController(til);
    		}
    	});
    }
}
