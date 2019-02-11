package f06;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Viewer {
    private JTextArea textArea;
    
    public Viewer() {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			JFrame frame = new JFrame("Viewer");
    			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    			textArea = new JTextArea();
    			textArea.setLineWrap(true);
    			textArea.setWrapStyleWord(true);
    			textArea.setPreferredSize(new Dimension(300,400));
    			frame.add(textArea);
    			frame.pack();
    			frame.setVisible(true);
    		}
    	});
    }
    
    public void append(String str) {
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
        		textArea.append(str);
    		}
    	});
    }
    
//    public synchronized void show(String[] message, int delay) {
    public void show(String[] message, int delay) {
        for(int i=0; i<message.length; i++) {
            append(message[i] + " ");
            try {
            	Thread.sleep(delay);
            }catch(InterruptedException e) {}
        }
    }    
}
