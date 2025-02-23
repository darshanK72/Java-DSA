/**
 * Problem Statement:
 * 
 *     Given a singly linked list, delete the middle node of the list.
 *     If the list contains an even number of nodes, delete the second middle node.
 *     If the list has only one node, return null.
 * 
 * Input:
 *     - A singly linked list where each node contains:
 *         - `data` (an integer)
 *         - `next` (pointer to the next node)
 * 
 * Output:
 *     - The head of the modified linked list after deleting the middle node.
 * 
 * Example 1:
 *     Input:
 *         Linked List: 1 -> 2 -> 3 -> 4 -> 5
 *     Output:
 *         1 -> 2 -> 4 -> 5
 * 
 *     Explanation:
 *     - The middle node is `3`, so it is removed.
 * 
 * Example 2:
 *     Input:
 *         Linked List: 1 -> 2 -> 3 -> 4
 *     Output:
 *         1 -> 2 -> 4
 * 
 *     Explanation:
 *     - The middle nodes are `2` and `3`, but we remove `3` as per the problem statement.
 * 
 * Example 3:
 *     Input:
 *         Linked List: 1
 *     Output:
 *         null
 * 
 *     Explanation:
 *     - The only node is removed, resulting in an empty list.
 */

public class j03DeleteMiddleElement {
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
     * - Then, compute the index of the middle node.
     * - Traverse again to delete the middle node.
     * 
     * Why This Works:
     * - If we know the size `n`, the middle node is at index `(n / 2)`.
     * - We traverse once to find `n`, then traverse again to remove the middle node.
     * 
     * Explanation:
     * 1. Traverse the list to compute `size`.
     * 2. If the list has only one node, return `null`.
     * 3. Traverse the list again and stop at `(size / 2 - 1)th` node.
     * 4. Modify the next pointer to skip the middle node.
     * 5. Return the modified head.
     * 
     * Time Complexity:
     * - O(n), since we traverse the list twice.
     * 
     * Space Complexity:
     * - O(1), as we use only extra variables.
     */
    public static Node deleteMiddleTwoPass(Node head) {
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

        // Second pass: Move to the (size / 2 - 1)th node
        temp = head;
        for (int i = 0; i < size / 2 - 1; i++) {
            temp = temp.next;
        }

        // Delete the middle node
        temp.next = temp.next.next;
        return head;
    }

    /**
     * Approach 2: Fast and Slow Pointer (Efficient)
     * 
     * Intuition:
     * - Use two pointers:
     *   - `fast` moves two steps at a time.
     *   - `slow` moves one step at a time.
     *   - When `fast` reaches the end, `slow` will be at the middle node.
     * - Keep track of the previous node (`prev`) to remove the middle node efficiently.
     * 
     * Why This Works:
     * - The `fast` pointer reaches the end when `slow` is exactly at the middle.
     * - We use `prev` to modify the next pointer and delete `slow`.
     * 
     * Explanation:
     * 1. Use `fast` and `slow` pointers to find the middle node.
     * 2. Keep track of `prev`, which points to the node before `slow`.
     * 3. If the list has only one node, return `null`.
     * 4. Modify `prev.next` to skip `slow` (middle node).
     * 5. Return the modified head.
     * 
     * Time Complexity:
     * - O(n), since we traverse the list once.
     * 
     * Space Complexity:
     * - O(1), as we use only two pointers.
     */
    public static Node deleteMiddleSlowFast(Node head) {
        if (head == null || head.next == null) {
            return null; // If only one node exists, return null.
        }

        Node slow = head;
        Node fast = head;
        Node prev = null;

        // Move `fast` two steps and `slow` one step
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Delete the middle node by skipping it
        prev.next = slow.next;
        return head;
    }

    /**
     * Approach 3: Optimized Fast and Slow Pointer
     * 
     * Intuition:
     * - Similar to the previous approach but with slight optimizations.
     * - Handles edge cases efficiently.
     * 
     * Why This Works:
     * - We adjust `fast` pointer initially to avoid unnecessary conditions.
     * - Ensures correct handling of edge cases.
     * 
     * Explanation:
     * 1. If only one node exists, return null.
     * 2. Initialize `slow` and `fast` pointers.
     * 3. Move `fast` two steps and `slow` one step until `fast` reaches the end.
     * 4. Delete the middle node by skipping it.
     * 5. Return the modified head.
     * 
     * Time Complexity:
     * - O(n), since we traverse the list once.
     * 
     * Space Complexity:
     * - O(1), as we use only extra pointers.
     */
    public static Node deleteMiddleEfficient(Node head) {
        if (head == null || head.next == null) {
            return null; // If only one node exists, return null.
        }

        Node slow = head;
        Node fast = head.next.next;

        // Move `fast` two steps and `slow` one step
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Delete the middle node by skipping it
        slow.next = slow.next.next;
        return head;
    }
}
