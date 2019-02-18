package f07.udpserver1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

public class UDPServer {
	private DatagramSocket socket;
	private ClientListener clientListener;
	private Thread listener = new Thread(new UDPListener());

	public UDPServer(int requestPort, ClientListener clientListener) {
		this.clientListener = clientListener;
		try {
			socket = new DatagramSocket(requestPort); 
			socket.setSoTimeout( 100 );
			listener.start();
		} catch(Exception e) { 
			System.err.println(e);
		}
	}

	public void stop() {
		if(socket!=null) {
			listener.interrupt();
		}
	}

	public void send(InetAddress address, int clientPort, String str) {
		if(socket!=null) {
			byte[] data = str.getBytes();
			DatagramPacket packet = new DatagramPacket(data,data.length,address,clientPort);
			try {
				socket.send(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
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
					clientListener.receive(new String(packet.getData(),0,packet.getLength()), packet.getAddress(),packet.getPort());
				} catch(SocketTimeoutException e) {
				} catch(Exception e) { 
					break;
				}
			}
			socket.close();
			socket = null;
		}
	}
}
