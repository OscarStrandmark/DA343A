package f07.udpserver1;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class StringServer {
	private ArrayList<String> words = new ArrayList<String>();
	private UDPServer connection;

	public StringServer(int requestPort) {
		TextWindow.println("Server: Start av UDPServer, Lyssnar på "+requestPort);
		connection = new UDPServer(requestPort, new ClientResponse());
	}

	private String get() {
		String response = "";
		for(int i=0; i<words.size(); i++) {
			response += (String)words.get(i) + "\n";
		}
		if(response.length()>1024)
			response = response.substring(0,1024);
		return response;
	}

	private String clear() {
		words.clear();
		return "cleared";
	}

	private String put(String str) {
		words.add(str);
		return str + " added";
	}

	private void send(InetAddress address, int clientPort, String response) throws IOException {
		connection.send(address, clientPort, response);
	}
	
	private void newRequest(String message, InetAddress address, int port) {
		String response="";
		try {
			TextWindow.println(message + " from "+address.getHostAddress()+":"+port);
			if(message.startsWith("GET")) {
				response=get();
			} else if(message.startsWith("CLR")) {
				response=clear();
			} else if(message.startsWith("PUT")) {
				response=put(message.substring(3,message.length()));
			} else
				TextWindow.println("Server: Okänd förfrågan: "+message);
			send(address,port,response);
		} catch(IOException e) { 
			System.err.println(e);
		}
	}

	private class ClientResponse implements ClientListener {
		public void receive(String message, InetAddress address, int port) {
			newRequest(message,address,port);
		}
	}
}
