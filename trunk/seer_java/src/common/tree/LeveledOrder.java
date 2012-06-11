package common.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeveledOrder {

	public static void main(String[] args) {
		
		
		List<Node<Integer>> list = Util.getTrees();
		
		
		for(Node<Integer> root : list){
			
			System.out.println("-------------------------------");
			System.out.println("leveled1");
			leveled1(root);
			System.out.println();
			
			System.out.println("-------------------------------");
			System.out.println("leveled2");
			leveled2(root);
			System.out.println();
			
			System.out.println("-------------------------------");
			System.out.println("pointToRight");
			pointToRight(root);
			System.out.println();

			System.out.println("-------------------------------");
			System.out.println("zigzaged");
			zigzaged(root);
			System.out.println();
		}
	}
	
	
	public static <T extends Comparable<?>> void pointToRight(Node<T> root){
		
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		
		if(root != null){
			queue.offer(root);
			queue.offer(null);
		}
		
		while(!queue.isEmpty()){
			
			Node<T> node = queue.remove();
			
			if(node == null){
				System.out.println();
				if(!queue.isEmpty()){
					queue.offer(null);
				}
			} else {
				System.out.print("[" + node + " -> " + queue.element() + "]");
				
				if(node.left != null){
					queue.offer(node.left);
				}
				
				if(node.right != null){
					queue.offer(node.right);
				}
			}
		}
	}

	/**
	 * Print a tree by level
	 * Used one queue
	 */
	public static <T extends Comparable<?>> void leveled1(Node<T> root){
		
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		
		if(root != null){
			queue.offer(root);
			queue.offer(null);
		}
		
		while(!queue.isEmpty()){
			
			Node<T> node = queue.remove();
			
			if(node == null){
				System.out.println();
				if(!queue.isEmpty()){
					queue.offer(null);
				}
			} else {
				System.out.print(node + " ");
				
				if(node.left != null){
					queue.offer(node.left);
				}
				
				if(node.right != null){
					queue.offer(node.right);
				}
			}
		}
	}
	
	/**
	 * Print a tree by level
	 * Used two queues
	 */
	public static <T extends Comparable<?>> void leveled2(Node<T> root){
		
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
