package f04;
import javax.swing.Icon;
import javax.swing.SwingUtilities;

public class TwoIcon3 extends TwoIconLabel {
    private long delay;
    private boolean started = false;
    
    public TwoIcon3(Icon icon1, Icon icon2) {
        this(icon1, icon2, 500);
    }

    public TwoIcon3(Icon icon1, Icon icon2, int delay) {
        this(icon1, icon2, 200, 200, delay);
    }

    public TwoIcon3(Icon icon1, Icon icon2, int width, int height, int delay) {
    	super(icon1, icon2, width, height);
        this.delay = delay;
    }
    
    public void start() {
    	if(!started) {
    		ToDo thread = new ToDo();
            thread.start();
    		started = true;
    	}
    }
    
    private class ToDo extends Thread {
        private Runnable changeIcon = new ChangeIcon();
        
        public void run() {
            while(true) {
                try {
                    Thread.sleep(delay);
                } catch(InterruptedException e) {}
                SwingUtilities.invokeLater( changeIcon );
            }
        }
    }
    
    private class ChangeIcon implements Runnable {
    	public void run() {
    		changeIcon();
    	}
    }
}
