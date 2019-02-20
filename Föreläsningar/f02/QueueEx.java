package f02;
import java.util.*;

/**
 * queue1 ger exempel på en vanlig kö.
 * 
 * queue2 visar hur en PriorityQueue fungerar. 
 * Vid konstruktion utan parametrar ordnas elementen med Comparable-implementeringen. 
 *   I fallet med strängar så ordnas dessa växande.
 * Vid konstruktion med initial kapacitet och en Comparator-implementering ordnas
 *   elementen med Comparator-implementeringen. I det här fallet avtagande.
 * 
 * @author Rolf Axelsson
 */
public class QueueEx {	
	public static void addToQueue(Queue<Person> queue) {
        queue.add(new Person("66","A"));
        queue.add(new Person("36","B"));
        queue.add(new Person("66","C"));
        queue.add(new Person("66","D"));
        queue.add(new Person("34","E"));
        queue.add(new Person("66","F"));
	}
	
    public static void main(String[] args) {
        Queue<Person> queue1 = new LinkedList<Person>();
        Queue<Person> queue2 = new PriorityQueue<Person>(); // Elementen ordnas med Comparable-implementering. Ordningen mellan element som är lika stora är godtycklig.
        Queue<Person> queue3 = new PriorityQueue<Person>(4, new Descending<Person>()); // Elementen ordnas med Comparable-implementering. Ordningen mellan element som är lika stora är godtycklig.
        addToQueue(queue1);
        System.out.println("--- Queue ---");
        while(!queue1.isEmpty()) {
            System.out.println(queue1.remove());
        }
        System.out.println("--- Priority queue ---");
        addToQueue(queue2);
        while(!queue2.isEmpty()) {
            System.out.println(queue2.remove());
        }
        System.out.println("--- Priority queue, descending ---");
        addToQueue(queue3);
        while(!queue3.isEmpty()) {
            System.out.println(queue3.remove());
        }
    }
}

class Descending<T extends Comparable<T>> implements Comparator<T> {
    public int compare(T obj1, T obj2) {
    	Comparable<T> comp = (Comparable<T>)obj2;
        return comp.compareTo(obj1);
    }
}

