package zest;

import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;
    // Correction: otherwise empty node will have value 0
    boolean empty = false;
    ListNode() { this.empty = true; }
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class MergeKSortedLists {

    private boolean invariant(ListNode dummy, ListNode tail){

        // invariant - sortedness
        if (dummy == tail) { return true; } // empty list of ListNode
        ListNode runner = dummy.next;
        while (runner != tail){
            if (runner.next.val < runner.val) {
                return false;
            }
            runner = runner.next;
        }// while
        return true;
    }

    /**
     * The methods merges the linked lists in the lists param into one single linked list.
     *
     * @param lists a list of linked lists, represented by their head node. The list can be empty. Each element must be of type ListNode, can be empty.
     * @return a ListNode that is the head node of the single merged linked list. Can be an empty ListNode.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        // pre-condition
        if (lists.length > 10000) {
            throw new IllegalArgumentException( "The input contains too many linked lists, at most 10^4" );
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int nodeCount = 0;

        for (ListNode node : lists) {

            // Correction - otherwise empty nodes will have value 0
            if (node == null || node.empty) {
                continue;
            }

            if (node != null) {
                //pre-condition
                if (node.val <= 10000 && node.val >= -10000){
                    queue.add(node);
                }
                else {
                    throw new IllegalArgumentException( "Values in linked lists must be between -10^4 and 10^4" );
                }

            }
        }

        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;

            nodeCount += 1;

            //pre-condition
            if (nodeCount > 10000){
                throw new IllegalArgumentException( "The input contains too many values, at most 10^4" );
            }

            if (tail.next != null) {
                queue.add(tail.next);
            }

            if (!invariant(dummy, tail)){
                throw new RuntimeException( "The return linked list is not sorted during execution" );
            }

        }

        // post-condition
        if (!invariant(dummy, tail)){
            throw new RuntimeException( "The return linked list is not sorted after execution" );
        }

        return dummy.next;
    }
}
