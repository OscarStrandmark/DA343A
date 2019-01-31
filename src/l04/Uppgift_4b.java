package l04;

import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.SwingUtilities;

public class Uppgift_4b extends TwoIconLabel{

	private static final long serialVersionUID = 3020197217562591939L;
	
	private java.util.Timer timer;
	private int delay;
	private boolean started = false;

	public Uppgift_4b(Icon icon1, Icon icon2) {
        this(icon1, icon2, 500);
    }

	public Uppgift_4b(Icon icon1, Icon icon2, int delay) {
        this(icon1, icon2, 200, 200, delay);
    }

	public Uppgift_4b(Icon icon1, Icon icon2, int width, int height, int delay) {
    	super(icon1, icon2, width, height);
    	this.delay = delay;
    }

	public void start() {
		if (timer == null) {
			timer = new java.util.Timer();
			timer.schedule(new ToDo(), delay, delay);
			started = true;
		}
	}
	
	public void stop() {
		if(timer != null) {
			timer.cancel();
			timer = null;
		}
	}

	private class ToDo extends TimerTask {
		private ChangeIcon ci = new ChangeIcon();

		public void run() {
			SwingUtilities.invokeLater(ci);
		}
	}

	private class ChangeIcon implements Runnable {
		public void run() {
			changeIcon();
		}
	}
}
