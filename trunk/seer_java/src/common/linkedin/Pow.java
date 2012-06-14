package common.linkedin;

public class Pow {

	public static void main(String[] args) {
		Object[][] testcases = {
				{234, 0, 1.0},
				{0.0, 1, 0.0},
				{0.0, 2, 0.0},
				{2.1, 2, 2.1 * 2.1},
				{2.1, -10, Math.pow(2.1, -10)},
				{-3.34343, 20, Math.pow(-3.34343, 20)},
		};
		
		for(Object[] objs : testcases){
			double a = Double.valueOf(objs[0].toString());
			int b = Integer.valueOf(objs[1].toString());
			double expected = Double.valueOf(objs[2].toString());
			double c = pow(a, b);
			
			if(Math.abs(c - expected) > 1e-5){
				System.out.format("FAIL: pow(%f, %d) = %f, expect %f\n", a, b, c, expected);
			} else {
				System.out.format("PASS: pow(%f, %d) = %f, expect %f\n", a, b, c, expected);
			}
			
		}

	}

	static double pow(double a, int b){
		if(b == 0){
			if(Math.abs(a) < 1e-6){
				throw new IllegalArgumentException("No sense for 0 ^ 0");
			}
			
			return 1.0;
		}
		
		if(b < 0){
			a = 1 / a;
			b = -b;
		}
		
		if(b == 1){
			return a;
		}
		
		double ret = pow(a, b / 2);
		
		ret *= ret;
		
		if(b % 2 == 1){
			ret *= a;
		}
		
		return ret;
	}
}
