/**
 * Problem Statement:
 * 
 *      Given the heads of two singly linked lists, return a new linked list that represents their intersection.
 *      The intersection is defined by nodes that have the same value in both lists.
 *      The order of the intersection nodes in the output list should follow their appearance in the first list.
 * 
 * Constraints:
 *      - The input lists may have different lengths.
 *      - Nodes are compared based on their values, not their memory references.
 *      - The resulting list should not contain duplicates.
 *      - The original lists should remain unchanged.
 * 
 * Input:
 *      - Two singly linked lists (head1, head2).
 * 
 * Output:
 *      - A new linked list containing the intersection of the two lists.
 * 
 * Example:
 * 
 *     Input:
 *     List 1: 1 -> 2 -> 3 -> 4 -> 5
 *     List 2:       2 -> 4 -> 6
 * 
 *     Output:
 *     2 -> 4
 * 
 *     Explanation:
 *     - Nodes with values 2 and 4 appear in both lists, so they are included in the result.
 */

import java.util.HashSet;

public class j07IntersectionOfTwoLists {

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Approach: Using a HashSet to Store Values of the Second List
     * 
     * Intuition:
     * - Since we need to find common values, we can use a HashSet to store elements from one list.
     * - Then, we iterate through the other list and check if its elements exist in the HashSet.
     * - If a match is found, we add that value to the output linked list.
     * 
     * Explanation:
     * 1. Traverse the second linked list and store all its values in a HashSet.
     * 2. Traverse the first linked list and check which values exist in the HashSet.
     * 3. Construct a new linked list with the intersection values in the order they appear in the first list.
     * 
     * Time Complexity:
     * - O(N + M) → N for adding elements from head2 to HashSet, M for checking elements in head1.
     * 
     * Space Complexity:
     * - O(M) → Storing elements of head2 in a HashSet.
     * 
     * @param head1 The head of the first linked list.
     * @param head2 The head of the second linked list.
     * @return A new linked list containing intersection elements.
     */
    public static Node findIntersection(Node head1, Node head2) {
        HashSet<Integer> set = new HashSet<>();
        Node temp = head2;

        // Store all values from the second list in a HashSet
        while (temp != null) {
            set.add(temp.data);
            temp = temp.next;
        }

        // Create a dummy node for the result list
        Node outHead = new Node(-1);
        Node tempOut = outHead;
        temp = head1;

        // Check which values from the first list exist in the HashSet
        while (temp != null) {
            if (set.contains(temp.data)) {
                tempOut.next = new Node(temp.data);
                tempOut = tempOut.next;
            }
            temp = temp.next;
        }

        return outHead.next; // Return the intersection list (skip dummy node)
    }
}
