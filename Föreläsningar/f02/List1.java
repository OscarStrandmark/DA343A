package f02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class List1 {
	
	private List<Person> getList() {
		ArrayList<Person> list = new ArrayList<Person>();
//		LinkedList<Person> list = new LinkedList<Person>();
		
		list.add(new Person("6234","ABC"));
		list.add(new Person("9876","EFG"));
		list.add(new Person("7654","CDE"));
		list.add(new Person("5432","DEF"));
		list.add(new Person("8123","BCD"));
		
		return list;
	}
	
	public void example1() {
		List<Person> persons = getList();
		System.out.println("Before sort: "+persons);
		Collections.sort(persons);
		System.out.println("Sorted: "+persons);
		System.out.println(Collections.binarySearch(persons, persons.get(3)));
		System.out.println(Collections.binarySearch(persons, new Person("7777","")));
		Collections.reverse(persons);
		System.out.println("reverse: "+persons);
		Collections.rotate(persons,3);
		System.out.println("rotate 3: "+persons);
		Collections.rotate(persons,-3);
		System.out.println("rotate back: "+persons);
		Collections.shuffle(persons);
		System.out.println("shuffle: "+persons);
	}
	
	public void example2() {
		List<Person> persons = getList();
//		Comparator<Person> comp = new IdDesc();
		Comparator<Person> comp = new Descending<Person>();
		System.out.println("Before sort: "+persons);
		Collections.sort(persons,comp);
		System.out.println("Sorted: "+persons);
		System.out.println(Collections.binarySearch(persons, persons.get(3), comp));
		System.out.println(Collections.binarySearch(persons, new Person("7777",""), comp));
	}
	
	public static void main(String[] args) {
		List1 prog = new List1();
//		prog.example1();
		prog.example2();
	}
}

class IdDesc implements Comparator<Person> {
    public int compare( Person p1, Person p2 ) {
        return p2.getId().compareTo(p1.getId()); 
    }
}

