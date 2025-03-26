/**
 * Problem Statement:
 * 
 *      Given a singly linked list and an integer `k`, swap the values of the 
 *      k-th node from the beginning and the k-th node from the end.
 * 
 *      You are required to swap only the node values, not the actual nodes.
 * 
 * Constraints:
 *      - The list is 1-indexed.
 *      - The length of the linked list is at least `k`.
 *      - The linked list consists of distinct node values.
 * 
 * Input:
 *      - A singly linked list.
 *      - An integer `k` representing the k-th node position.
 * 
 * Output:
 *      - The modified linked list after swapping values of k-th nodes.
 * 
 * Example:
 * 
 *     Input:
 *     Linked List: 1 -> 2 -> 3 -> 4 -> 5
 *     k = 2
 * 
 *     Output:
 *     Linked List: 1 -> 4 -> 3 -> 2 -> 5
 * 
 *     Explanation:
 *     - The 2nd node from the start is `2`.
 *     - The 2nd node from the end is `4`.
 *     - We swap their values to get: `1 -> 4 -> 3 -> 2 -> 5`.
 */

public class j05SwapNodesInLinkedList {

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Approach: Two Pointers
     * 
     * Intuition:
     * - We need to swap the values of the k-th node from the beginning and the k-th node from the end.
     * - We first locate the k-th node from the start.
     * - We then locate the k-th node from the end using the **two-pointer technique**:
     *   - Move a fast pointer `k` steps ahead.
     *   - Then, move a slow pointer from the head while advancing the fast pointer.
     *   - When `fast` reaches the end, `slow` will point to the k-th node from the end.
     * - Finally, swap the values of the two nodes.
     * 
     * Explanation:
     * 1. Initialize two pointers, `first` and `fast`, at the head.
     * 2. Move `first` and `fast` `k-1` steps forward to find the k-th node from the beginning.
     * 3. Continue moving `fast` to the end while introducing a `slow` pointer from the head.
     * 4. When `fast` reaches the end, `slow` will be at the k-th node from the end.
     * 5. Swap the values of the `first` and `slow` nodes.
     * 6. Return the modified list.
     * 
     * Time Complexity:
     * - O(N) → We traverse the list twice (once to find the k-th node, then to find the k-th node from the end).
     * 
     * Space Complexity:
     * - O(1) → We use only a few pointers, so no extra space is needed.
     * 
     * @param head The head of the linked list.
     * @param k The position of the node to be swapped.
     * @return The head of the modified linked list.
     */
    public Node swapNodes(Node head, int k) {
        Node first = head;
        Node fast = head;

        // Move first pointer to the k-th node from start
        for (int i = 0; i < k - 1; i++) {
            first = first.next;
            fast = fast.next;
        }

        // Move fast pointer to the end while introducing slow pointer
        Node slow = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Swap values of the two nodes
        int temp = first.data;
        first.data = slow.data;
        slow.data = temp;

        return head;
    }
}
