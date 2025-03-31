/**
 * Problem Statement:
 * 
 *     Given a singly linked list, rotate the list to the right by `k` places.
 * 
 * Input:
 *     - A singly linked list where each node contains an integer value.
 *     - An integer `k` representing the number of rotations.
 * 
 * Output:
 *     - The head of the rotated linked list.
 * 
 * Example:
 *     Input:
 *         head = [1, 2, 3, 4, 5], k = 2
 *     Output:
 *         [4, 5, 1, 2, 3]
 * 
 *     Explanation:
 *         After rotating the list to the right by 2 places, the new head is 4, and the list becomes [4, 5, 1, 2, 3].
 */

public class j03RotateLinkedList {

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
     * Approach: Rotate the List
     * 
     * Intuition:
     * - To rotate the list to the right by `k` places, we can think of it as moving the last `k` nodes
     *   to the front of the list.
     * - This can be achieved by making the list circular temporarily and then breaking it at the correct position.
     * 
     * Explanation:
     * 1. Handle edge cases where the list is empty, has only one node, or `k` is 0.
     * 2. Calculate the size of the list by traversing it.
     * 3. Update `k` to `k % size` to handle cases where `k` is greater than the size of the list.
     * 4. If `k` is 0 after the modulo operation, return the head as no rotation is needed.
     * 5. Make the list circular by connecting the last node to the head.
     * 6. Find the new tail of the list, which is `(size - k)` nodes from the start.
     * 7. Break the circular connection to form the rotated list.
     * 8. Return the new head of the rotated list.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the linked list. Each node is visited a constant number of times.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @param k The number of rotations to the right.
     * @return The head of the rotated linked list.
     */
    public Node rotateRight(Node head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head; // Base case: empty list, single node, or no rotation needed
        }

        // Step 1: Calculate the size of the list
        Node curr = head;
        int size = 1;
        while (curr.next != null) {
            curr = curr.next;
            size++;
        }

        // Step 2: Update k to k % size
        k = k % size;
        if (k == 0) {
            return head; // No rotation needed
        }

        // Step 3: Make the list circular
        curr.next = head;

        // Step 4: Find the new tail of the list
        Node newTail = head;
        for (int i = 1; i < (size - k); i++) {
            newTail = newTail.next;
        }

        // Step 5: Break the circular connection and set the new head
        Node newHead = newTail.next;
        newTail.next = null;

        return newHead; // Return the new head of the rotated list
    }
}