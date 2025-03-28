/**
 * Problem Statement:
 * 
 *     Given a doubly linked list and an integer `x`, remove all occurrences of `x` from the list.
 * 
 * Input:
 *     - A doubly linked list where each node contains an integer value.
 *     - An integer `x` representing the value to be removed from the list.
 * 
 * Output:
 *     - The head of the modified doubly linked list after removing all occurrences of `x`.
 * 
 * Example:
 *     Input:
 *         head = [1, 2, 3, 2, 4, 2]
 *         x = 2
 *     Output:
 *         [1, 3, 4]
 * 
 *     Explanation:
 *         All nodes with the value `2` are removed from the list. The resulting list is [1, 3, 4].
 */

public class j06RemoveNodesOfValueFromDLL {

    /**
     * Node Class:
     * 
     * Represents a node in a doubly linked list. Each node contains:
     * - `data`: The integer value stored in the node.
     * - `next`: A reference to the next node in the list.
     * - `prev`: A reference to the previous node in the list.
     */
    static class Node {
        public int data;
        public Node next;
        public Node prev;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * Approach: Iterative Traversal
     * 
     * Intuition:
     * - To remove all occurrences of `x`, we traverse the doubly linked list node by node.
     * - If the current node's value matches `x`, we adjust the `next` and `prev` pointers of the
     *   surrounding nodes to exclude the current node from the list.
     * - A dummy node is used to simplify edge cases, such as when the head node needs to be removed.
     * 
     * Explanation:
     * 1. Create a dummy node and link it to the head of the list.
     * 2. Traverse the list using two pointers: `prev` (points to the previous node) and `curr` (points to the current node).
     * 3. If the current node's value matches `x`:
     *    - Update the `next` pointer of the previous node to skip the current node.
     *    - If the current node is not the last node, update the `prev` pointer of the next node to skip the current node.
     * 4. If the current node's value does not match `x`, move the `prev` pointer forward.
     * 5. Move the `curr` pointer to the next node in the list.
     * 6. Return the new head of the list (i.e., `dummy.next`).
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the doubly linked list.
     * @param x The value to be removed from the list.
     * @return The head of the modified doubly linked list.
     */
    static Node deleteAllOccurOfX(Node head, int x) {
        Node dummy = new Node(0); // Create a dummy node
        dummy.next = head; // Link dummy to the head
        head.prev = dummy; // Link head's prev to dummy

        Node prev = dummy; // Initialize the previous pointer
        Node curr = head; // Initialize the current pointer

        while (curr != null) {
            if (curr.data == x) { // If the current node's value matches `x`
                prev.next = curr.next; // Skip the current node
                if (curr.next != null) { // If the current node is not the last node
                    curr.next.prev = prev; // Update the `prev` pointer of the next node
                }
            } else {
                prev = prev.next; // Move the previous pointer forward
            }
            curr = curr.next; // Move the current pointer forward
        }

        return dummy.next; // Return the new head of the list
    }
}