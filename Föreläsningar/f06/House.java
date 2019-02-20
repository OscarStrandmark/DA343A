package f06;

// Det går endast att vila om man får vara ensam i vilorummet och
// ingen pratar i telefon i telefonrummet.
// Det går endast att tala i telefon om man är ensam i telefonrummet
// och ingen snarkar i vilorummet.
public class House {    
    public synchronized void rest(long ms) {
        Thread thread  = Thread.currentThread();
        System.out.println(thread.getName()+" vilar i " + ms + " ms.");
        try {
            Thread.sleep(ms);
        } catch(InterruptedException e) {}
        System.out.println(thread.getName()+" har vilat färdigt och lämnar vilrummet.");
    }
    
    public void call() {
        synchronized(this) {
            Thread thread  = Thread.currentThread();
            System.out.println("  " + thread.getName()+" talar i telefon.");
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {}
            System.out.println("  " + thread.getName()+" har nu talat färdigt.");
        }
    }
}
