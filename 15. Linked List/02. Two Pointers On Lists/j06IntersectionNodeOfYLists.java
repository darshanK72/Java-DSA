/**
 * Problem Statement:
 * 
 *      Given the heads of two singly linked lists, determine the node at which the two lists intersect.
 *      If the two linked lists have no intersection, return null.
 * 
 * Constraints:
 *      - The lists may have different lengths.
 *      - The intersection is defined by reference, not value.
 *      - The linked lists retain their original structure after the function is executed.
 *      - The problem guarantees that the lists either intersect at some node or do not intersect at all.
 * 
 * Input:
 *      - Two singly linked lists.
 * 
 * Output:
 *      - The node where the two linked lists intersect, or null if they do not intersect.
 * 
 * Example:
 * 
 *     Input:
 *     List A: 1 -> 2 -> 3 -> 4 -> 5
 *     List B:       9 -> 4 -> 5
 * 
 *     Output:
 *     Node with value 4
 * 
 *     Explanation:
 *     - The two lists merge at node 4, which is returned as the intersection.
 */

public class j06IntersectionNodeOfYLists {

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * Approach: Align the Start of Both Lists and Traverse Together
     * 
     * Intuition:
     * - If two linked lists intersect, they will have a common suffix.
     * - The simplest way to locate the intersection is to first align both lists to the same length.
     * - We do this by:
     *   1. Computing the length of both lists.
     *   2. Moving the longer list’s pointer forward until both lists are the same distance from the end.
     *   3. Traversing both lists together until they meet at the intersection node.
     * 
     * Explanation:
     * 1. Calculate the sizes of both linked lists.
     * 2. Identify which list is longer and move its pointer forward by the difference in sizes.
     * 3. Traverse both lists simultaneously. If a common node is found, return it.
     * 4. If no intersection is found, return `null`.
     * 
     * Time Complexity:
     * - O(N + M) → We traverse both lists once to compute lengths and then again for alignment and checking.
     * 
     * Space Complexity:
     * - O(1) → No additional data structures are used.
     * 
     * @param headA The head of the first linked list.
     * @param headB The head of the second linked list.
     * @return The intersection node or null if no intersection exists.
     */
    public Node getIntersectionNode(Node headA, Node headB) {
        int sizeA = sizeOfLL(headA);
        int sizeB = sizeOfLL(headB);
        Node tempA = headA;
        Node tempB = headB;

        // Align the start positions of both lists
        for (int i = 0; i < Math.abs(sizeA - sizeB); i++) {
            if (sizeA > sizeB) {
                tempA = tempA.next;
            } else {
                tempB = tempB.next;
            }
        }

        // Traverse both lists together until intersection is found
        while (tempA != tempB) {
            tempA = tempA.next;
            tempB = tempB.next;
        }

        return tempA; // This will be the intersection node, or null if no intersection exists
    }

    /**
     * Utility Method: Calculate the Length of a Linked List
     * 
     * Explanation:
     * - This helper function iterates through the linked list to count the number of nodes.
     * - It is used to determine the difference in length between the two lists.
     * 
     * Time Complexity:
     * - O(N) → We traverse the list once.
     * 
     * Space Complexity:
     * - O(1) → No extra space is used.
     * 
     * @param head The head of the linked list.
     * @return The size of the linked list.
     */
    public static int sizeOfLL(Node head) {
        int size = 0;
        Node temp = head;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }
}
