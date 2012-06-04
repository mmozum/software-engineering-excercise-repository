package interviewstreet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LuckyNumber {

	static final int MAX_DIGIT = 18;
	static final int M = 9 * MAX_DIGIT + 1;
	static final int N = 81 * MAX_DIGIT + 1;
	static long numTable[][][] = new long[MAX_DIGIT + 1][M][N];
	static BitSet primeSet;

	public static void main(String[] args) {

//		long t0 = System.currentTimeMillis();
		
		for(int i = 0; i < 10; i ++){
			numTable[1][i][i*i] ++;
		}
		
		for(int i = 1; i <= MAX_DIGIT; i ++){
			for(int j = 0; j < M; j ++){
				for(int k = 0; k < N; k ++){
					
					for(int d = 0; d < 10; d ++){
						if(j < d || k < d * d){
							continue;
						}
						
						numTable[i][j][k] += numTable[i-1][j-d][k-d*d];
					}
					
				}
			}
		}
		
		
		primeSet = buildPrimeSet(N);
		
//		long t1 = System.currentTimeMillis() - t0;
//		
//		System.out.format("initialize time: %d ms\n", t1);

		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int T = scanner.nextInt();
		List<Long> answerList = new ArrayList<Long>(T);
		for(int t = 0; t < T; t ++){
			long a = scanner.nextLong();
			long b = scanner.nextLong();
			answerList.add(countLuckyNumber(b) - countLuckyNumber(a-1));
		}
		
		System.out.println(joinList(answerList, "\n"));
//		test();

	}
	
	static <E> String joinList(List<E> list, String sep){
		
		if(list == null || list.isEmpty()){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(list.get(0));
		
		Iterator<E> it = list.iterator();
		it.next();
		
		while(it.hasNext()){
			sb.append(sep).append(it.next());
		}
		
		return sb.toString();
	}
	
//	private static long countLuckyNumberBT(long a, long b){
//		
//		long cnt = 0;
//		
//		for(long i = a; i <= b; i ++){
//			
//			long n = i;
//			int sum = 0;
//			int squareSum = 0;
//			while(n != 0){
//				int d = (int)(n % 10);
//				sum += d;
//				squareSum += d * d;
//				n /= 10;
//			}
//			
//			if(primeSet.get(sum) && primeSet.get(squareSum)){
//				cnt ++;
//			}
//		}
//		
//		return cnt;
//		
//	}

	static void test() {

		Random rand = new Random();
		
		long maxTime = 0;
		
		for(int i = 0; i < 10000; i ++){
			long a = Math.abs(rand.nextInt());
			long b = Math.abs(rand.nextLong()) % 1000000000000000000L;
			
			if(a > b){
				long tmp = a;
				a = b;
				b = tmp;
			}
			
			long t0 = System.currentTimeMillis();
			try{
			long tmp = countLuckyNumber(b) - countLuckyNumber(a-1);
			} catch (Throwable e){
				System.out.format("ERROR [%d, %d]\n", a, b);
			}
			long t1 = System.currentTimeMillis() - t0;
			
			if(t1 > maxTime){
				maxTime = t1;
			}
			System.out.format("[%d,%d] %d ms\n", a, b, t1);
		}
		
		System.out.format("max time is %d", maxTime);
		
	}

	static long countLuckyNumber(long n) {
		
		long result = 0;
		
		if(n == 0){
			return 0;
		}
		
		if(n%10 == 0){
			result += isLuckyNumber(n);
			n --;
		}
		
		int totalNumOfDigit = 0;
		long tens = 1;
		while(tens <= n){
			tens *= 10;
			totalNumOfDigit ++;
		}
		
		result += countLuckyNumberImpl(n, 0, 0, totalNumOfDigit);
		return result;
	}

	private static BitSet buildPrimeSet(int n) {
		BitSet bs = new BitSet(n);
		bs.set(2,n);
		for(int i = 2; i < n; i ++){
			if(bs.get(i)){
				for(int j = 2 * i; j < n; j += i){
					bs.clear(j);
				}
			}
		}
		return bs;
	}

	static long countLuckyNumberImpl(long num, int sum, int squareSum, int totalNumOfDigit) {
		
		if(num == 0){
			return isLuckyNumber(sum, squareSum);
		}
		
		long tens = 1;
		int numDigit = 0;
		while(tens * 10 <= num){
			tens *= 10;
			numDigit ++;
		}
		
		int firstDigit = (int) (num / tens);
		long newNum = num - firstDigit * tens;
		long total = 0;
		
			
		for(int d = 0; d < firstDigit; d ++){
			if(newNum == 0){
				total += isLuckyNumber(sum + d, squareSum + d * d);
			} else {
				total += countPrimes(sum + d, squareSum + d * d, numDigit);
			}
		}
		
		
		total += countLuckyNumberImpl(newNum, sum + firstDigit, squareSum + firstDigit * firstDigit, totalNumOfDigit);
		
		return total;
	}

	private static long countPrimes(int sum, int squareSum, int numDigit) {
		
		long total = 0;
		
		for(int j = M - 1 - sum; j >= 0; j --){
			for(int k = N - 1 - squareSum; k >= 0; k --){
				if(primeSet.get(j + sum) && primeSet.get(k + squareSum)){
					//total += numTable[numDigit][j + sum][k + squareSum];
					total += numTable[numDigit][j][k];
				}
			}
		}
		return total;
	}
	
	private static long isLuckyNumber(int sum, int squareSum) {
		if(primeSet.get(sum) && primeSet.get(squareSum)){
			return 1;
		}
		return 0;
	}
	
	private static long isLuckyNumber(long number) {
		
		long n = number;
		int sum = 0;
		int squareSum = 0;
		while(n != 0){
			int d = (int)(n % 10);
			sum += d;
			squareSum += d * d;
			n /= 10;
		}
		
		if(primeSet.get(sum) && primeSet.get(squareSum)){
			return 1;
		}
		
		return 0;
	}
}
