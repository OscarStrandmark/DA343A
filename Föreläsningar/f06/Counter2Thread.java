package f06; 
public class Counter2Thread extends Thread {
    private Counter2 counter;
    private int times;
    
    public Counter2Thread(Counter2 counter, String name, int times) {
    	this.counter = counter;
    	this.setName(name);
    	this.times = times;
    }

    // 1. synchronized-satsen bortkommenterad
    // 2. synchronized-satsen aktiv, testa som argument till synchronized-satsen a) counter b) this
    // 3. vad händer om även rad 26 ingår i synchronized-satsen? 
    public void run() {
    	int value;
        System.out.println(getName() + " startar");
        while (times-->0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        	synchronized(this) {
        		counter.incCounter();
        		value = counter.getCounter();
        	    System.out.println(getName()+": "+value);
        	}
        }
    }
    
    public static void main(String[] args) {
		Counter2 counter = new Counter2();
		Counter2Thread ct1 = new Counter2Thread(counter,"A",20);
		Counter2Thread ct2 = new Counter2Thread(counter,"B",20);
		ct1.start();
		ct2.start();
	}
}
