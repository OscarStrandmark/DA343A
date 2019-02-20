package f07.udpserver1;

import javax.swing.SwingUtilities;

import f07.udpserver1.StringServer;

public class StartServer {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new StringServer(3500);
				} catch(Exception e) {
					System.out.println("Program: "+e);
				}
			}
		});
	}
}
