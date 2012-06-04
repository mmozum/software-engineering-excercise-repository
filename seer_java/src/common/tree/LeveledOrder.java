package common.tree;

import java.util.LinkedList;
import java.util.List;

public class LeveledOrder {

	public static void main(String[] args) {
		
		
		List<Node<Integer>> list = Util.getTrees();
		
		
		for(Node<Integer> root : list){
			
			System.out.println("-------------------------------");
			System.out.println("leveled");
			leveled(root);
			System.out.println();
			
			System.out.println("-------------------------------");
			System.out.println("zigzaged");
			zigzaged(root);
			System.out.println();
		}
	}

	/**
	 * Print a tree by level
	 */
	public static <T extends Comparable<?>> void leveled(Node<T> root){
		
		LinkedList<Node<T>> currentLevel = new LinkedList<Node<T>>();
		LinkedList<Node<T>> nextLevel = new LinkedList<Node<T>>();
		
		if(root != null){
			currentLevel.add(root);
		}
		
		while(!currentLevel.isEmpty()){
			for(Node<T> n : currentLevel){
				System.out.print(n.data + " ");
				
				if(n.left != null)
					nextLevel.add(n.left);
				
				if(n.right != null)
					nextLevel.add(n.right);
			}
			
			System.out.println();
			currentLevel = nextLevel;
			nextLevel = new LinkedList<Node<T>>();
		}
	}
	
	/**
	 * Print a tree by level but in a zigzaged manner
	 */
	public static <T extends Comparable<?>> void zigzaged(Node<T> root){

		LinkedList<Node<T>> currentLevel = new LinkedList<Node<T>>();
		LinkedList<Node<T>> nextLevel = new LinkedList<Node<T>>();
		
		boolean isEven = true;
		
		if(root != null){
			currentLevel.add(root);
		}
		
		while(!currentLevel.isEmpty()){
			
			for(Node<T> n : currentLevel){
				System.out.print(n.data + " ");
				
				if(isEven){
					if(n.left != null)
						nextLevel.addFirst(n.left);
					
					if(n.right != null)
						nextLevel.addFirst(n.right);
				} else {
					
					if(n.right != null)
						nextLevel.addFirst(n.right);
					
					if(n.left != null)
						nextLevel.addFirst(n.left);
				}
			}
			
			System.out.println();
			isEven = !isEven;
			currentLevel = nextLevel;
			nextLevel = new LinkedList<Node<T>>();
		}
	}
}
