package f03;
import java.io.*;

import javax.swing.*;

public class PersonTest {

	private static void writePersons(Person[] pers) throws IOException {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("files/personer.dat"))){
			oos.writeObject(pers);
		}
	}

	private static Person[] readPersons() throws IOException {
		Person[] pers=null;
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("files/personer.dat"))) {
			pers = (Person[])ois.readObject();
		}
		catch(ClassNotFoundException e) {}
		return pers;
	}

	private static String information(Person[] personer) {
		String res = "";
		for(int i=0; i<personer.length; i++)
			res += personer[i] + "\n";
		return res;
	}

	public static void main(String [] args) throws IOException{
		Person[] persons = {new Person("Liam"),new Person("Charlie"),
				new Person("Saga"),new Person("Olivia"),
				new Person("Vincent")};

		JOptionPane.showMessageDialog( null, PersonTest.information(persons) );

		persons[0].together(persons[2]);
		JOptionPane.showMessageDialog( null, PersonTest.information(persons)+ "\nSparar lyckligt tillstånd!" );
		PersonTest.writePersons(persons);

		persons[2].separate();
		persons[1].together(persons[3]);
		JOptionPane.showMessageDialog( null, PersonTest.information(persons) + "\nHämtar lyckligt tillstånd!" );

		persons = PersonTest.readPersons();
		JOptionPane.showMessageDialog( null, PersonTest.information(persons) );
	}
}