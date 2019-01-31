package l04;

public class Uppgift_2c {

	private String[] strings;
	
	
	private Thread thread;
	private int sleepTime = 1000;
	
	public Uppgift_2c(String[] strings) {
		this.strings = strings;
	}
	
	public void start() {
		if(thread == null) {
			thread = new Activity();
			thread.start();
		}
	}
	
	public static void main(String[] args) {
		String[] strings1 = {"Jag heter Rut", "Du heter Sven", "Han heter Ola"};
		String[] strings2 = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};
		
		Uppgift_2c u2c1 = new Uppgift_2c(strings1);
		Uppgift_2c u2c2 = new Uppgift_2c(strings2);
		
		u2c1.start();
		u2c2.start();
		
	}
	
	private class Activity extends Thread {
		private int counter = 0;
		
		public void run() {
			while( counter < strings.length) {
				System.out.println(strings[counter]);
				counter++;
				try {
					Thread.sleep(sleepTime);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
