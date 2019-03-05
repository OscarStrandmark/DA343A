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

public class MessageServer {

	private MessageManager mm;
	private int port;
	
	private ArrayList<ClientHandler> clients;
	private Thread server;
	
	public MessageServer(MessageManager messageManager, int port) {
		this.mm = messageManager;
		this.port = port;
		clients = new ArrayList<ClientHandler>();
		mm.addObserver(new MMObserver());
		server = new Connection(port);
	}

	public void send2Clients(Message msg) {
		Iterator<ClientHandler> iter = clients.iterator();
		while(iter.hasNext()) {
			iter.next().put(msg);
		}
	}
	
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
						System.out.println("server listening for connections");
						Socket socket = serverSocket.accept();
						ClientHandler client = new ClientHandler(socket);
						clients.add(client);
						System.out.println("Clients: " + clients.size());
					} catch (Exception e) {
						System.out.println("E1");
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				System.out.println("E2");
				e.printStackTrace();
			}
		}
	}
	
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
