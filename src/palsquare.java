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
import java.util.StringTokenizer;

public class palsquare {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("palsquare.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
		
		int B = Integer.parseInt(br.readLine());
		
		int result = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
		pw.println(result);
		pw.close();
		System.exit(0);

	}

}
