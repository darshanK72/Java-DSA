/**
 * Problem Statement:
 * 
 *     Given the head of a singly linked list, reverse the nodes of the list in groups of size `k`, 
 *     and return the modified list.
 * 
 * Input:
 *     - A singly linked list represented by its head node.
 *     - An integer `k` (1 <= k <= n), where `n` is the length of the linked list.
 * 
 * Output:
 *     - The head of the modified linked list after reversing the nodes in groups of size `k`.
 * 
 * Example:
 *     Input:
 *         head = [1, 2, 3, 4, 5], k = 2
 *     Output:
 *         [2, 1, 4, 3, 5]
 * 
 *     Explanation:
 *         The nodes are reversed in groups of size 2. The first group [1, 2] is reversed to [2, 1], 
 *         and the second group [3, 4] is reversed to [4, 3]. The last node [5] remains as is.
 */

public class j07ReverseNodesInGroups {

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
     * Approach: Reverse Nodes in Groups of Size K (Iterative)
     * 
     * Intuition:
     * - This approach reverses the nodes in groups of size `k` iteratively.
     * - A dummy node is used to simplify edge cases, such as when the list starts with a group of size `k`.
     * - The size of the list is calculated beforehand to ensure that only complete groups of size `k` are reversed.
     * 
     * Explanation:
     * - Step 1: Create a dummy node and point its `next` to the head of the list.
     * - Step 2: Calculate the size of the linked list using the `getSizeOfLL` method.
     * - Step 3: Traverse the list while there are enough nodes left to form a group of size `k`:
     *     1. Reverse the current group of `k` nodes using a loop.
     *     2. Update the `next` pointer of the previous group's tail to point to the new head of the reversed group.
     *     3. Update the `prevTail` pointer to the tail of the current group.
     * - Step 4: If there are fewer than `k` nodes left, connect the remaining nodes as is.
     * - Step 5: Return the new head of the list (i.e., `dummy.next`).
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @param k The size of the groups to reverse.
     * @return The head of the modified linked list.
     */
    public Node reverseKGroup1(Node head, int k) {
        if (head == null) return head; // Base case: empty list
        Node dummy = new Node(-1); // Dummy node to simplify edge cases
        int size = getSizeOfLL(head); // Calculate the size of the linked list
        dummy.next = head;
        Node prevTail = dummy; // Pointer to the tail of the previous group
        Node curr = head; // Pointer to the current node

        while (curr != null) {
            if (size >= k) { // If there are enough nodes to form a group of size `k`
                Node prev = null;
                Node tail = curr; // Mark the current node as the tail of the group
                for (int i = 0; i < k; i++) { // Reverse the group of `k` nodes
                    Node next = curr.next; // Store the next node
                    curr.next = prev; // Reverse the `next` pointer
                    prev = curr; // Move `prev` forward
                    curr = next; // Move `curr` forward
                }
                size -= k; // Decrease the size by `k`
                prevTail.next = prev; // Connect the previous group's tail to the new head of the reversed group
                prevTail = tail; // Update the `prevTail` pointer to the tail of the current group
            } else { // If there are fewer than `k` nodes left
                prevTail.next = curr; // Connect the remaining nodes as is
                break;
            }
        }

        return dummy.next; // Return the new head of the list
    }

    /**
     * Helper Method: getSizeOfLL
     * 
     * Intuition:
     * - This method calculates the size (number of nodes) of a singly linked list by traversing it.
     * - A counter is incremented for each node visited until the end of the list is reached.
     * 
     * Explanation:
     * - Step 1: Initialize a counter (`size`) to 0.
     * - Step 2: Traverse the list starting from the head:
     *     1. For each node, increment the counter.
     *     2. Move to the next node using the `next` pointer.
     * - Step 3: When the traversal is complete (i.e., `head` becomes null), return the counter as the size of the list.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from the counter variable.
     * 
     * @param head The head of the singly linked list.
     * @return The size (number of nodes) of the linked list.
     */
    public int getSizeOfLL(Node head) {
        int size = 0; // Initialize the counter
        while (head != null) { // Traverse the list until the end
            size++; // Increment the counter for each node
            head = head.next; // Move to the next node
        }
        return size; // Return the total size of the list
    }

    /**
     * Approach 2: Efficient Reverse Nodes in Groups of Size K
     * 
     * Intuition:
     * - This approach reverses the nodes in groups of size `k` by identifying the sublist of `k` nodes, 
     *   reversing it, and reconnecting it to the rest of the list.
     * - A dummy node is used to simplify edge cases, such as when the list starts with a group of size `k`.
     * 
     * Explanation:
     * - Step 1: Create a dummy node and point its `next` to the head of the list.
     * - Step 2: Traverse the list to process groups of size `k`:
     *     1. Use the `findKthNode` method to locate the `k`th node in the current group.
     *     2. Reverse the sublist of `k` nodes using the `reverseList` method.
     *     3. Reconnect the reversed sublist to the rest of the list.
     * - Step 3: Stop when there are fewer than `k` nodes left in the list.
     * - Step 4: Return the new head of the list (i.e., `dummy.next`).
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @param k The size of the groups to reverse.
     * @return The head of the modified linked list.
     */
    public Node reverseKGroup(Node head, int k) {
        if (k == 1 || head == null)
            return head;

        Node dummy = new Node(0);
        dummy.next = head;
        Node prevTail = dummy;
        Node temp = head;

        while (temp != null) {
            Node kthNode = findKthNode(temp, k);
            if (kthNode == null)
                break;
            Node next = kthNode.next;
            kthNode.next = null;
            Node newHead = reverseList(temp);
            prevTail.next = newHead;
            temp.next = next;
            prevTail = temp;
            temp = next;
        }
        return dummy.next;
    }

    /**
     * Helper Method: findKthNode
     * 
     * Intuition:
     * - This method finds the `k`th node from the given starting node in the linked list.
     * - It traverses the list up to `k` nodes or until the end of the list is reached.
     * 
     * Explanation:
     * - Step 1: Start from the given node (`head`).
     * - Step 2: Traverse the list for `k` steps or until the end of the list is reached.
     * - Step 3: Return the `k`th node if it exists, or `null` if there are fewer than `k` nodes.
     * 
     * Time Complexity:
     * - O(k), where `k` is the number of steps to traverse.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a temporary pointer.
     * 
     * @param head The starting node of the linked list.
     * @param k The number of steps to traverse.
     * @return The `k`th node, or `null` if there are fewer than `k` nodes.
     */
    public Node findKthNode(Node head, int k) {
        Node temp = head;
        for (int i = 1; i < k; i++) {
            if (temp == null)
                return null;
            temp = temp.next;
        }
        return temp;
    }

    /**
     * Helper Method: reverseList
     * 
     * Intuition:
     * - This method reverses a singly linked list using an iterative approach.
     * - It changes the direction of the `next` pointer for each node in the list.
     * 
     * Explanation:
     * - Step 1: Initialize `prev` to `null` and `curr` to the head of the list.
     * - Step 2: Traverse the list:
     *     1. Store the next node of `curr` in `next` to preserve the reference.
     *     2. Reverse the `next` pointer of `curr` to point to `prev`.
     *     3. Move `prev` to `curr` and `curr` to `next` to continue the traversal.
     * - Step 3: When `curr` becomes `null`, the list is fully reversed, and `prev` will point to the new head.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @return The head of the reversed linked list.
     */
    public Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}