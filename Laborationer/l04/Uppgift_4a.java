package l04;

public class Uppgift_4a {

	private String[] strings;

	private Thread thread;
	private int sleepTime = 1000;

	public Uppgift_4a(String[] strings) {
		this.strings = strings;
	}

	public void start() {
		if (thread == null) {
			thread = new Activity();
			thread.start();
		}
	}

	public void stop() {
		if (thread != null) {
			thread.interrupt();
		}
	}

	public static void main(String[] args) {
		String[] strings1 = { "Jag heter Rut", "Du heter Sven", "Han heter Ola" };
		String[] strings2 = { "Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag" };
		Uppgift_4a u4a = new Uppgift_4a(strings1);
		Uppgift_4a u4b = new Uppgift_4a(strings2);
		u4a.start();
		u4b.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		u4a.stop();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		u4a.start();
		u4b.stop();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
		u4b.start();

	}

	private class Activity extends Thread {
		private int counter = 0;

		public void run() {
			while ((counter < strings.length) && !Thread.interrupted()) {
				System.out.println(strings[counter]);
				counter++;
				try {
					Thread.sleep(sleepTime);
				} catch (Exception e) {
					//e.printStackTrace();
					break;
				}
			}
			thread = null;
		}
	}
}
