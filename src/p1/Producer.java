package p1;

/**
 * Denna klass tar alla {@link Message}-objekt ur en {@link MessageProducer} och lägger in dem i en {@link Buffer}
 * 
 * @author Oscar Strandmark
 */
public class Producer extends Thread {

	private Buffer<MessageProducer> producerBuffer;
	private Buffer<Message> messageBuffer;

	/**
	 * Skapar ett Producer objekt. När
	 * 
	 * @param prodBuffer    En {@link Buffer} med med typvariablen {@link MessageProducer}
	 * @param messageBuffer En {@link Buffer} med typvariablen {@link Message}
	 */
	public Producer(Buffer<MessageProducer> prodBuffer, Buffer<Message> messageBuffer) {
		producerBuffer = prodBuffer;
		this.messageBuffer = messageBuffer;
	}

	//Ärver javadoc av Thread
	@Override
	public synchronized void start() {
		super.start();
	}
	
	//Ärver javadoc av Thread
	@Override
	public void run() {
		while (!Thread.interrupted()) {
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
}
