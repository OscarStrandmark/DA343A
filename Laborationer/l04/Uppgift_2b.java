package l04;

public class Uppgift_2b implements Runnable {

	private String[] strings;
	
	private int sleepTime = 1000;
	
	public Uppgift_2b(String[] strings) {
		this.strings = strings;
	}

	@Override
	public void run() {
		int counter = 0;

		while (counter < strings.length) {
			System.out.println(strings[counter]);

			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			counter++;

		}
	}

	public static void main(String[] args) {
		String[] strings1 = {"Jag heter Rut", "Du heter Sven", "Han heter Ola"};
		String[] strings2 = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};
		
		Uppgift_2b u2b  = new Uppgift_2b(strings1);
		Uppgift_2b u2b2 = new Uppgift_2b(strings2);
		
		Thread t1 = new Thread(u2b);
		Thread t2 = new Thread(u2b2);
		
		t1.start();
		t2.start();
	}

}
