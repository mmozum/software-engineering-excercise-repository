package usaco1;

/*
ID: jastina1
LANG: JAVA
TASK: transform
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class transform {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("transform.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
		
		int N = Integer.parseInt(br.readLine());
		
		char[][] before = new char[N][];
		for(int i = 0; i < N; i ++){
			before[i] = br.readLine().toCharArray();
		}
		char[][] after = new char[N][];
		for(int i = 0; i < N; i ++){
			after[i] = br.readLine().toCharArray();
		}
		
		Matrix beforeMat = new Matrix(before);
		Matrix afterMat = new Matrix(after);
		
		int result = detect(beforeMat, afterMat);
		
	
		System.out.println(result);
		
		pw.println(result);
		pw.close();
		System.exit(0);

	}

	private static int detect(Matrix m1, Matrix m2) {
		
		m1.rotation = 90;
		if(equals(m1, m2)){
			return 1;
		}
		
		m1.rotation = 180;
		if(equals(m1, m2)){
			return 2;
		}	
		
		m1.rotation = 270;
		if(equals(m1, m2)){
			return 3;
		}
		
		m1.rotation = 0;
		m1.flip = true;
		if(equals(m1, m2)){
			return 4;
		}
		
		m1.rotation = 90;
		if(equals(m1, m2)){
			return 5;
		}
		
		m1.rotation = 180;
		if(equals(m1, m2)){
			return 5;
		}
		
		
		m1.rotation = 270;
		if(equals(m1, m2)){
			return 5;
		}

		m1.rotation = 0;
		m1.flip = false;
		if(equals(m1, m2)){
			return 6;
		}
		return 7;
	}

	private static boolean equals(Matrix beforeMat, Matrix afterMat) {

		int n = beforeMat.data.length;
		
		for(int i = 0; i < n; i ++){
			for(int j = 0; j < n; j ++){
				if(beforeMat.get(i, j) != afterMat.get(i, j)){
					return false;
				}
			}
		}
		return true;
	}

	static class Matrix{
		char[][] data;
		boolean flip;
		int rotation;
		
		Matrix(char [][] mat){
			data = mat;
			flip = false;
			rotation = 0;
		}
		
		char get(int i, int j){
			
			int x0 = i, y0 = j;
			
			if(flip){
				y0 = data.length - 1 - y0;
			}
			
			int x = x0, y = y0;
			
			if(rotation == 90){
				y = x0;
				x = data.length - 1 - y0;
			} else if(rotation == 180){
				y = data.length - 1 - y0;
				x = data.length - 1 - x0;
			} else if(rotation == 270){
				y = data.length - 1 - x0;
				x = y0;
			}
			
			return data[x][y];
		}
	}
}
