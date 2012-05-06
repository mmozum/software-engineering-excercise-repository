package usaco1;

/*
ID: jastina1
LANG: JAVA
TASK: palsquare
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class palsquare {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
		
		int B = Integer.parseInt(br.readLine());
		br.close();
		
		List<Integer> solution = new ArrayList<Integer>();
		
		for(int i = 1; i <= 300; i ++){
			
			if(isPalindrom(toBase(i * i, B))){
				solution.add(i);
			}
		}
		
		for(Integer i : solution){
			pw.println(toBase(i, B) + " " + toBase(i*i, B));
			System.out.println(toBase(i, B) + " " + toBase(i*i, B));
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
