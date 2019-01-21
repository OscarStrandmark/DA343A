package l01;

public interface Queue<T> {
	
	void add(T obj);
	
	T remove();
	
	T element();
	
	boolean isEmpty();
	
	int size();
	
}