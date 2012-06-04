package common.tree;

/**
 * My list implementation
 * @author Jason Huang
 *
 */
public class MyList {
	
	public static <T extends Comparable<?>> Node<T> reverse_iterative(Node<T> list){
		if(list == null){
			return null;
		}
		
		Node<T> newHead = null;
		while(list != null){
			Node<T> next = list.right;
			list.right = newHead;
			newHead = list;
			list = next;
		}
		
		return newHead;
	}
	
	public static <T extends Comparable<?>> Node<T> reverse_recursive(Node<T> list){
		if(list == null || list.right == null){
			return list;
		}
		
		Node<T> head = reverse_recursive(list.right);
		list.right.right = list;
		list.right = null;
		return head;
	}
	
	public static void findLoop(){
		
		final int N = 9;
		Node<Integer> list = Util.generateRandomList(N);
		
		Util.printList(list);
		
		Node<Integer> tail = list;
		Node<Integer> current = list;
		for(int i = 0; i < 3; i ++){
			current = current.right;
		}
		
		for(int i = 0; i < N - 1; i ++){
			tail = tail.right;
		}
		
		assert(tail.right == null);
		
		tail.right = current;
		System.out.println("common node is: " + current);
		
		// now Find the loop
		current = tail = list;
		
		do{
			current = current.right.right;
			tail = tail.right;
		} while(current != tail);
		
		tail = list;
		
		while(current != tail){
			current = current.right;
			tail = tail.right;
		}
		System.out.println("common node is: " + current);
	}
	
	
	static void findCross(){
		
		final int M = 9;
		final int N = 9;
		
		Node<Integer> list1 = Util.generateRandomList(M);
		Node<Integer> list2 = Util.generateRandomList(N);
		
		Node<Integer> p1 = list1, p2 = list2;
		for(int i = 0; i < N - 1; i ++){
			p2 = p2.right;
		}
		
		assert(p2.right == null);
		
		p2.right = p1.right.right;
		
		Util.printList(list1);
		System.out.println("---");
		Util.printList(list2);
		
		// now find the first common node
		p1 = list1;	p2 = list2;
		int m = 0, n = 0;
		
		while(p1 != null){
			p1 = p1.right;
			m ++;
		}
		
		while(p2 != null){
			p2 = p2.right;
			n ++;
		}
		
		p1 = list1; p2 = list2;
		
		if(m > n){
			for(int i = 0; i < m - n; i ++){
				p1 = p1.right;
			}
		} else {
			for(int i = 0; i < n - m; i ++){
				p2 = p2.right;
			}
		}
		
		while(p1 != p2){
			p1 = p1.right;
			p2 = p2.right;
		}
		
		System.out.println("Found: " + p1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

//		Node<Integer> list = Util.generateRandomList(10);
//		Util.printList(list);
//		System.out.println("=================");
//		list = reverse_recursive(list);
//		Util.printList(list);
		
		findCross();

	}

}
