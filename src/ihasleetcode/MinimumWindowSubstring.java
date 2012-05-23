package ihasleetcode;

import java.util.*;

public class MinimumWindowSubstring {


	public static void main(String[] args) {

		String[][] tests = {
				{"a","a"},
				{"a","aa"},
				{"a","b"},
				{"a","ab"},
				{"ab","a"},
				{"ab","b"},
				{"ab","A"},
				{"aa","aa"},
				{"aa","aaa"},
				{"abc","a"},
				{"abc","b"},
				{"abc","c"},
				{"abc","ab"},
				{"abc","bc"},
				{"abc","ac"},
				{"abc","cba"},
				{"abc","dabc"},
				{"abc","aabc"},
				{"aab","aab"},
				{"bdab","ab"},
				{"bba","ab"},
				{"bbaa","aba"},
				{"bbaac","aba"},
				{"acbbaca","aba"},
				{"ADOBECODEBANC","ABC"},
				{"cabeca","cae"},
				{"cfabeca","cae"},
				{"cabefgecdaecf","cae"},
				{"cabwefgewcwaefgcf","cae"},
				{"abcabdebac","cda"},
				{"abcabdebac","cea"},
				{"acbdbaab","aabd"},
				{"caaec","cae"},
				{"caae","cae"},
				{"acbba","aab"},
				{"adobecodebanc","abc"},
				{"adobecodebanc","abcda"},
				{"adobecodebanc","abdbac"},
				{"adobecodebancbbcaa","abc"},
				{"acccabeb","ab"},
				{"aaabdabcefaecbef","abc"},
				{"coobdafceeaxab","abc"},
				{"of_characters_and_as","aas"},
		};
		
		for(String[] test : tests){
//			System.out.println(minWindow(test[0], test[1]));
		}
		System.out.println(minWindow("adobecodebanc", "abcda"));

	}
	
	static class Item{
		Character c;
		int index;
		
		public Item(Character c, int index) {
			this.c = c;
			this.index = index;
		}
		
		public String toString(){
			return String.format("<%c, %d>", c, index);
		}
		
	}
	
	static public String minWindow(String S, String T) {
		
		if(T.isEmpty()){
			return "";
		}
		
		LinkedList<Item> queue = new LinkedList<Item>();
		int[] countS = new int[256];
		int[] countT = new int[256];
		
		// populate countT
		for(char c : T.toCharArray()){
			countT[c] ++;
		}
		
		int toGo = T.length();
		int current = -1;
		int minWindow = Integer.MAX_VALUE;
		int minStart = -1;
		
		while(current < S.length()){
			
			int oldToGo = toGo;
			// expand
			while(++current < S.length()){
				
				char c = S.charAt(current);
				if(T.indexOf(c) < 0){
					continue;
				}
				
				queue.add(new Item(c, current));
				
				if(countS[c] < countT[c]){
					toGo --;
				}
				
				countS[c] ++;
				
				char first = queue.getFirst().c;
				if((oldToGo > 0 && toGo == 0) || countS[first] > countT[first]){
					break;
				}
			}
			
			if(!queue.isEmpty() && toGo == 0){
				// contract
				char first = queue.getFirst().c;
				while(countS[first] > countT[first]){
					countS[first] --;
					queue.removeFirst();
					first = queue.getFirst().c;
				}
				
				int min = queue.getLast().index - queue.getFirst().index;
				if(min < minWindow){
					minWindow = min;
					minStart = queue.getFirst().index;
				}
			}
		}
		
		if(minWindow == Integer.MAX_VALUE){
			return "";
		}
		return S.substring(minStart, minStart + minWindow + 1);
	}


}
