package l09;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServerD {
	
	private Calculator calculator;
	private int port;
	
	public CalcServerD(Calculator calculator, int port) {
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
			try (ServerSocket serverSocket = new ServerSocket(port)){
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
			System.out.println("Server stopped");
		}
		
	}
	
	private class ClientHandler extends Thread {
		
		
		private Socket socket;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		
		public ClientHandler(Socket socket) throws IOException {
			this.socket = socket;
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			start();
		}
		
		public void run() {
			Expression exp;
			double answer;
			Calculation response;
			try {
				while(true) {
					exp = (Expression)ois.readObject();
					answer = calculator.calculator(exp.getNbr1(), exp.getNbr2(), exp.getOperation());
					response = new Calculation(answer, exp);
					oos.writeObject(response);
					oos.flush();
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
