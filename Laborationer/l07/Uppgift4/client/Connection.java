package l07.Uppgift4.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class Connection {

	private DatagramSocket socket;
	private InetAddress address;
	private int port;
	private ServerListener serverListener;
	private Thread listener = new Thread(new UDPListener());

	public Connection(String address, int port, ServerListener serverListener) {
		
		this.port = port;
		this.serverListener = serverListener;
		
		try {
			this.address = InetAddress.getByName(address);
			this.socket = new DatagramSocket(port);
			this.socket.setSoTimeout(100);
			this.listener.start();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public void send(String s) {
		if (socket != null) {
			byte[] data = s.getBytes();
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			try {
				socket.send(packet);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public void stop() {
		if(listener != null) {
			listener.interrupt();
		}
	}
	
	private class UDPListener extends Thread {

		public void run() {
			DatagramPacket packet;
			byte[] readBuffer = new byte[1024];
			while(!Thread.interrupted()) {
				try {
					packet = new DatagramPacket(readBuffer, readBuffer.length);
					socket.receive(packet);
					String response = new String(packet.getData(),0,packet.getLength());
					serverListener.receive(response);
				} catch (SocketTimeoutException socketE) {
				} catch (Exception e) {
					break;
				}
			}
			socket.close();
			socket = null;
		}
	}
}
