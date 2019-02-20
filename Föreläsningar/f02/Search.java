package f02;
import java.util.*;

public class Search {
	private final int SIZE = 100000;
    private HashMap<String, Person> hash = new HashMap<String, Person>();
    private TreeMap<String, Person> tree = new TreeMap<String, Person>();
    private ArrayList<Person> list1 = new ArrayList<Person>();
    private ArrayList<Person> list2 = new ArrayList<Person>();
    private Person[] test = new Person[SIZE];
    private Stopwatch watch = new Stopwatch();

    public Search() {
    	Person.getPersons(test);
    }

    public void test(int n) {
        Person person; 
        long hashGet, treeGet, list1Get, list2Get;
        int index;

        hash.clear();
        tree.clear();
        list1.clear();
        list2.clear();
        n = Math.min(n, SIZE);
        for (int i = 0; i < n; i++) {
            hash.put(test[i].getId(), test[i]);
            tree.put(test[i].getId(), test[i]);
            list1.add(test[i]);
            list2.add(test[i]);
        }
        Collections.sort(list2); 

        watch.start();
        for (int i = 0; i < 100000; i++) {
            person = hash.get(test[i % n].getId());
        }
        watch.stop();
        hashGet = watch.getMilliseconds();
        
        watch.start();
        for (int i = 0; i < 100000; i++) {
            person = tree.get(test[i % n].getId());
        }
        watch.stop();
        treeGet = watch.getMilliseconds();

        watch.start();
        for (int i = 0; i < 100000; i+=10) {
            index = list1.indexOf(test[i % n]);
            person = list1.get(index);
        }
        watch.stop();
        list1Get = watch.getMilliseconds()*10;

        watch.start();
        for (int i = 0; i < 100000; i++) {
            index = Collections.binarySearch(list2, test[i % n]);
            person = list2.get(index);
        }
        watch.stop();
        list2Get = watch.getMilliseconds();

        System.out.println(n + "  HashMap: get=" + hashGet + "    TreeMap: get=" + treeGet + 
        		"    ArrayList, osort=" + list1Get + "    ArrayList, sort=" + list2Get);
    }

    public static void main(String[] args) {
        Search speed = new Search();
        speed.test(1000);
        speed.test(10000);
        speed.test(100000);

    }
}