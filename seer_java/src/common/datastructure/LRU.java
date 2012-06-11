package common.datastructure;

public interface LRU<K, V> {
	boolean put(K key, V val);
	V get(K key);
}
