package p2;

import java.io.ObjectInputStream;
import java.net.Socket;

import p1.Buffer;
import p1.Message;

public class MessageClient {

	private Buffer<Message> messageBuffer;
	
	private CallbackInterface ci;
	
	public MessageClient(String address, int port) {
		new Connection(address, port);
		messageBuffer = new Buffer<Message>();
	}

	public void addListener(CallbackInterface ci) {
		this.ci = ci;
	}
	
	private class Connection extends Thread {

		private String address;
		private int port;

		public Connection(String address, int port) {
			this.address = address;
			this.port = port;
			start();
		}

		public void run() {
			try (Socket socket = new Socket(address,port)){
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				while(true) {
					try {
						Message msg = (Message)ois.readObject();
						messageBuffer.put(msg);
						ci.getMessage(msg);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
