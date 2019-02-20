package f07.udpclient1;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ClientController {
	private ClientPanel clientUI = new ClientPanel(this);
	private UDPClient connection;

	public ClientController(String ipAddress, int port) {
		ServerListener listener = new ServerListener() {
			public void receive(String s) {
				clientUI.addResponse(s);
			}
		};
		try {			
			connection = new UDPClient(ipAddress,port,listener);
			showClient();
		} catch(Exception e) { 
			System.err.println(e);
		}
	}


	private void showClient() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				clientUI = new ClientPanel(ClientController.this);
				JFrame frame = new JFrame();
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(clientUI);
				frame.pack();
				frame.setVisible(true);				
			}
		});
	}

	public void sendMessage(String message) {
		connection.send(message);
	}

	public void exit() {
		connection.stop();
	}
}
