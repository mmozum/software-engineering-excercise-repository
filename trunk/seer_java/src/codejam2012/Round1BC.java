package codejam2012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Round1BC {
	
	
	static List<Long> list1, list2;
	static long target;

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new BufferedReader(new FileReader(
				"C-large-practice.in")));
		Output out = new Output("A.out");

		int T = scanner.nextInt();

		for (int t = 1; t <= T; t++) {
			
			int N = scanner.nextInt();
			long[] sArr = new long[N];
			
			for(int i = 0; i < N; i ++){
				sArr[i] = scanner.nextLong();
			}


			Set<Long> sumSet = new HashSet<Long>();
			ArrayList<Long> workspace = new ArrayList<Long>();
			
			boolean b = search1(sumSet, workspace, sArr, 0, 0);
			
			if(b){
				workspace.clear();
				search2(workspace, sArr, 0, 0);
				out.format("Case #%d:\n", t);

				out.format("%s\n", joinList(list1, " "));
				out.format("%s", joinList(list2, " "));
				
			} else {
				out.format("Case #%d: Impossible", t);
				
			}
			
			
//			
//			for(int i = 1; i < N; i ++){
//				out.format(" %f", result[i]);
//			}
			
			out.println("");

		}

		scanner.close();
		out.close();
	}


	private static boolean search2(ArrayList<Long> workspace, long[] sArr,
			int current, long sum) {
		
		if(current >= sArr.length){
			return false;
		}
		
		sum += sArr[current];
		workspace.add(sArr[current]);
		
		if(sum == target){
			list2 = workspace;
			return true;
		}
		
		boolean b = search2(workspace, sArr, current + 1, sum);
		
		if(b){
			return true;
		}
		
		sum -= sArr[current];
		workspace.remove(workspace.size() - 1);
		
		return search2(workspace, sArr, current + 1, sum);
	}


	private static boolean search1(Set<Long> sumSet,
			ArrayList<Long> workspace, long[] sArr, int current, long sum) {

		if(current >= sArr.length){
			return false;
		}
		
		sum += sArr[current];
		workspace.add(sArr[current]);
		
		if(sumSet.contains(sum)){
			list1 = new ArrayList<Long>(workspace);
			target = sum;
			return true;
		}
		
		sumSet.add(sum);
		
		boolean b = search1(sumSet, workspace, sArr, current+1, sum);
		
		if(b){
			return true;
		}
		
		sum -= sArr[current];
		workspace.remove(workspace.size() - 1);
		return search1(sumSet, workspace, sArr, current + 1, sum);
	}

	static <E> String joinList(List<E> list, String sep){
		
		if(list == null || list.isEmpty()){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(list.get(0));
		
		Iterator<E> it = list.iterator();
		it.next();
		
		while(it.hasNext()){
			sb.append(sep).append(it.next());
		}
		
		return sb.toString();
	}
	
	static String joinArray(int[] arr, String sep){
		
		if(arr == null || arr.length == 0){
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(arr[0]);
		
		for(int i = 1; i < arr.length; i ++){
			sb.append(sep).append(arr[i]);
		}
		
		return sb.toString();
	}
	

	static class Output {

		PrintWriter pw;

		public Output(String filename) throws IOException {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
		}

		public void print(String s) {
			pw.print(s);
			System.out.print(s);
		}

		public void println(String s) {
			pw.println(s);
			System.out.println(s);
		}

		public void println() {
			pw.println();
			System.out.println();
		}

		public void format(String format, Object... args) {
			pw.format(format, args);
			System.out.format(format, args);
		}

		public void close() {
			pw.close();
		}
	}

}
