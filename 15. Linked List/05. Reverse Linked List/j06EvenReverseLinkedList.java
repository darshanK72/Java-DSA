/**
 * Problem Statement:
 * 
 *     Given the head of a singly linked list, reverse the nodes at even positions and append them 
 *     back to their original positions in the list.
 * 
 * Input:
 *     - A singly linked list represented by its head node.
 * 
 * Output:
 *     - The head of the modified linked list after reversing the even-positioned nodes and 
 *       appending them back to their original positions.
 * 
 * Example:
 *     Input:
 *         head = [1, 2, 3, 4, 5]
 *     Output:
 *         [1, 4, 3, 2, 5]
 * 
 *     Explanation:
 *         The nodes at even positions (2 and 4) are reversed and appended back to their original 
 *         positions in the list.
 */

public class j06EvenReverseLinkedList {

    /**
     * Definition of a Node in the linked list.
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
     * Approach: Reverse Even-Positioned Nodes
     * 
     * Intuition:
     * - This approach separates the nodes at even positions from the list, reverses them, and then 
     *   appends them back to their original positions.
     * - Two pointers (`odd` and `even`) are used to traverse the list and manage the separation and 
     *   reinsertion of nodes.
     * 
     * Explanation:
     * - Step 1: Traverse the list to separate the even-positioned nodes:
     *     1. Use the `odd` pointer to traverse the list.
     *     2. Detach the even-positioned nodes and add them to a separate list (`even`), reversing 
     *        them in the process.
     * - Step 2: Traverse the list again to reinsert the reversed even-positioned nodes:
     *     1. Use the `odd` pointer to traverse the original list.
     *     2. Insert the nodes from the reversed `even` list back into their original positions.
     * - Step 3: Return the modified head of the list.
     * 
     * Time Complexity:
     * - O(n), where n is the number of nodes in the linked list. Each node is visited twice: once 
     *   during separation and once during reinsertion.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the modified linked list.
     */
    public Node solve(Node head) {
        // Step 1: Separate and reverse the even-positioned nodes
        Node odd = head;
        Node even = null;
        while (odd != null && odd.next != null) {
            Node temp = odd.next; // Detach the even node
            odd.next = temp.next; // Skip the even node
            temp.next = even; // Add the even node to the reversed list
            even = temp; // Update the head of the reversed even list
            odd = odd.next; // Move to the next odd node
        }

        // Step 2: Reinsert the reversed even-positioned nodes
        odd = head;
        Node curr = even;
        while (curr != null) {
            Node temp = curr.next; // Store the next node in the reversed even list
            curr.next = odd.next; // Insert the even node after the current odd node
            odd.next = curr; // Update the next pointer of the odd node
            odd = odd.next.next; // Move to the next odd node
            curr = temp; // Move to the next node in the reversed even list
        }

        // Step 3: Return the modified head of the list
        return head;
    }
}