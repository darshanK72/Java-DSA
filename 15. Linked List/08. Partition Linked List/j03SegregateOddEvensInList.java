/**
 * Problem Statement:
 * 
 *     Given a singly linked list, segregate its nodes such that all even-valued nodes appear
 *     before all odd-valued nodes. The relative order of nodes within the even and odd groups
 *     should remain the same as in the input.
 * 
 * Input:
 *     - A singly linked list where each node contains an integer value.
 * 
 * Output:
 *     - The head of the modified linked list with even-valued nodes followed by odd-valued nodes.
 * 
 * Example:
 *     Input:
 *         head = [1, 2, 3, 4, 5]
 *     Output:
 *         [2, 4, 1, 3, 5]
 * 
 *     Explanation:
 *         The even-valued nodes (2, 4) are grouped together, followed by the odd-valued nodes (1, 3, 5).
 */

public class j03SegregateOddEvensInList {

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
     * Approach: Two Pointers for Segregation
     * 
     * Intuition:
     * - Use two separate dummy nodes to create two separate lists for even-valued and odd-valued nodes.
     * - Traverse the input list and append each node to the corresponding list based on its value.
     * - Finally, connect the two lists together to form the segregated list.
     * 
     * Explanation:
     * 1. Create two dummy nodes (`even` and `odd`) to represent the heads of the even and odd lists.
     * 2. Use two pointers (`temp1` and `temp2`) to build the even and odd lists.
     * 3. Traverse the input list:
     *     - If the current node's value is even, append it to the list starting with `even`.
     *     - If the current node's value is odd, append it to the list starting with `odd`.
     * 4. Connect the even list to the odd list.
     * 5. Ensure the last node of the odd list points to `null`.
     * 6. Return the head of the even list as the new head of the segregated list.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the modified linked list with even-valued nodes followed by odd-valued nodes.
     */
    Node divide(Node head) {
        Node even = new Node(-1); // Dummy node for the list of even-valued nodes
        Node odd = new Node(-1);  // Dummy node for the list of odd-valued nodes
        Node temp1 = even; // Pointer for the even list
        Node temp2 = odd;  // Pointer for the odd list
        Node curr = head;  // Pointer to traverse the input list

        // Traverse the input list and segregate nodes into even and odd lists
        while (curr != null) {
            if ((curr.data % 2) == 0) { // If the value is even
                temp1.next = curr; // Append to the even list
                temp1 = temp1.next;
            } else { // If the value is odd
                temp2.next = curr; // Append to the odd list
                temp2 = temp2.next;
            }
            curr = curr.next; // Move to the next node
        }

        temp1.next = odd.next; // Connect the even list to the odd list
        even = even.next; // Update the head of the even list
        temp2.next = null; // Ensure the last node of the odd list points to null

        return even; // Return the head of the segregated list
    }
}