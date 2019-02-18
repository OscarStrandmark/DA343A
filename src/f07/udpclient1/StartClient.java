package f07.udpclient1;

import javax.swing.SwingUtilities;

import f07.udpserver1.StringServer;

public class StartClient {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new ClientController("localhost",3500);
					new ClientController("127.0.0.1",3500);
				} catch(Exception e) {
					System.out.println("Program: "+e);
				}
			}
		});
	}
}
