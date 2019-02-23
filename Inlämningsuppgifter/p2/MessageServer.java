package p2;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import p1.Buffer;
import p1.Message;
import p1.MessageManager;

@SuppressWarnings("deprecation")

public class MessageServer {

	private int port;
	private List<ClientHandler> clientList;
	private Buffer<Message> msgBuffer;
	
	public MessageServer(MessageManager messageManager, int port) {
		messageManager.addObserver(new MessageObserver());
		this.port = port;
		new MessageSender().start();
		new ConnectionHandler(port).start();
	}

	
	private class ConnectionHandler extends Thread {

		private int port;
		
		public ConnectionHandler(int port) {
			this.port = port;
		}

		public void run() {
			Socket socket = null;
			System.out.println("MessageServer started");
			try (ServerSocket serverSocket = new ServerSocket(port)){
				while(true) {
					socket = serverSocket.accept();
					clientList.add(new ClientHandler(socket));
				}
			} catch (Exception e) {
			}
		}
		
	}
	
	private class ClientHandler extends Thread {

		private Socket socket;
		private ObjectOutputStream oos;
		
		public ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
			oos = new ObjectOutputStream(socket.getOutputStream());
			start();
		}

		
		public void send(Object obj) {
			try {
				oos.writeObject(obj);
				oos.flush();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	private class MessageSender extends Thread {
		public void run() {
			Message msg = null;
			while(true) {
				try {
					msg = msgBuffer.get();
					for (int i = 0; i < clientList.size(); i++) {
						clientList.get(i).send(msg);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private class MessageObserver implements Observer {

		public void update(Observable o, Object arg) {
			Message msg = (Message)arg;
			msgBuffer.put(msg);
		}
	}
}
