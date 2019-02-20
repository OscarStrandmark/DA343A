package l05.l5alarm;

import java.util.Observable;
import java.util.Observer;

public class DemoAlarmA {
	public DemoAlarmA(int ms) {
		AlarmThreadA at = new AlarmThreadA(ms);
		at.addObserver(new AlarmPrinter());
		at.addObserver(new WakeUpPrinter());
		at.addObserver(new ConsolePrinter("Ett meddelande"));
		at.startAlarm();
	}
	
	public static void main(String[] args) {
		DemoAlarmA da = new DemoAlarmA(4000);
	}
	
	private class AlarmPrinter implements Observer {
		@Override
		public void update(Observable o, Object arg) {
			System.out.println("ALARM");
		}
	}
	
	private class WakeUpPrinter implements Observer {
		@Override
		public void update(Observable o, Object arg) {
			System.out.println("WAKE UP");
		}
	}
	
	private class ConsolePrinter implements Observer {
		
		private String msg;
		
		public ConsolePrinter(String msg) {
			this.msg = msg;
		}
		
		@Override
		public void update(Observable o, Object arg) {
			System.out.println(msg);
		}
	}
}
