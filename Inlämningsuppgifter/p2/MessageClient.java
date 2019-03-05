package p2;

import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import p1.Buffer;
import p1.Message;

/**
 * Client class that recieves {@link Message} object from the {@link MessageServer} and sends them to listening classes.
 * 
 * @author Oscar Strandmark
 */
public class MessageClient {

	private Buffer<Message> messageBuffer;
	
	private ArrayList<CallbackInterface> callbackList;
	
	/**
	 * Creates a MessageClient that recieves {@link Message} objects from a server and sends them to listening classes.
	 * 
	 * @param address The address to the server.
	 * @param port The port the server is listening on.
	 */
	public MessageClient(String address, int port) {
		new Connection(address, port);
		callbackList = new ArrayList<CallbackInterface>();
		messageBuffer = new Buffer<Message>();
	}

	/**
	 * Adds the parameter object as a listener to this class.
	 * 
	 * @param ci Object of a class that implements the interface {@link CallbackInterface}
	 */
	public void addListener(CallbackInterface ci) {
		callbackList.add(ci);
	}

	/**
	 * Private method used by the Connection thread to send the recieved message to all listeners.
	 * 
	 * @param msg The message to send to the listeners.
	 */
	private void sendList(Message msg) {
		Iterator<CallbackInterface> iter = callbackList.iterator();
		while(iter.hasNext()) {
			iter.next().getMessage(msg);
		}
	}
	
	/**
	 * Private class that handles recieving messages from the server which the client is connected to.
	 * 
	 * @author Oscar Strandmark
	 */
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
