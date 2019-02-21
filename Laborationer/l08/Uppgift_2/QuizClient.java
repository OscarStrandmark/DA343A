package l08.Uppgift_2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class QuizClient {

	private QuizController controller;
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	public QuizClient(QuizController controller) {
		this.controller = controller;
	}


	public void connect(String ip, int port) throws IOException{
		socket = new Socket(ip,port);
		dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		new Listener().start();
	}
	
	public void disconnect() throws IOException {
		if(socket != null) {
			socket.close();
		}
	}
	
	public void question(int nbr) throws IOException {
		dos.writeUTF("QUESTION");
		dos.writeInt(nbr);
		dos.flush();
	}

	public void answer(int nbr, int answer) throws IOException {
		dos.writeUTF("ANSWER");
		dos.writeInt(nbr);
		dos.writeInt(answer);
		dos.flush();
	}

	private class Listener extends Thread {
		public void run() {
			String request;
			int nbr;
			String response;
			try {
				response = dis.readUTF();
				controller.connected(response);
				while(true) {
					request = dis.readUTF();
					nbr = dis.readInt();
					response = dis.readUTF();
					if("QUESTION".equals(request.toUpperCase())){
						controller.newQuestion(response, nbr);
					} else {
						controller.newResponse(response, nbr);
					}
				}
			} catch (Exception e) {
				controller.disconnected(e.toString());
			}
		}
	}

}
