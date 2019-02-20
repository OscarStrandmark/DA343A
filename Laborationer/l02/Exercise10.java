package l02;

import java.util.*;

public class Exercise10 {
	private LinkedList<String> list = new LinkedList<String>();

	public void add(String str) {
		list.add(str);
	}

	public void remove(String str) {
		list.remove(str);
	}

	public void print() {
		Iterator<String> iter = list.iterator();

		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

	public void printReverse() {
		Object[] listArr = list.toArray();

		for (int i = listArr.length; i >= 0; i--) {
			System.out.println(listArr[i]);
		}
	}

	public int size() {
		return list.size();
	}

	public void print6() {
		Iterator<String> iter = list.iterator();

		while (iter.hasNext()) {
			String current = iter.next();
			if (current.length() >= 6) {
				System.out.println(current);
			}
		}
	}

	public int count(char chr) {
		Iterator<String> iter = list.iterator();
		int count = 0;
		while (iter.hasNext()) {
			String current = iter.next();
			if (current.charAt(0) == chr) {
				count++;
			}
		}
		return count;
	}
}