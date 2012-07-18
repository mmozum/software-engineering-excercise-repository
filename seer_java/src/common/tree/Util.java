package common.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Util {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BTreePrinter.printNode(getSampleTree());

	}

	/**
	 * Generate trees for testing purpose
	 * @return
	 */
	public static List<Node<Integer>> getTrees(){
		
		List<Node<Integer>> list = new ArrayList<Node<Integer>>();
		
		// empty tree
		list.add(null);
		
		// single node tree
		list.add(new Node<Integer>(0));
		
		list.add(getSampleTree());
		list.add(getSampleTree2());
		
		return list;
	}
	
	static Node<Integer> getSampleTree(){
		Node<Integer> node1 = new Node<Integer>(1);
		Node<Integer> node2 = new Node<Integer>(2);
		Node<Integer> node3 = new Node<Integer>(3);
		Node<Integer> node4 = new Node<Integer>(4);
		Node<Integer> node5 = new Node<Integer>(5);
		Node<Integer> node6 = new Node<Integer>(6);
		Node<Integer> node7 = new Node<Integer>(7);
		Node<Integer> node8 = new Node<Integer>(8);
		Node<Integer> node9 = new Node<Integer>(9);
		
		node1.left = node2;
		node2.parent = node1;
		
		node1.right = node7;
		node7.parent = node1;
		
		node2.left = node3;
		node3.parent = node2;
		node2.right = node4;
		node4.parent = node2;
		
		node4.left = node5;
		node5.parent = node4;
		node4.right = node6;
		node6.parent = node4;
		
		node7.right = node8;
		node8.parent = node7;
		node8.left = node9;
		node9.parent = node8;
		
		return node1;
	}
	
	static Node<Integer> getSampleTree2(){
		Node<Integer> node1 = new Node<Integer>(1);
		Node<Integer> node2 = new Node<Integer>(2);
		Node<Integer> node3 = new Node<Integer>(3);
		Node<Integer> node4 = new Node<Integer>(4);
		Node<Integer> node5 = new Node<Integer>(5);
		Node<Integer> node6 = new Node<Integer>(6);
		Node<Integer> node7 = new Node<Integer>(7);
		Node<Integer> node8 = new Node<Integer>(8);
		Node<Integer> node9 = new Node<Integer>(9);
		
		node1.left = node2;
		node2.parent = node1;
		
		node1.right = node3;
		node3.parent = node1;
		
		node2.left = node4;
		node4.parent = node2;
		
		node4.left = node5;
		node5.parent = node4;
		
		node5.left = node8;
		node8.parent = node5;
		
		node3.left = node6;
		node3.parent = node3;
		
		node6.right = node7;
		node7.parent = node6;
		
		node7.left = node9;
		node9.parent = node7;
		
		return node1;
	}
	
	/**
	 * Generate an array of randomized integer
	 * @param n - size of generated array
	 * @return
	 */
	public static Integer[] generateRandomArray(int n){
		
		if(n < 0){
			return null;
		}
		
		// generate an ordered array
		int[] arr = new int[4 * n];
		
		for(int i = 1; i < arr.length; i ++){
			arr[i] = i;
		}
		
		Integer[] resultArr = new Integer[n];
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
	
	/**
	 * Generate a list of randomized integer
	 * @param n - size of the list
	 * @return
	 */
	public static Node<Integer> generateRandomList(int n){
		return array2List( generateRandomArray(n) );
	}
	
	
	/**
	 * Convert an array to list (Using Node structure)
	 * @param arr
	 * @return
	 */
	public static <T extends Comparable<?>> Node<T> array2List(T[] arr){
		
		if(arr == null || arr.length == 0){
			return null;
		}
		
		Node<T> root = new Node<T>( arr[0] );
		Node<T> current = root;
		
		for(int i = 1; i < arr.length; i ++){
			
			Node<T> next = new Node<T>(arr[i]);
			current.right = next;
			next.left = current;
			current = next;
		}
		return root;
	}
	
	/**
	 * Print list
	 * @param list
	 */
	public static void printList(Node<?> list){
		
		while(list != null){
			System.out.println(list.data + " =>");
			list = list.right;
		}
	}
	
}
