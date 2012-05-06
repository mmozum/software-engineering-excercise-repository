package ihasleetcode;

import java.util.*;

public class MinimumWindowSubstring {


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	static class Item{
		Character c;
		int index;
		
		public Item(Character c, int index) {
			this.c = c;
			this.index = index;
		}
		
	}

    public String minWindow(String S, String T) {
        
    	LinkedList<Item> queue = new LinkedList<Item>();
    	Hashtable<Character, Integer> map = new Hashtable<Character, Integer>();
    	Hashtable<Character, Integer> map0 = new Hashtable<Character, Integer>();
    	
    	int minLen = Integer.MAX_VALUE;
    	int minStart = -1;
    	int minEnd = -1;
    	int toGo = T.length();
    	
    	for(char c : T.toCharArray()){
    		
    		if(map0.containsKey(c)){
    			
    			map0.put(c, map0.get(c) + 1);
    			
    		} else {
    			
    			map0.put(c, 1);
    		}
    	}
    	
    	for(int i = 0; i < S.length(); i ++){
    		
    		char c = S.charAt(i);
    		
    		if(T.indexOf(c) < 0){
    			continue;
    		}

    		Item item = new Item(c, i);
    		queue.add(item);
    		
    		if(map.containsKey(c)){
    			
    			int existingCount = map.get(c);
    			
    			if(existingCount < map0.get(c)){
    				toGo --;
    			}
    			
    			map.put(c, existingCount + 1);
    			
    			while(!queue.isEmpty()){

    				Item tmp = queue.getFirst();

    				if(map.get(tmp.c) <= map0.get(tmp.c)){
    					break;
    				}

    				map.put(tmp.c, map.get(tmp.c) - 1);
    				queue.removeFirst();
    			}

    		} else {
    			map.put(c, 1);
    			toGo --;
    		}
    		
    		
    		if(toGo == 0){
    			int len = i - queue.getFirst().index + 1;
    			if(len < minLen){
    				minLen = len;
    				minStart = queue.getFirst().index;
    				minEnd = i;
    			}
    		}
    	}
    	
    	
    	if(minStart == -1){
    		return "";
    	} else {
    		return S.substring(minStart, minEnd + 1);
    	}
    }
}
