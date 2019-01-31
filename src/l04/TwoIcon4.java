package l04;
import javax.swing.Icon;
import javax.swing.SwingUtilities;

public class TwoIcon4 extends TwoIconLabel implements Runnable, StartStopListener {
    private long delay;
    private Thread thread;
    
    public TwoIcon4(Icon icon1, Icon icon2) {
        this(icon1, icon2, 500);
    }

    public TwoIcon4(Icon icon1, Icon icon2, int delay) {
        this(icon1, icon2, 200, 200, delay);
    }

    public TwoIcon4(Icon icon1, Icon icon2, int width, int height, int delay) {
    	super(icon1, icon2, width, height);
        this.delay = delay;
    }
    
    public void start() {
    	if(thread == null) {
    		thread = new Thread(new ToDo());
            thread.start();
    	}
    }
    
	@Override
	public void stop() {
		if(thread != null) {
			thread.interrupt();
		}
	}

	@Override
	public void run() {
		while(!Thread.interrupted()) {
			try {
				Thread.sleep(delay);
			} catch (Exception e) {
				break;
			}
			SwingUtilities.invokeLater(new ChangeIcon());
		}
		thread = null;
	}
    
    private class ToDo implements Runnable {
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
