package f04;

public class Counter4 {
    private int counter=0;
    private Thread thread = new Thread(new Activity());
    
    // Om tråden ska startas när den skapas, start() ska tas bort
//    public Counter3() {
//        thread.start();
//    }
    public void start() {
        thread.start();
    }
    
    private class Activity implements Runnable {
        public void run() {
            System.out.println("Counter3 startar");
            while(true) {
                System.out.println(counter);
                try {
                    Thread.sleep(800);
                } catch(InterruptedException e) {}
                counter++;
            }
        }
    }
}
