package f02;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map;

public class Maps {
    public static void main(String[] args) {
    	Map<String, Person> dictionary = new HashMap<String, Person>();
//        Map<String, Person> dictionary = new TreeMap<String, Person>();
        Person[] persons = new Person[6];
        Person.getPersons(persons);
        for(Person p : persons) {
        	dictionary.put(p.getId(), p);
        }
        System.out.println(dictionary);
        System.out.println("Antal <key,value>-par: " + dictionary.size());
        System.out.println(dictionary.get("12345"));
        String key = persons[3].getId();
        System.out.println("key="+key+", "+dictionary.get(key));
        System.out.println("remove: " + dictionary.remove(key));
        System.out.println("Antal <key,value>-par: " + dictionary.size());
    }
}
