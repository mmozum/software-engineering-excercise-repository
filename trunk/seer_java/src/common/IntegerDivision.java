package common;

public class IntegerDivision {

	public static void main(String[] args) {
		
		System.out.println(divide(9, 10));
	}

	/**
	 * @return a / b
	 */
	static int divide(int a, int b){
		
		if(b == 0){
			throw new ArithmeticException("Cannot divide 0");
		}
		
		boolean neg = false;
		if(a < 0){
			neg = !neg;
			a = -a;
		}
		
		if(b < 0){
			neg = !neg;
			b = -b;
		}
		
		int d = 0;
		while(b << (d+1) <= a){
			d ++;
		}
		
		int result = 0;
		
		for(int i = d; i >= 0; i --){
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
