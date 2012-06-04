package ihasleetcode;

/**
Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
 */

/**
 * This is a very open ended question. Some questions regarding
 * the requirement need to be asked:
 * 
 * 1. Integer or Floating point number?
 * 2. What's the number base/radix? Assume 10 or can it be others?
 *    If it's multiple bases are possible, then probably we are just
 *    talking about integers.
 * 3. Can the number be negative? probably yes
 * 4. If it's floating point number, pay attention to scientific
 *    notation.
 * 5. What's the range? What's the behavior when overflow?
 * 6. Is empty space allowed?
 */

public class IsNumber {

	public static void main(String[] args) {
		
		String[] sArr = {"0", " 0.5 ", "abc", "1 a", "2e10",
				"-100", "+25.54", "-+36", ".56", "988.", "6e", "  ",
				"a1", "1a", "2e.3", "3e3.", "3e3.1"};
		
		for(String s : sArr){
			System.out.format("'%s' => %b\n", s, isNumber(s));
		}

	}
	
    static public boolean isNumber(String s) {
    	
    	if(s == null){
    		return false;
    	}
    	
    	char[] sArr = s.toLowerCase().toCharArray();
    	
    	boolean isValidSoFar = false;
    	boolean seenDecimalPoint = false;
    	boolean seenE = false;
    	
    	int idx = 0;
    	while(idx < sArr.length && sArr[idx] == ' '){
    		++ idx;
    	}
    	
    	if(idx < sArr.length && (sArr[idx] == '+' || sArr[idx] == '-')){
    		++ idx;
    	}
    	
    	char c;
    	
    	while(idx < sArr.length && (c = sArr[idx]) != ' '){
    		
    		if(Character.isDigit(c)){
    			isValidSoFar = true;
    		} else if(c == '.'){
    			if(seenDecimalPoint || seenE){
    				isValidSoFar = false;
    				break;
    			} else {
    				seenDecimalPoint = true;
    			}
    		} else if(c == 'e') {
    			if(!isValidSoFar || seenE){
    				isValidSoFar = false;
    				break;
    			} else {
    				seenE = true;
    				isValidSoFar = false;
    			}
    		} else if(c == '+' || c == '-'){
    			if(sArr[idx-1] != 'e'){
    				isValidSoFar = false;
    				break;
    			}
    		} else {
				isValidSoFar = false;
				break;
    		}
    		
    		++ idx;
    	}
    	
    	while(idx < sArr.length){
    		if(sArr[idx] != ' '){
				isValidSoFar = false;
				break;
    		}
    		++ idx;
    	}

        return isValidSoFar;
    }
}
