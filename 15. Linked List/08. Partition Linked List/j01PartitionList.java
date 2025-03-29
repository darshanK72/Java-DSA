/**
 * Problem Statement:
 * 
 *     Given a singly linked list and an integer `x`, partition the list such that all nodes with
 *     values less than `x` come before nodes with values greater than or equal to `x`.
 *     The relative order of nodes in each partition should remain the same as in the input.
 * 
 * Input:
 *     - A singly linked list where each node contains an integer value.
 *     - An integer `x` representing the partition value.
 * 
 * Output:
 *     - The head of the modified linked list with nodes partitioned around `x`.
 * 
 * Example:
 *     Input:
 *         head = [1, 4, 3, 2, 5, 2], x = 3
 *     Output:
 *         [1, 2, 2, 4, 3, 5]
 * 
 *     Explanation:
 *         Nodes with values less than 3 (1, 2, 2) come before nodes with values greater than or equal to 3 (4, 3, 5).
 */

public class j01PartitionList {

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
     * Approach: Two Pointers for Partitioning
     * 
     * Intuition:
     * - Use two separate dummy nodes to create two separate lists:
     *     - One for nodes with values less than `x`.
     *     - One for nodes with values greater than or equal to `x`.
     * - Traverse the input list and append each node to the corresponding list based on its value.
     * - Finally, connect the two lists together to form the partitioned list.
     * 
     * Explanation:
     * 1. Create two dummy nodes (`one` and `two`) to represent the heads of the two partitions.
     * 2. Use two pointers (`temp1` and `temp2`) to build the two partitions.
     * 3. Traverse the input list:
     *     - If the current node's value is less than `x`, append it to the list starting with `one`.
     *     - Otherwise, append it to the list starting with `two`.
     * 4. Connect the `one` list to the `two` list.
     * 5. Ensure the last node of the `two` list points to `null`.
     * 6. Return the head of the `one` list as the new head of the partitioned list.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @param x The partition value.
     * @return The head of the modified linked list with nodes partitioned around `x`.
     */
    public Node partition(Node head, int x) {
        Node one = new Node(-1); // Dummy node for the list of nodes less than `x`
        Node two = new Node(-1); // Dummy node for the list of nodes greater than or equal to `x`

        Node temp1 = one; // Pointer for the first partition
        Node temp2 = two; // Pointer for the second partition
        Node curr = head; // Pointer to traverse the input list

        // Traverse the input list and partition nodes into two lists
        while (curr != null) {
            if (curr.data < x) { // If the value is less than `x`
                temp1.next = curr; // Append to the first partition
                temp1 = temp1.next;
            } else { // If the value is greater than or equal to `x`
                temp2.next = curr; // Append to the second partition
                temp2 = temp2.next;
            }
            curr = curr.next; // Move to the next node
        }

        temp1.next = two.next; // Connect the first partition to the second partition
        temp2.next = null; // Ensure the last node of the second partition points to null

        return one.next; // Return the head of the partitioned list
    }
}