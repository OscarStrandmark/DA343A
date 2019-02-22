package l09;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TimeServer extends Thread {

	public TimeServer() {}

	public void run() {
		System.out.println("Server is running on " + this.getName());
		try (ServerSocket serverSocket = new ServerSocket(13)) {
			while (true) {
				try (Socket socket = serverSocket.accept();
					 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					){
					bw.write(new Date().toString());
					bw.newLine();
					bw.flush();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		new TimeServer().start();
	}
}
