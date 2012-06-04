package common.tree;

import java.util.List;
import java.util.Stack;

public class InOrder {

	public static void main(String[] args) {
		
		
		List<Node<Integer>> list = Util.getTrees();
		
		
		for(Node<Integer> root : list){
			
			System.out.println("-------------------------------");
			System.out.println("recursive");
			inorder_recursive(root);
			System.out.println();
			
			System.out.println("stack");
			inorder_stack(root);
			System.out.println();
			
			System.out.println("parent pointer");
			inorder_parent_pointer(root);
			System.out.println();
			
			System.out.println("Morris traversal");
			inorder_parent_pointer(root);
			System.out.println();
		}
	}

	/**
	 * Use recursion
	 */
	public static <T extends Comparable<?>>void inorder_recursive(Node<T> node){
		
		if(node == null){
			return;
		}
		
		inorder_recursive(node.left);
		System.out.print(node.data.toString() + " ");
		inorder_recursive(node.right);
	}
	
	/**
	 * Use stack
	 */
	public static <T extends Comparable<?>>void inorder_stack(Node<T> root){
		
		Stack<Node<T>> stack = new Stack<Node<T>>();
		while(root != null){
			stack.push(root);
			root = root.left;
		}
		
		while(!stack.isEmpty()){
			Node<T> node = stack.pop();
			System.out.print(node.data + " ");
			
			node = node.right;
			while(node != null){
				stack.push(node);
				node = node.left;
			}
		}
	}
	
	/**
	 * Use parent pointer
	 */
	public static <T extends Comparable<?>>void inorder_parent_pointer(Node<T> root){
		
		if(root == null){
			return;
		}
		
		Node<T> current = root;
		
		while(current.left != null){
			current = current.left;
		}
		
		while(current != null){
			
			System.out.print(current.data.toString() + " ");
			
			if(current.right != null){
				current = current.right;
				
				while(current.left != null){
					current = current.left;
				}
				
			} else {

				while(current.parent != null && current != current.parent.left){
					current = current.parent;
				}
				
				current = current.parent;
			}
		}
	}
	
	/**
	 * Morris Traversal
	 */
	
	public static <T extends Comparable<?>> void inorder_morris(Node<T> root){
		
		Node<T> current = root;
		
		while(current != null){
			
			if(current.left != null){
				Node<T> tmp = current.left;
				
				while(tmp.right != null && tmp.right != current){
					tmp = tmp.right;
				}
				
				if(tmp.right == null){
					tmp.right = current;
					current = current.left;
				} else {
					tmp.right = null;
					System.out.print(current.data + " ");
					current = current.right;
				}
			} else {
				System.out.print(current.data + " ");
				current = current.right;
			}
		}
		
	}
}
