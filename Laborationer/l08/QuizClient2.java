package l08;

import java.io.*;
import java.net.*;

import javax.swing.JOptionPane;

public class QuizClient2 {

	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private String ip;
	private int port;
	private boolean connected;

	public QuizClient2(String ip, int port) {
		this.ip = ip;
		this.port = port;
		new Send().start();
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
				System.out.println("connect(): " + e);
			}
		}
	}

	private void disconnect() {
		if (connected) {
			try {
				socket.close();
			} catch (Exception e) {
				connected = false;
			}
		}
	}

	private int getInt(String txt) {
		int nbr = 0;
		boolean nbrOk = false;
		do {
			try {
				nbr = Integer.parseInt(JOptionPane.showInputDialog(txt));
				nbrOk = true;
			} catch (Exception e) {}
		} while(!nbrOk);
		return nbr;
	}

	private void question() {
		int nbr;
		if (connected) {
			try {
				nbr = getInt("Ange frågans nummer (1-20)");
				dos.writeUTF("QUESTION");
				dos.writeInt(nbr);
				dos.flush();
				System.out.println("QUESTION skickad: " + nbr);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
	
	private void answer() {
		int nbr;
		int answer;
		if(connected) {
			try {
				nbr = getInt("Ange frågans nummer (1-20)");
				answer = getInt("Ange årtal");
				dos.writeUTF("ANSWER");
				dos.writeInt(nbr);
				dos.writeInt(answer);
				dos.flush();
				System.out.println("ANSWER skickad: " + nbr + ", " + answer);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	private class Send extends Thread {
		
		public void run() {
			String menu = "1. Koppla upp \n";
			menu +="2. Koppla ner \n";
			menu +="3. Hämta fråga \n";
			menu +="4. Lämna svar \n \n";
			menu +="0. Avsluta";
			
			int choice = getInt(menu);
			
			while(choice != 0) {
				switch (choice) {
				case -1:
					choice = getInt(menu);
					break;
				case 1:	
					connect();
					break;
				case 2:
					disconnect();
					break;
				case 3:
					question();
					break;
				case 4:
					answer();
					break;
				}
			}
		}
	}
	
	private class Listener extends Thread {
		public void run() {
			String request;
			String response;
			int nbr;
			try {
				System.out.println("listening");
				request = dis.readUTF();
				System.out.println(request);
				while(true) {
					request = dis.readUTF();
					nbr = dis.readInt();
					response = dis.readUTF();
					System.out.println(request + ", fråga " + nbr + "\n" + response);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			System.out.println("Not listening");
			disconnect();
		}
	}
	
	public static void main(String[] args) {
		new QuizClient2("195.178.227.53",1494);
	}
}
