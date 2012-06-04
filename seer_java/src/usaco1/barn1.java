package usaco1;

/*
ID: jastina1
LANG: JAVA
TASK: barn1
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class barn1 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("barn1.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("barn1.out")));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int M = Integer.parseInt(st.nextToken());
		int S = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		int[] cows = new int[C];
		for(int i = 0;  i < C; i ++){
			cows[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		
		Arrays.sort(cows);
		boolean[] stall = new boolean[ cows[cows.length - 1] - cows[0] + 1 ];
		Arrays.fill(stall, false);
		
		for(int i : cows){
			stall[i - cows[0]] = true;
		}
		
		int start = -1;
		List<Integer> interval = new ArrayList<Integer>();
		
		for(int i = 0; i < stall.length; i ++){
			
			if(stall[i]){

				if(i > start + 1)
					interval.add( i - start - 1 );
				
				start = i;
			}
		}
		
		Collections.sort(interval, Collections.reverseOrder());
		
		int blocked = stall.length;
		for(int i = 0; i < M - 1 && i < interval.size() && blocked > 0; i ++){
			blocked -= interval.get(i);
		}
		
		System.out.println(blocked);
		pw.println(blocked);
		pw.close();
		System.exit(0);

	}

}
