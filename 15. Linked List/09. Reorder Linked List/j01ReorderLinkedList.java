/**
 * Problem Statement:
 * 
 *     Given a singly linked list, reorder it such that the nodes are rearranged in the following order:
 *     - The first node is followed by the last node, then the second node, then the second-to-last node, and so on.
 * 
 * Input:
 *     - A singly linked list where each node contains an integer value.
 * 
 * Output:
 *     - The head of the reordered linked list.
 * 
 * Example:
 *     Input:
 *         head = [1, 2, 3, 4, 5]
 *     Output:
 *         [1, 5, 2, 4, 3]
 * 
 *     Explanation:
 *         The list is reordered such that the first node is followed by the last node, then the second node,
 *         then the second-to-last node, and so on.
 */

public class j01ReorderLinkedList {

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
     * Approach: Reorder the List
     * 
     * Intuition:
     * - The problem can be solved in three steps:
     *     1. Find the middle node of the list to divide it into two halves.
     *     2. Reverse the second half of the list.
     *     3. Merge the two halves alternately to achieve the desired order.
     * 
     * Explanation:
     * 1. Use the `getMiddleNode` method to find the middle node of the list and split it into two halves.
     * 2. Reverse the second half of the list using the `reverseList` method.
     * 3. Merge the two halves alternately:
     *     - Use two pointers to traverse the first and second halves.
     *     - Append nodes alternately from the first and second halves to the result list.
     * 4. Update the head of the list to point to the reordered list.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the linked list. Each node is visited a constant number of times.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     */
    public void reorderList(Node head) {
        if (head == null || head.next == null) {
            return; // Base case: empty list or single node
        }

        // Step 1: Find the middle node and split the list into two halves
        Node mid = getMiddleNode(head);

        // Step 2: Reverse the second half of the list
        Node right = reverseList(mid);

        // Step 3: Merge the two halves alternately
        Node out = new Node(-1); // Dummy node to simplify merging
        Node temp1 = head; // Pointer for the first half
        Node temp2 = right; // Pointer for the second half

        while (temp1 != null && temp2 != null) {
            out.next = temp1; // Append a node from the first half
            temp1 = temp1.next;
            out = out.next;

            out.next = temp2; // Append a node from the second half
            temp2 = temp2.next;
            out = out.next;
        }

        // Update the head of the list to point to the reordered list
        head = out.next;
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
     *     - Move `slow` one step forward.
     *     - Move `fast` two steps forward.
     * 3. Disconnect the left half from the right half by setting `prev.next` to null.
     * 4. Return the `slow` pointer, which points to the middle node.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the list.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param head The head of the singly linked list.
     * @return The middle node of the list.
     */
    public Node getMiddleNode(Node head) {
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
     * Helper Method: Reverse a Linked List
     * 
     * Intuition:
     * - Reversing a linked list involves reversing the direction of the `next` pointers.
     * - Use three pointers (`prev`, `curr`, and `next`) to iteratively reverse the list.
     * 
     * Explanation:
     * 1. Initialize `prev` to null and `curr` to the head of the list.
     * 2. Traverse the list while `curr` is not null:
     *     - Store the next node in a temporary variable (`next`).
     *     - Reverse the `next` pointer of the current node to point to `prev`.
     *     - Move `prev` and `curr` one step forward.
     * 3. Return `prev`, which will be the new head of the reversed list.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the list.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param head The head of the linked list.
     * @return The head of the reversed linked list.
     */
    public Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node next = curr.next; // Store the next node
            curr.next = prev; // Reverse the `next` pointer
            prev = curr; // Move `prev` forward
            curr = next; // Move `curr` forward
        }

        return prev; // Return the new head of the reversed list
    }
}