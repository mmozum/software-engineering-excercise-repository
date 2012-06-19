package common.google;

import java.util.Iterator;


//Provide an implementation of the following interface:
//public interface Powers extends Iterator<Long>
//{
///* Returns the next integer a in the arithmetic sequence of integers where
//* a = m^n, m > 1 n > 1, and m and n are both integers
//* Thus, the first few outputs will be 4, 8, 9, 16, 25, 27, 32, 36, etc.
//*/
//
//public Long next();
//
///* Resets the sequence to the beginning, such that the next call to next()
//* will return 4.
//*/
//public void reset();
//}

public class IntegerGenerator implements Iterator<Long> {
	
	private long num = 4;
	
	public void reset(){
		num = 4;
	}

	@Override
	public Long next(){

		while(!test(num)){
			num++;
		}
		
		return num++;
	}
	
	@Override
	public boolean hasNext(){
		return true;
	}
	
	@Override
	public void remove(){
		throw new UnsupportedOperationException();
	}

	private boolean test(long n){
		
		final long limit = (long)Math.sqrt(n);
		for(long i = 2; i <= limit; i ++){
			long nn = i;
			while(nn < n){
				nn *= i;
			}
			if(nn == n){
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		IntegerGenerator g = new IntegerGenerator();
		
		for(int i = 0; i < 1000; i ++){
			System.out.println(g.next());
		}

	}

}
