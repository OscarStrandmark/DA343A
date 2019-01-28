package l03;

import java.io.*;

public class Uppgift_2 {

	private static String filename = "C:\\Users\\oscar\\git\\DA343A\\src\\l03\\files\\medlemmar.txt";

	private void read(String filename) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
			String person = "";
			int count = 0;
			int countM = 0;
			int countF = 0;

			person = br.readLine();
			while (person != null) {
				count++;
				if ((person.charAt(person.length() - 1) == 'M')) {
					countM++;
				}
				if ((person.charAt(person.length() - 1) == 'K')) {
					countF++;
				}

				System.out.println(person.substring(0, person.length() - 2));
				person = br.readLine();
			}
			System.out.println("Antal medlemmar: " + count);
			System.out.println("Antal män: " + countM);
			System.out.println("Antal kvinnor: " + countF);
		}
	}

	public static void main(String[] args) {
		Uppgift_2 u2 = new Uppgift_2();
		try {
			u2.read(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
