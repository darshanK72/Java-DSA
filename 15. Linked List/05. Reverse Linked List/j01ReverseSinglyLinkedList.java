/**
 * Problem Statement:
 * 
 *     Given the head of a singly linked list, reverse the list and return the new head.
 * 
 * Input:
 *     - A singly linked list represented by its head node.
 * 
 * Output:
 *     - The head of the reversed linked list.
 * 
 * Example:
 *     Input:
 *         Linked List: 1 -> 2 -> 3 -> 4 -> 5 -> null
 *     Output:
 *         Reversed Linked List: 5 -> 4 -> 3 -> 2 -> 1 -> null
 * 
 * Explanation:
 *     The linked list is reversed by iteratively changing the direction of the `next` pointer
 *     for each node, starting from the head and moving to the end of the list.
 */

public class j01ReverseSinglyLinkedList {

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Approach 1: Two Pointers with Data Swapping
     * 
     * Intuition:
     * - This approach calculates the length of the linked list and swaps the data of nodes
     *   from the start and end iteratively until the middle of the list is reached.
     * - The `getNode` method is used to fetch nodes at specific indices for swapping.
     * - This approach does not reverse the `next` pointers but instead swaps the data values
     *   of the nodes, effectively reversing the list.
     * 
     * Explanation:
     * - Step 1: Calculate the length of the linked list by traversing it once.
     * - Step 2: Use two pointers, `s` (start) and `e` (end), initialized to the first and last indices.
     * - Step 3: Swap the data of the nodes at indices `s` and `e` using the `getNode` method.
     * - Step 4: Increment `s` and decrement `e` to move towards the middle of the list.
     * - Step 5: Repeat the swapping process until `s` is no longer less than `e`.
     * - Step 6: Return the head of the modified list.
     * 
     * Time Complexity:
     * - O(n^2), where n is the number of nodes in the linked list. The `getNode` method is called
     *   for each swap, and it traverses the list up to the specified index, leading to quadratic time complexity.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few variables.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the reversed linked list.
     */
    public Node reverseListTwoPointers(Node head) {
        int length = 0; // Initialize the length of the linked list
        Node temp = head; // Temporary pointer to traverse the list
        while (temp != null) { // Calculate the length of the list
            temp = temp.next;
            length++;
        }

        int s = 0; // Start pointer
        int e = length - 1; // End pointer
        while (s < e) { // Swap nodes until the middle of the list is reached
            Node start = getNode(head, s); // Get the node at index `s`
            Node end = getNode(head, e); // Get the node at index `e`
            int t = start.data; // Swap the data of the nodes
            start.data = end.data;
            end.data = t;
            s++; // Move the start pointer forward
            e--; // Move the end pointer backward
        }
        return head; // Return the modified list
    }

    /**
     * Helper Method: getNode
     * 
     * Intuition:
     * - This method is used to fetch the node at a specific index in the linked list.
     * - It traverses the list from the head node until the desired index is reached.
     * 
     * Explanation:
     * - Step 1: Start from the head of the list.
     * - Step 2: Traverse the list by moving to the `next` node in each iteration.
     * - Step 3: Stop when the desired index is reached and return the node at that index.
     * 
     * Time Complexity:
     * - O(k), where k is the index of the node to be fetched. The method traverses up to the specified index.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a temporary pointer.
     * 
     * @param head The head of the singly linked list.
     * @param index The index of the node to fetch (0-based).
     * @return The node at the specified index.
     */
    public static Node getNode(Node head, int index) {
        Node temp = head; // Start from the head of the list
        for (int i = 0; i < index; i++) { // Traverse the list up to the specified index
            temp = temp.next; // Move to the next node
        }
        return temp; // Return the node at the specified index
    }

