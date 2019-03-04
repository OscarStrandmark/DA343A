package p2;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import p1.Buffer;
import p1.MessageProducerInput;

public class MessageProducerServer {

	private Thread server;
	private MessageProducerInput MPI;

	public MessageProducerServer(MessageProducerInput mpInput, int port) {
		this.MPI = mpInput;
		server = new ConnectionHandler(port);
	}

	public void startServer() {
		server.start();
	}

	private class ConnectionHandler extends Thread {

		private int port;

		public ConnectionHandler(int port) {
			this.port = port;
		}

		public void run() {

			try (ServerSocket servSocket = new ServerSocket(port)) {
				System.out.println("MessageProducerServer started");
				while (true) {
					try {
						Socket socket = servSocket.accept();
						ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
						ArrayProducer ap = (ArrayProducer) ois.readObject();
						MPI.addMessageProducer(ap);
						socket.close();
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
