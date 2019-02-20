package f07;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class URLImage {
	private static void saveToFile(URL source, String filename) {
		try( BufferedInputStream bis = new BufferedInputStream(source.openStream());
			 BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filename))) {						

			InOut.copy(bis, bos);
		} catch(IOException e) {
			System.err.println(e);
		}
	}
	
	private static void showAsImageIcon(URL source) {
		ImageIcon icon = new ImageIcon(source);
		JOptionPane.showMessageDialog(null, icon);
	}
	
	public static void main(String[] args) throws MalformedURLException {
//		URL url = new URL("http://ddwap.mah.se/tsroax/memory/Memory.jpg");
		URL url = new URL("http","ddwap.mah.se","/tsroax/memory/Memory.jpg");
		showAsImageIcon(url);
		saveToFile(url,"files/Memory.jpg");
	}
}
