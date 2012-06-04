package usaco1;

/*
ID: jastina1
LANG: JAVA
TASK: milk2
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class milk2 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("milk2.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
		
		int N = Integer.parseInt(br.readLine());
		
		Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
		
		for(int i = 0; i < N; i ++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if(!table.containsKey(start)){
				table.put(start, 1);
			} else {
				table.put(start, table.get(start) + 1);
			}
			
			if(!table.containsKey(end)){
				table.put(end, -1);
			} else {
				table.put(end, table.get(end) - 1);
			}
			
		}
		
		Integer[] points = table.keySet().toArray(new Integer[0]);
		Arrays.sort(points);
		
		int busy = 0, idle = 0;
		int count = table.get(points[0]);
		int start = 0;
		
		for(int i = 1; i < points.length; i ++){
			
			count += table.get(points[i]);
			
			if(count == 0){
				int busyInterval = points[i] - points[start];
				if(busyInterval > busy){
					busy = busyInterval;
				}
				
				if(i < points.length - 1){
					int idleInterval = points[i + 1] - points[i];
					if(idleInterval > idle){
						idle = idleInterval;
					}
					
					start = i + 1;
				}
			}
			
		}
		
		System.out.println(busy + " " + idle);
		pw.println(busy + " " + idle);
		pw.close();
		System.exit(0);

	}

}
