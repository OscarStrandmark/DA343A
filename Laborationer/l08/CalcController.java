package l08;

import java.net.*;
import java.nio.file.attribute.DosFileAttributes;
import java.io.*;

public class CalcController {

	private Socket socket;
	private String ip;
	private int port;
	private boolean connected = false;
	
	private DataInputStream dis;
	private DataOutputStream dos;

	public CalcController(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	private void connect() {
		if (!connected) {
			try {
				socket = new Socket(ip, port);
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				new Listener().start();
				connected = true;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	private void disconnect() {
		if(connected) {
			try {
				socket.close();
			} catch (Exception e) {
				connected = false;
			}
		}
	}

	public void newCalculation(String nbr1, String nbr2, String operation) {
		if(connected) {
			try {
				dos.writeUTF(nbr1 + "," + nbr2 + "," + operation);
				dos.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	private class Listener extends Thread {
		public void run() {
			String answer;
			
		}
	}
}
