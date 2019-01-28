package l03;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Uppgift_1 {

	private static FileInputStream inputStream;
	
	public static void main(String[] args) {

		try {
			inputStream = new FileInputStream("C:\\Users\\oscar\\git\\DA343A\\src\\l03\\files\\heltal.dat");
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Filen kunde inte hittas");
			e.printStackTrace();
		}
		
		DataInputStream data = new DataInputStream(inputStream);
		try {
			while(data.available() > 10) {
				try {
					System.out.println(data.readInt());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
