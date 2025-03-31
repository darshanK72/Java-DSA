/**
 * Problem Statement:
 * 
 *     Implement insertion sort for a singly linked list.
 * 
 * Input:
 *     - A singly linked list where each node contains an integer value.
 * 
 * Output:
 *     - The head of the sorted linked list.
 * 
 * Example:
 *     Input:
 *         head = [4, 2, 1, 3]
 *     Output:
 *         [1, 2, 3, 4]
 * 
 *     Explanation:
 *         The input list is sorted using the insertion sort algorithm, which inserts each node
 *         into its correct position in a new sorted list.
 */

public class j04InsertionSortLinkedList {

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
     * Approach: Insertion Sort
     * 
     * Intuition:
     * - Insertion sort works by iteratively taking each node from the input list and inserting it
     *   into its correct position in a new sorted list.
     * - A dummy node is used to simplify edge cases, such as inserting at the head of the sorted list.
     * 
     * Explanation:
     * 1. Create a dummy node (`sorted`) to represent the head of the sorted list.
     * 2. Traverse the input list using a pointer (`curr`):
     *     - For each node, find its correct position in the sorted list by traversing the sorted list.
     *     - Insert the node into the sorted list by updating the `next` pointers.
     * 3. Continue until all nodes from the input list are processed.
     * 4. Return the head of the sorted list (i.e., `sorted.next`).
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the number of nodes in the linked list. For each node, we may need to traverse
     *   the sorted list to find its correct position.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the sorted linked list.
     */
    public Node insertionSortList(Node head) {
        Node sorted = new Node(Integer.MIN_VALUE); // Dummy node for the sorted list
        Node curr = head; // Pointer to traverse the input list

        while (curr != null) {
            Node temp = sorted; // Pointer to traverse the sorted list
            Node next = curr.next; // Store the next node in the input list

            // Find the correct position in the sorted list
            while (temp.next != null && temp.next.data < curr.data) {
                temp = temp.next;
            }

            // Insert the current node into the sorted list
            curr.next = temp.next;
            temp.next = curr;

            // Move to the next node in the input list
            curr = next;
        }

        return sorted.next; // Return the head of the sorted list
    }
}