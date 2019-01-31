package l04;

import java.util.Timer;
import java.util.TimerTask;

public class Uppgift_1 {

	private String[] strings;
	
	
	private Timer timer;
	private int delay = 0;
	private int period = 1000;
	
	
	public Uppgift_1(String[] strings) {
		timer = new Timer();
		this.strings = strings;
		timer.schedule(new Activity(), delay, period);
	}

	public static void main(String[] args) {
		String[] strings1 = {"Jag heter Rut", "Du heter Sven", "Han heter Ola"};
		String[] strings2 = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag",
		 "Lördag", "Söndag"};
		Uppgift_1 u1a = new Uppgift_1(strings1);
		Uppgift_1 u1b = new Uppgift_1(strings2);
	}

	public class Activity extends TimerTask {
		private int counter = 0;
		@Override
		public void run() {
			if(counter >= strings.length) {
				timer.cancel();
				timer.purge();
			} else {
				System.out.println(strings[counter]);
				counter++;
			}
			
		}
	}
}
