package l08.Uppgift_3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CalcClientC implements CalcClient {

	private CalcController controller;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;

	public CalcClientC(String address, int port) throws IOException {
		socket = new Socket(address, port);
		dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		new Listener().start();
	}

	@Override
	public void newCalculation(String expression) throws IOException {
		String[] parts = expression.split(",");
		
		dos.writeDouble(Double.parseDouble(parts[0]));
		dos.writeDouble(Double.parseDouble(parts[1]));
		dos.writeChar(parts[2].charAt(0));
		dos.flush();
	}

	@Override
	public void setCalcController(CalcController controller) {
		this.controller = controller;
	}

	private class Listener extends Thread {
		public void run() {
			String response;
			try {
				while (true) {
					response = dis.readUTF();
					controller.newResponse(response);
				}
			} catch (Exception e) {
			}

			try {
				socket.close();
			} catch (Exception e) {
				controller.newResponse("Klienten kopplar ner");
			}
		}
	}
}
