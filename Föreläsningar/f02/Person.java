package f02;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Person implements Comparable<Person>{
    private String id;
    private String name;
  
    public Person(String id, String name) {
    	this.id = id;
        this.name = name;
    }
    
    public void setId(String id) {
    	this.id = id;
    }
    
    public String getId() {
    	return id;
    }
  
    public void setName(String name) {
        this.name = name;
    }
  
    public String getName() {
        return name;
    }

    public String toString() {
        return ("(id="+id+", name="+name+")");
    }
    
    public int compareTo( Person p ) {
        return id.compareTo(p.id);      // p.id.compareTo(id) ordnar avtagande
    }
    
    public boolean equals(Object obj) {
    	boolean res = false;
    	if(obj instanceof Person) {
    		Person p = (Person)obj;
    		res = id.equals(p.id);
    	}
    	return res;
    }
    
    
    
    public static void getPersons(Person[] arr) {
    	String idStr = "0123456789";
    	String nameStr = "abcdefghijklmnopqrstuvwxyz";
    	for(int i=0; i<arr.length; i++) {
    		arr[i] = new Person(getRandomString(idStr,6),getRandomString(nameStr,4));
    	}
    }
    
    public static void getPersons(Collection<Person> coll, int count) {
    	String idStr = "0123456789";
    	String nameStr = "abcdefghijklmnopqrstuvwxyz";
    	while(count-- > 0) {
    		coll.add(new Person(getRandomString(idStr,6),getRandomString(nameStr,4)));
    	}
    }
    
    public static void getPersons(Map<String,Person> map, int count) {
    	String idStr = "0123456789";
    	String nameStr = "abcdefghijklmnopqrstuvwxyz";
    	String id;
    	while(count-- > 0) {
    		id = getRandomString(idStr,6);
    		map.put(id, new Person(id,getRandomString(nameStr,4)));
    	}
    }

    private static String getRandomString(String str, int n) {
    	Random rnd = new Random();
    	StringBuilder res = new StringBuilder(n);
    	while(n-- > 0) {
    		res.append(str.charAt(rnd.nextInt(str.length())));
    	}
    	return res.toString();
    }
    
    public static void main(String[] args) {
//		ArrayList list = new ArrayList();
//		getPersons(list,50);
//		for(int i=0; i<list.size(); i++) {
//			System.out.println(list.get(i).toString());
//		}
//		HashMap<String,Person> map = new HashMap<String,Person>();
//		getPersons(map,20);
//		Iterator iter = map.values().iterator();
//		while(iter.hasNext()) {
//			System.out.println(iter.next().toString());
//		}
    }
}
