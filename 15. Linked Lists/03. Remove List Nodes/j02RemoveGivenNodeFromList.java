/*-
 * Problem Statement:
 * 
 *      Given a non-tail node in a singly linked list, delete it in-place.
 * 
 * Constraints:
 *      - The given node is not the tail node.
 *      - No direct access to the head of the linked list.
 * 
 * Input:
 *      - A reference to the node that needs to be deleted.
 * 
 * Output:
 *      - The given node is removed in-place without modifying other parts of the list.
 * 
 * Example:
 * 
 *     Input:
 *     List: 1 -> 2 -> 3 -> 4 -> 5
 *     Node to delete: 3
 * 
 *     Output:
 *     1 -> 2 -> 4 -> 5
 */

public class j02RemoveGivenNodeFromList {
    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /*-
     * Approach: Copy & Bypass Technique
     * 
     * Intuition:
     * - Normally, to delete a node, we need access to the previous node.
     * - Since we do not have access to the head, we cannot find the previous node.
     * - Instead, we copy the data of the next node into the current node.
     * - Then, we bypass the next node by changing the current node's `next` pointer.
     * 
     * Explanation:
     * 1. Copy the `data` of the next node into the given node.
     * 2. Update `node.next` to skip the next node (`node.next = node.next.next`).
     * 3. This effectively removes the given node by replacing it with its next node.
     * 
     * Edge Cases Considered:
     * - The problem guarantees the node is not the last node, so no need to handle tail deletion.
     * - The list must have at least two nodes for this to work.
     * 
     * Time Complexity:
     * - O(1) → The deletion operation is done in constant time.
     * 
     * Space Complexity:
     * - O(1) → No extra space is used.
     * 
     * @param node The node to be removed from the list.
     */
    public void deleteNode(Node node) {
        if (node == null || node.next == null) {
            throw new IllegalArgumentException("Node to delete cannot be the last node.");
        }

        node.data = node.next.data; // Copy data from next node
        node.next = node.next.next; // Bypass next node
    }
}
