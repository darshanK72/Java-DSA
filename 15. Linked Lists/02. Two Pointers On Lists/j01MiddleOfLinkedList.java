/*-
 * Problem Statement:
 * 
 *     Given a singly linked list, find the middle node of the list. 
 *     If there are two middle nodes (even length list), return the second middle node.
 * 
 * Input:
 *     - A singly linked list where each node contains:
 *         - `data` (an integer)
 *         - `next` (pointer to the next node)
 * 
 * Output:
 *     - The middle node of the linked list.
 * 
 * Example 1:
 *     Input:
 *         1 -> 2 -> 3 -> 4 -> 5
 *     Output:
 *         3
 * 
 *     Explanation:
 *     - The linked list has 5 nodes. The middle node is at position `5 / 2 = 2` (0-based index), which is `3`.
 * 
 * Example 2:
 *     Input:
 *         1 -> 2 -> 3 -> 4 -> 5 -> 6
 *     Output:
 *         4
 * 
 *     Explanation:
 *     - The linked list has 6 nodes. Since there are two middle nodes (3 and 4), return the second one, i.e., `4`.
 */

public class j01MiddleOfLinkedList {

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String args[]) {
        Node head = new Node(1);
        Node temp = head;
        for (int i = 1; i < 5; i++) {
            Node node = new Node(i + 1);
            temp.next = node;
            temp = temp.next;
        }

        printList(head);
        head = middleNodeTwoPass(head);
        printList(head);
    }


    /*-
     * Approach 1: Two-Pass Traversal
     * 
     * Intuition:
     * - First, count the total number of nodes in the linked list.
     * - Then, traverse the list again to find the middle node using the size.
     * 
     * Why This Works:
     * - The first pass determines the total size of the list.
     * - The second pass stops at index `size / 2`, which is the middle element.
     * 
     * Explanation:
     * 1. Initialize `size = 0` and traverse the list to count total nodes.
     * 2. Reset `temp` to the head and move `size / 2` steps forward.
     * 3. Return the node at this position as the middle node.
     * 
     * Time Complexity:
     * - O(n), as we traverse the list twice.
     * 
     * Space Complexity:
     * - O(1), as we use only a few extra variables.
     */
    public static Node middleNodeTwoPass(Node head) {
        int size = 0;
        Node temp = head;

        // First pass: Count the size of the linked list
        while (temp != null) {
            size++;
            temp = temp.next;
        }

        // Second pass: Move to the middle node
        temp = head;
        for (int i = 0; i < size / 2; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /*-
     * Approach 2: Fast and Slow Pointer (Efficient)
     * 
     * Intuition:
     * - Use two pointers (`slow` and `fast`):
     *   - `slow` moves one step at a time.
     *   - `fast` moves two steps at a time.
     * - When `fast` reaches the end, `slow` is at the middle.
     * 
     * Why This Works:
     * - The `fast` pointer covers twice the distance of `slow`.
     * - By the time `fast` reaches the end, `slow` will be at the middle.
     * - If the list has an even number of nodes, `slow` will point to the second middle node.
     * 
     * Explanation:
     * 1. Initialize `slow` and `fast` pointers at the head.
     * 2. Move `slow` one step and `fast` two steps until `fast` reaches the end.
     * 3. When `fast` is null or its next is null, `slow` is at the middle node.
     * 4. Return `slow` as the middle node.
     * 
     * Time Complexity:
     * - O(n), as we traverse the list once.
     * 
     * Space Complexity:
     * - O(1), as we use only two pointers.
     */
    public static Node middleNodeEfficient(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next; // Move one step
            fast = fast.next.next; // Move two steps
        }
        return slow;
    }

    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
