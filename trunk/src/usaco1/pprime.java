package usaco1;

/*
ID: jastina1
LANG: JAVA
TASK: pprime
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class pprime {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		// generate all palindromes and then verify
		
		alldone:
		for(int i = 1; i <= 4; i ++){
			
			int from = getStart(i);
			int to = getStart(i + 1) - 1;
			
			for(int j = from; j <= to; j ++){

				String s = toPalindrome(j, false);
				int pal = Integer.parseInt(s);
				
				if(pal < a || pal % 2 == 0 || pal % 3 == 0){
					continue;
				}
				
				if(pal > b){
					break alldone;
				}
				
				if(isPrime(pal)){
					System.out.println(pal);
					pw.println(pal);
				}
			}
			
			if(i == 1 && a <= 11 && 11 <= b){
				System.out.println(11);
				pw.println(11);				
			}
		}
		
		//pw.println(result);
		pw.close();
		System.exit(0);

	}

	private static boolean isPrime(int pal) {
		
		for(int i = 2; i <= Math.sqrt(pal); i ++){
			if(pal % i == 0){
				return false;
			}
		}
		return true;
	}

	private static String toPalindrome(int j, boolean isEven) {
		String s = "" + j;
		
		char[] arr;
		
		if(isEven){
			arr = new char[s.length() * 2];
		} else {
			arr = new char[s.length() * 2 - 1];
		}
		
		for(int i = 0; i < s.length(); i ++){
			arr[i] = arr[arr.length - 1 - i] = s.charAt(i);
		}
		
		return new String(arr);
	}

	private static int getStart(int numDigit) {

		int s = 1;
		
		for(int i = 1; i < numDigit; i ++){
			s *= 10;
		}
		return s;
	}



}
