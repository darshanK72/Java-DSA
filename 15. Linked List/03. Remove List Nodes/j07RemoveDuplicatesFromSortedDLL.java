/**
 * Problem Statement:
 * 
 *     Given a sorted doubly linked list, remove all duplicate nodes such that each element
 *     appears only once in the list.
 * 
 * Input:
 *     - A sorted doubly linked list where each node contains an integer value.
 * 
 * Output:
 *     - The head of the modified doubly linked list after removing all duplicate nodes.
 * 
 * Example:
 *     Input:
 *         head = [1, 1, 2, 3, 3, 4]
 *     Output:
 *         [1, 2, 3, 4]
 * 
 *     Explanation:
 *         All duplicate nodes are removed. The resulting list contains only unique elements.
 */

public class j07RemoveDuplicatesFromSortedDLL {

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
     * - Since the list is sorted, all duplicate nodes will appear consecutively.
     * - We can traverse the list and compare each node with its next node. If their values are equal,
     *   we skip the duplicate nodes by updating the `next` and `prev` pointers of the surrounding nodes.
     * 
     * Explanation:
     * 1. Start with the head of the list and initialize the `curr` pointer to the head.
     * 2. Traverse the list while `curr` and `curr.next` are not null:
     *    - If the value of `curr` is equal to the value of `curr.next`:
     *        - Update the `next` pointer of `curr` to skip the duplicate node.
     *        - If the next node exists, update its `prev` pointer to point back to `curr`.
     *    - If the values are not equal, move the `curr` pointer forward.
     * 3. Return the head of the modified list.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the sorted doubly linked list.
     * @return The head of the modified doubly linked list with duplicates removed.
     */
    Node removeDuplicates(Node head) {
        Node curr = head; // Initialize the current pointer

        while (curr != null && curr.next != null) {
            if (curr.data == curr.next.data) { // If the current node and next node have the same value
                curr.next = curr.next.next; // Skip the duplicate node
                if (curr.next != null) {
                    curr.next.prev = curr; // Update the `prev` pointer of the next node
                }
            } else {
                curr = curr.next; // Move the current pointer forward
            }
        }
        return head; // Return the modified head of the list
    }
}