package p2;

import java.io.ObjectOutputStream;
import java.net.Socket;

import p1.Message;
import p1.MessageProducer;

public class MessageProducerClient {

	private String address;
	private int port;

	public MessageProducerClient(String address, int port) {
		this.address = address;
		this.port = port;
	}

	public void send(MessageProducer producer) {
		ArrayProducer ap = null;
		int times = 0;
		int delay = 0;
		int size = 0;
		
			times = producer.times();
			delay = producer.delay();
			size = producer.size();
			Message[] messages = new Message[size];
			
			for (int i = 0; i < size; i++) {
				messages[i] = producer.nextMessage();
			}
			
			ap = new ArrayProducer(messages, times, delay);
		
		
		try (Socket socket = new Socket(address, port)) {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ArrayProducer obj = ap;
			oos.writeObject(obj);
			Thread.sleep(500);
			oos.flush();
			oos.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
