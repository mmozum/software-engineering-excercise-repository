package ihasleetcode;

public class CountAndSay {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(divide(-2147483648, 3));
		System.out.println(divide(2147483647, 1));

	}

    static public String countAndSay(int n) {

    	String current = "1";
    	
    	for(int i = 0; i < n - 1; i ++){
    		StringBuilder sb = new StringBuilder();
    		
    		char[] currentArr = current.toCharArray();
    		char currentChar = 0;
    		int count = 0;
    		for(int j = 0; j < currentArr.length; j ++){
    			
    			if(currentChar == 0 || currentArr[j] != currentChar){
    				currentChar = currentArr[j];
    				count = 1;
    			} else {
    				count ++;
    			}
    			
    			if(j == currentArr.length - 1 || currentArr[j+1] != currentChar){
    				sb.append(count).append(currentChar);
    			}
    			
    		}
    		
    		current = sb.toString();
    	}
    	
    	return current;
        
    }
    
  static public int divide(int dividend, int divisor) {
        
        int numOfNegative = 0;
        long dividendl = dividend;
        long divisorl = divisor;
        
        if(dividend < 0){
            numOfNegative ++;
            dividendl = 0L - dividend;
        }
        
        if(divisor < 0){
            numOfNegative ++;
            divisorl = 0L - divisor;
        }
        
        if(dividendl < divisorl){
        	return 0; 
        }
        
        long quotient = 0;
        
        while(dividendl >= divisorl){
        
	        long prod = divisorl;
	        long prod2 = prod + prod;
	        int tmpQuotient = 1;
	        
	        while(prod2 < dividendl){
	        	prod = prod2;
	        	prod2 += prod2;
	        	tmpQuotient <<= 1;
	        }
	        
	        quotient += tmpQuotient;
	        dividendl -= prod;
        }
        
        return (int)((numOfNegative == 1) ? -quotient : quotient);
        
    }
}
