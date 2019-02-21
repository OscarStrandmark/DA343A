package l07;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Uppg3 {

	public static void main(String[] args) throws MalformedURLException {
		ImageIcon img = new ImageIcon(new URL("http://ddwap.mah.se/aj0173/dog.jpg"));
		JOptionPane.showMessageDialog(null, img);
	}
}
