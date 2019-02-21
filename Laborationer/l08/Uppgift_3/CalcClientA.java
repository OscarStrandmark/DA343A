package l08.Uppgift_3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CalcClientA implements CalcClient {

	private CalcController controller;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	private String address;
	private int port;
	
	public CalcClientA(String address, int port) {
		this.address = address;
		this.port = port;
	}

	public void newCalculation(String expression) throws IOException {
		connect();
		dos.writeUTF(expression);
		dos.flush();
		String response = dis.readUTF();
		controller.newResponse(response);
		disconnect();
	}

	public void setCalcController(CalcController controller) {
		this.controller = controller;
	}

	private void connect() throws IOException {
		socket = new Socket(address,port);
		socket.setSoTimeout(5000);
		dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	}
	
	private void disconnect() throws IOException {
		socket.close();
	}
}
