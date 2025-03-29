/**
 * Problem Statement:
 * 
 *     Given a singly linked list, group all odd-indexed nodes together followed by the even-indexed nodes.
 *     The relative order inside both the odd and even groups should remain as it was in the input.
 * 
 * Input:
 *     - A singly linked list where each node contains an integer value.
 * 
 * Output:
 *     - The head of the modified linked list with odd-indexed nodes followed by even-indexed nodes.
 * 
 * Example:
 *     Input:
 *         head = [1, 2, 3, 4, 5]
 *     Output:
 *         [1, 3, 5, 2, 4]
 * 
 *     Explanation:
 *         The odd-indexed nodes (1, 3, 5) are grouped together, followed by the even-indexed nodes (2, 4).
 */

public class j05OddEvenLinkedList {

    /**
     * Node Class:
     * 
     * Represents a node in a singly linked list. Each node contains:
     * - `data`: The integer value stored in the node.
     * - `next`: A reference to the next node in the list.
     */
    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Approach: Odd-Even Grouping
     * 
     * Intuition:
     * - Separate the nodes into two groups: odd-indexed nodes and even-indexed nodes.
     * - Use two pointers, one for odd nodes and one for even nodes, to traverse the list.
     * - At the end, connect the last odd node to the head of the even nodes.
     * 
     * Explanation:
     * 1. If the list is empty or contains only one node, return the head as it is already grouped.
     * 2. Initialize two pointers:
     *     - `oddHead` to the head of the list (first odd node).
     *     - `evenHead` to the second node in the list (first even node).
     * 3. Use two pointers, `odd` and `even`, to traverse the list:
     *     - Update the `next` pointer of the current odd node to skip the next even node and point to the next odd node.
     *     - Update the `next` pointer of the current even node to skip the next odd node and point to the next even node.
     * 4. When the traversal is complete, connect the last odd node to the head of the even nodes.
     * 5. Return the head of the modified list.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the modified linked list with odd-indexed nodes followed by even-indexed nodes.
     */
    public Node oddEvenList(Node head) {
        if (head == null || head.next == null) {
            return head; // Base case: empty list or single node
        }

        Node oddHead = head; // Head of the odd-indexed nodes
        Node evenHead = head.next; // Head of the even-indexed nodes

        Node odd = oddHead; // Pointer for odd nodes
        Node even = evenHead; // Pointer for even nodes

        while (even != null && even.next != null) {
            odd.next = odd.next.next; // Skip the next even node
            even.next = even.next.next; // Skip the next odd node

            odd = odd.next; // Move the odd pointer forward
            even = even.next; // Move the even pointer forward
        }

        odd.next = evenHead; // Connect the last odd node to the head of the even nodes

        return head; // Return the head of the modified list
    }
}