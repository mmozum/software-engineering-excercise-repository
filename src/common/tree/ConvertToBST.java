package common.tree;

import java.util.Arrays;

public class ConvertToBST {

	static Node globalNode;
	
	public static void main(String[] args){
		
		final int n = 20;
		Integer[] arr = Util.generateRandomArray(n);
		Arrays.sort(arr);
		Node<Integer> treeFromArray = array2bst(arr);
		BTreePrinter.printNode(treeFromArray);
		
		System.out.println("----------------------------");
		Node<Integer> list = Util.array2List(arr);
		Node<Integer> treeFromList = list2bst(list);
		BTreePrinter.printNode(treeFromArray);
	}

	// convert a sorted list to BST
	public static <T extends Comparable<?>> Node<T> list2bst(Node<T> list) {
		
		if(list == null){
			return null;
		}
		
		int n = 0;
		for(Node<T> node = list; node != null; node = node.right){
			n ++;
		}
		
		globalNode = list;
		return list2bstImpl(n);
	}

	private static <T extends Comparable<?>> Node<T> list2bstImpl(int n) {
		
		if(n <= 0){
			return null;
		}
		
		int middle = n / 2;
		Node<T> left = list2bstImpl(middle - 1);
		Node<T> current = (Node<T>)globalNode;
		globalNode = globalNode.right;
		Node<T> right = list2bstImpl(n - middle - 1);
		
		current.left = left;
		current.right = right;
		return current;
	}

	// convert a sorted array to BST
	public static <T extends Comparable<?>> Node<T> array2bst(T[] arr) {
		
		if(arr == null){
			return null;
		}
		
		return array2bstImpl(arr, 0, arr.length - 1);
	}
	
	private static <T extends Comparable<?>> Node<T> array2bstImpl(T[] arr, int start, int end){
		
		if(start > end){
			return null;
		}
		
		int middle = start + (end - start) / 2;
		Node<T> node = new Node<T>(arr[middle]);
		node.left = array2bstImpl(arr, start, middle - 1);
		node.right = array2bstImpl(arr, middle + 1, end);
		return node;
	}

}
