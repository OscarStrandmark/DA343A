package l05.l5alarm;

import java.util.LinkedList;

public class AlarmThreadB {
	private Thread thread;
	private long ms;
	private LinkedList<AlarmListener> list = new LinkedList<AlarmListener>();
	
	public AlarmThreadB(long ms) {
		this.ms = ms;
	}
	
	public void startAlarm() {
		if(thread==null) {
			thread = new AT();
			thread.start();
		}
	}
	
	public void addAlarmListener(AlarmListener listener) {
		list.add(listener);
	}
	
	private class AT extends Thread {
		public void run() {
			try {
				Thread.sleep(ms);
			}catch(InterruptedException e) {
				
			}
			for(AlarmListener al : list) {
				al.alarm();
			}
			
			thread = null;
		}
	}
}

