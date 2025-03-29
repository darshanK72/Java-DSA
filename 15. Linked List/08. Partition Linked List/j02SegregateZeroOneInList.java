/**
 * Problem Statement:
 * 
 *     Given a singly linked list where each node contains an integer value (0 or 1),
 *     segregate the nodes such that all nodes with value 0 come first, followed by nodes with value 1.
 *     The relative order of nodes within each group should remain the same as in the input.
 * 
 * Input:
 *     - A singly linked list where each node contains an integer value (0 or 1).
 * 
 * Output:
 *     - The head of the modified linked list with nodes segregated by their values.
 * 
 * Example:
 *     Input:
 *         head = [1, 0, 1, 0, 1]
 *     Output:
 *         [0, 0, 1, 1, 1]
 * 
 *     Explanation:
 *         The nodes are rearranged such that all 0s come first, followed by all 1s.
 */

public class j02SegregateZeroOneInList {

    /**
     * Node Class:
     * 
     * Represents a node in a singly linked list. Each node contains:
     * - `data`: The integer value stored in the node (0 or 1).
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
     * Approach: Two Pointers for Segregation
     * 
     * Intuition:
     * - Use two separate dummy nodes to create two separate lists for nodes with values 0 and 1.
     * - Traverse the input list and append each node to the corresponding list based on its value.
     * - Finally, connect the two lists together to form the segregated list.
     * 
     * Explanation:
     * 1. Create two dummy nodes (`zero` and `one`) to represent the heads of the 0 and 1 lists.
     * 2. Use two pointers (`temp1` and `temp2`) to build the 0 and 1 lists.
     * 3. Traverse the input list:
     *     - If the current node's value is 0, append it to the list starting with `zero`.
     *     - If the current node's value is 1, append it to the list starting with `one`.
     * 4. Connect the 0 list to the 1 list.
     * 5. Ensure the last node of the 1 list points to `null`.
     * 6. Return the head of the 0 list as the new head of the segregated list.
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
    public Node sortBinaryList(Node head) {
        Node zero = new Node(-1); // Dummy node for the list of 0s
        Node one = new Node(-1);  // Dummy node for the list of 1s
        Node temp1 = zero; // Pointer for the 0 list
        Node temp2 = one;  // Pointer for the 1 list
        Node curr = head;  // Pointer to traverse the input list

        // Traverse the input list and segregate nodes into 0 and 1 lists
        while (curr != null) {
            if (curr.data == 0) { // If the value is 0
                temp1.next = curr; // Append to the 0 list
                temp1 = temp1.next;
            } else { // If the value is 1
                temp2.next = curr; // Append to the 1 list
                temp2 = temp2.next;
            }
            curr = curr.next; // Move to the next node
        }

        temp1.next = one.next; // Connect the 0 list to the 1 list
        zero = zero.next; // Update the head of the 0 list
        temp2.next = null; // Ensure the last node of the 1 list points to null

        return zero; // Return the head of the segregated list
    }
}