package p2;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import p1.Buffer;
import p1.Message;
import p1.MessageManager;
/**
 * Class that recieves {@link Message} objects from the {@link MessageManager} and sends them to {@link MessageClient}-objects via TCP.
 * 
 * @author Oscar Strandmark
 */
public class MessageServer {

	private MessageManager mm;
	private int port;
	
	private ArrayList<ClientHandler> clients;
	private Thread server;
	
	/**
	 * Creates a MessageServer
	 * 
	 * @param messageManager the MessageManager which the server will recieve Messages from
	 * @param port The port the server will run on
	 */
	public MessageServer(MessageManager messageManager, int port) {
		this.mm = messageManager;
		this.port = port;
		clients = new ArrayList<ClientHandler>();
		mm.addObserver(new MMObserver());
		server = new Connection(port);
	}

	/**
	 * Used by the observer to transfer the Messages from the MessageManager to the Clients
	 * 
	 * @param msg
	 */
	private void send2Clients(Message msg) {
		Iterator<ClientHandler> iter = clients.iterator();
		while(iter.hasNext()) {
			iter.next().put(msg);
		}
	}
	
	/**
	 * Class that handles new incoming connections and creates a new Thread for each.
	 * 
	 * @author Oscar Strandmark
	 */
	private class Connection extends Thread {

		private int port;
		
		public Connection(int port) {
			this.port = port;
			start();
		}
		
		public void run() {
			try (ServerSocket serverSocket = new ServerSocket(port)){
				System.out.println("MessageServer Started");
				while(true) {
					try {
						Socket socket = serverSocket.accept();
						ClientHandler client = new ClientHandler(socket);
						clients.add(client);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Class that handles a connection and sends the messages to the Clients
	 * 
	 * @author Oscar Strandmark
	 */
	private class ClientHandler extends Thread {

		private Socket socket;

		private Buffer<Message> messageBuffer;
		
		public ClientHandler(Socket socket) {
			this.socket = socket;
			messageBuffer = new Buffer<Message>();
			start();
		}
		
		public void put(Message msg) {
			messageBuffer.put(msg);
		}
		
		public void run() {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				while(true) {
					try {
						Message msg = messageBuffer.get();
						oos.writeObject(msg);
						oos.flush();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private class MMObserver implements Observer {
		public void update(Observable o, Object arg) {
			Message msg = (Message)arg;
			send2Clients(msg);
		}
	}
}
