/*
ID: jastina1
LANG: JAVA
TASK: milk
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class milk {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("milk.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk.out")));
		
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		final int[] P = new int[M];
		int[] A = new int[M];
		Integer[] indexP = new Integer[M];
		
		for(int i = 0; i < M; i ++){
			st = new StringTokenizer(br.readLine());
			P[i] = Integer.parseInt(st.nextToken());
			A[i] = Integer.parseInt(st.nextToken());
			indexP[i] = i;
		}
		
		br.close();
		
		Arrays.sort(indexP, new Comparator<Integer>(){
			
			@Override
			public int compare(Integer a, Integer b) {
				return P[a] - P[b];
			}
			
		});
		
		int price = 0;
		int p = 0;
		
		while(N > 0){
			
			int buy = (N < A[indexP[p]]) ? N : A[indexP[p]];
			price += buy * P[indexP[p]];
			N -= buy;
			p++;
			
		}
		
		System.out.println(price);
		pw.println(price);
		pw.close();
		System.exit(0);

	}

}
