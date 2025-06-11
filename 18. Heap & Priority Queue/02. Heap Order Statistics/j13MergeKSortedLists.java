/**
 * LeetCode 23: Merge K Sorted Lists
 * 
 * Problem Statement:
 *     You are given an array of k linked-lists lists, each linked-list is sorted
 *     in ascending order. Merge all the linked-lists into one sorted linked-list
 *     and return it.
 * 
 * Input:
 *     - lists[]: Array of k sorted linked lists
 * 
 * Output:
 *     - ListNode: Head of the merged sorted linked list
 * 
 * Example:
 *     Input: lists = [[1,4,5],[1,3,4],[2,6]]
 *     Output: [1,1,2,3,4,4,5,6]
 * 
 *     Explanation:
 *     The linked-lists are:
 *     [
 *       1->4->5,
 *       1->3->4,
 *       2->6
 *     ]
 *     Merging them into one sorted list:
 *     1->1->2->3->4->4->5->6
 */

import java.util.PriorityQueue;

public class j13MergeKSortedLists {

    /**
     * Helper Class: ListNode
     * 
     * Represents a node in a singly linked list:
     * - val: The value stored in the node
     * - next: Reference to the next node in the list
     * 
     * Provides two constructors:
     * - Single parameter: Creates node with value
     * - Two parameters: Creates node with value and next reference
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int v) {
            this.val = v;
        }

        ListNode(int v, ListNode n) {
            this.val = v;
            this.next = n;
        }
    }

    /**
     * Approach: Min Heap with List Tracking
     * 
     * Intuition:
     * - Use min heap to maintain smallest node from each list
     * - Extract minimum node and add its next node to heap
     * - Build merged list by connecting extracted nodes
     * 
     * Explanation:
     * 1. Handle edge case: empty lists array
     * 2. Initialize min heap with first node from each non-empty list
     * 3. Create dummy node to build result list
     * 4. While heap is not empty:
     *    - Extract minimum node
     *    - Add it to result list
     *    - If node has next, add next to heap
     * 5. Return head of merged list
     * 
     * Time Complexity: O(n log k) where n is total number of nodes
     *                  - O(log k) for each heap operation
     *                  - O(n) nodes to process
     * Space Complexity: O(k) for storing k nodes in heap
     * 
     * @param lists    Array of k sorted linked lists
     * @return         Head of the merged sorted linked list
     */
    public ListNode mergeKLists(ListNode[] lists) {
        // Handle empty lists array
        if (lists.length == 0)
            return null;

        // Initialize min heap with custom comparator
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });

        // Add first node from each non-empty list to heap
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.add(lists[i]);
            }
        }

        // Create dummy node to build result list
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        // Process nodes until heap is empty
        while (!pq.isEmpty()) {
            // Extract minimum node
            ListNode node = pq.remove();
            
            // Add node to result list
            curr.next = node;
            curr = curr.next;
            
            // Add next node to heap if available
            if (curr.next != null) {
                pq.add(curr.next);
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        j13MergeKSortedLists solution = new j13MergeKSortedLists();

        // Test Case 1: Basic case
        System.out.println("\nBasic Test Case:");
        ListNode[] lists1 = new ListNode[3];
        lists1[0] = new ListNode(1, new ListNode(4, new ListNode(5)));
        lists1[1] = new ListNode(1, new ListNode(3, new ListNode(4)));
        lists1[2] = new ListNode(2, new ListNode(6));
        ListNode result1 = solution.mergeKLists(lists1);
        System.out.println("Input: [[1,4,5],[1,3,4],[2,6]]");
        System.out.print("Output: ");
        printList(result1);

        // Test Case 2: Empty lists
        System.out.println("\nEmpty Lists Test:");
        ListNode[] lists2 = new ListNode[0];
        ListNode result2 = solution.mergeKLists(lists2);
        System.out.println("Input: []");
        System.out.print("Output: ");
        printList(result2);

        // Test Case 3: Single list
        System.out.println("\nSingle List Test:");
        ListNode[] lists3 = new ListNode[1];
        lists3[0] = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode result3 = solution.mergeKLists(lists3);
        System.out.println("Input: [[1,2,3]]");
        System.out.print("Output: ");
        printList(result3);

        // Test Case 4: Lists with null elements
        System.out.println("\nNull Lists Test:");
        ListNode[] lists4 = new ListNode[3];
        lists4[0] = new ListNode(1, new ListNode(2));
        lists4[1] = null;
        lists4[2] = new ListNode(3, new ListNode(4));
        ListNode result4 = solution.mergeKLists(lists4);
        System.out.println("Input: [[1,2],null,[3,4]]");
        System.out.print("Output: ");
        printList(result4);
    }

    // Helper method to print linked list
    private static void printList(ListNode head) {
        if (head == null) {
            System.out.println("[]");
            return;
        }
        System.out.print("[");
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print(",");
            }
            head = head.next;
        }
        System.out.println("]");
    }
}
