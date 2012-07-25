package common;

public class IntegerDivision {

	public static void main(String[] args) {
		
		System.out.println(divide(9, 2));
	}

	/**
	 * @return a / b
	 */
	static int divide(int dividend, int divisor){
		
		if(divisor == 0){
			throw new ArithmeticException("Cannot divide 0");
		}
		
		long a = dividend;
		boolean neg = false;
		if(a < 0){
			neg = !neg;
			a = -a;
		}
		
		long b = divisor;
		if(b < 0){
			neg = !neg;
			b = -b;
		}
		
		int result = 0;
		
		for(int i = Integer.SIZE - 1; i >= 0; i --){
			if(b << i <= a){
				result |= (1 << i);
				a -= (b << i);
			}
		}
		
		if(neg){
			result = -result;
		}
		return result;
	}
}
