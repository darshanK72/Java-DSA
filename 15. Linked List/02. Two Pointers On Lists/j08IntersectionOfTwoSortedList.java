/**
 * Problem Statement:
 * 
 *      Given two sorted linked lists, return a new linked list that represents their intersection.
 *      The intersection should contain elements that appear in both lists, preserving their relative order.
 * 
 * Constraints:
 *      - Both input linked lists are sorted in non-decreasing order.
 *      - The result should not contain duplicate nodes.
 *      - The original lists should remain unchanged.
 * 
 * Input:
 *      - Two sorted singly linked lists (head1, head2).
 * 
 * Output:
 *      - A new linked list containing the intersection of the two lists.
 * 
 * Example:
 * 
 *     Input:
 *     List 1: 1 -> 2 -> 3 -> 4 -> 5
 *     List 2:       2 -> 4 -> 6
 * 
 *     Output:
 *     2 -> 4
 * 
 *     Explanation:
 *     - Nodes with values 2 and 4 appear in both lists, so they are included in the result.
 */

public class j08IntersectionOfTwoSortedList {

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Approach: Two-Pointer Technique on Sorted Lists
     * 
     * Intuition:
     * - Since both lists are sorted, we can use two pointers to traverse them efficiently.
     * - If both nodes have the same value, we add it to the result and move both pointers forward.
     * - If one node has a smaller value, move that pointer forward to find a match.
     * - This ensures that we process each list in a single pass.
     * 
     * Explanation:
     * 1. Initialize two pointers: `temp1` (pointing to head1) and `temp2` (pointing to head2).
     * 2. Create a dummy node `outHead` to store the result.
     * 3. Iterate while both pointers are not null:
     *    - If `temp1.data == temp2.data`: Add the value to the result list and move both pointers.
     *    - If `temp1.data < temp2.data`: Move `temp1` forward.
     *    - If `temp1.data > temp2.data`: Move `temp2` forward.
     * 4. Return the next node of `outHead` (skipping the dummy node).
     * 
     * Time Complexity:
     * - O(N + M) → Each list is traversed at most once.
     * 
     * Space Complexity:
     * - O(1) → We only use a few extra pointers, and the output list uses existing values.
     * 
     * @param head1 The head of the first sorted linked list.
     * @param head2 The head of the second sorted linked list.
     * @return A new sorted linked list containing intersection elements.
     */
    public static Node findIntersection(Node head1, Node head2) {
        Node temp1 = head1;
        Node temp2 = head2;
        Node outHead = new Node(-1); // Dummy node for output list
        Node outTemp = outHead;

        // Traverse both lists using two pointers
        while (temp1 != null && temp2 != null) {
            if (temp1.data == temp2.data) {
                outTemp.next = new Node(temp1.data); // Add matching value to result list
                outTemp = outTemp.next;
                temp1 = temp1.next;
                temp2 = temp2.next;
            } else if (temp1.data < temp2.data) {
                temp1 = temp1.next; // Move first pointer forward
            } else {
                temp2 = temp2.next; // Move second pointer forward
            }
        }

        return outHead.next; // Return the intersection list (skip dummy node)
    }
}
