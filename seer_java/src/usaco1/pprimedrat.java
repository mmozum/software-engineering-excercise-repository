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
import java.util.BitSet;
import java.util.StringTokenizer;

public class pprimedrat {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("pprime.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		
		BitSet bitset = new BitSet(b - a + 1);
		
		for(int i = 2; i < (int)Math.sqrt(b); i ++){
			
			for(int j = Math.max(a,  i * 2) / i; j < (b+i) / i; j ++){
				
				int p = i * j;
				
				if(p < a || p > b) continue;
				
				bitset.set(p - a);
			}
		}
		
		for(int i = a; i <= b; i ++){
			if(!bitset.get(i - a) && isPalindrome("" + i)){
				pw.println(i);
				System.out.println(i);
			}
		}
		//pw.println(result);
		pw.close();
		System.exit(0);

	}

	private static boolean isPalindrome(String str) {

		if(str == null || str.isEmpty()){
			return true; // or false
		}
		
		for(int i = 0, j = str.length() -1; i < j; i ++, j --){
			if(str.charAt(i) != str.charAt(j)){
				return false;
			}
		}
		return true;
	}

}
