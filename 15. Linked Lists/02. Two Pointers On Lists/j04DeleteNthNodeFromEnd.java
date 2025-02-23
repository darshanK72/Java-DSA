/**
 * Problem Statement:
 * 
 *     Given a singly linked list, remove the N-th node from the end of the list and return the head.
 *     If the list contains only one node, return null.
 * 
 * Input:
 *     - A singly linked list where each node contains:
 *         - `data` (an integer)
 *         - `next` (pointer to the next node)
 *     - An integer `n` (1 ≤ n ≤ size of the list), representing the position from the end to be removed.
 * 
 * Output:
 *     - The head of the modified linked list after removing the N-th node from the end.
 * 
 * Example 1:
 *     Input:
 *         Linked List: 1 -> 2 -> 3 -> 4 -> 5
 *         n = 2
 *     Output:
 *         1 -> 2 -> 3 -> 5
 * 
 *     Explanation:
 *     - The 2nd node from the end is `4`, so it is removed.
 * 
 * Example 2:
 *     Input:
 *         Linked List: 1 -> 2
 *         n = 2
 *     Output:
 *         2
 * 
 *     Explanation:
 *     - The 2nd node from the end is `1`, so it is removed.
 * 
 * Example 3:
 *     Input:
 *         Linked List: 1
 *         n = 1
 *     Output:
 *         null
 * 
 *     Explanation:
 *     - The only node is removed, resulting in an empty list.
 */

public class j04DeleteNthNodeFromEnd {

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Approach 1: Two-Pass Traversal
     * 
     * Intuition:
     * - First, determine the total size of the linked list.
     * - Compute the position from the start: `(size - n)`.
     * - Traverse again to remove the node at that position.
     * 
     * Why This Works:
     * - We use the first traversal to determine the exact node position from the start.
     * - The second traversal removes the node efficiently.
     * 
     * Explanation:
     * 1. Traverse the list to compute `size`.
     * 2. If `size == 1`, return `null` (single-node case).
     * 3. If `n == size`, remove the head node and return the new head.
     * 4. Traverse again to reach the `(size - n - 1)`-th node.
     * 5. Modify its `next` pointer to skip the target node.
     * 6. Return the modified head.
     * 
     * Time Complexity:
     * - O(n), since we traverse the list twice.
     * 
     * Space Complexity:
     * - O(1), as we use only extra variables.
     */
    public static Node removeNthFromEndTwoPass(Node head, int n) {
        int size = 0;
        Node temp = head;

        // First pass: Count the size of the linked list
        while (temp != null) {
            size++;
            temp = temp.next;
        }

        // If only one node is present, return null
        if (size == 1) {
            return null;
        }

        // If removing the first node, update head
        if (n == size) {
            head = head.next;
            return head;
        }

        // Second pass: Move to the (size - n - 1)th node
        temp = head;
        for (int i = 0; i < size - n - 1; i++) {
            temp = temp.next;
        }

        // Remove the N-th node from the end
        temp.next = temp.next.next;
        return head;
    }

    /**
     * Approach 2: Fast and Slow Pointer (Efficient)
     * 
     * Intuition:
     * - Use two pointers:
     *   - `fast` moves `n` steps ahead.
     *   - `slow` starts from the head.
     *   - When `fast` reaches the last node, `slow` will be just before the node to remove.
     * 
     * Why This Works:
     * - The `fast` pointer reaches the end when `slow` is exactly at `(size - n - 1)th` node.
     * - We use `slow` to modify `next` and skip the target node efficiently.
     * 
     * Explanation:
     * 1. Move `fast` pointer `n` steps ahead.
     * 2. If `fast == null`, remove the head node.
     * 3. Move `fast` and `slow` simultaneously until `fast.next == null`.
     * 4. Modify `slow.next` to skip the target node.
     * 5. Return the modified head.
     * 
     * Time Complexity:
     * - O(n), since we traverse the list once.
     * 
     * Space Complexity:
     * - O(1), as we use only two pointers.
     */
    public static Node removeNthFromEndSlowFast(Node head, int n) {
        if (head == null || head.next == null) {
            return null; // If only one node exists, return null.
        }

        Node slow = head;
        Node fast = head;

        // Move `fast` pointer `n` steps ahead
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // If `fast == null`, it means `n == size`, so remove the head node
        if (fast == null) {
            return head.next;
        }

        // Move `fast` and `slow` simultaneously until `fast.next == null`
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove the N-th node from the end
        slow.next = slow.next.next;
        return head;
    }
}
