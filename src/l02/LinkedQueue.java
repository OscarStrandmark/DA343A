package l02;

import java.util.Collection;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

import f2.LinkedStack;
import laboration1.QueueException;

public class LinkedQueue<T> implements Queue<T> {
	private LinkedList<T> queue = new LinkedList<T>();

	public void add(T element) {
		queue.addLast(element);
	}

	public T remove() {
		if(queue.size()==0)
			throw new QueueException("dequeue: Queue is empty");
		return queue.removeFirst();
	}

	public T element() {
		if(queue.size()==0)
			throw new QueueException("peek: Queue is empty");
		return queue.getFirst();
	}

	public boolean isEmpty() {
		return (queue.size()==0);
	}

	public int size() {
		return queue.size();
	}
	
	public static void main(String[] args) {
		LinkedQueue<Double> queue = new LinkedQueue<Double>();
		for(double d = 10; d<40; d++) {
			queue.add(d);
		}
		System.out.println("size="+queue.size()+", first element="+queue.element());
		while(!queue.isEmpty()) {
			System.out.print(queue.remove()+" ");
		}
	}

}
