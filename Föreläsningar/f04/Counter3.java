package f04;
public class Counter3 {
    private int counter=0;
    private Activity activity = new Activity();
    
    // Om tråden ska startas när den skapas, start() ska tas bort
//    public Counter3() {
//        aktivitet.start();
//    }
    public void start() {
        activity.start();
    }
    
    private class Activity extends Thread {
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
