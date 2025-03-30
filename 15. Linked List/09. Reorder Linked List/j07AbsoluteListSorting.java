/**
 * Problem Statement:
 * 
 *     Given a singly linked list where nodes may have negative or positive values, sort the list
 *     such that all negative values appear before positive values while maintaining their original
 *     relative order.
 * 
 * Input:
 *     - A singly linked list where each node contains an integer value.
 * 
 * Output:
 *     - The head of the sorted linked list with all negative values appearing before positive values.
 * 
 * Example:
 *     Input:
 *         head = [1, -2, -3, 4, -5]
 *     Output:
 *         [-5, -3, -2, 1, 4]
 * 
 *     Explanation:
 *         All negative values are moved to the front of the list while maintaining their original order.
 */

public class j07AbsoluteListSorting {

    /*-
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

    /*-
     * Approach: Absolute Sorting of the List
     * 
     * Intuition:
     * - Traverse the list and move all nodes with negative values to the front of the list.
     * - Maintain the relative order of the nodes by inserting negative nodes at the head of the list.
     * 
     * Explanation:
     * 1. Use a pointer (`curr`) to traverse the list.
     * 2. If the `next` node has a negative value:
     *     - Remove the `next` node from its current position.
     *     - Insert it at the head of the list.
     * 3. If the `next` node has a positive value, move the pointer to the next node.
     * 4. Continue until the end of the list is reached.
     * 5. Return the updated head of the list.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the sorted linked list with all negative values moved to the front.
     */
    public Node sortList(Node head) {
        Node curr = head;

        // Traverse the list and move negative nodes to the front
        while (curr != null && curr.next != null) {
            if (curr.next.data < 0) { // If the next node has a negative value
                Node negative = curr.next; // Extract the negative node
                curr.next = negative.next; // Remove the negative node from its current position
                negative.next = head; // Insert the negative node at the head
                head = negative; // Update the head of the list
            } else {
                curr = curr.next; // Move to the next node
            }
        }

        return head; // Return the updated head of the list
    }
}