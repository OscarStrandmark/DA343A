package l09;

import java.io.*;
import java.net.*;
import java.util.Date;

public class TimeServer implements Runnable {

	private Thread serverThread = new Thread(this);

	private int port;
	
	
	public TimeServer(int port) throws IOException {
		this.port = port;
		serverThread.start();
	}


	@Override
	public void run() {
		System.out.println("Server running on " + serverThread.getName());
		
		try (ServerSocket serverSocket = new ServerSocket(port)){
			while(serverSocket.isBound()) {
				try (Socket socket = serverSocket.accept();
					 BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))){
					bw.write(new Date().toString());
					bw.newLine();
					bw.flush();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void main(String[] args) throws IOException {
		new TimeServer(13);
	}
}
