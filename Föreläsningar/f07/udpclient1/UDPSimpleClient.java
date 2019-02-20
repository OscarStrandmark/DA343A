package f07.udpclient1;

import javax.swing.JOptionPane;

public class UDPSimpleClient {
	private UDPClient connection;
	private String menutext = "Ange val:\n1. PUT\n2. GET\n3. CLR\n4. EXIT";
	private String name;
	
	public UDPSimpleClient(String ip, int port, String name) {
		connection = new UDPClient(ip,port,new Listener());
		this.name = name;
		new Communicate().start();
	}
	
	private String getRequest() {
		String request = "";
		String choice = JOptionPane.showInputDialog(menutext);
		if(choice.equals("1")) {
			request = "PUT"+JOptionPane.showInputDialog("ange text att lägga till");
		} else if(choice.equals("2")) {
			request = "GET";
		} else if(choice.equals("3")) {
			request = "CLR";
		} else if(choice.equals("4")) {
			request = "EXIT";
		}
		return request;
	}

	private class Communicate extends Thread {
		public void run() {
			String request = getRequest();
			while(!request.equals("EXIT")) {
				if(request.length()>0) {
					connection.send(request);
				}
				request = getRequest();
			}
			System.exit(0);
		}
	}
	
	private class Listener implements ServerListener {
		public void receive(String s) {
			System.out.println(name+"\n"+s);
		}
	}
	
	public static void main(String[] args) {
		UDPSimpleClient sc1 = new UDPSimpleClient("localhost",3500,"Client A");
	}
}
