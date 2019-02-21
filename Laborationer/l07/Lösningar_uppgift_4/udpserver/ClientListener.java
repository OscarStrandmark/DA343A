package l07.Lösningar_uppgift_4.udpserver;

import java.net.InetAddress;

public interface ClientListener {
	public void receive(String s, InetAddress address, int port);
}
