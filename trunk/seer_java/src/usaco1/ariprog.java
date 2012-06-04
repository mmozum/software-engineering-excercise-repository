package usaco1;

/*
ID: jastina1
LANG: JAVA
TASK: ariprog
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ariprog {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("ariprog.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
		
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		
		Set<Integer> set = new HashSet<Integer>();
		
		for(int i = 0; i <= M; i ++){
			for(int j = i; j <= M; j ++){
				
				set.add(i * i + j * j);
			}
		}
		
		final int LIMIT = M * M * 2;
		List<Integer> list = new ArrayList<Integer>(set);
		Collections.sort(list);
		
		List<int[]> resultList = new ArrayList<int[]>();
		
		for(int i = 0; i < list.size() - 1; i ++){
			
			for(int j = i + 1; j < list.size(); j ++){
				
				int a = list.get(i);
				int b = list.get(j) - a;
				int k = a + (N-1) * b;
				
				if(k > LIMIT){
					//System.out.println(k);
					break;
				}
				
				boolean allFound = true;
				
				for(; k > a; k -= b){
					
					if(!set.contains(k)){
						allFound = false;
						break;
					}
				}
				
				if(allFound){
					resultList.add(new int[]{a, b});
				}
			}
		}
		

		Collections.sort(resultList, new Comparator<int[]>(){

			@Override
			public int compare(int[] a1, int[] a2) {
				
				if(a1[1] != a2[1]){
					return a1[1] - a2[1];
				}
				return a1[0] - a2[0];
			}
			
		});
		
		for(int[] r : resultList){
			System.out.println(r[0] + " " + r[1]);
			pw.println(r[0] + " " + r[1]);
		}
		
		if(resultList.isEmpty()){
			System.out.println("NONE");
			pw.println("NONE");
		}
		pw.close();
		System.exit(0);

	}

}
