package ihasleetcode;

import java.util.LinkedList;

public class ReverseNodesInK {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(6);
		
		n1.next = n2;
//		n2.next = n3;
//		n3.next = n4;
//		n4.next = n5;
//		n5.next = n6;
		
		reverseKGroup(n1, 3);

	}
	
	static class ListNode{
		int val;
		ListNode next;
		
		ListNode(int v){
			val = v;
			next = null;
		}
	}
	
    static public ListNode reverseKGroup(ListNode head, int k) {

        if(k < 1 || head == null) return head;
        
        ListNode newHead = null;
        ListNode newTail = null;
        
        ListNode secHead = null;
        ListNode secTail = null;
        ListNode current = head;
        
        int size = 0;
        while(current != null){
        	size ++;
        	current = current.next;
        }
        
        int num = size / k;
        current = head;
        
        for(int i = 0; i < num; i ++){
        	
        	for(int j = 0; j < k; j ++){
        		ListNode next = current.next;
        		
        		if(secHead == null){
        			secHead = secTail = current;
        			secTail.next = null;
        		} else {
        			current.next = secHead;
        			secHead = current;
        		}
        		
        		current = next;
        	}
        	
        	if(newHead == null){
        		newHead = secHead;
        		newTail = secTail;
        	} else {
        		
        		newTail.next = secHead;
        		newTail = secTail;
        	}
        	
        	secHead = secTail = null;
        }
        
        if(current != null){
        	if(newHead == null){
        		newHead = current;
        	} else {
        		newTail.next = current;
        	}
        }
        
        return newHead;
        
    }
    

}
