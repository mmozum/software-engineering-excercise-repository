package common;

import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.Arrays;
import java.util.Random;

/**
 * Given a double array, return the maximum product in continuous subarray
 * @author Jason Huang
 *
 */
public class MaximumSubArrayProduct {
	
	
	
	
	public static void main(String[] args) {

		for (int i = 0; i < 100; i++) {
			double[] arr = randomDoubleArray(20, 40);
			double a = maxSubArrayProduct(arr);
			double b = maxSubArrayProductBruteForce(arr);
			if(a - b > 1e-5){
				System.out.println(Arrays.toString(arr));
				System.out.println("a = " + a);
				System.out.println("b = " + b);
			}

		}
		
		System.out.println("done~");
	}
	
	
	static double maxSubArrayProduct(double[] arr){
		
		double ret = Double.MIN_VALUE;
		
		if(arr == null || arr.length == 0){
			return ret;
		}
		
		double hi = arr[0], 
				lo = arr[0];
		
		for(int i = 1; i < arr.length; i ++){

			double newHi = max(arr[i], arr[i] * hi);
			newHi = max(newHi, arr[i] * lo);
			
			double newLo = min(arr[i], arr[i] * hi);
			newLo = min(newLo, arr[i] * lo);
			
			if(newHi > ret){
				ret = newHi;
			}
			
			hi = newHi;
			lo = newLo;
		}
		
		return ret;
	}
	
	static double maxSubArrayProductBruteForce(double[] arr){
		
		double ret = Double.MIN_VALUE;
		
		for(int i = 0; i < arr.length; i ++){
			double p = 1;
			for(int j = i; j < arr.length; j ++){
				p *= arr[j];
				if(p > ret){
					ret = p;
				}
			}
		}
		
		return ret;
	}
	
	/**
	 * Generate an array of random doubles
	 * @return
	 */
	public static double[] randomDoubleArray(int size, int range){
		
		if(size <= 0 || range <= 0){
			return null;
		}
		
		Random rand = new Random();
		
		double[] arr = new double[size];
		double d = range / 4;
		
		for(int i = 0; i < size; i ++){
			arr[i] = (rand.nextInt(2 * range) - range / 2) / d;
		}
		
		return arr;
	}
}
