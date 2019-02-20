package l07;

import java.net.DatagramPacket;

public class Uppgift_3_Server {

	private Thread listener = new Thread(new UDPListener());
	private DatagramPacket socket;
	public Uppgift_3_Server() {

		listener.start();
	}

	private class UDPListener extends Thread {

		public void run() {
			DatagramPacket packet;
			byte[] readBuffer = new byte[1024];
			while(!Thread.interrupted()) {
				try {
					packet = new DatagramPacket(readBuffer, readBuffer.length);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
}
