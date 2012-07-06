package common.tree;

import java.util.Stack;

public class InorderSuccessor {

	public static void main(String[] args) {

	}
	
	static <T extends Comparable<?>>Node<T> next(Node<T> tree, Node<T> node) {

		if(tree == null){
			return null;
		}
		
		if(node.right != null){
			Node<T> tmp = node.right;
			while(tmp.left != null){
				tmp = tmp.left;
			}
			return tmp;
		}
		
		Stack<Node<T>> stk = new Stack<Node<T>>();
		if(!searchNode(tree, node, stk)){
			return null;
		}
		
		Node<T> child = stk.pop();
		while(!stk.isEmpty()){
			Node<T> parent = stk.pop();
			if(child == parent.left){
				return parent;
			}
			child = parent;
		}
		return null;

	}
	
	static <T extends Comparable<?>> boolean searchNode(Node<T> tree, Node<T> node, Stack<Node<T>> stk){
		
		if(tree == null){
			return false;
		}
		
		stk.push(tree);
		if(tree == node || 
				searchNode(tree.left, node, stk) || 
				searchNode(tree.right, node, stk)){
			return true;
		}
		stk.pop();
		return false;
	}
}
