package p1;

public class MessageProducerInput {

	private Buffer<MessageProducer> producerBuffer;
	
	public MessageProducerInput(Buffer<MessageProducer> mp) {
		producerBuffer = mp;
	}
	
	public void addMessageProducer(MessageProducer m) {
		producerBuffer.put(m);
	}
	
}
