package p1;

public class Producer extends Thread {

	private Buffer<MessageProducer> producerBuffer;
	private Buffer<Message> messageBuffer;

	public Producer(Buffer<MessageProducer> prodBuffer, Buffer<Message> messageBuffer) {
		producerBuffer = prodBuffer;
		this.messageBuffer = messageBuffer;
	}

	@Override
	public synchronized void start() {
		super.start();
	}
	
	public void run() {

		try {
			MessageProducer mp = producerBuffer.get();
			for (int times = 0; times < mp.times(); times++) {
				for (int i = 0; i < mp.size(); i++) {
					messageBuffer.put(mp.nextMessage());

					Thread.sleep(mp.delay());
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
