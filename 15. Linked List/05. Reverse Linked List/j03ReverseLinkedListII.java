/**
 * Problem Statement:
 * 
 *     Given the head of a singly linked list and two integers `left` and `right` 
 *     where `left <= right`, reverse the nodes of the list from position `left` 
 *     to position `right`, and return the reversed list.
 * 
 * Input:
 *     - A singly linked list represented by its head node.
 *     - Two integers `left` and `right` (1 <= left <= right <= n), where `n` is 
 *       the length of the linked list.
 * 
 * Output:
 *     - The head of the modified linked list after reversing the nodes between 
 *       positions `left` and `right`.
 * 
 * Example:
 *     Input:
 *         head = [1, 2, 3, 4, 5], left = 2, right = 4
 *     Output:
 *         [1, 4, 3, 2, 5]
 * 
 *     Explanation:
 *         The sublist [2, 3, 4] is reversed to [4, 3, 2], and the rest of the 
 *         list remains unchanged.
 */

public class j03ReverseLinkedListII {

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
     * Approach: Iterative Reversal
     * 
     * Intuition:
     * - To reverse a sublist of a linked list, we first locate the node just before the start of 
     *   the sublist (`preStart`) and the first node of the sublist (`curr`). Then, we reverse the
     *   links between the nodes in the range `[left, right]` using a standard iterative reversal 
     *   technique.
     * - After reversing, we reconnect the reversed sublist to the rest of the list by updating the 
     *   pointers of `preStart` and the last node of the reversed sublist.
     * 
     * Explanation:
     * 1. Create a dummy node to simplify edge cases (e.g., reversing from the head of the list).
     * 2. Traverse the list to find the node just before the start of the sublist (`preStart`).
     * 3. Reverse the sublist using an iterative approach:
     *    - Keep track of the previous node (`prev`) and the current node (`curr`).
     *    - Update the `next` pointer of the current node to point to the previous node.
     * 4. Reconnect the reversed sublist to the rest of the list:
     *    - Update the `next` pointer of `preStart` to point to the new head of the reversed sublist.
     *    - Update the `next` pointer of the last node in the reversed sublist to point to the node after \
     *      the sublist.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the linked list. We traverse the list once to 
     *   locate `preStart` and once to reverse the sublist.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of extra space.
     * 
     * @param head The head of the linked list.
     * @param left The starting position of the sublist to reverse.
     * @param right The ending position of the sublist to reverse.
     * @return The head of the modified linked list.
     */
    public Node reverseBetween(Node head, int left, int right) {
        if (left == right) {
            return head; // No need to reverse if left == right
        }

        // Create a dummy node to simplify edge cases
        Node dummy = new Node(0);
        dummy.next = head;

        // Find the node just before the start of the sublist
        Node preStart = dummy;
        for (int i = 1; i < left; i++) {
            preStart = preStart.next;
        }

        // Reverse the sublist
        Node prev = null;
        Node curr = preStart.next;
        for (int i = left; i <= right; i++) {
            Node next = curr.next; // Store the next node
            curr.next = prev; // Reverse the link
            prev = curr; // Move prev forward
            curr = next; // Move curr forward
        }

        // Reconnect the reversed sublist to the rest of the list
        preStart.next.next = curr; // Connect the last node of the reversed sublist
        preStart.next = prev; // Connect preStart to the new head of the sublist

        return dummy.next; // Return the new head of the list
    }
}