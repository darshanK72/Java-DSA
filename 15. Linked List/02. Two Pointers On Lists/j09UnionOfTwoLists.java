/**
 * Problem Statement:
 * 
 *      Given two singly linked lists, return a new linked list representing the union of the two lists.
 *      The union should contain all unique elements from both lists.
 * 
 * Constraints:
 *       - The original lists should remain unchanged.
 *      - The output can be either sorted or unsorted, based on the method used.
 * 
 * Input:
 *      - Two singly linked lists (head1, head2).
 * 
 * Output:
 *      - A new linked list containing the unique elements from both lists.
 * 
 * Example 1 (Unsorted Union):
 * 
 *     Input:
 *     List 1: 1 -> 2 -> 3
 *     List 2: 3 -> 4 -> 5
 * 
 *     Output:
 *     1 -> 2 -> 3 -> 4 -> 5
 * 
 * Example 2 (Sorted Union):
 * 
 *     Input:
 *     List 1: 3 -> 1 -> 2
 *     List 2: 5 -> 4 -> 3
 * 
 *     Output:
 *     1 -> 2 -> 3 -> 4 -> 5
 */

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class j09UnionOfTwoLists {

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Approach 1: HashSet for Unsorted Union
     * 
     * Intuition:
     * - Use a HashSet to store unique elements from both lists.
     * - Construct a new linked list based on these elements.
     * - Order is preserved based on their first appearance.
     * 
     * Explanation:
     * 1. Create an empty HashSet to store seen values.
     * 2. Iterate through the first list:
     *    - If the value is not in the set, add it to the new list.
     *    - Store the value in the set.
     * 3. Repeat the same for the second list.
     * 4. Return the new linked list containing all unique values.
     * 
     * Time Complexity:
     * - O(N + M) → Each node is processed once.
     * 
     * Space Complexity:
     * - O(N + M) → Stores unique elements in a HashSet.
     * 
     * @param head1 First linked list.
     * @param head2 Second linked list.
     * @return A new linked list with the union of both lists.
     */
    public static Node findUnionUnsortedResult(Node head1, Node head2) {
        HashSet<Integer> set = new HashSet<>();
        Node outHead = new Node(-1); // Dummy node for output list
        Node tempOut = outHead;

        Node temp = head1;
        while (temp != null) {
            if (!set.contains(temp.data)) {
                tempOut.next = new Node(temp.data);
                tempOut = tempOut.next;
            }
            set.add(temp.data);
            temp = temp.next;
        }

        temp = head2;
        while (temp != null) {
            if (!set.contains(temp.data)) {
                tempOut.next = new Node(temp.data);
                tempOut = tempOut.next;
            }
            set.add(temp.data);
            temp = temp.next;
        }

        return outHead.next; // Return the union list (skip dummy node)
    }

    /**
     * Approach 2: TreeSet for Sorted Union
     * 
     * Intuition:
     * - Use a TreeSet instead of a HashSet to store unique elements in sorted order.
     * - Construct a new linked list from the sorted set.
     * 
     * Explanation:
     * 1. Use a TreeSet to automatically store unique elements in sorted order.
     * 2. Iterate through both linked lists and insert all elements into the set.
     * 3. Create a new linked list from the sorted set.
     * 
     * Time Complexity:
     * - O((N + M) log(N + M)) → Due to TreeSet insertion (logN complexity per element).
     * 
     * Space Complexity:
     * - O(N + M) → Stores unique elements in a TreeSet.
     * 
     * @param head1 First sorted linked list.
     * @param head2 Second sorted linked list.
     * @return A new linked list with sorted unique elements.
     */
    public static Node findUnionSortedResult(Node head1, Node head2) {
        Set<Integer> set = new TreeSet<>();
        Node outHead = new Node(-1); // Dummy node for output list

        Node temp = head1;
        while (temp != null) {
            set.add(temp.data);
            temp = temp.next;
        }

        temp = head2;
        while (temp != null) {
            set.add(temp.data);
            temp = temp.next;
        }

        temp = outHead;
        for (int i : set) {
            temp.next = new Node(i);
            temp = temp.next;
        }

        return outHead.next; // Return the union list (skip dummy node)
    }
}
