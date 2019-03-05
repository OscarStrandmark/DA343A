package p2;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import p1.Buffer;
import p1.Message;

public class MessageClient {

	private Buffer<Message> messageBuffer;
	
	private ArrayList<CallbackInterface> callbackList;
	
	public MessageClient(String address, int port) {
		new Connection(address, port);
		callbackList = new ArrayList<CallbackInterface>();
		messageBuffer = new Buffer<Message>();
	}

	public void addListener(CallbackInterface ci) {
		callbackList.add(ci);
	}

	private void sendList(Message msg) {
		Iterator<CallbackInterface> iter = callbackList.iterator();
		while(iter.hasNext()) {
			iter.next().getMessage(msg);
		}
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
						sendList(msg);
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
