package l03;

import java.io.*;

public class Uppgift_4 {

	private final static String filename = "C:\\Users\\oscar\\git\\DA343A\\src\\l03\\files\\persons.dat";
	
	public void avg(String filename) throws IOException, ClassNotFoundException	{
		try(ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))){
		
			int amount = ois.readInt();
			int sum = 0;
			
			Person person;
			
			for (int i = 0; i < amount; i++) {
				person = (Person) ois.readObject();
				sum += person.getAge();
			}
			System.out.println("avg: " + sum / amount);
			ois.close();
		}
	}
	
	public void avgFemale(String filename) throws IOException, ClassNotFoundException {
		try (ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))){
			
			int amount = ois.readInt();
			int amount_female = 0;
			int sum = 0;
			
			Person person;
			
			for (int i = 0; i < amount; i++) {
				person = (Person) ois.readObject();
				
				if(person.getSex() == 'K') {
					sum += person.getAge();
					amount_female++;
				}
			}
			System.out.println("female avg: " + sum / amount_female);
			ois.close();
		}
	}
	
	public static void main(String[] args) {
		Uppgift_4 u4 = new Uppgift_4();

		try {
			u4.avg(filename);
			u4.avgFemale(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
