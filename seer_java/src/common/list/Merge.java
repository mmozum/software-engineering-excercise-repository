package common.list;

/**
 * merge two sorted linkedlist
 * @author Jason Huang
 *
 */
public class Merge {


	public static void main(String[] args) {

		ListNode<Integer> list1 = ListUtil.generateSortedList(15);
		ListNode<Integer> list2 = ListUtil.generateSortedList(10);
		
		System.out.println("Merging impl 1");
		ListUtil.printList(list1);
		System.out.println();
		ListUtil.printList(list2);
		System.out.println();

		ListNode<Integer> head = merge(list1, list2);
		
		ListUtil.printList(head);
		System.out.println();

		System.out.println("Merging impl 2");
		list1 = ListUtil.generateSortedList(15);
		list2 = ListUtil.generateSortedList(10);
		
		ListUtil.printList(list1);
		System.out.println();
		ListUtil.printList(list2);
		System.out.println();
		
		head = merge2(list1, list2);
		
		ListUtil.printList(head);
	}
	
	public static <T extends Comparable<T>> ListNode<T> merge(ListNode<T> list1, ListNode<T> list2){
		
		if(list1 == null){
			return list2;
		}
		
		if(list2 == null){
			return list1;
		}
		
		ListNode<T> head = null;
		ListNode<T> tail = null;
		
		if(list1.data.compareTo(list2.data) <= 0){
			head = list1;
			list1 = list1.next;
		} else {
			head = list2;
			list2 = list2.next;
		}
		
		tail = head;
		
		while(list1 != null && list2 != null){

			if(list1.data.compareTo(list2.data) <= 0){
				tail.next = list1;
				list1 = list1.next;
			} else {
				tail.next = list2;
				list2 = list2.next;
			}
			
			tail = tail.next;
		}
		
		if(list1 == null){
			list1 = list2;
		}
		
		tail.next = list1;
		
		return head;
	}
	

	public static <T extends Comparable<T>> ListNode<T> merge2(ListNode<T> list1, ListNode<T> list2){
		
		ListNode<T> head = new ListNode<T>();
		ListNode<T> current = head;
		
		while(list1 != null && list2 != null){
			if(list1.data.compareTo(list2.data) <= 0){
				current.next = list1;
				list1 = list1.next;
			} else {
				current.next = list2;
				list2 = list2.next;
			}
			current = current.next;
		}
		
		if(list1 == null){
			current.next = list2;
		} else {
			current.next = list1;
		}
		
		return head.next;
		
	}

}
