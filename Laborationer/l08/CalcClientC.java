package l08;

import java.io.*;
import java.net.*;

public class CalcClientC implements CalcClient {
	private CalcController controller;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;

	public CalcClientC(String ip, int port) throws IOException {
		socket = new Socket(ip, port);
		dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		new Listener().start();
	}

	public void setCalcController(CalcController controller) {
		this.controller = controller;
	}

	public void newCalculation(String expression) throws IOException {
		double nbr1 = Double.NaN, nbr2 = Double.NaN;
		char operation = ' ';
		String[] parts = expression.split(",");
		try {
			nbr1 = Double.parseDouble(parts[0]);
			nbr2 = Double.parseDouble(parts[1]);
			operation = parts[2].charAt(0);
		} catch (Exception e) {
			controller.newResponse("Bad arguments: " + parts[0] + parts[2] + parts[1]);
			return;
		}
		dos.writeDouble(nbr1);
		dos.writeDouble(nbr2);
		dos.writeChar(operation);
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
