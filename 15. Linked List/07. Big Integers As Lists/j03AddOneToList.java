/**
 * Problem Statement:
 * 
 *     Given a singly linked list representing a non-negative integer, where each node contains
 *     a single digit, add one to the number and return the resulting linked list.
 * 
 * Input:
 *     - A singly linked list where each node contains a single digit.
 *       The digits are stored in normal order, meaning the most significant digit is at the head.
 * 
 * Output:
 *     - A singly linked list representing the number after adding one, also in normal order.
 * 
 * Example:
 *     Input:
 *         head = [1, 2, 3]
 *     Output:
 *         [1, 2, 4]
 * 
 *     Explanation:
 *         The number represented by the list is 123. Adding one results in 124, which is
 *         represented as [1, 2, 4].
 */

public class j03AddOneToList {

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
     * Approach 1: Reverse and Add
     * 
     * Intuition:
     * - Since the digits are stored in normal order, reversing the list allows us to process
     *   the least significant digit first.
     * - After adding one, reverse the list again to restore the original order.
     * 
     * Explanation:
     * 1. Reverse the input list using the `reverseList` method.
     * 2. Traverse the reversed list, adding one to the least significant digit.
     * 3. Propagate the carry to the next digits as needed.
     * 4. If there is a carry left after processing all digits, create a new node to store it.
     * 5. Reverse the list again to restore the original order.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the list.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the modified linked list after adding one.
     */
    public Node addOne(Node head) {
        head = reverseList(head); // Reverse the list
        Node tail = null;
        Node temp = head;
        int carry = 1;

        while (temp != null) {
            int val = temp.data + carry; // Add the carry to the current digit
            temp.data = val % 10; // Update the current digit
            carry = val / 10; // Update the carry
            tail = temp;
            temp = temp.next;
        }

        if (carry > 0) { // If there is a carry left, create a new node
            Node carryNode = new Node(carry);
            tail.next = carryNode;
        }

        return reverseList(head); // Reverse the list again to restore the original order
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

    /**
     * Approach 2: Recursive Addition
     * 
     * Intuition:
     * - Use recursion to traverse to the end of the list and add one to the least significant digit.
     * - Propagate the carry back through the recursive calls.
     * 
     * Explanation:
     * 1. Use a recursive helper method `addOneInList` to traverse to the end of the list.
     * 2. Add one to the least significant digit and propagate the carry back through the recursive calls.
     * 3. If there is a carry left after processing the most significant digit, create a new node to store it.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the list.
     * 
     * Space Complexity:
     * - O(n), due to the recursive stack.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the modified linked list after adding one.
     */
    public Node addOneRecursive(Node head) {
        int carry = addOneInList(head); // Add one recursively
        if (carry > 0) { // If there is a carry left, create a new node
            Node carryNode = new Node(carry);
            carryNode.next = head;
            head = carryNode;
        }
        return head;
    }

    /**
     * Helper Method: Add One in List Recursively
     * 
     * Intuition:
     * - Traverse to the end of the list and add one to the least significant digit.
     * - Propagate the carry back through the recursive calls.
     * 
     * Explanation:
     * 1. If the current node is null, return a carry of 1.
     * 2. Recursively call the method for the next node.
     * 3. Add the carry to the current node's value and update the carry.
     * 4. Return the carry to the previous recursive call.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the list.
     * 
     * Space Complexity:
     * - O(n), due to the recursive stack.
     * 
     * @param head The current node in the linked list.
     * @return The carry to be propagated to the previous node.
     */
    public int addOneInList(Node head) {
        if (head == null) {
            return 1; // Base case: add one to the least significant digit
        }

        int carry = addOneInList(head.next); // Recursively add one to the next node
        int value = head.data + carry; // Add the carry to the current digit
        head.data = value % 10; // Update the current digit
        carry = value / 10; // Update the carry

        return carry; // Return the carry to the previous node
    }
}