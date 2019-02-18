package f07.udpclient1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class UDPClient {
	private DatagramSocket socket;
	private InetAddress address;
	private int serverPort;
	private ServerListener serverListener;
	private Thread listener = new Thread(new UDPListener());

	public UDPClient(String ipAddress, int port, ServerListener serverListener) {
		this.serverPort = port;
		this.serverListener = serverListener;
		try {
			this.address = InetAddress.getByName(ipAddress);
			this.socket = new DatagramSocket();  // ledig port
			this.socket.setSoTimeout(100); // avbrott
			this.listener.start();
		} catch(Exception e) { 
			System.err.println(e);
		}
	}

	public void send(String str) {
		if(socket!=null) {
			byte[] data = str.getBytes();
			DatagramPacket packet = new DatagramPacket(data,data.length,address,serverPort);
			try {
				socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void stop() {
		if(listener!=null) {
			listener.interrupt();
		}
	}

	private class UDPListener extends Thread {
		public void run() {
			DatagramPacket packet;
			byte[] readBuffer = new byte[1024];
			while(!Thread.interrupted()) {
				try {
					packet = new DatagramPacket(readBuffer,readBuffer.length);
					socket.receive(packet);	
					String response = new String(packet.getData(),0,packet.getLength());
					serverListener.receive(response);
				} catch(SocketTimeoutException e) {  
				} catch(Exception e) { // InterruptedException, IOException
					break;
				}
			}
			System.out.println(this+" avslutas");
			socket.close();
			socket = null;
		}
	}
}
