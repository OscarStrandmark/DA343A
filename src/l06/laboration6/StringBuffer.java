package l06.laboration6;

import java.util.LinkedList;

public class StringBuffer {

	private LinkedList<String> queue;
	
	public StringBuffer() {
		queue = new LinkedList<String>();
	}
	
	public synchronized void put(String str) {
		queue.add(str);
		notifyAll();
	}
	
	public synchronized String get() throws InterruptedException {
		while(queue.isEmpty()) {
			wait();
		}
		return queue.removeFirst();
	}
}
