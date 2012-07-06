package common.list;

import java.util.Arrays;

import common.tree.Util;

public final class ListUtil {
	
	private ListUtil(){}
	
	static public ListNode<Integer> generateSortedList(int n){
		Integer[] arr = Util.generateRandomArray(n);
		Arrays.sort(arr);
		return arr2list(arr);
	}

	static public ListNode<Integer> generateList(int n){
		
		Integer[] arr = Util.generateRandomArray(n);
		
		return arr2list(arr);
	}
	
	static public <T>void printList(ListNode<T> head){
		
		if(head == null){
			System.out.println(head);
			return;
		}
		
		while(head != null){
			
			for(int i = 0; i < 20 && head != null; i ++){
				System.out.print(head + " -> ");
				head = head.next;
			}
			
			System.out.println();
		}
		
	}

	static private ListNode<Integer> arr2list(Integer[] arr) {
		
		ListNode<Integer> head = new ListNode<Integer>();
		ListNode<Integer> tail = head;
		for(Integer i : arr){
			tail.next = new ListNode<Integer>(i);
			tail = tail.next;
		}
		return head.next;
	}
	
	

}
