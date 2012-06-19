package common;

import java.util.Arrays;

/**
 * Arbitrarily long integer
 * @author Jason Huang
 *
 */
public class BigInteger {
	
	private static final long LONG_MASK = 0xFFFFFFFFL;
	
	private int[] data;
	
	public BigInteger(int n){
		data = new int[1];
		data[0] = n;
	}
	
	public BigInteger(int[] data){
		this.data = data;
	}
	
	public BigInteger multiply(BigInteger num){
		
		final int M = data.length;
		final int N = num.data.length;
		int[] buff = Arrays.copyOf(data, M + N + 1);
		
		
		for(int i = 0; i < num.data.length; i ++){
			multiplyAdd(buff, i, LONG_MASK & num.data[i]);
		}
		
		int idx = buff.length - 1;
		
		while(idx >= 0 && buff[idx] == 0){
			idx --;
		}
		
		return new BigInteger(Arrays.copyOf(buff, idx + 1));
	}
	
	private void multiplyAdd(int[] num1, int start, long num2){
		long carry = 0;
		for(int i = start; i < num1.length; i ++){
			long sum = num2 * num1[i] + carry;
			num1[i] = (int)sum;
			carry = sum >>> 32;
		}
	}
	
	private long divideMinus(int[] num1, int start, long num2){
		long remainder = 0;
		for(int i = start; i >= 0; i --){
			long sum = (remainder << 32) + (LONG_MASK & num1[i]);
			num1[i] = (int)(sum / num2);
			remainder = sum % num2;
		}
		return remainder;
	}
	
	@Override
	public String toString(){

		return toDedimalString();
	}
	
	String toHexString(){
		StringBuilder sb = new StringBuilder("0x");
		
		for(int i = data.length - 1; i >= 0; i --){
			sb.append(String.format("%08X", data[i]));
		}
		return sb.toString();
	}
	
	String toDedimalString(){
		StringBuilder sb = new StringBuilder();
		int[] buff = Arrays.copyOf(data, data.length);
		int idx = buff.length - 1;
		
		while(idx >= 0){
			long remainder = divideMinus(buff, idx, 10);
			sb.insert(0, (char)('0' + remainder % 10));
			if(buff[idx] == 0){
				idx --;
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		BigInteger bi = new BigInteger(Integer.MAX_VALUE);
		BigInteger bi2 = bi.multiply(bi);
		//System.out.println(bi);
		System.out.println(bi2.toHexString());
		System.out.println(bi2.toDedimalString());

	}

}
