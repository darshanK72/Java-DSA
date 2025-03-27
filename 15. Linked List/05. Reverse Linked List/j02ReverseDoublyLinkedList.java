/**
 * Problem Statement:
 * 
 *     Given a Doubly Linked List (DLL), reverse the list so that the first node 
 *     becomes the last, the last node becomes the first, and so on. Ensure that 
 *     the `prev` and `next` pointers are correctly updated.
 * 
 * Input:
 *     - A doubly linked list where each node contains:
 *         - `data` (an integer)
 *         - `next` (pointer to the next node)
 *         - `prev` (pointer to the previous node)
 * 
 * Output:
 *     - The head of the reversed doubly linked list.
 * 
 * Example:
 *     Input:
 *         1 <-> 2 <-> 3 <-> 4 <-> 5
 *     Output:
 *         5 <-> 4 <-> 3 <-> 2 <-> 1
 * 
 *     Explanation:
 *     - The `prev` and `next` pointers of each node are swapped to reverse the list.
 */

public class j02ReverseDoublyLinkedList {

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

    public static void main(String args[]) {
        Node head = new Node(1);
        Node temp = head;
        for (int i = 1; i < 5; i++) {
            Node node = new Node(i + 1);
            node.prev = temp;
            temp.next = node;
            temp = temp.next;
        }

        printList(head);
        head = reverseDLLEfficient(head);
        printList(head);
    }

    /**
     * Approach 1: Reversing by Swapping Data
     * 
     * Intuition:
     * - Instead of modifying the `prev` and `next` pointers, we swap `data` values 
     *   between nodes from the start and end moving towards the center.
     * 
     * Why This Works:
     * - Since the DLL allows bidirectional traversal, we can use two pointers:
     *   - One starting from the head.
     *   - One starting from the tail.
     * - By swapping data instead of modifying pointers, the structure remains intact.
     * 
     * Explanation:
     * 1. Find the tail node (last node).
     * 2. Use two pointers: 
     *    - `temp` starts from head.
     *    - `tail` starts from last node.
     * 3. Swap `temp.data` and `tail.data` so that their values are exchanged.
     * 4. Move `temp` forward (`temp = temp.next`) and `tail` backward (`tail = tail.prev`).
     * 5. Stop when the two pointers meet or cross.
     * 
     * Time Complexity:
     * - O(n), as we traverse the list once.
     * 
     * Space Complexity:
     * - O(1), as we only use extra variables for swapping.
     */
    public static Node reverseDLLBySwappingData(Node head) {
        if (head == null)
            return null;

        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }

        Node temp = head;
        while (temp != tail && temp.prev != tail) {
            int tempData = temp.data;
            temp.data = tail.data;
            tail.data = tempData;

            temp = temp.next;
            tail = tail.prev;
        }
        return head;
    }

    /**
     * Approach 2: Reversing by Swapping Pointers (Efficient)
     * 
     * Intuition:
     * - Instead of swapping data, we reverse the list by swapping `prev` and `next` 
     *   pointers of each node.
     * 
     * Why This Works:
     * - The structure of a DLL allows us to modify the `prev` and `next` pointers 
     *   without needing extra space.
     * - Since every node has two pointers (`prev` and `next`), swapping them will
     *   effectively reverse the list.
     * 
     * Explanation:
     * 1. Initialize:
     *    - `temp = null` (to keep track of the previous node).
     *    - `current = head` (start from the first node).
     * 2. Iterate through the list:
     *    - Swap `prev` and `next` pointers of `current` node.
     *    - Move to the next node (which is now stored in `prev`).
     * 3. When the loop ends:
     *    - The last processed node (`temp`) is the new head.
     *    - Return `temp.prev` as the new head.
     * 
     * Time Complexity:
     * - O(n), as we traverse the list once.
     * 
     * Space Complexity:
     * - O(1), as we modify pointers in-place.
     */
    public static Node reverseDLLEfficient(Node head) {
        if (head == null || head.next == null)
            return head;

        Node temp = null;
        Node current = head;

        while (current != null) {
            // Swap `prev` and `next` pointers
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;

            // Move to the next node (which was previous `prev`)
            current = current.prev;
        }

        // The new head is the last processed node, which is stored in `temp`
        return temp.prev;
    }

    /**
     * Approach 3: Recursive Reversal
     * 
     * Intuition:
     * - Instead of using iteration, we recursively process each node by reversing 
     *   its pointers.
     * 
     * Why This Works:
     * - We process one node at a time and swap its `prev` and `next` pointers.
     * - The recursion ensures that we reach the last node first and set it as the new head.
     * 
     * Explanation:
     * 1. Base Case:
     *    - If `head` is null, return null.
     * 2. Swap `prev` and `next` pointers of the current node.
     * 3. Recursively process the next node (which is now stored in `prev`).
     * 4. When `prev` is null, return the current node as the new head.
     * 
     * Time Complexity:
     * - O(n), as each node is processed once.
     * 
     * Space Complexity:
     * - O(n), due to the recursion stack.
     */
    public static Node reverseDLLRecursive(Node head) {
        if (head == null)
            return null;

        // Swap pointers
        Node temp = head.next;
        head.next = head.prev;
        head.prev = temp;

        // Base condition: if we reached the new head
        if (head.prev == null) {
            return head;
        }

        // Recursive call
        return reverseDLLRecursive(head.prev);
    }

    /**
     * Utility Function: Print Doubly Linked List
     * 
     * - This function prints the elements of a doubly linked list.
     * - Traverses the list and prints each node’s data.
     */
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}
