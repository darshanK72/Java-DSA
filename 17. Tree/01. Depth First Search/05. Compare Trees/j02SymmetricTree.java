/**
 * Problem Statement:
 *     LeetCode 101. Symmetric Tree
 * 
 *     Given the root of a binary tree, check whether it is a mirror of itself
 *     (i.e., symmetric around its center).
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 *     - Node values can be any integer
 * 
 * Output:
 *     - Boolean indicating if tree is symmetric
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   2
 *        / \ / \
 *       3  4 4  3
 *     
 *     Output: true
 *     Explanation: Tree is mirror symmetric around center
 */

public class j02SymmetricTree {

    /**
     * TreeNode class represents a node in binary tree
     * Contains value and references to left and right children
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Symmetric tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(3);
        System.out.println("Test Case 1 (Symmetric Tree): " + 
            isSymmetric(root1));  // Expected: true

        // Test Case 2: Non-symmetric values
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.right = new TreeNode(3);
        root2.right.right = new TreeNode(4);
        System.out.println("Test Case 2 (Non-symmetric Values): " + 
            isSymmetric(root2));  // Expected: false

        // Test Case 3: Single node
        TreeNode root3 = new TreeNode(1);
        System.out.println("Test Case 3 (Single Node): " + 
            isSymmetric(root3));  // Expected: true

        // Test Case 4: Empty tree
        System.out.println("Test Case 4 (Empty Tree): " + 
            isSymmetric(null));   // Expected: true
    }

    /**
     * Approach: Recursive Mirror Comparison
     * 
     * Intuition:
     * - A tree is symmetric if left subtree mirrors right subtree
     * - For mirroring, compare:
     *   * Left's left with Right's right
     *   * Left's right with Right's left
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;                            // Empty tree is symmetric
        return isMirror(root.left, root.right);    // Check if subtrees mirror
    }

    /**
     * Helper method to check if trees are mirrors of each other
     * Compares corresponding nodes in mirror positions
     */
    public static boolean isMirror(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;                           // Both empty - symmetric
        if (p == null || q == null)
            return false;                          // One empty - not symmetric
        if (p.val != q.val)
            return false;                          // Different values - not symmetric
            
        return isMirror(p.left, q.right)          // Check mirror positions
            && isMirror(p.right, q.left);
    }
}
