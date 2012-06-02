package common.tree;

import java.util.List;
import java.util.Stack;

public class PostOrder {

	public static void main(String[] args) {
		
		
		List<Node<Integer>> list = Util.getTrees();
		
		
		for(Node<Integer> root : list){
			
			System.out.println("-------------------------------");
			System.out.println("recursive");
			postorder_recursive(root);
			System.out.println();
			
			System.out.println("stack");
			postorder_stack(root);
			System.out.println();
			
			System.out.println("parent pointer");
			postorder_parent_pointer(root);
			System.out.println();
		}
	}

	/**
	 * Use recursion
	 */
	public static <T extends Comparable<?>>void postorder_recursive(Node<T> node){
		
		if(node == null){
			return;
		}
		
		postorder_recursive(node.left);
		postorder_recursive(node.right);
		System.out.print(node.data.toString() + " ");
	}
	
	/**
	 * Use stack + a previous pointer
	 */
	public static <T extends Comparable<?>>void postorder_stack(Node<T> root){
		
		Node<T> previous = null;
		Stack<Node<T>> stack = new Stack<Node<T>>();
		
		while(root != null){
			stack.push(root);
			root = root.left;
		}
		
		while(!stack.isEmpty()){
			
			Node<T> current = stack.pop();
			
			if(current.right == previous || current.right == null){
				System.out.print(current.data + " ");
				previous = current;
			} else {
				stack.push(current);
				current = current.right;
				
				while(current != null){
					stack.push(current);
					current = current.left;
				}
			}
		}
		
	}
	
	/**
	 * Use parent pointer + a local previous pointer
	 */
	public static <T extends Comparable<?>>void postorder_parent_pointer(Node<T> root){
		
		if(root == null){
			return;
		}
		
		Node<T> current = root;
		Node<T> previous = null;
		
		while(current != null){
			
			if(previous == current.right || 
					previous == current.left && current.right == null ||
					current.left == null && current.right == null){
				System.out.print(current.data.toString() + " ");
				previous = current;
				current = current.parent;
				
			} else if(previous == current.left || current.left == null){
				assert(current.right != null);
				current = current.right;
			} else {
				current = current.left;
			}
		}
	}
}
