package l06.laboration6;

public class StringConsumer extends Thread {

	private long delay = 500;
	private StringBuffer buffer;
	
	public StringConsumer(StringBuffer buffer) {
		this.buffer = buffer;
	}
	
	@Override
	public void run() {
		String str;
		while(!Thread.interrupted()) {
			try {
				Thread.sleep(delay);
				str = buffer.get();
				TextWindow.println(str);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}
}
