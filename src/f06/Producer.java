package f06;

public class Producer extends Thread {
    private String message;
    private long delay;
    private Buffer<String> buffer;
    
    public Producer(String message, long seconds, Buffer<String> buffer) {
        this.message = message;
        this.delay = seconds*1000;
        this.buffer = buffer;
    }
    
    public void run() {
        while(!interrupted()) {
            try {
                Thread.sleep(delay);
                buffer.put(message);
            }
            catch(InterruptedException e) {
                break;
            }
        }
    }
}
