package org.leetcode.exercises.arrays.tortoisehare;
/*
142. Linked List Cycle II

    Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.
    There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.
    Do not modify the linked list.

Example 1:
    Input: head = [3,2,0,-4], pos = 1
    Output: tail connects to node index 1
    Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:
    Input: head = [1,2], pos = 0
    Output: tail connects to node index 0
    Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:
    Input: head = [1], pos = -1
    Output: no cycle
    Explanation: There is no cycle in the linked list.

Constraints:
    The number of the nodes in the list is in the range [0, 104].
    -105 <= Node.val <= 105
    pos is -1 or a valid index in the linked-list.

 Follow up: Can you solve it using O(1) (i.e. constant) memory?
 */

public class TortoiseHareWithLinkedList {
    public class ListNode {
        int val;
        int index = -1;
        ListNode next;
        public ListNode(int x) {
            val = x;
            next = null;
        }

        public void setIndex(int index){
            this.index = index;
        }

        public void setVal(int val){
            this.val = val;
        }

        public void setNext(ListNode next){
            this.next = next;
        }

        public String toString(){
            return "ListNode{ index = " + index + ", value = " + val + " }";
        }
    }

    public ListNode createListNode(int value){
        return new ListNode(value);
    }

    protected ListNode detectIntersection(ListNode head){
        // Phase 1: Locate the intersection point inside the cycle
        ListNode tortoise = head; // Slow pointer
        ListNode hare = head; // Fast pointer

        while(hare != null && hare.next != null){
            tortoise = tortoise.next; // Moves 1 step
            hare = hare.next.next;  // Moves 2 steps
            if(tortoise == hare){
                return hare;
            }
        }

        return null;
    }

    protected ListNode detectCycle(ListNode head){
        // Phase 2: Find the entrance to the cycle (the duplicate number)
        if(head == null || head.next == null){
            return new ListNode(-1);
        }

        ListNode hare = detectIntersection(head);
        if(hare == null){
            return new ListNode(-1);  // NO Intersection!!
        }

        ListNode tortoise = head;
        while (tortoise != hare) {
            tortoise = tortoise.next;          // Both move 1 step now
            hare = hare.next;
        }

        return tortoise;
    }

    /*
    public ListNode detectCycle(ListNode head) {
        // Phase 1: Locate the intersection point inside the cycle
        ListNode tortoise = head; // Slow pointer
        ListNode hare = head; // Fast pointer

        while(hare != null && hare.next != null){
            tortoise = tortoise.next; // Moves 1 step
            hare = hare.next.next;  // Moves 2 steps
            if(tortoise == hare){
                break;
            }
        }
        if(hare == null || tortoise == null) return new ListNode(-1);

        // Phase 2: Find the entrance to the cycle (the duplicate number)
        tortoise = head;                     // Reset tortoise to start

        while (tortoise != hare) {
            tortoise = tortoise.next;          // Both move 1 step now
            hare = hare.next;
        }

        return tortoise;
    }*/

    public static void main(String args[]){
        TortoiseHareWithLinkedList tortoiseHareWithLinkedList = new TortoiseHareWithLinkedList();
        /*
        Example 1:
          Input: head = [3,2,0,-4], pos = 1
          Output: tail connects to node index 1
          Explanation: There is a cycle in the linked list, where tail connects to the second node.

        */
        TortoiseHareWithLinkedList.ListNode head = null, node1 = null, node2 = null, node3 = null;
        head = tortoiseHareWithLinkedList.createListNode(3);
        head.setIndex(0);
        node1 = tortoiseHareWithLinkedList.createListNode(2);
        node1.setIndex(1);
        node2 = tortoiseHareWithLinkedList.createListNode(0);
        node2.setIndex(2);
        node3 = tortoiseHareWithLinkedList.createListNode(-4);
        node3.setIndex(3);

        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node1);


        System.out.println("1. Tail connects to node with value of: " + tortoiseHareWithLinkedList.detectCycle(head) + "\n");

        head = tortoiseHareWithLinkedList.createListNode(3);
        node1 = tortoiseHareWithLinkedList.createListNode(2);
        node2 = tortoiseHareWithLinkedList.createListNode(0);
        node3 = tortoiseHareWithLinkedList.createListNode(-4);


        /*
        Example 2:
            Input: head = [1,2], pos = 0
            Output: tail connects to node index 0
            Explanation: There is a cycle in the linked list, where tail connects to the first node.|
         */

        head.setVal(1);
        head.setIndex(0);
        node1.setVal(2);
        node1.setIndex(1);
        head.setNext(node1);
        node1.setNext(head);
        System.out.println("2. Tail connects to node with value of: " + tortoiseHareWithLinkedList.detectCycle(head) + "\n");

        /*
        Example 3:
            Input: head = [1], pos = -1
            Output: no cycle
            Explanation: There is no cycle in the linked list.
         */
        head.setVal(1);
        head.setIndex(0);
        head.setNext(null);
        System.out.println("3. Tail connects to node with value of: " + tortoiseHareWithLinkedList.detectCycle(head) + "\n");
    }
}
