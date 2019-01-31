package f04;
public class Counter2 implements Runnable {
    private int counter=0;
    
    public void run() {
        System.out.println("Counter2 startar");
        while(true) {
            System.out.println(counter);
            try {
                Thread.sleep(900);
            } catch(InterruptedException e) {}
            counter++;
        }
    }
}