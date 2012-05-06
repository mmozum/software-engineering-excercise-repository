package usaco1;

/*
ID: jastina1
LANG: JAVA
TASK: sprime
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.StringTokenizer;

public class sprimedrat {

	/**
	 * This implementation tries to filter out all prime numbers then pick prime ribs. It failed.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("sprime.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int upperBound = pow10(N + 1) - 1;
		
		BitSet bitset = new BitSet(upperBound + 1);
		bitset.set(0);
		bitset.set(1);
		
		for(int i = 2; i < (int)Math.sqrt(upperBound); i ++){
			
			for(int j = 2; j < (upperBound+i) / i; j ++){
				
				int p = i * j;
				
				if(p > upperBound) continue;
				
				bitset.set(p);
			}
		}
		
		next:
		for(int i = pow10(N); i < upperBound; i ++){
			
			if(bitset.get(i)){
				continue;
			}
			
			for(int j = i/10; j > 0; j /= 10){
				if(bitset.get(j)){
					continue next;
				}
			}
			
			System.out.println(i);
			pw.println(i);
		}
		

		//pw.println(result);
		pw.close();
		System.exit(0);

	}

	private static int pow10(int numDigit) {

		int s = 1;
		
		for(int i = 1; i < numDigit; i ++){
			s *= 10;
		}
		return s;
	}
}
