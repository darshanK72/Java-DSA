/**
 * Problem Statement:
 * 
 *     Given a singly linked list, find the k-th node from the end of the list.
 *     If k is larger than the size of the list or invalid, return -1.
 * 
 * Input:
 *     - A singly linked list where each node contains:
 *         - `data` (an integer)
 *         - `next` (pointer to the next node)
 *     - An integer `k`, representing the position from the end (1-based index).
 * 
 * Output:
 *     - The data of the k-th node from the end of the linked list.
 * 
 * Example 1:
 *     Input:
 *         Linked List: 1 -> 2 -> 3 -> 4 -> 5
 *         k = 2
 *     Output:
 *         4
 * 
 *     Explanation:
 *     - The second node from the end is `4`.
 * 
 * Example 2:
 *     Input:
 *         Linked List: 1 -> 2 -> 3 -> 4 -> 5
 *         k = 6
 *     Output:
 *         -1
 * 
 *     Explanation:
 *     - The list has only 5 nodes, so `k = 6` is invalid.
 */

public class j02GetKthNodeFromEnd {
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
     * - Then, compute the index of the k-th node from the start.
     * - Traverse again to get the k-th node from the end.
     * 
     * Why This Works:
     * - If we know the size `n`, then the k-th node from the end is at index `(n - k)` from the beginning.
     * 
     * Explanation:
     * 1. Traverse the list once to calculate the total number of nodes (`size`).
     * 2. If `k` is invalid (k > size or k <= 0), return `-1`.
     * 3. Traverse the list again and stop at the `(size - k)`th node.
     * 4. Return the value of that node.
     * 
     * Time Complexity:
     * - O(n), as we traverse the list twice.
     * 
     * Space Complexity:
     * - O(1), as we use only a few extra variables.
     */
    public static int getKthFromLastTwoPass(Node head, int k) {
        int size = 0;
        Node temp = head;

        // First pass: Count the size of the linked list
        while (temp != null) {
            size++;
            temp = temp.next;
        }

        // If k is out of bounds, return -1
        if (k <= 0 || k > size)
            return -1;

        // Second pass: Move to the (size - k)th node
        temp = head;
        for (int i = 0; i < size - k; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    /**
     * Approach 2: Fast and Slow Pointer (Efficient)
     * 
     * Intuition:
     * - Use two pointers:
     *   - `fast` moves `k` steps ahead first.
     *   - Then, `slow` and `fast` move together one step at a time.
     *   - When `fast` reaches the end, `slow` will be at the k-th node from the end.
     * 
     * Why This Works:
     * - By moving `fast` `k` steps ahead, there is exactly a gap of `k` between `slow` and `fast`.
     * - When `fast` reaches the end (null), `slow` is at the desired position.
     * 
     * Explanation:
     * 1. Move `fast` pointer `k` steps forward.
     * 2. If `fast` reaches null before `k` steps, return `-1` (invalid `k`).
     * 3. Move `slow` and `fast` together until `fast` reaches the end.
     * 4. Return the value of `slow`, which is now at the k-th node from the end.
     * 
     * Time Complexity:
     * - O(n), as we traverse the list once.
     * 
     * Space Complexity:
     * - O(1), as we use only two pointers.
     */
    public static int getKthFromLastSlowFast(Node head, int k) {
        Node slow = head;
        Node fast = head;

        // Move fast pointer k steps ahead
        for (int i = 0; i < k; i++) {
            if (fast == null)
                return -1; // k is out of bounds
            fast = fast.next;
        }

        // Move slow and fast together until fast reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.data;
    }
}
