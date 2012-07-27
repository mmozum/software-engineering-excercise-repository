package common.twitter;

import java.util.HashMap;
import java.util.Random;

/**
 * @author Jason Huang
 */
public class RandomNumberGenerator {

	public static void main(String[] args) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		int m = 3;
		int n = 20;
		
		for(int i = 0; i < 10000; i ++){
			int r = randomGen(m, n);
			
			if(!map.containsKey(r)){
				map.put(r, 0);
			}
			
			map.put(r, map.get(r)+1);
		}

		System.out.println(map);
	}
	
	/**
	 * Given a uniform integer random number generator [0, m),
	 * write a uniform integer random number generator [0, n).
	 * Both M and N are positive integers.
	 */
	public static int randomGen(int m, int n){
		
		assert((m == 1 && n == 1) || (m > 1 && n > 0));
		
		int exp = 1;
		int M = m;
		while(M < n){
			M *= m;
			exp ++;
		}
		
		int upperBound = M / n * n;
		int r = randomGenM(m, exp);
		
		while(r >= upperBound){
			r = randomGenM(m, exp);
		}
		
		return r % n;
	}
	
	/**
	 * Helper function. 
	 * Given a uniform integer random number generator [0, m),
	 * generate an integer in [0, m^exp).
	 */
	private static int randomGenM(int m, int exp){
		int ret = 0;
		Random rand = new Random();
		
		for(int i = 0; i < exp; i ++){
			ret = m * ret + rand.nextInt(m);
		}
		
		return ret;
	}

}
