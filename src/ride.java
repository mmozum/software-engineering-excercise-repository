/*
ID: jastina1
LANG: JAVA
TASK: ride
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ride {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("ride.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
		
		String comet = br.readLine();
		String group = br.readLine();
		
		long cometNumber = toNumber(comet);
		long groupNumber = toNumber(group);
		String result = (cometNumber % 47 == groupNumber % 47) ? "GO" : "STAY";
		pw.println(result);
		pw.close();
		System.exit(0);

	}

	private static long toNumber(String str) {

		long p = 1;
		
		for(int i = 0; i < str.length(); i++){
			p *= (str.charAt(i) - 'A' + 1);
		}
		return p;
	}

}
