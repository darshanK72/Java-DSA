/**
 * Problem Statement:
 * 
 *      Given a singly linked list, remove all nodes that have a specific value.
 * 
 * Constraints:
 *      - The original list should not be modified; instead, a new modified list should be returned.
 *      - The relative order of elements should be preserved.
 * 
 * Input:
 *      - A singly linked list (head).
 *      - An integer value (val) that needs to be removed.
 * 
 * Output:
 * - A modified linked list with all occurrences of val removed.
 * 
 * Example:
 * 
 *     Input:
 *     List: 1 -> 2 -> 6 -> 3 -> 4 -> 5 -> 6
 *     val = 6
 * 
 *     Output:
 *     1 -> 2 -> 3 -> 4 -> 5
 */

public class j01RemoveListNodeOfValue {
    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Approach: Sentinel Node & Iteration
     * 
     * Intuition:
     * - The challenge in removing elements from a linked list is handling the head node.
     * - Using a sentinel (dummy) node simplifies this by ensuring the head is never removed directly.
     * 
     * Explanation:
     * 1. Create a dummy node (`dummy`) pointing to the actual head.
     * 2. Use a `temp` pointer to traverse the list.
     * 3. If `temp.next.data == val`, skip the node (`temp.next = temp.next.next`).
     * 4. Otherwise, move `temp` forward.
     * 5. Return `dummy.next` to discard the dummy node.
     * 
     * Edge Cases Considered:
     * - Removing the head node.
     * - Removing consecutive nodes with the target value.
     * - Removing the last node.
     * - List with all nodes having `val`.
     * 
     * Time Complexity:
     * - O(N) → Every node is visited once.
     * 
     * Space Complexity:
     * - O(1) → No additional space apart from pointers.
     * 
     * @param head The head of the linked list.
     * @param val The value to be removed.
     * @return Modified linked list after removing nodes with `val`.
     */
    public Node removeElements(Node head, int val) {
        Node dummy = new Node(-1); // Dummy node
        dummy.next = head;
        Node temp = dummy;

        while (temp.next != null) {
            if (temp.next.data == val) {
                temp.next = temp.next.next; // Remove node
            } else {
                temp = temp.next; // Move forward
            }
        }
        return dummy.next; // Skip dummy node and return updated list
    }
}
