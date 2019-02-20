package f07;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressEx1 {
	public void myIP() {
		try {
			System.out.println("Denna dator:");
			InetAddress myAddress = InetAddress.getLocalHost(); 
			System.out.println(myAddress.getHostAddress());
			System.out.println(myAddress.getHostName());
		} catch (UnknownHostException ex) {
			System.out.println("Kan ej finna adressen till datorn");
		}		
	}
	
	public void ip(String host) {
		try {
			System.out.println(host+" till IP:");
			InetAddress address = InetAddress.getByName(host); 
			System.out.println(address);
		} catch (UnknownHostException ex) {
			System.out.println("Kan ej finna "+host);
		}		
	}
	
	public static void main(String[] args) {
		InetAddressEx1 ia = new InetAddressEx1();
		ia.myIP();
		ia.ip("www.dn.se");
	}
}
