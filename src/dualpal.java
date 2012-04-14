/*
ID: jastina1
LANG: JAVA
TASK: dualpal
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class dualpal {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("dualpal.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		
		br.close();
		
		List<Integer> solution = new ArrayList<Integer>();
		
		for(int i = S + 1; solution.size() < N; i ++){
			
			int inPal = 0;
			
			for(int B = 2; B <= 10 && inPal < 2; B++){
				if(isPalindrom(toBase(i, B))){
					inPal ++;
				}
			}
			
			if(inPal == 2){
				solution.add(i);
			}
			
		}
		
		for(Integer i : solution){
			pw.println(i);
			System.out.println(i);
		}
		
		pw.close();
		System.exit(0);

	}

	private static boolean isPalindrom(String str) {
		
		for(int i = 0, j = str.length() - 1; i < j; i ++, j --){
			if(str.charAt(i) != str.charAt(j)){
				return false;
			}
		}
		return true;
	}

	/**
	 * Convert integer i to base b ( 2 <= b <= 36 )
	 * @param i
	 * @param b
	 * @return
	 */
	private static String toBase(int i, int b) {
		
		char[] alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

		List<Character> buffer = new ArrayList<Character>();
		
		while(i > 0){
			buffer.add( alphabet[i % b] );
			i /= b;
		}
		
		Collections.reverse(buffer);
		StringBuilder sb = new StringBuilder();
		
		for(Character c : buffer){
			sb.append(c);
		}
		
		return sb.toString();
	}

}
