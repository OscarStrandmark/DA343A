package l08;

import java.io.*;
import java.net.Socket;

public class CalcClientB implements CalcClient {
	private CalcController controller;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;

	public CalcClientB(String ip, int port) throws IOException {
		socket = new Socket(ip, port);
		dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		new Listener().start();
	}

	public void setCalcController(CalcController controller) {
		this.controller = controller;
	}

	public void newCalculation(String expression) throws IOException {
		dos.writeUTF(expression);
		dos.flush();
	}

	private class Listener extends Thread {
		public void run() {
			String response;
			try {
				while (true) {
					response = dis.readUTF();
					controller.newResponse(response);
				}
			} catch (IOException e) {
			}
			try {
				socket.close();
			} catch (Exception e) {
			}
			controller.newResponse("Klient kopplar ner");
		}
	}
}