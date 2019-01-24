package l02;

import java.util.*;
import java.io.*;

public class Exercise2 {
	private ArrayList<Person> list = new ArrayList<Person>();

	public Exercise2(String filename) {
		list = Exercise1.readPersons(filename);
	}

	public String toString() {
		return "Lagrade Person-objekt: " + list.toString();
	}

	public void printPersons() {
		String retVal = "[";
		for (int i = 0; i < list.size(); i++) {
			Person current = list.get(i);
			if (i != list.size() - 1) {
				retVal += current.getId() + " " + current.getFirstName() + " " + current.getLastName() + ", ";
			} else {
				retVal += current.getId() + " " + current.getFirstName() + " " + current.getLastName() + "]";
			}
		}
		System.out.println(retVal);
	}

	public int position(String id) {
		for (int i = 0; i < list.size(); i++) {
			if (id.equals(list.get(i).getId())) {
				return i;
			}
		}
		return -1;
	}

	public void printName(String id) {
		for (int i = 0; i < list.size(); i++) {
			if (id.equals(list.get(i).getId())) {
				System.out.println(list.get(i).getFirstName() + " " + list.get(i).getLastName());
				break;
			}
		}
	}

	public boolean existsFirstName(String firstName) {
		for (int i = 0; i < list.size(); i++) {
			if (firstName.equals(list.get(i).getFirstName())) {
				return true;
			}
		}
		return false;
	}

	public boolean changeLastName(String id, String lastName) {
		for (int i = 0; i < list.size(); i++) {
			if (id.equals(list.get(i).getId())) {
				list.get(i).setLastName(lastName);
				return true;
			}
		}
		return false;
	}

	// Uppgift 3a
	public int position2(String id) {
		Person p = new Person(id, "", "");
		return list.indexOf(p);
	}

	// Uppgift 3b
	public void sort() {
		Collections.sort(list);
	}

	// Uppgift 3b
	public int position3(String id) {
		Person p = new Person(id, "", "");
		return Collections.binarySearch(list, p);
	}

	public static void main(String[] args) {
		Exercise2 ex2 = new Exercise2("C:\\\\Users\\\\oscar\\\\git\\\\DA343A\\\\src\\\\l02\\\\files\\\\personer.txt");
		System.out.println(ex2.toString());
		ex2.printPersons();
		System.out.println(ex2.position("840102-4567"));
		System.out.println(ex2.position("111111-2222"));
		ex2.printName("840102-4567");
		ex2.printName("111111-2222");
		System.out.println(ex2.existsFirstName("Edit"));
		System.out.println(ex2.existsFirstName("Anna"));
		ex2.changeLastName("660503-8901", "Trädrot");
		System.out.println(ex2.toString());
		System.out.println(ex2.position2("840102-4567")); // Uppgift 3a
        ex2.sort(); // Uppgift 3b
        System.out.println( ex2.toString() );
        System.out.println( ex2.position3("840102-4567") ); // Uppgift 3b
	}
}
