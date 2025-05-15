/**
 * Problem Statement:
 *     LeetCode 572. Subtree of Another Tree
 * 
 *     Given the roots of two binary trees root and subRoot, return true if there
 *     is a subtree of root with the same structure and node values of subRoot
 *     and false otherwise.
 * 
 * Input:
 *     - Root node of main tree
 *     - Root node of potential subtree
 * 
 * Output:
 *     - Boolean indicating if subtree exists
 * 
 * Example:
 *     Input: 
 *     Tree:      3         SubTree:    4
 *               / \                    / \
 *              4   5                 1   2
 *             / \
 *            1   2
 *     
 *     Output: true
 *     Explanation: Tree contains subtree starting at left child
 */

public class j07SubtreeOfAnotherTree {

    /**
     * TreeNode class represents a node in binary tree
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
        // Test Case 1: Basic example
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(2);
        
        TreeNode subRoot1 = new TreeNode(4);
        subRoot1.left = new TreeNode(1);
        subRoot1.right = new TreeNode(2);
        
        System.out.println("Test Case 1: " + isSubtree(root1, subRoot1));  // Expected: true

        // Test Case 2: Similar but different structure
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(5);
        root2.left.left = new TreeNode(1);
        
        TreeNode subRoot2 = new TreeNode(4);
        subRoot2.right = new TreeNode(1);
        
        System.out.println("Test Case 2: " + isSubtree(root2, subRoot2));  // Expected: false

        // Test Case 3: Empty subtree
        TreeNode root3 = new TreeNode(1);
        System.out.println("Test Case 3: " + isSubtree(root3, null));  // Expected: true

        // Test Case 4: Empty main tree
        TreeNode subRoot4 = new TreeNode(1);
        System.out.println("Test Case 4: " + isSubtree(null, subRoot4));  // Expected: false
    }

    /**
     * Approach: DFS with Tree Matching
     * 
     * Intuition:
     * - For each node in main tree:
     *   1. Check if current subtree matches target
     *   2. If not, recursively check left and right subtrees
     * - Use separate method to check exact tree matching
     * 
     * Time Complexity: O(m * n)
     * - m is number of nodes in main tree
     * - n is number of nodes in subtree
     * - Need to check subtree matching at each node
     * 
     * Space Complexity: O(h)
     * - h is height of main tree (recursion stack)
     */
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if(subRoot == null) 
            return true;                    // Empty subtree is always contained
        if(root == null) 
            return false;                   // Can't find subtree in empty tree
            
        return isSameTree(root, subRoot)    // Check current node
            || isSubtree(root.left, subRoot) // Check left subtree
            || isSubtree(root.right, subRoot); // Check right subtree
    }

    /**
     * Helper method to check if two trees are identical
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;                    // Both empty trees
        if (p == null || q == null)
            return false;                   // One empty, one not
        if (p.val != q.val)
            return false;                   // Different values

        return isSameTree(p.left, q.left)   // Check subtrees
            && isSameTree(p.right, q.right);
    }
}
