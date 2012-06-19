package common.google;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/*
get, put, remove, getRandom
 */
public class DataStructure {
	
	final int DEFAULT_SIZE = 4;
	HashMap<String, Integer> map = new HashMap<String, Integer>();
	String[] keyArr = new String[DEFAULT_SIZE];
	String[] valArr = new String[DEFAULT_SIZE];
	int size = 0;
	Random rand = new Random();

	String get(String key){
		if(!map.containsKey(key)){
			return null;
		}
		
		return valArr[map.get(key)];
	}
	
	void put(String key, String val){
		if(map.containsKey(key)){
			return ;
		}
		
		if(size == keyArr.length){
			keyArr = Arrays.copyOf(keyArr, keyArr.length << 1);
			valArr = Arrays.copyOf(valArr, valArr.length << 1);
		}
		
		map.put(key, size);
		keyArr[size] = key;
		valArr[size] = val;
		size ++;
	}
	
	String remove(String key){
		
		if(!map.containsKey(key)){
			return null;
		}
		
		int idx = map.remove(key);
		String ret = valArr[idx];
		
		size --;
		if(idx != size){
			keyArr[idx] = keyArr[size];
			valArr[idx] = valArr[size];
		}
		keyArr[size] = null;
		valArr[size] = null;
		return ret;		
	}
	
	String getRandom(){
		if(size == 0){
			return null;
		}
		
		return valArr[rand.nextInt(size)];
	}
}
