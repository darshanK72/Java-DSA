/**
 * Problem Statement:
 * 
 *     Given a singly linked list where each node contains an integer value (0, 1, or 2),
 *     segregate the nodes such that all nodes with value 0 come first, followed by nodes with
 *     value 1, and then nodes with value 2. The relative order of nodes within each group
 *     should remain the same as in the input.
 * 
 * Input:
 *     - A singly linked list where each node contains an integer value (0, 1, or 2).
 * 
 * Output:
 *     - The head of the modified linked list with nodes segregated by their values.
 * 
 * Example:
 *     Input:
 *         head = [1, 2, 0, 1, 2, 0, 1]
 *     Output:
 *         [0, 0, 1, 1, 1, 2, 2]
 * 
 *     Explanation:
 *         The nodes are rearranged such that all 0s come first, followed by all 1s, and then all 2s.
 */

public class j04SegregateZeroOneTwoInList {

    /**
     * Node Class:
     * 
     * Represents a node in a singly linked list. Each node contains:
     * - `data`: The integer value stored in the node (0, 1, or 2).
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
     * Approach: Three Pointers for Segregation
     * 
     * Intuition:
     * - Use three separate dummy nodes to create three separate lists for nodes with values 0, 1, and 2.
     * - Traverse the input list and append each node to the corresponding list based on its value.
     * - Finally, connect the three lists together to form the segregated list.
     * 
     * Explanation:
     * 1. Create three dummy nodes (`zero`, `one`, `two`) to represent the heads of the three lists.
     * 2. Use three pointers (`temp0`, `temp1`, `temp2`) to build the three lists.
     * 3. Traverse the input list:
     *     - If the current node's value is 0, append it to the list starting with `zero`.
     *     - If the current node's value is 1, append it to the list starting with `one`.
     *     - If the current node's value is 2, append it to the list starting with `two`.
     * 4. Connect the three lists:
     *     - Connect the `zero` list to the `one` list (if it exists) or directly to the `two` list.
     *     - Connect the `one` list to the `two` list.
     * 5. Ensure the last node of the `two` list points to `null`.
     * 6. Return the head of the `zero` list as the new head of the segregated list.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the modified linked list with nodes segregated by their values.
     */
    public Node segregateZeroOneTwo(Node head) {
        Node zero = new Node(-1); // Dummy node for the list of 0s
        Node one = new Node(-1);  // Dummy node for the list of 1s
        Node two = new Node(-1);  // Dummy node for the list of 2s

        Node temp0 = zero; // Pointer for the list of 0s
        Node temp1 = one;  // Pointer for the list of 1s
        Node temp2 = two;  // Pointer for the list of 2s
        Node curr = head;  // Pointer to traverse the input list

        // Traverse the input list and segregate nodes into three lists
        while (curr != null) {
            if (curr.data == 0) {
                temp0.next = curr; // Append to the list of 0s
                temp0 = temp0.next;
            } else if (curr.data == 1) {
                temp1.next = curr; // Append to the list of 1s
                temp1 = temp1.next;
            } else {
                temp2.next = curr; // Append to the list of 2s
                temp2 = temp2.next;
            }
            curr = curr.next; // Move to the next node
        }

        // Connect the three lists
        temp0.next = (one.next != null) ? one.next : two.next; // Connect 0s to 1s or 2s
        temp1.next = two.next; // Connect 1s to 2s
        temp2.next = null; // Ensure the last node points to null

        return zero.next; // Return the head of the segregated list
    }
}