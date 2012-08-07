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
			System.out.println(minWindow(test[0], test[1]));
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

        HashMap<Character,Integer> map = new HashMap<Character, Integer>();
        
        for(char c : T.toCharArray()){
            if(!map.containsKey(c)){
                map.put(c,1);
            } else {
                map.put(c, map.get(c) + 1);
            }
        }
        
        String ret = "";
        int start = 0;
        int counter = map.keySet().size();

        for(int i = 0; i < S.length(); i ++){
            char c = S.charAt(i);
            
            if(!map.containsKey(c)){
                continue;
            }
            
            map.put(c, map.get(c) - 1);
            if(map.get(c).equals(0)){
                counter --;
            }
            
            
            while(counter == 0){
                c = S.charAt(start);
                
                if(map.containsKey(c) && map.get(c) == 0){
                    break;
                }
                
                if(map.containsKey(c) && map.get(c) < 0){
                    map.put(c, map.get(c) + 1);
                }
                
                start ++;
            }
            
            if(counter == 0){
                if(ret.equals("") || i - start + 1 < ret.length()){
                    ret = S.substring(start, i+1);
                }
            }
            
        }
        
        return ret;
    }


}
