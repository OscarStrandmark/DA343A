package l07;

import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Uppgift_2 {

	public static void main(String[] args) {
		
		try {
			URL src = new URL("http://ddwap.mah.se/aj0173/dog.jpg");
			ImageIcon img = new ImageIcon(src);
			JOptionPane.showMessageDialog(null,img);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
