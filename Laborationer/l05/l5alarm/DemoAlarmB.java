package l05.l5alarm;

public class DemoAlarmB {
	public DemoAlarmB(int ms) {
		AlarmThreadB bt = new AlarmThreadB(ms);
		bt.addAlarmListener(new AlarmPrinter());
		bt.addAlarmListener(new WakeUpPrinter());
		bt.addAlarmListener(new ConsolePrinter("Ett meddelande"));
		bt.startAlarm();
	}
	
	public static void main(String[] args) {
		DemoAlarmB da = new DemoAlarmB(4000);
	}
	
	private class AlarmPrinter implements AlarmListener {
		@Override
		public void alarm() {
			System.out.println("ALARM");			
		}
	}
	
	private class WakeUpPrinter implements AlarmListener {
		@Override
		public void alarm() {
			System.out.println("WAKE UP");
		}
	}
	
	private class ConsolePrinter implements AlarmListener {
		
		private String msg;
		
		public ConsolePrinter(String msg) {
			this.msg = msg;
		}
		@Override
		public void alarm() {
			System.out.println(msg);
		}	
	}
}
