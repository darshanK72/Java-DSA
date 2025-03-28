/**
 * Problem Statement:
 * 
 *     Given a linked list where every node represents the head of a vertically sorted linked list,
 *     flatten it into a single sorted linked list.
 * 
 * Input:
 *     - A linked list where each node contains:
 *         - `data`: An integer value.
 *         - `next`: A reference to the next node in the horizontal list.
 *         - `bottom`: A reference to the head of a vertically sorted linked list.
 * 
 * Output:
 *     - The head of the flattened and sorted linked list.
 * 
 * Example:
 *     Input:
 *         root = [5 -> 10 -> 19 -> 28]
 *                |     |      |      |
 *                7     20     22     35
 *                |            |      |
 *                8            50     40
 *                |                   |
 *                30                  45
 *     Output:
 *         [5 -> 7 -> 8 -> 10 -> 19 -> 20 -> 22 -> 28 -> 30 -> 35 -> 40 -> 45 -> 50]
 * 
 *     Explanation:
 *         The vertical lists are merged into a single sorted list.
 */

public class j05FlattenTheSortedVerticalLists {

    /**
     * Node Class:
     * 
     * Represents a node in the linked list. Each node contains:
     * - `data`: The integer value stored in the node.
     * - `next`: A reference to the next node in the horizontal list.
     * - `bottom`: A reference to the head of a vertically sorted linked list.
     */
    static class Node {
        public int data;
        public Node next;
        public Node bottom;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.bottom = null;
        }
    }

    /**
     * Approach 1: Iterative Flattening
     * 
     * Intuition:
     * - The horizontal list represents the heads of multiple vertically sorted linked lists.
     * - To flatten the list, we iteratively merge the current flattened list with the next vertical list.
     * - Use the `mergeTwoLists` method to merge two vertically sorted linked lists.
     * 
     * Explanation:
     * 1. Start with the first vertical list (`root`) as the initial flattened list.
     * 2. Traverse the horizontal list using the `next` pointer.
     * 3. For each node in the horizontal list, merge its vertical list with the current flattened list.
     * 4. Continue until all vertical lists are merged.
     * 5. Return the head of the flattened and sorted linked list.
     * 
     * Time Complexity:
     * - O(n * m), where `n` is the number of nodes in the horizontal list and `m` is the average number
     *   of nodes in each vertical list.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param root The head of the linked list containing vertically sorted lists.
     * @return The head of the flattened and sorted linked list.
     */
    public static Node flattenVerticalLists(Node root) {
        Node out = root; // Initialize the flattened list as the first vertical list
        Node temp = root.next; // Pointer to traverse the horizontal list

        while (temp != null) {
            out = mergeTwoLists(out, temp); // Merge the current flattened list with the next vertical list
            temp = temp.next; // Move to the next node in the horizontal list
        }

        return out; // Return the head of the flattened list
    }

    /**
     * Approach 2: Recursive Flattening
     * 
     * Intuition:
     * - The horizontal list represents the heads of multiple vertically sorted linked lists.
     * - To flatten the list, we recursively flatten the rest of the horizontal list and then merge it with the current vertical list.
     * - Use the `mergeTwoLists` method to merge two vertically sorted linked lists.
     * 
     * Explanation:
     * 1. Base Case:
     *    - If the `root` is null or there is only one node in the horizontal list, return `root` as it is already flattened.
     * 2. Recursive Step:
     *    - Recursively flatten the rest of the horizontal list (`root.next`).
     *    - Merge the current vertical list (`root`) with the flattened list (`root.next`).
     * 3. Return the head of the flattened and sorted linked list.
     * 
     * Time Complexity:
     * - O(n * m), where `n` is the number of nodes in the horizontal list and `m` is the average number of nodes in each vertical list.
     * - This is because each merge operation takes linear time, and there are `n` recursive calls.
     * 
     * Space Complexity:
     * - O(n), due to the recursion stack used for `n` recursive calls.
     * 
     * @param root The head of the linked list containing vertically sorted lists.
     * @return The head of the flattened and sorted linked list.
     */
    public static Node flattenRecursive(Node root) {
        // Base case: if root is null or there is only one list
        if (root == null || root.next == null) {
            return root;
        }

        // Recursively flatten the rest of the list
        root.next = flattenRecursive(root.next);

        // Merge the current list with the flattened list
        root = mergeTwoLists(root, root.next);

        // Return the flattened list
        return root;
    }

    /**
     * Helper Method: Merge Two Sorted Vertical Lists
     * 
     * Intuition:
     * - Use two pointers to traverse the two vertical lists and compare their values.
     * - Append the smaller value to the merged list and move the corresponding pointer forward.
     * 
     * Explanation:
     * 1. Initialize two pointers, `temp1` and `temp2`, to the heads of the two vertical lists.
     * 2. Create a dummy node to serve as the starting point of the merged list.
     * 3. Traverse both lists while neither pointer is null:
     *    - Compare the values of the nodes pointed to by `temp1` and `temp2`.
     *    - Append the smaller value to the merged list and move the corresponding pointer forward.
     * 4. If one of the lists is exhausted, append the remaining nodes of the other list to the merged list.
     * 5. Return the merged list starting from `dummy.bottom`.
     * 
     * Time Complexity:
     * - O(n + m), where `n` and `m` are the lengths of the two vertical lists.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param list1 The head of the first sorted vertical list.
     * @param list2 The head of the second sorted vertical list.
     * @return The head of the merged sorted vertical list.
     */
    public static Node mergeTwoLists(Node list1, Node list2) {
        Node temp1 = list1; // Pointer for the first list
        Node temp2 = list2; // Pointer for the second list
        Node dummy = new Node(-1); // Dummy node to simplify the merging process
        Node out = dummy; // Pointer to build the merged list

        while (temp1 != null && temp2 != null) {
            if (temp1.data < temp2.data) { // If the value in list1 is smaller
                out.bottom = temp1; // Append the node from list1
                temp1 = temp1.bottom; // Move the pointer in list1 forward
            } else { // If the value in list2 is smaller or equal
                out.bottom = temp2; // Append the node from list2
                temp2 = temp2.bottom; // Move the pointer in list2 forward
            }
            out = out.bottom; // Move the output pointer forward
        }

        // Append the remaining nodes of list1, if any
        if (temp1 != null) {
            out.bottom = temp1;
        }

        // Append the remaining nodes of list2, if any
        if (temp2 != null) {
            out.bottom = temp2;
        }

        return dummy.bottom; // Return the merged list starting from the first real node
    }
}