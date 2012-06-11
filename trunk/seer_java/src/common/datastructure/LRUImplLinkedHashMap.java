package common.datastructure;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUImplLinkedHashMap<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = 7470551700731363899L;

	final int capacity;

	public LRUImplLinkedHashMap(int capacity) {
		super(16, 0.75f, true);
		this.capacity = capacity;
	}

	@Override
	protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
		return size() > capacity;
	}
	
	public static void main(String[] args){
		LRUImplLinkedHashMap<Integer, Integer> lru = new LRUImplLinkedHashMap<Integer, Integer>(3);
		
		lru.put(1, 1);
		lru.put(2, 2);
		lru.put(3, 3);
		lru.put(4, 4);
		
		for(Map.Entry<Integer, Integer> entry : lru.entrySet()){
			System.out.println(entry);
		}
		
		lru.put(2, 2);
		
		for(Map.Entry<Integer, Integer> entry : lru.entrySet()){
			System.out.println(entry);
		}
		
	}

}
