package ihasleetcode;

public class ReverseLinkedListII {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	

	 public class ListNode {
	      int val;
	      ListNode next;
	      ListNode(int x) {
	          val = x;
	          next = null;
	      }
	 }
	 
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head == null || head.next == null || m == n){
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode tail1 = dummy;
        int level = 1;
        
        while(level < m){
            tail1 = tail1.next;
            level ++;
        }

        ListNode tail2 = tail1.next;
        ListNode head2 = null;
        ListNode cur = tail1.next;
        while(level < n){
            ListNode next = cur.next;
            cur.next = head2;
            head2 = cur;
            cur = next;
            level ++;
        }

        tail1.next = cur;
        tail2.next = cur.next;
        cur.next = head2;
  
        return dummy.next;
    }
    
}
