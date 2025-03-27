/**
 * Problem Statement:
 * 
 *     Given the head of a singly linked list, determine if it is a palindrome.
 * 
 * Input:
 *     - A singly linked list represented by its head node.
 * 
 * Output:
 *     - A boolean value: `true` if the linked list is a palindrome, otherwise `false`.
 * 
 * Example:
 *     Input:
 *         head = [1, 2, 2, 1]
 *     Output:
 *         true
 * 
 *     Explanation:
 *         The linked list reads the same forward and backward, so it is a palindrome.
 */

public class j04PalindromeLinkedList {

    /**
     * Definition of a Node in the linked list.
     */
    static class Node {
        public int data;
        public Node next;
        public Node prev;

        public Node() {
            this.data = 0;
            this.next = null;
            this.prev = null;
        }

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    /**
     * Approach 1: Two Pointers with Data Comparison
     * 
     * Intuition:
     * - This approach calculates the length of the linked list and uses two pointers to compare
     *   the data of nodes from the start and end of the list, moving towards the middle.
     * - The `getNode` method is used to fetch nodes at specific indices for comparison.
     * 
     * Explanation:
     * - Step 1: Calculate the length of the linked list by traversing it once.
     * - Step 2: Use two pointers, `s` (start) and `e` (end), initialized to the first and last indices.
     * - Step 3: Compare the data of the nodes at indices `s` and `e` using the `getNode` method.
     * - Step 4: Increment `s` and decrement `e` to move towards the middle of the list.
     * - Step 5: If all comparisons match, return `true`. Otherwise, return `false`.
     * 
     * Time Complexity:
     * - O(n^2), where n is the number of nodes in the linked list. The `getNode` method is called
     *   for each comparison, and it traverses the list up to the specified index, leading to quadratic time complexity.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few variables.
     * 
     * @param head The head of the singly linked list.
     * @return `true` if the linked list is a palindrome, otherwise `false`.
     */
    public boolean isPalindromeTwoPointers(Node head) {
        int length = 0;
        Node temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }

        int s = 0;
        int e = length - 1;
        while (s < e) {
            Node left = getNode(head, s);
            Node right = getNode(head, e);
            if (left.data != right.data)
                return false;
            s++;
            e--;
        }
        return true;
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
    public Node getNode(Node head, int index) {
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    /**
     * Approach 2: Duplicate List Comparison
     * 
     * Intuition:
     * - This approach creates a reversed copy of the linked list and compares it with the original list.
     * - If all corresponding nodes in the original and reversed lists have the same data, the list is a palindrome.
     * 
     * Explanation:
     * - Step 1: Create a reversed copy of the linked list using the `createReverseLinkedList` method.
     * - Step 2: Compare the data of nodes in the original and reversed lists.
     * - Step 3: If all comparisons match, return `true`. Otherwise, return `false`.
     * 
     * Time Complexity:
     * - O(n), where n is the number of nodes in the linked list. Each node is visited once to create the reversed list
     *   and once for comparison.
     * 
     * Space Complexity:
     * - O(n), due to the creation of the reversed list.
     * 
     * @param head The head of the singly linked list.
     * @return `true` if the linked list is a palindrome, otherwise `false`.
     */
    public boolean isPalindromeDuplicateList(Node head) {
        Node newHead = createReverseLinkedList(head);
        Node temp1 = head;
        Node temp2 = newHead;
        while (temp1 != null || temp2 != null) {
            if (temp1.data != temp2.data)
                return false;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return true;
    }

    /**
     * Helper Method: Cloning Linked List in Reverse
     * 
     * Intuition:
     * - This method creates a reversed copy of the given linked list.
     * - It iterates through the original list and prepends each node's data to the new list.
     * 
     * Explanation:
     * - Step 1: Traverse the original list node by node.
     * - Step 2: For each node, create a new node with the same data and prepend it to the new list.
     * - Step 3: Update the `next` pointer of the new node to point to the current head of the new list.
     * - Step 4: Update the head of the new list to the newly created node.
     * 
     * Time Complexity:
     * - O(n), where n is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(n), due to the creation of the reversed list.
     * 
     * @param head The head of the original linked list.
     * @param newHead The head of the reversed linked list (output parameter).
     */
    public Node createReverseLinkedList(Node head) {
        Node newHead = new Node();
        Node temp = head;
        Node prev = null;
        while (temp != null) {
            Node newNode = new Node(temp.data);
            newNode.next = prev;
            prev = newNode;
            temp = temp.next;
        }
        newHead.data = prev.data;
        newHead.next = prev.next;
        return newHead;
    }

    /**
     * Approach 3: Efficient Palindrome Check
     * 
     * Intuition:
     * - This approach uses the "two-pointer" technique to find the middle of the list, reverses the second half,
     *   and compares it with the first half.
     * - By reversing only the second half of the list, we avoid the need for extra space.
     * 
     * Explanation:
     * - Step 1: Find the middle node of the list using the `getMiddleNode` method.
     * - Step 2: Reverse the second half of the list starting from the middle node using the `reverseList` method.
     * - Step 3: Compare the data of nodes in the first half and the reversed second half.
     * - Step 4: If all comparisons match, return `true`. Otherwise, return `false`.
     * 
     * Time Complexity:
     * - O(n), where n is the number of nodes in the linked list. Each node is visited once to find the middle,
     *   reverse the second half, and compare the halves.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param head The head of the singly linked list.
     * @return `true` if the linked list is a palindrome, otherwise `false`.
     */
    public boolean isPalindromeEfficient(Node head) {
        Node middle = getMiddleNode(head);
        Node newHead = reverseList(middle);
        Node temp1 = head;
        Node temp2 = newHead;
        while (temp2 != null) {
            if (temp1.data != temp2.data)
                return false;
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return true;
    }

    /**
     * Helper Method: getMiddleNode
     * 
     * Intuition:
     * - This method finds the middle node of the linked list using the "slow and fast pointer" technique.
     * - The slow pointer moves one step at a time, while the fast pointer moves two steps at a time.
     * - When the fast pointer reaches the end, the slow pointer will be at the middle.
     * 
     * Explanation:
     * - Step 1: Initialize `slow` and `fast` pointers to the head of the list.
     * - Step 2: Move `slow` one step and `fast` two steps in each iteration until `fast` reaches the end.
     * - Step 3: Return the `slow` pointer as the middle node.
     * 
     * Time Complexity:
     * - O(n), where n is the number of nodes in the linked list. Each node is visited once.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from the pointers.
     * 
     * @param head The head of the singly linked list.
     * @return The middle node of the linked list.
     */
    public Node getMiddleNode(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
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
     *     1. Store the next node of `curr` in `temp` to preserve the reference.
     *     2. Reverse the `next` pointer of `curr` to point to `prev`.
     *     3. Move `prev` to `curr` and `curr` to `temp` to continue the traversal.
     * - Step 3: When `curr` becomes `null`, the list is fully reversed, and `prev` will point to the new head.
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
    public Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }
}