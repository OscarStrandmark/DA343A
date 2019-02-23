package l09;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServerB implements Runnable {

	private Calculator calculator;
	private ServerSocket servSocket;
	private Thread server = new Thread(this);

	private int port;
	
	public CalcServerB(Calculator calculator, int port) {
		try {
			this.calculator = calculator;
			this.port = port;
			servSocket = new ServerSocket(port);
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println("Server running on " + server.getName());
		while(true) {
			try {
				Socket socket = servSocket.accept();
				new ClientHandler(socket).start();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	private class ClientHandler extends Thread {
		
		private Socket socket;
		
		public ClientHandler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			
			try(DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());){
				while(true) {
					double nbr1 = dis.readDouble();
					double nbr2 = dis.readDouble();
					char op = dis.readChar();
					double answer = calculator.calculator(nbr1, nbr2, op);
					String response = answer + "\n" + nbr1 + op + nbr2 + "=" + answer;
					dos.writeUTF(response);
					dos.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (Exception e) {
				System.out.println("Klient nerkopplad");
			}
			
		}
	}
	
}
