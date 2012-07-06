package j;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Util {
	
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
	
	/**
	 * Generate an array of non-negative integers
	 * @param size
	 * @param range
	 * @return
	 */
	public static int[] randomeArray(int size, int range){
		
		if(size <= 0 || range <= 0){
			return null;
		}
		
		Random rand = new Random();
		
		int[] arr = new int[size];
		
		for(int i = 0; i < size; i ++){
			arr[i] = rand.nextInt(range);
		}
		
		return arr;
	}
	

	
	/**
	 * compare the equality of two integer arrays
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static boolean arrayEquals(int[] arr1, int[] arr2){
		
		if(arr1 == null || arr2 == null){
			return arr1 == arr2;
		}
		
		if(arr1.length != arr2.length){
			return false;
		}
		
		for(int i = 0; i < arr1.length; i ++){
			if(arr1[i] != arr2[i]){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Generate an array of randomized integer
	 * @param n - size of generated array
	 * @return
	 */
	public static int[] generateRandomArray(int n){
		
		if(n < 0){
			return null;
		}
		
		// generate an ordered array
		int[] arr = new int[4 * n];
		
		for(int i = 1; i < arr.length; i ++){
			arr[i] = i;
		}
		
		int[] resultArr = new int[n];
		Random rand = new Random();
		
		// shuffle the first n elements as output
		for(int i = 0; i < n; i ++){
			int j = i + rand.nextInt(arr.length - i);
			int tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
			resultArr[i] = arr[i];
		}
		
		return resultArr;
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
