package l08;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CalcClientA implements CalcClient {
	private CalcController controller;
	private String ip;
	private int port;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;

	public CalcClientA(String ip, int port) throws IOException {
		this.ip = ip;
		this.port = port;
	}

	public void setCalcController(CalcController controller) {
		this.controller = controller;
	}

	private void connect() throws IOException {
		socket = new Socket(ip, port);
		socket.setSoTimeout(5000);
		dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	}

	private void disconnect() throws IOException {
		socket.close();
	}

	public void newCalculation(String expression) throws IOException {
		connect();
		dos.writeUTF(expression);
		dos.flush();
		String response = dis.readUTF();
		controller.newResponse(response);
		disconnect();
	}
}
