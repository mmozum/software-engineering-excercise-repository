package common.tree;

import java.util.List;
import java.util.Stack;

public class PreOrder {

	public static void main(String[] args) {
		
		
		List<Node<Integer>> list = Util.getTrees();
		
		
		for(Node<Integer> root : list){
			
			System.out.println("-------------------------------");
			System.out.println("recursive");
			preorder_recursive(root);
			System.out.println();
			
			System.out.println("stack");
			preorder_stack(root);
			System.out.println();
			
			System.out.println("parent pointer");
			preorder_parent_pointer(root);
			System.out.println();
		}
	}

	/**
	 * Use recursion
	 */
	public static <T extends Comparable<?>>void preorder_recursive(Node<T> node){
		
		if(node == null){
			return;
		}
		
		System.out.print(node.data.toString() + " ");
		
		preorder_recursive(node.left);
		preorder_recursive(node.right);
	}
	
	/**
	 * Use stack
	 */
	public static <T extends Comparable<?>>void preorder_stack(Node<T> root){
		
		Stack<Node<T>> stack = new Stack<Node<T>>();
		stack.push(root);
		
		while(!stack.isEmpty()){
			Node<T> node = stack.pop();
			
			if(node == null){
				continue;
			}
			
			System.out.print(node.data.toString() + " ");
			stack.push(node.right);
			stack.push(node.left);
			
		}
	}
	
	/**
	 * Use parent pointer
	 */
	public static <T extends Comparable<?>>void preorder_parent_pointer(Node<T> root){
		
		Node<T> current = root;
		
		while(current != null){
			
			System.out.print(current.data.toString() + " ");
			
			if(current.left != null){
				current = current.left;
			} else if(current.right != null){
				current = current.right;
			} else {
				
				while(current.parent != null && current != current.parent.left){
					current = current.parent;
				}
				
				if(current.parent != null){
					current = current.parent.right;
				} else {
					break;
				}
			}
		}
	}
}
