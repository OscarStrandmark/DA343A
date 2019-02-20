package f06;


public class Sender {
    private String[] message;
    private Viewer viewer;
    private Object lock = new Object();
    
    public Sender(String[] message, Viewer viewer) {
        this.message = message;
        this.viewer = viewer;
    }
    
    public void start() {
    	new SenderThread1().start();
    	new SenderThread2().start();
    }
    
    private class SenderThread1 extends Thread {
    	public void run() {
    		synchronized(lock) { // testa även this
    			viewer.show(message,100);
    		}
    	}
    }
    
    private class SenderThread2 extends Thread {
    	public void run() {
    		synchronized(lock) { // testa även this
    			viewer.show(message,200);
    		}
    	}
    }
}
