package p2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import p1.Buffer;
import p1.Message;
import p1.MessageProducer;

public class MessageProducerClient {

	private String address;
	private int port;

	private Buffer<ArrayProducer> buffer;

	public MessageProducerClient(String address, int port) {
		buffer = new Buffer<ArrayProducer>();

		try {
			new Connection(address, port);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sends parameted MessageProducer to the address specified in the constructor.
	 * 
	 * @param messageProducer the MessageProducer to send.
	 */
	public void send(MessageProducer messageProducer) {
		//Converts the parameter messageProducer into an ArrayProducer so that it can be sent across an ObjectOutputStream.
		int size  = messageProducer.size();
		int times = messageProducer.times();
		int delay = messageProducer.delay();
		Message[] messages = new Message[size];
		for (int i = 0; i < messages.length; i++) {
			messages[i] = messageProducer.nextMessage();
		}
		//Places converted MessageProducer in a buffer to be picked up by the Connection thread.
		buffer.put(new ArrayProducer(messages, times, delay));
	}

	private class Connection extends Thread {

		private ObjectOutputStream oos;
		private Socket socket;

		public Connection(String address, int port) throws UnknownHostException, IOException {
			this.socket = new Socket(address, port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			start();
		}

		public void run() {
			ArrayProducer obj;
			while (true) {
				try {
					//Buffer is synchronized, thread will wait on .get() until it can obtain an object from the buffer. 
					obj = buffer.get();
					oos.writeObject(obj);
					oos.flush();
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

/*
 * Klienten utgörs troligen av ett antal klasser (kan vara inre klasser).
 * Klienten ska koppla upp mot server, överföra en
 * MessageProducer-implementering och sedan koppla ner förbindelsen.
 */