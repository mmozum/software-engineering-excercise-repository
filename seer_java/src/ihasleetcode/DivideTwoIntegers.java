package ihasleetcode;

public class DivideTwoIntegers {

	public static void main(String[] args) {

		System.out.println(divide(11, 3));
	}
	
	static public int divide(int dividend, int divisor) {
		
		boolean neg = false;
		long a = dividend;
		if(a < 0){
			a = -a;
			neg = !neg;
		}
		
		long b = divisor;
		if(b < 0){
			b = -b;
			neg = !neg;
		}
		
		int ret = 0;
		
		for(int i = Integer.SIZE - 1; i >= 0; i --){
			long c = b << i;
			if(c <= a){
				a -= c;
				ret |= 1 << i;
			}
		}
		
		return neg ? -ret : ret;
	}
	
		//	recursive solution
//    static public int divide(int dividend, int divisor) {
//    	
//    	int sign = 1;
//    	long a = dividend;
//    	if(a < 0){
//    		a = -a;
//    		sign *= -1;
//    	}
//    	
//    	long b = divisor;
//    	if(b < 0){
//    		b = -b;
//    		sign *= -1;
//    	}
//    	
//    	return (int)(sign * div(a, b));
//        
//    }
//    
//    static long div(long a, long b){
//    	
//    	if(a < b){
//    		return 0;
//    	}
//    	
//    	long bb = b;
//    	long ans = 1;
//    	while(bb << 1 <= a){
//    		bb <<= 1;
//    		ans <<= 1;
//    	}
//    	
//    	return ans + div(a - bb, b);
//    }
}
