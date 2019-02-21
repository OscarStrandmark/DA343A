package l07.Uppgift4.client;

public class Controller {

	private UI ui;
	private Connection connection;
	
	public Controller(String ipAddress, int port) {
		ServerListener listener = new ServerListener() {

			public void receive(String s) {
				ui.addResponse(s);
			}
		};
		try {
			connection = new Connection(ipAddress, port, listener);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	public void addConnection(Connection conn) {
		connection = conn;
	}
	
	public void addUI(UI ui) {
		this.ui = ui;
	}
	
	public void sendMessage(String message) {
		connection.send(message);
	}
	
	public void newExpression(String op1, String op2, char op) {
		System.out.println("EXP:" + op1 + op + op2);
	}

}
