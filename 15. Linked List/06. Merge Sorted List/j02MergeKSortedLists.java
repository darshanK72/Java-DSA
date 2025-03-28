/**
 * Problem Statement:
 * 
 *     Given an array of `k` sorted singly linked lists, merge them into a single sorted linked list.
 * 
 * Input:
 *     - An array of `k` sorted singly linked lists, where each node contains an integer value.
 * 
 * Output:
 *     - The head of the merged sorted linked list.
 * 
 * Example:
 *     Input:
 *         lists = [[1, 4, 5], [1, 3, 4], [2, 6]]
 *     Output:
 *         [1, 1, 2, 3, 4, 4, 5, 6]
 * 
 *     Explanation:
 *         The three lists are merged into a single sorted list: [1, 1, 2, 3, 4, 4, 5, 6].
 */

public class j02MergeKSortedLists {

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
     * Approach 1: Merge One by One
     * 
     * Intuition:
     * - Start with the first list and iteratively merge it with the next list until all lists are merged.
     * - This approach is simple but not the most efficient.
     * 
     * Explanation:
     * 1. Initialize the output list as the first list in the array.
     * 2. Iterate through the remaining lists and merge each one with the output list using the `mergeTwoLists` method.
     * 3. Return the final merged list.
     * 
     * Time Complexity:
     * - O(k * n), where `k` is the number of lists and `n` is the average number of nodes in each list.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param lists An array of sorted linked lists.
     * @return The head of the merged sorted linked list.
     */
    public Node mergeKListsOneByOne(Node[] lists) {
        Node out = lists[0];
        for (int i = 1; i < lists.length; i++) {
            out = mergeTwoLists(out, lists[i]);
        }
        return out;
    }

    /**
     * Approach 2: Divide and Conquer
     * 
     * Intuition:
     * - Use a divide-and-conquer strategy to merge the lists in pairs.
     * - This reduces the number of merge operations and improves efficiency.
     * 
     * Explanation:
     * 1. Divide the array of lists into two halves recursively until each half contains only one list.
     * 2. Merge the two halves using the `mergeTwoLists` method.
     * 3. Return the merged list.
     * 
     * Time Complexity:
     * - O(n * log(k)), where `k` is the number of lists and `n` is the total number of nodes across all lists.
     * 
     * Space Complexity:
     * - O(log(k)), due to the recursive stack used in the divide-and-conquer approach.
     * 
     * @param lists An array of sorted linked lists.
     * @param left The starting index of the current range of lists.
     * @param right The ending index of the current range of lists.
     * @return The head of the merged sorted linked list.
     */
    public Node mergeDivideAndConqueror(Node[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        Node n1 = mergeDivideAndConqueror(lists, left, mid);
        Node n2 = mergeDivideAndConqueror(lists, mid + 1, right);

        return mergeTwoLists(n1, n2);
    }

    /**
     * Helper Method: Merge Two Sorted Lists
     * 
     * Intuition:
     * - Use two pointers to traverse the two lists and compare their values.
     * - Append the smaller value to the merged list and move the corresponding pointer forward.
     * 
     * Explanation:
     * 1. Initialize two pointers, `temp1` and `temp2`, to the heads of the two lists.
     * 2. Create a dummy node to serve as the starting point of the merged list.
     * 3. Traverse both lists while neither pointer is null:
     *    - Compare the values of the nodes pointed to by `temp1` and `temp2`.
     *    - Append the smaller value to the merged list and move the corresponding pointer forward.
     * 4. If one of the lists is exhausted, append the remaining nodes of the other list to the merged list.
     * 5. Return the merged list starting from `dummy.next`.
     * 
     * Time Complexity:
     * - O(n + m), where `n` and `m` are the lengths of the two lists.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used apart from a few pointers.
     * 
     * @param list1 The head of the first sorted linked list.
     * @param list2 The head of the second sorted linked list.
     * @return The head of the merged sorted linked list.
     */
    public Node mergeTwoLists(Node list1, Node list2) {
        Node temp1 = list1;
        Node temp2 = list2;
        Node dummy = new Node(-1);
        Node out = dummy;

        while (temp1 != null && temp2 != null) {
            if (temp1.data < temp2.data) {
                out.next = temp1;
                temp1 = temp1.next;
            } else {
                out.next = temp2;
                temp2 = temp2.next;
            }
            out = out.next;
        }

        if (temp1 != null) {
            out.next = temp1;
        }

        if (temp2 != null) {
            out.next = temp2;
        }

        return dummy.next;
    }
}