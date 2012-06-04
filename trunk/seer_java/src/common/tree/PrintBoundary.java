package common.tree;


public class PrintBoundary {


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
		
		printBoundary(tree);
		
	}

	
	static void printBoundary(Node<?> tree) {

		if(tree == null){
			return;
		}
		
		System.out.println(tree.data);
		
		printLeftBoundary(tree.left, true);
		printRightBoundary(tree.right, true);
	}


	private static void printLeftBoundary(Node<?> tree, boolean b) {
		
		if(tree == null){
			return;
		}
		
		if(b || (tree.left == null && tree.right == null)){
				System.out.println(tree.data);
		}
		
		printLeftBoundary(tree.left, b);
		printLeftBoundary(tree.right, false);
		
	}
	
	private static void printRightBoundary(Node<?> tree, boolean b) {
		
		if(tree == null){
			return;
		}
		
		printRightBoundary(tree.left, false);
		printRightBoundary(tree.right, b);

		if(b || (tree.left == null && tree.right == null)){
			System.out.println(tree.data);
		}
		
		
	}
}
