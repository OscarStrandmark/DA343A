package f04;
public class TestCounter {
    public static void main(String[] args) {
        Counter1 counter = new Counter1();
//        Thread counter = new Thread(new Counter2());
//        Counter3 counter = new Counter3();
//        Counter4 counter = new Counter4();
        counter.start();
        // main-tråden skriver ut 10 "Hej" innan den avslutas
        // Mellan varja utskrift pausas main-tråden i 1,3 sekunder
        for(int i=0; i<10; i++) {
            try {
                Thread.sleep(1300);
                System.out.println("Hej");
            }catch(InterruptedException e) {}
        }
        System.exit(0);
    }
}