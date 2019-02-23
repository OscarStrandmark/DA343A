package l09;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServerC {

	private Calculator calculator;

	public CalcServerC(Calculator calculator, int port) {
		this.calculator = calculator;
		new Connection(port).start();
	}

	private class Connection extends Thread {

		private int port;

		public Connection(int port) {
			this.port = port;
		}

		public void run() {
			Socket socket = null;
			System.out.println("Server started");
			try (ServerSocket serverSocket = new ServerSocket(port)) {
				while (true) {
					try {
						socket = serverSocket.accept();
						new ClientHandler(socket);
					} catch (Exception e) {
						System.err.println(e);
						if (socket != null) {
							socket.close();
						}
					}
				}
			} catch (Exception e) {
				System.err.println(e);
			}
			System.out.println("Server stopped");
		}
	}

	private class ClientHandler extends Thread {

		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;

		public ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			start();
		}

		public void run() {
			double nbr1, nbr2, answer;
			char op;
			String response;

			try {
				while(true) {
					nbr1 = dis.readDouble();
					nbr2 = dis.readDouble();
					op = dis.readChar();
					answer = calculator.calculator(nbr1, nbr2, op);
					response = answer + "\n" + nbr1 + op + nbr2 + "=" + answer;
					dos.writeUTF(response);
					dos.flush();
				}
			} catch (IOException e) {
				try {
					socket.close();
				} catch (Exception e2) {}
			}
			System.out.println("Client disconnected");
		}
	}
}
