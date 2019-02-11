package l06.laboration6;

public class StringProducer extends Thread {

	private StringBuffer buffer;
	private long delay = 500;
	private String[] strings = {"A","B","C","D","E","F"};
	
	public StringProducer(StringBuffer buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		System.out.println("StringConsumer runs");
		for(String str : strings) {
			try {
				Thread.sleep(delay);
			} catch (Exception e) {}
			buffer.put(str);
		}
	}
}
