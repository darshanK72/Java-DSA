/**
 * Problem Statement:
 * 
 *     Given two sorted singly linked lists, merge them into a single sorted linked list.
 * 
 * Input:
 *     - Two sorted singly linked lists, `list1` and `list2`, where each node contains an integer value.
 * 
 * Output:
 *     - The head of the merged sorted linked list.
 * 
 * Example:
 *     Input:
 *         list1 = [1, 2, 4]
 *         list2 = [1, 3, 4]
 *     Output:
 *         [1, 1, 2, 3, 4, 4]
 * 
 *     Explanation:
 *         The two lists are merged into a single sorted list: [1, 1, 2, 3, 4, 4].
 */

public class j01MergeTwoSortedLists {

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
     * Approach: Iterative Merging
     * 
     * Intuition:
     * - Since both input lists are sorted, we can use two pointers to traverse the lists and compare their values.
     * - At each step, append the smaller value to the merged list and move the corresponding pointer forward.
     * - Use a dummy node to simplify the process of building the merged list.
     * 
     * Explanation:
     * 1. Initialize two pointers, `temp1` and `temp2`, to the heads of `list1` and `list2`, respectively.
     * 2. Create a dummy node to serve as the starting point of the merged list.
     * 3. Traverse both lists while neither pointer is null:
     *    - Compare the values of the nodes pointed to by `temp1` and `temp2`.
     *    - Append the smaller value to the merged list and move the corresponding pointer forward.
     * 4. If one of the lists is exhausted, append the remaining nodes of the other list to the merged list.
     * 5. Return the merged list starting from `dummy.next`.
     * 
     * Time Complexity:
     * - O(n + m), where `n` and `m` are the lengths of `list1` and `list2`, respectively.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param list1 The head of the first sorted linked list.
     * @param list2 The head of the second sorted linked list.
     * @return The head of the merged sorted linked list.
     */
    public Node mergeTwoLists(Node list1, Node list2) {
        Node temp1 = list1; // Pointer for the first list
        Node temp2 = list2; // Pointer for the second list
        Node dummy = new Node(-1); // Dummy node to simplify the merging process
        Node out = dummy; // Pointer to build the merged list

        while (temp1 != null && temp2 != null) {
            if (temp1.data < temp2.data) { // If the value in list1 is smaller
                out.next = temp1; // Append the node from list1
                temp1 = temp1.next; // Move the pointer in list1 forward
            } else { // If the value in list2 is smaller or equal
                out.next = temp2; // Append the node from list2
                temp2 = temp2.next; // Move the pointer in list2 forward
            }
            out = out.next; // Move the output pointer forward
        }

        // Append the remaining nodes of list1, if any
        if (temp1 != null) {
            out.next = temp1;
        }

        // Append the remaining nodes of list2, if any
        if (temp2 != null) {
            out.next = temp2;
        }

        return dummy.next; // Return the merged list starting from the first real node
    }
}