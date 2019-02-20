package l04;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.SwingUtilities;

public class TwoIcon6 extends TwoIconLabel implements StartStopListener {
    private long delay;
    private ToDo thread=null;
    
    public TwoIcon6(Icon icon1, Icon icon2) {
        this(icon1, icon2, 500);
    }

    public TwoIcon6(Icon icon1, Icon icon2, int delay) {
        this(icon1, icon2, 200, 200, delay);
    }

    public TwoIcon6(Icon icon1, Icon icon2, int width, int height, int delay) {
    	super(icon1, icon2, width, height);
        this.delay = delay;
    }
    
    public void start() {
    	if(thread==null) {
    		thread = new ToDo();
    		thread.start();
    	}
    }
    
    public void stop() {
    	if(thread!=null) {
    		thread.interrupt();
    	}
    }
        
    private class ToDo extends Thread {
        private Runnable changeIcon = new ChangeIcon();
        
        public void run() {
            while(!Thread.interrupted()) {
                SwingUtilities.invokeLater( changeIcon );
                try {
                    Thread.sleep(delay);
                } catch(InterruptedException e) {
                	break;
                }
            }
    		thread = null;
        }
    }
    
    private class ChangeIcon implements Runnable {
    	public void run() {
    		changeIcon();
    	}
    }
}
