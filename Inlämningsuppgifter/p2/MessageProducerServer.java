package p2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import p1.MessageProducerInput;

public class MessageProducerServer {

	private MessageProducerInput MPI;
	private int port;
	
	public MessageProducerServer(MessageProducerInput mpInput, int port) {
		this.MPI = mpInput;
		this.port = port;
	}

	public void startServer() {
		new ConnectionHandler(port).start();
	}
	
	private class ConnectionHandler extends Thread {
		
		private int port;
		
		public ConnectionHandler(int port) {
			this.port = port;
		}

		public void run() {
			Socket socket = null;
			System.out.println("MessageProducerServer started");
			try(ServerSocket serverSocket = new ServerSocket(port)){
				while(true) {
					try {
						socket = serverSocket.accept();
						new ClientHandler(socket);
					} catch (Exception e) {
						System.err.println(e);
						if(socket != null) {
							socket.close();
						}
					}
				}
			} catch (Exception e) {
				System.err.println(e);
			}
			System.out.println("Server stopped running");
		}
	}

	private class ClientHandler extends Thread {
		
		private Socket socket;
		private ObjectInputStream ois;
		
		public ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
			ois = new ObjectInputStream(socket.getInputStream());
			start();
		}

		public void run() {
			ArrayProducer recieved;
			try {
				while(true) {
					recieved = (ArrayProducer)ois.readObject();
					MPI.addMessageProducer(recieved);
				}
			} catch (Exception e) {
				try {
					socket.close();
				} catch (Exception e2) {}
			}
			System.out.println("Client disconnected");
		}
	}
}
/*
MessageProducerServer måste veta på vilken port den ska lyssna hantera uppkopplingar.
Servern måste också ha en referens till MessageProducerInput. Servern utgörs troligen av ett
antal klasser (kan vara inre klasser).
Servern ska vara en iterativ server som använder en tråd. Servern ska alltså hanterar en klient i
taget. Kommunikationen mellan klient och server sker med objektströmmar, dvs
ObjectInputStream respektive ObjectOutputStream ska användas.
*/