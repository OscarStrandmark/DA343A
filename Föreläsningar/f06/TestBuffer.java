package f06;

public class TestBuffer {
    public static void main(String[] args)  {
        Buffer<String> buffer = new Buffer<String>();
        Producer[] prod = {new Producer("Ole",  5,  buffer), new Producer("Dole", 7, buffer), new Producer("Doff", 9, buffer)};
        Consumer[] cons = {new Consumer(1, buffer), new Consumer(1, buffer)};  // använd även 6 sek
        
        for(int i=0; i<prod.length; i++) {
            prod[i].start();
        }
        for(int i=0; i<cons.length; i++) {
            cons[i].start();
        }
        
        try {
            Thread.sleep(30000);
        }
        catch(InterruptedException e) {}
        
        for(int i=0; i<prod.length; i++) {
            prod[i].interrupt();
        }
        for(int i=0; i<cons.length; i++) {
            cons[i].stop();
        }
        System.out.println("Antal kvar i bufferten: " + buffer.size());
    }
}
