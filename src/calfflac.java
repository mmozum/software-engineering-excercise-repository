/*
ID: jastina1
LANG: JAVA
TASK: calfflac
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class calfflac {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("calfflac.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")));
		
		StringBuilder sb = new StringBuilder();
		String line;
		
		while((line = br.readLine()) != null){
			sb.append(line + "\n");
		}
		
		String rawInput = sb.toString();
		sb = new StringBuilder();
		
		for(char c : rawInput.toCharArray()){
		
			if(Character.isLetter(c)){
				sb.append( Character.toLowerCase(c) );
			}
		}
		String normalizedInput = sb.toString();
		br.close();
		
		int longestPal = 1;
		int longestPalIndex = 0;
		
		for(int i = 1; i < normalizedInput.length(); i ++){
			
			// check the odd number palindrome
			for(int p1 = i - 1, p2 = i + 1; 
					p1 >= 0 && p2 < normalizedInput.length() && normalizedInput.charAt(p1) == normalizedInput.charAt(p2);
					p1--, p2++){
				
				if(p2 - p1 + 1 > longestPal){
					longestPal = p2 - p1 + 1;
					longestPalIndex = p1;
				}
			}
			
			
			// check the even number palindrome
			for(int p1 = i - 1, p2 = i; 
					p1 >= 0 && p2 < normalizedInput.length() && normalizedInput.charAt(p1) == normalizedInput.charAt(p2);
					p1--, p2++){
				
				if(p2 - p1 + 1 > longestPal){
					longestPal = p2 - p1 + 1;
					longestPalIndex = p1;
				}
			}
		}
				
		int start = find(rawInput, 0, longestPalIndex);
		int end = find(rawInput, start, longestPal - 1);
		
		String ans = rawInput.substring(start, end+1);
		
		System.out.println(ans);
		pw.println(longestPal);
		
		pw.println(ans);
		pw.close();
		System.exit(0);

	}
	
	private static int find(String s, int start, int after){
		
		int p = start;
		
		// find first character
		while(!Character.isLetter(s.charAt(p))){
			p ++;
		}
		
		for(int i = 0; i < after; i ++){
			do {
				p++;
			}while(!Character.isLetter(s.charAt(p)));
		}
		
		return p;
	}

}
