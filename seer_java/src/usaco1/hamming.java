package usaco1;

/*
ID: jastina1
LANG: JAVA
TASK: hamming
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class hamming {
	
	static List<Integer> resultList = new ArrayList<Integer>();

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("hamming.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		next:
		for(int i = 0; i < 1 << B && resultList.size() < N; i ++){
			
			for(Integer j : resultList){
				
				int d = i ^ j.intValue();
				
				int c = 0;
				
				do{
					c ++;
					d &= (d-1);
				} while(d > 0);
				
				
				if(c < D){
					continue next;
				}
			}
			
			resultList.add(i);
			
		}
		
		int ind = 0;
		
		while(ind + 10 < resultList.size()){
			
			for(int i = 0; i < 9; i++){
				System.out.print(resultList.get(ind + i) + " ");
				pw.print(resultList.get(ind + i) + " ");
			}
			
			System.out.println(resultList.get(ind + 9));
			pw.println(resultList.get(ind + 9));
			ind += 10;
		}
		
		while(ind < resultList.size() - 1){
			System.out.print(resultList.get(ind) + " ");
			pw.print(resultList.get(ind) + " ");
			
			ind++;
		}
		
		System.out.println(resultList.get(ind));
		pw.println(resultList.get(ind++));
		
		pw.close();
		System.exit(0);

	}

}
