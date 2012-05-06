package ihasleetcode;

import java.util.ArrayList;

public class Sqrt {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(sqrt(2147395599));

		for(int i = 0; i < 10; i ++)
			System.out.println(sqrt(i));

	}

	static public int sqrt(int x) {

        if(x < 1){
            return 0;
        }
        int lo = 1;
        int hi = x;

        while(lo < hi){
            
            int mi = lo + ((hi - lo) >> 1);
            
            if(mi == x / mi || 
                mi < x / mi &&
                (mi + 1) > x / (mi + 1)){
                return mi;
            }
            
            if(mi < x / mi){
                lo = mi + 1;
            } else {
                hi = mi - 1;
            }
        }
        
        return lo;
    }

}
