/*-
 * Problem Statement:
 * 
 *     Given the head of a singly linked list, swap every two adjacent nodes and return its head.
 *     You must solve the problem without modifying the values in the list's nodes (i.e., only node
 *     swapping is allowed).
 * 
 * Input:
 *     - A singly linked list represented by its head node.
 * 
 * Output:
 *     - The head of the modified linked list after swapping every two adjacent nodes.
 * 
 * Example:
 *     Input:
 *         head = [1, 2, 3, 4]
 *     Output:
 *         [2, 1, 4, 3]
 * 
 *     Explanation:
 *         Nodes 1 and 2 are swapped, and nodes 3 and 4 are swapped.
 */

public class j05SwapNodesInPairs {

    /*-
     * Definition of a Node in the linked list.
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

    /*-
     * Approach 1: Swap Data Between Nodes
     * 
     * Intuition:
     * - This approach swaps the data of every two adjacent nodes instead of modifying the links.
     * - It uses two pointers (`first` and `second`) to traverse the list in pairs and swaps their data.
     * 
     * Explanation:
     * - Step 1: Initialize `first` to the head and `second` to the second node.
     * - Step 2: Traverse the list in pairs:
     *     1. Swap the data of `first` and `second`.
     *     2. Move `first` and `second` to the next pair of nodes.
     * - Step 3: Stop when there are no more pairs to process.
     * 
     * Time Complexity:
     * - O(n), where n is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the modified linked list.
     */
    public Node swapPairsCopyData(Node head) {
        if (head == null || head.next == null)
            return head;
        Node first = head;
        Node second = head.next;

        while (second != null) {
            int temp = first.data;
            first.data = second.data;
            second.data = temp;
            first = first.next.next;
            if (second.next == null)
                break;
            second = second.next.next;
        }
        return head;
    }

    /*-
     * Approach 2: Swap Nodes Using Links
     * 
     * Intuition:
     * - This approach swaps the actual nodes by modifying their `next` pointers.
     * - A dummy node is used to simplify edge cases, such as when the list has fewer than two nodes.
     * 
     * Explanation:
     * - Step 1: Create a dummy node and point its `next` to the head of the list.
     * - Step 2: Use two pointers (`prev` and `curr`) to traverse the list in pairs:
     *     1. Identify the two nodes to be swapped (`curr` and `curr.next`).
     *     2. Update the `next` pointers to swap the nodes.
     *     3. Move `prev` and `curr` to the next pair of nodes.
     * - Step 3: Return the new head of the list (i.e., `dummy.next`).
     * 
     * Time Complexity:
     * - O(n), where n is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the modified linked list.
     */
    public Node swapPairsUsingLinks(Node head) {
        if (head == null || head.next == null)
            return head;
        Node dummy = new Node(0);
        dummy.next = head;
        Node prev = dummy;
        Node curr = head;

        while (curr != null && curr.next != null) {
            Node next = curr.next.next;
            Node second = curr.next;
            second.next = curr;
            curr.next = next;
            prev.next = second;
            prev = curr;
            curr = next;
        }
        return dummy.next;
    }

    /*-
     * Approach 3: Recursive Solution
     * 
     * Intuition:
     * - This approach uses recursion to swap nodes in pairs.
     * - At each step, the first two nodes are swapped, and the recursion is called for the rest of the list.
     * 
     * Explanation:
     * - Step 1: If the list is empty or has only one node, return the head (base case).
     * - Step 2: Identify the first two nodes to be swapped (`first` and `second`).
     * - Step 3: Swap the nodes by updating their `next` pointers.
     * - Step 4: Recursively call the method for the rest of the list starting from the third node.
     * - Step 5: Return the new head of the list (i.e., `second`).
     * 
     * Time Complexity:
     * - O(n), where n is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(n), due to the recursive call stack.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the modified linked list.
     */
    public Node swapPairsRecursive(Node head) {
        if (head == null)
            return head;
        Node first = head;
        Node second = head.next;
        if (second == null)
            return first;

        Node next = second.next;
        second.next = first;
        first.next = swapPairsRecursive(next);
        return second;
    }
}