package l03;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Uppgift_1 {

	private static String filename = "C:\\Users\\oscar\\git\\DA343A\\src\\l03\\files\\heltal.dat";

	public void sum(String filename) throws IOException {
		try (DataInputStream dis = new DataInputStream(new FileInputStream(filename))) {
			int sum = 0;
			int n = dis.readInt();
			System.out.println(n + ":");

			for (int i = 0; i < n; i++) {
				System.out.println(sum += dis.readInt());
			}
			dis.close();
			System.out.println("Summan är: " + sum);
		}
	}

	public static void main(String[] args) {
		Uppgift_1 u1 = new Uppgift_1();
		try {
			u1.sum(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
