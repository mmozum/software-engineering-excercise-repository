package common.tree;

import java.util.Arrays;

public class TraverseTwoBST {

	/**
	 * http://www.careercup.com/question?id=13561671
	 * Given two BST print the element in sorted form
	 * complexity O(n) time maxm alloted space will be O(height of bigger tree)
	 */
	public static void main(String[] args) {
		
		// generate two trees
		Integer[] arr = Util.generateRandomArray(10);
		Arrays.sort(arr);
		Node<Integer> tree = ConvertToBST.array2bst(arr);
		arr = Util.generateRandomArray(20);
		Arrays.sort(arr);
		Node<Integer> tree2 = ConvertToBST.array2bst(arr);
		
		// print two trees
		BTreePrinter.printNode(tree);
		BTreePrinter.printNode(tree2);
		
		// traverse two trees
		traverse(tree, tree2);
	}

	static class ITHelper<T extends Comparable<?>> {
		Node<T> ref;
	}
	
	/**
	 * 
	 * @param tree
	 * @param tree2
	 */
	static <T extends Comparable<? super T>> void traverse(Node<T> tree, Node<T> tree2) {

		ITHelper<T> it = new ITHelper<T>();
		ITHelper<T> it2 = new ITHelper<T>();
		
		it.ref = tree; 
		it2.ref = tree2; 
		
		Node<T> node = getNextNode(it);
		Node<T> node2 = getNextNode(it2);
		
		while(node != null && node2 != null){
			
			if(node.data.compareTo((node2.data)) <= 0){
				System.out.println(node.data);
				node = getNextNode(it);
			} else {
				System.out.println(node2.data);
				node2 = getNextNode(it2);
			}
		}

		// take care of the remainder
		if(node == null){
			it = it2;
			node = node2;
		}
		
		while(node != null){
			System.out.println(node.data);
			node = getNextNode(it);
		}
	}
	
	/**
	 * Use Morris traversal algorithm to find next InOrder node
	 * @param it.ref
	 * @return
	 */
	static <T extends Comparable<?>> Node<T> getNextNode (ITHelper<T> it){
		
		Node<T> next = null;
		
		while(next == null && it.ref != null){
			
			if(it.ref.left != null){
				Node<T> child = it.ref.left;
				
				while(child.right != null && child.right != it.ref){
					child = child.right;
				}
				
				if(child.right == null){
					child.right = it.ref;
					it.ref = it.ref.left;
				} else {
					child.right = null;
					next = it.ref;
					it.ref = it.ref.right;
				}
			} else {
				next = it.ref;
				it.ref = it.ref.right;
			}
		}
		
		return next;
	}
	
}
