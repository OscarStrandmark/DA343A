package f04; 
public class Counter1 extends Thread {
    private int counter = 0;
    
// Om tråden ska startas när den skapas
//    public Counter1() {
//        start();
//    }
    
    public void run() {
        System.out.println("Counter1 startar");
        while (true) {
            System.out.println(counter);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
            counter++;
        }
    }
}
