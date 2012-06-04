package usaco1;

/*
ID: jastina1
LANG: JAVA
TASK: milk3
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class milk3 {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("milk3.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] capacity = { Integer.parseInt(st.nextToken()), 
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
		
		int[] state = {0, 0, capacity[2]};
		
		Stack<String> stack = new Stack<String>();
		Set<String> set = new HashSet<String>();
		Set<Integer> results = new HashSet<Integer>();
		
		stack.push(toString(state));
		
		int [][] pour = {
				{0,1}, {0,2},{1,2},
				{2,0}, {2,1},{1,0},
		};
		
		while(!stack.empty()){
			
			pop(stack, state);
			
			if(state[0] == 0){
				results.add(state[2]);
			}
			
			for(int i = 0; i < pour.length; i ++){
				
				int from = pour[i][0];
				int to = pour[i][1];
				int amt = Math.min(state[from], capacity[to] - state[to]);
				
				state[from] -= amt;
				state[to] += amt;
				
				String str = toString(state);
				
				if(!set.contains(str)){
					stack.push(str);
					set.add(str);
				}
				
				state[to] -= amt;
				state[from] += amt;
			}
		}
		
		ArrayList<Integer> list = new ArrayList<Integer>(results);
		Collections.sort(list);
		
		for(int i = 0; i < list.size() - 1; i ++){
			System.out.print(list.get(i) + " ");
			pw.print(list.get(i) + " ");
		}
		
		System.out.println(list.get(list.size() - 1));
		pw.println(list.get(list.size() - 1));

		pw.close();
		System.exit(0);

	}

	private static void pop(Stack<String> stack, int[] state) {
		StringTokenizer st = new StringTokenizer(stack.pop());
		
		state[0] = Integer.parseInt(st.nextToken());
		state[1] = Integer.parseInt(st.nextToken());
		state[2] = Integer.parseInt(st.nextToken());
	}

	private static String toString(int[] state) {
		return "" + state[0] + " " + state[1] + " "+ state[2];
	}

	
}
