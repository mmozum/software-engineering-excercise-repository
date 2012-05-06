package codejam2012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class KindomRush {
	
	static class Item {
		int cost1;
		int cost2;
		int level;
		int status;
		
		Item(int l, int c1, int c2){
			level = l;
			cost1 = c1;
			cost2 = c2;
		}
	}

	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(new BufferedReader(new FileReader(
				"A-large-practice.in.txt")));
		Output out = new Output("A.out");

		int T = scanner.nextInt();

		for (int t = 1; t <= T; t++) {

			int N = scanner.nextInt();
			
			Item[] itemsBy2 = new Item[N];

			for (int i = 0; i < N; i++) {
				
				itemsBy2[i] = new Item(i, scanner.nextInt(), scanner.nextInt());
			}
			
			
			Comparator<Item> cmp1 = new Comparator<Item>(){

				@Override
				public int compare(Item arg0, Item arg1) {
					
					int diff = arg0.cost1 - arg1.cost1;
					
					if(diff != 0){
						return diff;
					}
					
					diff = arg1.cost2 - arg0.cost2;
					return diff;
				}
				
			};

			Comparator<Item> cmp2 = new Comparator<Item>(){
				
				@Override
				public int compare(Item arg0, Item arg1) {
					
					return arg0.cost2 - arg1.cost2;
				}
				
			};
			
			Arrays.sort(itemsBy2, cmp2);
			
			int stars = 0;
			int hit = 0;
			int ind2 = 0;

			while(ind2 < N){
				
				if(stars >= itemsBy2[ind2].cost2){
					
					if(itemsBy2[ind2].status == 0){
						stars += 2;
					} else {
						stars += 1;
					}
					
					itemsBy2[ind2].status = 2;
					ind2 ++;
					hit ++;
					
				} else {
					
					Item i = null;
					
					for(int j = N - 1; j >= 0; j --){
						
						if(itemsBy2[j].cost1 <= stars && itemsBy2[j].status == 0){
							i = itemsBy2[j];
							break;
						}
					}
					
					if(i == null){
						break;
					}
					
					i.status = 1;
					stars ++;
					hit ++;
					
				} 
			}

			if(ind2 < N){
				out.format("Case #%d: Too Bad\n", t);
			} else {
				out.format("Case #%d: %d\n", t, hit);
			}
		}

		scanner.close();
		out.close();
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

		public void format(String format, Object... args) {
			pw.format(format, args);
			System.out.format(format, args);
		}

		public void close() {
			pw.close();
		}
	}

}
