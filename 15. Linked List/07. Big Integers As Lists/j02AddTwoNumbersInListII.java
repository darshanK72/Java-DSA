/**
 * Problem Statement:
 * 
 *     Given two non-empty linked lists representing two non-negative integers, where each node
 *     contains a single digit, add the two numbers and return the sum as a linked list.
 *     The digits are stored in normal order, meaning the most significant digit is at the head
 *     of the list.
 * 
 * Input:
 *     - Two singly linked lists `l1` and `l2`, where each node contains a single digit.
 *       The digits are stored in normal order.
 * 
 * Output:
 *     - A singly linked list representing the sum of the two numbers, also in normal order.
 * 
 * Example:
 *     Input:
 *         l1 = [7, 2, 4, 3]
 *         l2 = [5, 6, 4]
 *     Output:
 *         [7, 8, 0, 7]
 * 
 *     Explanation:
 *         The numbers represented by the lists are 7243 and 564. Their sum is 7807, which is
 *         represented as [7, 8, 0, 7] in normal order.
 */

public class j02AddTwoNumbersInListII {

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
     * Approach: Reverse and Add
     * 
     * Intuition:
     * - Since the digits are stored in normal order, we need to reverse the lists to process
     *   the least significant digits first.
     * - After reversing, the addition is performed similarly to adding numbers in reverse order.
     * - Finally, reverse the result list to restore the normal order.
     * 
     * Explanation:
     * 1. Reverse both input lists using the `reverseList` method.
     * 2. Traverse both reversed lists simultaneously, adding corresponding digits along with the carry.
     * 3. Create a new node to store the sum modulo 10 and append it to the result list.
     * 4. If one list is shorter than the other, continue processing the remaining nodes of the longer list.
     * 5. If there is a carry left after processing both lists, create a new node to store it.
     * 6. Reverse the result list to restore the normal order.
     * 
     * Time Complexity:
     * - O(n + m), where `n` and `m` are the lengths of `l1` and `l2`, respectively.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param l1 The head of the first linked list.
     * @param l2 The head of the second linked list.
     * @return The head of the new linked list representing the sum.
     */
    public Node addTwoNumbers(Node l1, Node l2) {
        l1 = reverseList(l1); // Reverse the first list
        l2 = reverseList(l2); // Reverse the second list

        Node dummy = new Node(-1); // Dummy node to simplify the merging process
        Node out = dummy; // Pointer to build the result list
        Node temp1 = l1;
        Node temp2 = l2;
        int carry = 0;

        while (temp1 != null || temp2 != null || carry > 0) {
            int d = carry;
            if (temp1 != null) {
                d += temp1.data;
            }
            if (temp2 != null) {
                d += temp2.data;
            }

            out.next = new Node(d % 10); // Create a new node for the current digit
            carry = d / 10; // Update the carry
            out = out.next;

            if (temp1 != null) {
                temp1 = temp1.next;
            }
            if (temp2 != null) {
                temp2 = temp2.next;
            }
        }

        return reverseList(dummy.next); // Reverse the result list to restore normal order
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
     *    - Store the next node in a temporary variable (`next`).
     *    - Reverse the `next` pointer of the current node to point to `prev`.
     *    - Move `prev` and `curr` one step forward.
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