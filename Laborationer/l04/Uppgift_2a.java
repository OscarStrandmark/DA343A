package l04;

public class Uppgift_2a extends Thread{
	
	private String[] strings;

	private int sleepTime = 1000;
	
	public Uppgift_2a(String[] strings) {
		this.strings = strings;
	}

	@Override
	public void run() {
		int counter = 0;
	
		while(counter < strings.length) {
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
		Uppgift_2a u2a = new Uppgift_2a(strings1);
		Uppgift_2a u2b = new Uppgift_2a(strings2);
		
		u2a.start();
		u2b.start();
	}

}
