package f06;

import java.util.HashMap;

public class SynchronizedHashMap<K,V> {
	private HashMap<K,V> map = new HashMap<K,V>();
	
	public synchronized V put(K key, V value) {
		return map.put(key, value);
	}
	
	public synchronized V remove(K key) {
		return map.remove(key);
	}
	
	public synchronized void clear() {
		map.clear();
	}
	
	public synchronized V get(K key) {
		return map.get(key);
	}
}
