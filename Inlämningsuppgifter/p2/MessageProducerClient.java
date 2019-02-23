package p2;

import java.io.IOException;
import java.net.Socket;

import p1.MessageProducer;

public class MessageProducerClient {

	private Socket socket;
	
	private String address;
	private int port;
	
	public MessageProducerClient(String address, int port) {
		try {
			socket = new Socket(address, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(MessageProducer messageProducer) {
		
	}

}

/*
MessageProducerClient måste känna till ipadress och uppkopplingsport till
MessageProducerServer. Klienten utgörs
troligen av ett antal klasser (kan vara inre
klasser). Klienten ska koppla upp mot server,
överföra en MessageProducer-implementering och sedan koppla ner förbindelsen.
*/