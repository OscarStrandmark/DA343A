package l01;

import java.util.ArrayList;

public class ArrayQueue<T> implements Queue<T> {

	private ArrayList<T> queue;
	
	public ArrayQueue(){
		queue = new ArrayList<T>();
	}
	
	@Override
	public void add(T obj) {
		queue.add(obj);
	}

	@Override
	public T remove() {
		T retVal = queue.get(0);
		queue.remove(0);
		return retVal;
	}

	@Override
	public T element() {
		return queue.get(0);
	}

	@Override
	public boolean isEmpty() {
		return queue.isEmpty();
	}

	@Override
	public int size() {
		return queue.size();
	}
	
}