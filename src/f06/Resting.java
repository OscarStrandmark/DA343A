package f06;

public class Resting implements Runnable {
    private Thread thread = new Thread(this);
    private long restMs;
    private House restRoom;

    public Resting(String name, long restMs, House room) {
        thread.setName(name);
        this.restMs = restMs;
        this.restRoom = room;
    }
    
    public void start() {
        thread.start();
    }
    
    public void run() {
        for(int i=0; i<3; i++) {
            restRoom.rest(restMs);
            try {
            	Thread.sleep((long)(Math.random()*10000));
            }catch(InterruptedException e) {}
        }
    }
}
