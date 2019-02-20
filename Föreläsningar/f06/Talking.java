package f06;

public class Talking implements Runnable {
    private Thread thread = new Thread(this);
    private House roomWithTelephone;

    public Talking(String name, House room) {
        thread.setName(name);
        this.roomWithTelephone = room;
    }
    
    public void start() {
        thread.start();
    }
    
    public void run() {
        for(int i=0; i<2; i++) {
            roomWithTelephone.call();
            try {
            	Thread.sleep((long)(Math.random()*10000));
            }catch(InterruptedException e) {}
         }
    }
}
