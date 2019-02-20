package f02;
import java.util.*;

public class ListSpeed {
    private static int ELEMENTS = 50000;
    private Person[] persons = new Person[ELEMENTS];
    private Vector<Person> vector = new Vector<Person>();
    private ArrayList<Person> arraylist = new ArrayList<Person>();
    private LinkedList<Person> linkedlist = new LinkedList<Person>();
    private Stopwatch watch = new Stopwatch();

    public ListSpeed() {
        Person.getPersons(persons);
    }

    public void clear() {
        vector.clear();
        arraylist.clear();
        linkedlist.clear();
    }
    
    private long timeRemoveFirst(List<Person> list) {
        watch.start();
        for (int i = 0; i < ELEMENTS; i++)
            list.remove(0);
        watch.stop();
        return watch.getMilliseconds();
    }
    
    public void removeFirst() {
        long vTime, aTime, lTime;
        vTime = timeRemoveFirst(vector);
        aTime = timeRemoveFirst(arraylist);
        lTime = timeRemoveFirst(linkedlist);
        System.out.println("Ta bort först:  Vector=" + vTime
                + "  ArrayList=" + aTime + "  LinkedList=" + lTime);
    }
    
    private long timeRemoveMiddle(List<Person> list) {
        watch.start();
        for (int i = 0; i < ELEMENTS; i++)
            list.remove(list.size()/2);
        watch.stop();
        return watch.getMilliseconds();
    }
    
    public void removeMiddle() {
        long vTime, aTime, lTime;
        vTime = timeRemoveMiddle(vector);
        aTime = timeRemoveMiddle(arraylist);
        lTime = timeRemoveMiddle(linkedlist);
        System.out.println("Ta bort i mitten:  Vector=" + vTime
                + "  ArrayList=" + aTime + "  LinkedList=" + lTime);
    }
    
    private long timeRemoveLast(List<Person> list) {
        watch.start();
        for (int i = 0; i < ELEMENTS; i++)
            list.remove(list.size()-1);
        watch.stop();
        return watch.getMilliseconds();
    }
    
    public void removeLast() {
        long vTime, aTime, lTime;
        vTime = timeRemoveLast(vector);
        aTime = timeRemoveLast(arraylist);
        lTime = timeRemoveLast(linkedlist);
        System.out.println("Ta bort sist:  Vector=" + vTime
                + "  ArrayList=" + aTime + "  LinkedList=" + lTime);
    }
    
    
    private long timeAddFirst(List<Person> list) {
        watch.start();
        for (int i = 0; i < ELEMENTS; i++)
            list.add(0, persons[i]);
        watch.stop();
        return watch.getMilliseconds();
    }
    
    public void addFirst() {
        long vTime, aTime, lTime;
        vTime = timeAddFirst(vector);
        aTime = timeAddFirst(arraylist);
        lTime = timeAddFirst(linkedlist);
        System.out.println("Lägg till först:  Vector=" + vTime
                + "  ArrayList=" + aTime + "  LinkedList=" + lTime);
    }
    
    private long timeAddMiddle(List<Person> list) {
        watch.start();
        for (int i = 0; i < ELEMENTS; i++)
            list.add(list.size()/2, persons[i]);
        watch.stop();
        return watch.getMilliseconds();
    }
    
    public void addMiddle() {
        long vTime, aTime, lTime;
        vTime = timeAddMiddle(vector);
        aTime = timeAddMiddle(arraylist);
        lTime = timeAddMiddle(linkedlist);
        System.out.println("Lägg till i mitten:  Vector=" + vTime
                + "  ArrayList=" + aTime + "  LinkedList=" + lTime);
    }
    
    private long timeAddLast(List<Person> list) {
        watch.start();
        for (int i = 0; i < ELEMENTS; i++)
            list.add(list.size(), persons[i]);
        watch.stop();
        return watch.getMilliseconds();
    }
    
    public void addLast() {
        long vTime, aTime, lTime;
        vTime = timeAddLast(vector);
        aTime = timeAddLast(arraylist);
        lTime = timeAddLast(linkedlist);
        System.out.println("Lägg till sist:  Vector=" + vTime
                + "  ArrayList=" + aTime + "  LinkedList=" + lTime);
    }

    public static void main(String[] args) {
        ListSpeed speed = new ListSpeed();

        speed.addFirst();
        speed.removeFirst();
        speed.clear();
        speed.addMiddle();
        speed.removeMiddle();
        speed.clear();
        speed.addLast();
        speed.removeLast();
    }
}
