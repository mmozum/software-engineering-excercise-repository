package common.tree;

/**
 * Generic tree/list node class
 * @author Jason Huang
 *
 */
public class Node <T extends Comparable<?>> {
	Node<T> left, right, parent;
	T data;
	
	public Node(T data){
		this.data = data;
	}
	
	@Override
	public String toString(){
		return data.toString();
	}
}
