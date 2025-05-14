/**
 * Problem Statement:
 *     LeetCode 1367. Linked List in Binary Tree
 * 
 *     Given a binary tree root and a linked list with head as the first node,
 *     return true if all the elements in the linked list starting from the head
 *     correspond to some downward path connected in the binary tree otherwise
 *     return false.
 * 
 * Input:
 *     - head: First node of linked list
 *     - root: Root node of binary tree
 *     - 1 <= Node.val <= 100
 * 
 * Output:
 *     - Boolean indicating if linked list exists as path in tree
 * 
 * Example:
 *     Input: 
 *     List: 4->2->8
 *     Tree:
 *           1
 *          / \
 *         4   4
 *        / \
 *       2   2
 *      /     \
 *     8       8
 *     Output: true
 *     Path: 4->2->8 exists in left subtree
 */

public class j01LinkedListInBinaryTree {

    /**
     * ListNode represents a node in linked list
     * Contains value and reference to next node
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * TreeNode represents a node in binary tree
     * Contains value and references to left and right children
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic path exists
        ListNode list1 = new ListNode(4);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(8);

        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(4);
        tree1.left.left = new TreeNode(2);
        tree1.left.left.left = new TreeNode(8);
        
        System.out.println("Test Case 1 (Path exists): " + 
            isSubPath(list1, tree1));  // Expected: true

        // Test Case 2: Path doesn't exist
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(2);

        TreeNode tree2 = new TreeNode(1);
        tree2.right = new TreeNode(2);
        
        System.out.println("Test Case 2 (Path doesn't exist): " + 
            isSubPath(list2, tree2));  // Expected: false
    }

    /**
     * Approach: DFS with Two-Level Search
     * 
     * Intuition:
     * - For each tree node:
     *   1. Try to find complete linked list starting from that node
     *   2. If not found, recursively check left and right children
     * - For each attempt to find list:
     *   * Match current values
     *   * Recursively match remaining list with child nodes
     * 
     * Time Complexity: O(N * min(L,H))
     * - N: number of tree nodes
     * - L: length of linked list
     * - H: height of tree
     * 
     * Space Complexity: O(H)
     * - H: height of tree (recursion stack)
     */
    public static boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null)
            return true;                    // Empty list always exists
        if (root == null)
            return false;                   // Can't find list in empty tree
        
        // Try three possibilities:
        return findLinkedList(head, root)   // 1. List starts at current node
            || isSubPath(head, root.left)   // 2. List starts in left subtree
            || isSubPath(head, root.right); // 3. List starts in right subtree
    }

    /**
     * Helper method to find linked list starting from current tree node
     * Returns true if remaining list matches a path from current node
     */
    public static boolean findLinkedList(ListNode head, TreeNode root) {
        if (head == null)
            return true;                    // Found complete list
        if (root == null)
            return false;                   // Reached end of path
        if (root.val != head.val)
            return false;                   // Values don't match
            
        // Continue matching in either child
        return findLinkedList(head.next, root.left)
            || findLinkedList(head.next, root.right);
    }
}
