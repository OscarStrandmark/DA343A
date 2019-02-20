package f06;

import java.util.LinkedList;

public class RunOnThread {
	private Buffer<Runnable> buffer = new Buffer<Runnable>();
	private Worker worker;
	
	public synchronized void start() {
		if(worker==null) {
		    worker = new Worker();
		    worker.start();
		}
	}
	
	public synchronized void execute(Runnable runnable) {
		if(runnable!=null)
		    buffer.put(runnable);
	}
	
	public synchronized void stop() {
		buffer.clear();
		worker.running = false; // Om tråden är blockerad och InterruptedException hanteras lokalt
		worker.interrupt();
	}
	
	private class Worker extends Thread {
		private boolean running = false; 
		
		public void run() {
			running = true;
			while(running) {
				try {
				    buffer.get().run();
				} catch(InterruptedException e) {
					break;
				}
			}
			worker = null;
			System.out.println(Thread.currentThread().getName() + " avslutas");
		}
	}
	
	private class Buffer<T> {
		private LinkedList<T> buffer = new LinkedList<T>();
		
		public synchronized void put(T obj) {
			buffer.addLast(obj);
			notifyAll();
		}
		
		public synchronized T get() throws InterruptedException {
			while(buffer.isEmpty()) {
				System.out.println(Thread.currentThread() + " is waiting");
				wait();
			}
			return buffer.removeFirst();
		}
			
		public synchronized void clear() {
			buffer.clear();
		}
		
		public synchronized int size() {
			return buffer.size();
		}
	}
}