    /**
     * Approach 2: Three Pointers Efficient
     * 
     * Intuition:
     * - To reverse a singly linked list, we need to change the direction of the `next` pointer
     *   for each node. This can be achieved iteratively by maintaining three pointers:
     *   `prev`, `curr`, and `next`.
     * - `prev` keeps track of the previous node, `curr` points to the current node being processed,
     *   and `next` stores the next node to avoid losing the reference during pointer reversal.
     * 
     * Explanation:
     * - Initialize `prev` to `null` and `curr` to the head of the list.
     * - Traverse the list:
     *     1. Store the next node of `curr` in `next` to preserve the reference.
     *     2. Reverse the `next` pointer of `curr` to point to `prev`.
     *     3. Move `prev` to `curr` and `curr` to `next` to continue the traversal.
     * - When `curr` becomes `null`, the list is fully reversed, and `prev` will point to the new head.
     * 
     * Time Complexity:
     * - O(n), where n is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the reversed linked list.
     */
    public Node reverseListefficient(Node head) {
        Node prev = null; // Initialize the previous node as null
        Node curr = head; // Start with the head node

        while (curr != null) { // Traverse the list until the current node is null
            Node next = curr.next; // Store the next node
            curr.next = prev; // Reverse the current node's pointer
            prev = curr; // Move the previous pointer to the current node
            curr = next; // Move the current pointer to the next node
        }

        return prev; // Return the new head of the reversed list
    }

    /**
     * Approach 3: Recursive Solution
     * 
     * Intuition:
     * - The recursive approach works by reversing the rest of the list first and then adjusting the pointers
     *   of the current node to point to the previous node.
     * - The base case is when the list is empty or has only one node, in which case the head is returned as is.
     * 
     * Explanation:
     * - Step 1: If the head is null or there is only one node, return the head (base case).
     * - Step 2: Recursively reverse the rest of the list starting from `head.next`.
     * - Step 3: Adjust the `next` pointer of the node after the head to point back to the head.
     * - Step 4: Set the `next` pointer of the head to null to terminate the reversed list.
     * - Step 5: Return the new head of the reversed list.
     * 
     * Time Complexity:
     * - O(n), where n is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(n), due to the recursive call stack.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the reversed linked list.
     */
    public Node reverseListRecursive(Node head) {
        // Base case: If the list is empty or has only one node, return the head
        if (head == null || head.next == null) {
            return head;
        }

        // Recursively reverse the rest of the list
        Node newHead = reverseListRecursive(head.next);

        // Adjust the pointers
        head.next.next = head; // Make the next node point back to the current node
        head.next = null; // Set the current node's next pointer to null

        return newHead; // Return the new head of the reversed list
    }

    /**
     * Approach 4: Create a Reversed Linked List (Recursive)
     * 
     * Intuition:
     * - This approach creates a new reversed linked list by traversing the original list recursively.
     *   At each step, a new node is created with the data of the current node, and it is appended
     *   to the reversed list.
     * - The base case is when the end of the original list is reached, at which point the recursion
     *   starts returning and building the reversed list.
     * 
     * Explanation:
     * - Step 1: If the current node (`head`) is the last node in the original list, create a new node
     *           with its data and return it as the new head of the reversed list.
     * - Step 2: Recursively process the rest of the list starting from `head.next`.
     * - Step 3: Create a new node with the data of the current node (`head`) and append it to the
     *           reversed list by linking it to the last node returned by the recursive call.
     * - Step 4: Return the newly created node as the last node of the reversed list.
     * 
     * Time Complexity:
     * - O(n), where n is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(n), due to the recursive call stack and the creation of new nodes for the reversed list.
     * 
     * @param head The head of the original linked list.
     * @param newHead The head of the new reversed linked list.
     * @return The last node of the reversed linked list.
     */
    public Node createReverseLinkedList(Node head, Node newHead) {
        // Base case: If the current node is the last node in the original list
        if (head.next == null) {
            newHead.data = head.data; // Set the data of the new head
            return newHead; // Return the new head
        }

        // Recursively process the rest of the list
        Node lastNode = createReverseLinkedList(head.next, newHead);

        // Create a new node with the data of the current node
        Node node = new Node(head.data);

        // Append the new node to the reversed list
        lastNode.next = node;

        // Return the newly created node as the last node of the reversed list
        return node;
    }
}
