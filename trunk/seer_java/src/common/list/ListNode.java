package common.list;

/**
 * Generic list node class
 * @author Jason Huang
 *
 */
public class ListNode <T> {
	ListNode<T> next, prev;
	T data;
	
	public ListNode(){
	}

	public ListNode(T data){
		this.data = data;
	}
	
	@Override
	public String toString(){
		return data.toString();
	}
}
