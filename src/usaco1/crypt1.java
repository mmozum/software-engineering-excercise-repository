/*
ID: jastina1
LANG: JAVA
TASK: crypt1
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class crypt1 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
		
		int N = Integer.parseInt(br.readLine());
		String alphabet = br.readLine();

		
		int numSol = 0;
		
		for(int num1 = 100; num1 <= 999; num1++){
			
			pass:
			for(int num2 = 10; num2 <= 99; num2++){
				
				int pp1 = num1 * (num2 % 10);
				int pp2 = num1 * (num2 / 10);
				int prod = num1 * num2;
				
				if(pp1 < 100 || pp1 > 999 || pp2 < 100 || pp2 > 999 || prod < 1000){
					continue;
				}
				
				if(prod > 9999){
					break;
				}
				
				String s = String.valueOf(num1);
				s += String.valueOf(num2);
				s += String.valueOf(pp1);
				s += String.valueOf(pp2);
				s += String.valueOf(prod);
				
				for(char c : s.toCharArray()){
					
					if(alphabet.indexOf(c) < 0){
						continue pass;
					}
				}
				numSol++;
				
			}
		}
		
		System.out.println(numSol);
		pw.println(numSol);
		pw.close();
		System.exit(0);

	}

}
