package p2;

import java.io.ObjectOutputStream;
import java.net.Socket;

import p1.Buffer;
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
		
		int times = producer.times();
		int delay = producer.delay();
		int size = producer.size();
		Message[] messages = new Message[size];
		
		for (int i = 0; i < size; i++) {
			messages[i] = producer.nextMessage();
		}
		
		ArrayProducer ap = new ArrayProducer(messages, times, delay);
		
		try (Socket socket = new Socket(address, port)) {
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ArrayProducer obj = ap;
			oos.writeObject(obj);
			oos.flush();
			oos.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
