package l09;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServerA extends Thread {

	private Calculator calculator;
	private ServerSocket servSocket;
	
	private DataInputStream dis;
	private DataOutputStream dos;
	
	private int port;
	
	public CalcServerA(Calculator calculator, int port) {
		
		try {
			this.calculator = calculator;
			this.port = port;
			servSocket = new ServerSocket(port);
			new Thread(this).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		System.out.println("Server is running on " + this.getName());
		String request;
		String[] parts;
		
		while(true) {
			try (Socket socket = servSocket.accept()){
				System.out.println(socket.getInetAddress().getHostName());
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				
				request = dis.readUTF();
				parts = request.split(",");
				
				if(parts.length == 3) {
					double nbr1 = Double.parseDouble(parts[0]);
					double nbr2 = Double.parseDouble(parts[1]);
					char op = parts[2].charAt(0);
					double answer = calculator.calculator(nbr1, nbr2, op);
					String result = answer + "\n" + nbr1 + op + nbr2 + "=" + answer;
					dos.writeUTF(result);
					dos.flush();
					socket.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
