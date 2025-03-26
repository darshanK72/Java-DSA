/**
 * Problem Statement:
 * 
 *      Given a sorted singly linked list, remove all duplicate elements such that each element appears only once.
 *      The resulting linked list should maintain the sorted order.
 * 
 * Constraints:
 *      - The list is sorted in non-decreasing order.
 *      - The list may contain duplicate values.
 * 
 * Input:
 *      - A reference to the head of the sorted linked list.
 * 
 * Output:
 *      - The head of the modified linked list with duplicates removed.
 * 
 * Example:
 * 
 *     Input:
 *     1 -> 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
 * 
 *     Output:
 *     1 -> 2 -> 3 -> 4 -> 5
 */

public class j03RemoveDuplicatesFromSortedListI {

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Approach 1: Iterative In-Place Removal
     * 
     * Intuition:
     * - Since the list is sorted, duplicate values will always be adjacent.
     * - We traverse the list and compare each node with its next node.
     * - If they are equal, we bypass the duplicate node.
     * - Otherwise, we move to the next node.
     * 
     * Explanation:
     * 1. Start from the head.
     * 2. If `temp.data == temp.next.data`, skip `temp.next` by setting `temp.next = temp.next.next`.
     * 3. Otherwise, move `temp` to the next node.
     * 4. Continue until the end of the list.
     * 
     * Edge Cases Considered:
     * - Empty list (head is null).
     * - List with only one node (no duplicates possible).
     * - All elements are unique.
     * - List contains multiple consecutive duplicates.
     * 
     * Time Complexity:
     * - O(N) → Each node is visited once.
     * 
     * Space Complexity:
     * - O(1) → No extra memory used.
     * 
     * @param head The head of the sorted linked list.
     * @return The head of the modified linked list with duplicates removed.
     */
    public Node deleteDuplicates(Node head) {
        Node temp = head;
        while (temp != null && temp.next != null) {
            if (temp.data == temp.next.data) {
                temp.next = temp.next.next; // Skip the duplicate node
            } else {
                temp = temp.next; // Move to the next distinct node
            }
        }
        return head;
    }

    /**
     * Approach 2: Using a Previous Pointer
     * 
     * Intuition:
     * - Instead of checking `temp` directly, use a `prev` pointer to track the last unique node.
     * - Start with a prev node (`prev` initialized to a value smaller than any possible list value).
     * - If `curr.data == prev.data`, skip `curr` by updating `prev.next = curr.next`.
     * - Otherwise, move `prev` forward.
     * 
     * Explanation:
     * 1. Initialize a `prev` node with a prev value (Integer.MIN_VALUE) and link it to the head.
     * 2. Traverse the list using `curr`.
     * 3. If `curr.data == prev.data`, bypass `curr`.
     * 4. Otherwise, move `prev` forward.
     * 5. Return the modified list.
     * 
     * Edge Cases Considered:
     * - Empty list.
     * - Single-element list.
     * - Fully unique list.
     * - List with consecutive duplicates.
     * 
     * Time Complexity:
     * - O(N) → Each node is processed once.
     * 
     * Space Complexity:
     * - O(1) → No additional space used.
     * 
     * @param head The head of the sorted linked list.
     * @return The head of the modified linked list with duplicates removed.
     */
    public Node deleteDuplicatesPrevPointer(Node head) {
        Node prev = new Node(Integer.MIN_VALUE); // prev node
        prev.next = head;
        Node curr = head;
        head = prev; // Update head to prev node
        while (curr != null) {
            if (curr.data == prev.data) {
                prev.next = curr.next; // Skip duplicate node
            } else {
                prev = prev.next; // Move prev to next distinct node
            }
            curr = curr.next; // Move current pointer
        }
        return head.next; // Skip the prev node
    }
}
