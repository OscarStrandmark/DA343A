package f06;

public class Consumer implements Runnable {
    private Thread thread;
    private Buffer<String> buffer;
    private long delay;
    
    public Consumer(long seconds, Buffer<String> buffer) {
        this.delay = seconds*1000;
        this.buffer = buffer;
    }
    
    public void start() {
    	if(thread==null) {
    		thread = new Thread(this);
            thread.start();
    	}
    }
    
    public void stop() {
    	if(thread!=null) {
            thread.interrupt();
    	}
    }
    
    public void run() {
        String txt;
        while(!Thread.interrupted()) {
            try {
                Thread.sleep(delay);
                txt = (String)buffer.get();
                System.out.println(txt);
            } catch(InterruptedException e) {
                break;
            }
        }
        thread=null;
    }
}
