package common.tree;


public class IsMirror {


	public static void main(String[] args) {
		
		Node<Integer> node1 = new Node<Integer>(2);
		Node<Integer> node2 = new Node<Integer>(3);
		Node<Integer> node3 = new Node<Integer>(3);
		Node<Integer> node4 = new Node<Integer>(4);
		Node<Integer> node5 = new Node<Integer>(5);
		Node<Integer> node6 = new Node<Integer>(5);
		Node<Integer> node7 = new Node<Integer>(4);
		
		node1.left = node2;
		node2.parent = node1;
		
		node1.right = node3;
		node3.parent = node1;
		
		node2.left = node4;
		node4.parent = node2;
		node2.right = node5;
		node5.parent = node2;
		
		node3.left = node6;
		node6.parent = node3;
		node3.right = node7;
		node7.parent = node3;
		
		Node<?> tree = node1;
		
		BTreePrinter.printNode(tree);
		
		System.out.println(isMirror(tree.left, tree.right));
	}

	
	static boolean isMirror(Node<?> tree, Node<?> tree2) {

		if(tree == null || tree2 == null){
			return tree == tree2;
		}
		
		if(tree.data != tree2.data){
			return false;
		}
		
		return isMirror(tree.right, tree2.left) && 
				isMirror(tree.left, tree2.right);
	}
}
