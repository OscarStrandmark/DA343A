package f06; 
public class Counter1Thread extends Thread {
    private Counter1 counter;
    private int times;
    
    public Counter1Thread(Counter1 counter, String name, int times) {
    	this.counter = counter;
    	this.setName(name);
    	this.times = times;
    }
     
    // Testa med Counter1-incCounter a) utan synchronized b) med synchronized
    public void run() {
    	int value;
    	System.out.println(getName() + " startar");
    	while (times-->0) {
    		try {
    			Thread.sleep(1000);
    		} catch (InterruptedException e) {}
    		value = counter.incCounter();
    		System.out.println(getName()+": "+value);
    	}
    }
    
      // testa med Counter1-incCounter utan synchronized
//    public void run() {
//    	int value;
//    	System.out.println(getName() + " startar");
//    	while (times-->0) {
//    		try {
//    			Thread.sleep(1000);
//    		} catch (InterruptedException e) {}
//    		synchronized(counter) {
//    			value = counter.incCounter();
//    		} 
//    		System.out.println(getName()+": "+value);
//    	}
//    }
    
    
    // testa med Counter1-incCounter utan synchronized
//    public void run() {
//    	int value;
//        System.out.println(getName() + " startar");
//        while (times-->0) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {}
//        	synchronized(counter) {
//        	    value = counter.incCounter();
//        	    System.out.println(getName()+": "+value);
//        	}
//        }
//    }
    
    public static void main(String[] args) {
		Counter1 counter = new Counter1();
		Counter1Thread ct1 = new Counter1Thread(counter,"A",20);
		Counter1Thread ct2 = new Counter1Thread(counter,"B",20);
		ct1.start();
		ct2.start();
	}
}
