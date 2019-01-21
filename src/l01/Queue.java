package l01;

public interface Queue<T> {
	
	void add(T obj);
	
	T remove();
	
	T element(T obj);
	
	boolean isEmpty();
	
	int size();
	
}