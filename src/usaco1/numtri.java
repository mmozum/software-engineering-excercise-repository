/*
ID: jastina1
LANG: JAVA
TASK: numtri
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class numtri {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("numtri.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] tri = new int[N][N];
		
		for(int i = 0; i < N; i ++){
			
			st = new StringTokenizer(br.readLine());
			
			for(int j = 0; j <= i; j ++){
				
				tri[i][j] = Integer.parseInt( st.nextToken() );
			}
		}
		

		for(int row = N - 2; row >= 0; row --){

			for(int col = 0; col <= row; col ++){
				tri[row][col] += Math.max(tri[row+1][col], tri[row+1][col+1]);
			}
		}
		
		System.out.println(tri[0][0]);
		pw.println(tri[0][0]);
		pw.close();
		System.exit(0);

	}



}
