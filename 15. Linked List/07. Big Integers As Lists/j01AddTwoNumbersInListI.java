/**
 * Problem Statement:
 * 
 *     Given two non-empty linked lists representing two non-negative integers, where each node
 *     contains a single digit, add the two numbers and return the sum as a linked list.
 * 
 * Input:
 *     - Two singly linked lists `l1` and `l2`, where each node contains a single digit.
 *       The digits are stored in reverse order, meaning the 1's digit is at the head of the list.
 * 
 * Output:
 *     - A singly linked list representing the sum of the two numbers, also in reverse order.
 * 
 * Example:
 *     Input:
 *         l1 = [2, 4, 3]
 *         l2 = [5, 6, 4]
 *     Output:
 *         [7, 0, 8]
 * 
 *     Explanation:
 *         The numbers represented by the lists are 342 and 465. Their sum is 807, which is
 *         represented as [7, 0, 8] in reverse order.
 */

public class j01AddTwoNumbersInListI {

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
     * Approach 1: In-Place Modification
     * 
     * Intuition:
     * - Modify the nodes of the first list (`l1`) directly to store the result.
     * - Use a carry variable to handle sums greater than 9.
     * 
     * Explanation:
     * 1. Traverse both lists simultaneously, adding corresponding digits along with the carry.
     * 2. Update the `data` field of the current node in `l1` to store the sum modulo 10.
     * 3. If `l1` is shorter than `l2`, append the remaining nodes of `l2` to the result.
     * 4. If there is a carry left after processing both lists, create a new node to store it.
     * 
     * Time Complexity:
     * - O(max(n, m)), where `n` and `m` are the lengths of `l1` and `l2`, respectively.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param l1 The head of the first linked list.
     * @param l2 The head of the second linked list.
     * @return The head of the modified linked list representing the sum.
     */
    public Node addTwoNumbersInPlace(Node l1, Node l2) {
        Node dummy = new Node(-1);
        Node out = dummy;
        Node temp1 = l1;
        Node temp2 = l2;
        int carry = 0;

        while (temp1 != null || temp2 != null) {
            int d = carry;
            if (temp1 != null) {
                d += temp1.data;
            }
            if (temp2 != null) {
                d += temp2.data;
            }

            int value = d % 10;
            carry = d / 10;

            if (temp1 != null) {
                temp1.data = value;
                out.next = temp1;
            } else if (temp2 != null) {
                temp2.data = value;
                out.next = temp2;
            }

            out = out.next;
            if (temp1 != null) {
                temp1 = temp1.next;
            }
            if (temp2 != null) {
                temp2 = temp2.next;
            }
        }

        if (carry > 0) {
            Node carryNode = new Node(carry);
            out.next = carryNode;
        }

        return dummy.next;
    }

    /**
     * Approach 2: Using Extra Output Space
     * 
     * Intuition:
     * - Create a new linked list to store the result instead of modifying the input lists.
     * - Use a carry variable to handle sums greater than 9.
     * 
     * Explanation:
     * 1. Traverse both lists simultaneously, adding corresponding digits along with the carry.
     * 2. Create a new node to store the sum modulo 10 and append it to the result list.
     * 3. If one list is shorter than the other, continue processing the remaining nodes of the longer list.
     * 4. If there is a carry left after processing both lists, create a new node to store it.
     * 
     * Time Complexity:
     * - O(max(n, m)), where `n` and `m` are the lengths of `l1` and `l2`, respectively.
     * 
     * Space Complexity:
     * - O(max(n, m)), as a new linked list is created to store the result.
     * 
     * @param l1 The head of the first linked list.
     * @param l2 The head of the second linked list.
     * @return The head of the new linked list representing the sum.
     */
    public Node addTwoNumbersExraOutputSpace(Node l1, Node l2) {
        Node dummy = new Node(-1);
        Node out = dummy;
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

            out.next = new Node(d % 10);
            carry = d / 10;
            out = out.next;

            if (temp1 != null) {
                temp1 = temp1.next;
            }
            if (temp2 != null) {
                temp2 = temp2.next;
            }
        }

        return dummy.next;
    }
}