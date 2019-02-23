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
				e.printStackTrace();
			}
		}
	}
	
	private class ClientHandler extends Thread {
		
		private Socket socket;
		
		public ClientHandler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			String request, response;
			String[] parts;
			double nbr1,nbr2,answer;
			char op;
			try(DataInputStream dis = new DataInputStream(socket.getInputStream());
				DataOutputStream dos = new DataOutputStream(socket.getOutputStream());){
				while(true) {
					request = dis.readUTF();
					parts = request.split(",");
					nbr1 = Double.parseDouble(parts[0]);
					nbr2 = Double.parseDouble(parts[1]);
					op = parts[2].charAt(0);
					answer = calculator.calculator(nbr1, nbr2, op);
					
					response = answer + "\n" + nbr1 + op + nbr2 + "=" + answer;
					dos.writeUTF(response);
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
