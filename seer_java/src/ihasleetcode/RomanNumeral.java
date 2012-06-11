package ihasleetcode;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeral {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for(int i = 1; i < 4000; i ++){
			assert(romanToInt(intToRoman(i)) == i);
		}
		System.out.println("done~");

	}
	
    static public String intToRoman(int num) {
    	
    	if(num <= 0 || num > 3999){
    		throw new IllegalArgumentException("overflow integer");
    	}
        
    	String[] symbols = { "I", "V", "X", "L", "C", "D", "M", "", "" };
    	StringBuilder sb = new StringBuilder();
    	int counter = 0;
    	
    	while(num > 0){
    		int d = num % 10;
    		String one = symbols[counter];
    		String five = symbols[counter + 1];
    		String ten = symbols[counter + 2];
    		
    		switch(d){
    		case 1: sb.insert(0, one); break;
    		case 2: sb.insert(0, one + one); break;
    		case 3: sb.insert(0, one + one + one); break;
    		case 4: sb.insert(0, one + five); break;
    		case 5: sb.insert(0, five); break;
    		case 6: sb.insert(0, five + one); break;
    		case 7: sb.insert(0, five + one + one); break;
    		case 8: sb.insert(0, five + one + one + one); break;
    		case 9: sb.insert(0, one + ten); break;
    		}
    		
    		num /= 10;
    		counter += 2;
    	}
    	
    	return sb.toString();
    }
    
    static int romanToInt(String roman){
    	
    	if(roman == null || roman.length() == 0){
    		return 0; // or throw an exception
    	}
    	
    	Map<Character, Integer> map = new HashMap<Character, Integer>();
    	map.put('I', 1);
    	map.put('V', 5);
    	map.put('X', 10);
    	map.put('L', 50);
    	map.put('C', 100);
    	map.put('D', 500);
    	map.put('M', 1000);
    	
    	int result = 0;
    	
    	for(int i = 0; i < roman.length(); i ++){
    		int currentDigit = map.get(roman.charAt(i));
    		int nextDigit = 0;
    		if(i + 1< roman.length()){
    			nextDigit = map.get(roman.charAt(i+1));
    		}
    		
    		if(nextDigit > currentDigit){
    			result += nextDigit - currentDigit;
    			i ++;
    		} else {
    			result += currentDigit;
    		}
    	}
    	
    	return result;
    }
}
