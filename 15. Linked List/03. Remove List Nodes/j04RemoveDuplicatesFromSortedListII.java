/*-
 * Problem Statement:
 * 
 *      Given a sorted singly linked list, remove all nodes that have duplicate values,
 *      leaving only distinct values in the linked list.
 * 
 * Constraints:
 *      - The list is sorted in non-decreasing order.
 *      - Nodes with duplicate values should be completely removed.
 *      - The resulting linked list should maintain the sorted order.
 * 
 * Input:
 *      - A reference to the head of the sorted linked list.
 * 
 * Output:
 *      - The head of the modified linked list with only distinct elements.
 * 
 * Example:
 * 
 *     Input:
 *     1 -> 2 -> 2 -> 3 -> 3 -> 4 -> 5 -> 5
 * 
 *     Output:
 *     1 -> 4
 */

public class j04RemoveDuplicatesFromSortedListII {

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /*-
     * Approach: Two-Pointer Method (Iterative)
     * 
     * Intuition:
     * - The list is sorted, so all duplicate values appear consecutively.
     * - We need to remove all nodes that have duplicates, not just the extra ones.
     * - Use a dummy node (`prev`) to track the new head and help with node removals.
     * - If a node has duplicates, we skip all occurrences of that value.
     * 
     * Explanation:
     * 1. Create a dummy node (`prev`) and point it to `head`. This helps handle edge cases.
     * 2. Traverse the list using a `curr` pointer.
     * 3. If `curr.data == curr.next.data`, store `curr.data` and skip all occurrences.
     * 4. If no duplicates are found, move `prev` forward.
     * 5. Return `head.next` since `head` was assigned to `prev` initially.
     * 
     * Edge Cases Considered:
     * - Empty list (head is null).
     * - List with only one node (no duplicates possible).
     * - All elements are unique.
     * - List contains only duplicates (resulting in an empty list).
     * 
     * Time Complexity:
     * - O(N) → Each node is visited once.
     * 
     * Space Complexity:
     * - O(1) → No extra memory used.
     * 
     * @param head The head of the sorted linked list.
     * @return The head of the modified linked list with duplicates removed.
     */
    public Node deleteDuplicates(Node head) {
        Node prev = new Node(Integer.MIN_VALUE); // Dummy node
        prev.next = head;
        Node curr = head;
        head = prev; // Assign head to dummy node

        while (curr != null) {
            // If duplicates exist, remove all occurrences of that value
            if (curr.next != null && curr.data == curr.next.data) {
                int duplicateValue = curr.data;
                while (curr != null && curr.data == duplicateValue) {
                    curr = curr.next; // Skip all nodes with duplicate value
                }
                prev.next = curr; // Link previous node to next unique node
            } else {
                prev = prev.next; // Move prev to next node
                curr = curr.next; // Move curr to next node
            }
        }
        return head.next; // Return modified list
    }
}
