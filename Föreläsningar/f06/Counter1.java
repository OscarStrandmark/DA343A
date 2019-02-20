package f06;

import java.util.Random;

public class Counter1 {
	private int counter;
	private Random rand = new Random();
	
	public int incCounter() {
		counter = counter + 1;
		return counter;
	}
	
//	public synchronized int incCounter() {
//		counter = counter + 1;
//		return counter;
//	}	
}
