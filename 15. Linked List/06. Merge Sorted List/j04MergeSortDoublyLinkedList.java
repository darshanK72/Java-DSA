/**
 * Problem Statement:
 * 
 *     Given a doubly linked list, sort it using the merge sort algorithm.
 * 
 * Input:
 *     - A doubly linked list where each node contains an integer value.
 * 
 * Output:
 *     - The head of the sorted doubly linked list.
 * 
 * Example:
 *     Input:
 *         head = [4, 2, 1, 3]
 *     Output:
 *         [1, 2, 3, 4]
 * 
 *     Explanation:
 *         The input list is divided into smaller sublists, sorted individually, and then merged
 *         to produce the final sorted list.
 */

public class j04MergeSortDoublyLinkedList {

    /**
     * Node Class:
     * 
     * Represents a node in a doubly linked list. Each node contains:
     * - `data`: The integer value stored in the node.
     * - `next`: A reference to the next node in the list.
     * - `prev`: A reference to the previous node in the list.
     */
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

    /**
     * Approach: Merge Sort
     * 
     * Intuition:
     * - Merge sort is a divide-and-conquer algorithm that splits the list into smaller sublists,
     *   sorts them individually, and then merges them back together in sorted order.
     * - The middle node is used to divide the list into two halves.
     * 
     * Explanation:
     * 1. If the list is empty or contains only one node, it is already sorted. Return the head.
     * 2. Find the middle node of the list using the `getMiddleNode` method.
     * 3. Recursively apply merge sort to the left and right halves of the list.
     * 4. Merge the two sorted halves using the `mergeTwoLists` method.
     * 5. Return the head of the merged sorted list.
     * 
     * Time Complexity:
     * - O(n * log(n)), where `n` is the number of nodes in the list. The list is divided log(n) times,
     *   and merging takes O(n) time for each level.
     * 
     * Space Complexity:
     * - O(log(n)), due to the recursive stack used in the merge sort algorithm.
     * 
     * @param head The head of the doubly linked list.
     * @return The head of the sorted doubly linked list.
     */
    static Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head; // Base case: list is empty or has only one node
        }

        Node mid = getMiddleNode(head); // Find the middle node
        Node n1 = mergeSort(head); // Recursively sort the left half
        Node n2 = mergeSort(mid); // Recursively sort the right half

        return mergeTwoLists(n1, n2); // Merge the two sorted halves
    }

    /**
     * Helper Method: Get Middle Node
     * 
     * Intuition:
     * - Use the slow and fast pointer technique to find the middle node of the list.
     * - The slow pointer moves one step at a time, while the fast pointer moves two steps.
     * - When the fast pointer reaches the end, the slow pointer will be at the middle.
     * 
     * Explanation:
     * 1. Initialize two pointers, `slow` and `fast`, to the head of the list.
     * 2. Traverse the list while `fast` and `fast.next` are not null:
     *    - Move `slow` one step forward.
     *    - Move `fast` two steps forward.
     * 3. Disconnect the left half from the right half by setting `prev.next` to null.
     * 4. Return the `slow` pointer, which points to the middle node.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the list.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param head The head of the doubly linked list.
     * @return The middle node of the list.
     */
    public static Node getMiddleNode(Node head) {
        if (head == null || head.next == null) {
            return head; // Base case: list is empty or has only one node
        }

        Node prev = null; // To disconnect the left half from the right half
        Node slow = head; // Slow pointer
        Node fast = head; // Fast pointer

        while (fast != null && fast.next != null) {
            prev = slow; // Keep track of the node before `slow`
            slow = slow.next; // Move `slow` one step forward
            fast = fast.next.next; // Move `fast` two steps forward
        }

        prev.next = null; // Disconnect the left half from the right half
        return slow; // Return the middle node
    }

    /**
     * Helper Method: Merge Two Sorted Doubly Linked Lists
     * 
     * Intuition:
     * - Use two pointers to traverse the two lists and compare their values.
     * - Append the smaller value to the merged list and move the corresponding pointer forward.
     * - Update the `prev` pointers to maintain the doubly linked list structure.
     * 
     * Explanation:
     * 1. Initialize two pointers, `temp1` and `temp2`, to the heads of the two lists.
     * 2. Create a dummy node to serve as the starting point of the merged list.
     * 3. Traverse both lists while neither pointer is null:
     *    - Compare the values of the nodes pointed to by `temp1` and `temp2`.
     *    - Append the smaller value to the merged list and move the corresponding pointer forward.
     *    - Update the `prev` pointer of the appended node to point to the previous node in the merged list.
     * 4. If one of the lists is exhausted, append the remaining nodes of the other list to the merged list.
     * 5. Return the merged list starting from `dummy.next`.
     * 
     * Time Complexity:
     * - O(n + m), where `n` and `m` are the lengths of the two lists.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param list1 The head of the first sorted doubly linked list.
     * @param list2 The head of the second sorted doubly linked list.
     * @return The head of the merged sorted doubly linked list.
     */
    public static Node mergeTwoLists(Node list1, Node list2) {
        Node temp1 = list1;
        Node temp2 = list2;
        Node dummy = new Node(-1);
        Node out = dummy;

        while (temp1 != null && temp2 != null) {
            if (temp1.data < temp2.data) {
                temp1.prev = out;
                out.next = temp1;
                temp1 = temp1.next;
            } else {
                temp2.prev = out;
                out.next = temp2;
                temp2 = temp2.next;
            }
            out = out.next;
        }

        if (temp1 != null) {
            temp1.prev = out;
            out.next = temp1;
        }

        if (temp2 != null) {
            temp2.prev = out;
            out.next = temp2;
        }

        dummy.next.prev = null; // Ensure the head's `prev` pointer is null
        return dummy.next;
    }
}