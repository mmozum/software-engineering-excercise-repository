package common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import common.tree.Util;

/**
 * K-way merge k sorted arrays
 * @author Jason Huang
 */

public class KWayMerge {

	static Random rand = new Random();

	static Integer[][] generateTestcase() {

		int N = 6 + rand.nextInt(5);

		Integer[][] testcases = new Integer[N][];

		for (int i = 0; i < N; i++) {
			testcases[i] = Util.generateRandomArray(10 + rand.nextInt(5));
			Arrays.sort(testcases[i]);
		}

		return testcases;
	}

	static class Item {
		int array;
		int index;
		
		public Item(int array, int index){
			this.array = array;
			this.index = index;
		}
	}

	public static void main(String[] args) {

		Integer[][] testcases = generateTestcase();
		
		List<Integer> list = kWayMerge(testcases);
		
		System.out.println(list);

	}
	
	public static List<Integer> kWayMerge(final Integer[][] arrays){
		
		final Comparator<Item> cmp = new Comparator<Item>() {

			@Override
			public int compare(Item it1, Item it2) {
				return arrays[it1.array][it1.index]
						- arrays[it2.array][it2.index];
			}
		};
		
		List<Integer> list = new ArrayList<Integer>();
		
		PriorityQueue<Item> queue = new PriorityQueue<Item>(arrays.length, cmp);
		for(int i = 0; i < arrays.length; i ++){
			if(arrays[i] != null && arrays[i].length > 0){
				queue.offer(new Item(i, 0));
			}
		}
		
		while(!queue.isEmpty()){
			Item item = queue.remove();
			list.add(arrays[item.array][item.index]);
			item.index ++;
			if(item.index < arrays[item.array].length){
				queue.offer(item);
			}
		}
		
		return list;
	}

}
