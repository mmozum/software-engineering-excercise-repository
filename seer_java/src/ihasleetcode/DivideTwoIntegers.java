package ihasleetcode;

public class DivideTwoIntegers {

	public static void main(String[] args) {

		System.out.println(divide(11, 3));
	}
	
    static public int divide(int dividend, int divisor) {
    	
    	int sign = 1;
    	long a = dividend;
    	if(a < 0){
    		a = -a;
    		sign *= -1;
    	}
    	
    	long b = divisor;
    	if(b < 0){
    		b = -b;
    		sign *= -1;
    	}
    	
    	return (int)(sign * div(a, b));
        
    }
    
    static long div(long a, long b){
    	
    	if(a < b){
    		return 0;
    	}
    	
    	long bb = b;
    	long ans = 1;
    	while(bb << 1 <= a){
    		bb <<= 1;
    		ans <<= 1;
    	}
    	
    	return ans + div(a - bb, b);
    }
}
