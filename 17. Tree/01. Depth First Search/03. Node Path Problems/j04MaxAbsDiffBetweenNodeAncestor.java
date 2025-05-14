/**
 * Problem Statement:
 *     LeetCode 1026. Maximum Absolute Difference Between Node and Ancestor
 * 
 *     Given the root of a binary tree, find the maximum value V for which there exist 
 *     different nodes A and B where V = |A.val - B.val| and A is an ancestor of B.
 *     A node A is an ancestor of B if either:
 *     - Any child of A is equal to B
 *     - Any child of A is an ancestor of B
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have up to 10^4 nodes
 *     - Node values range from 0 to 10^5
 * 
 * Output:
 *     - Maximum absolute difference between any node and its ancestor
 * 
 * Example:
 *     Input: 
 *           8
 *          / \
 *         3   10
 *        / \    \
 *       1   6    14
 *          / \
 *         4   7
 *     Output: 7
 *     Explanation: Maximum difference is |8-1| = 7, between root and node 1
 */

public class j04MaxAbsDiffBetweenNodeAncestor {

    /**
     * TreeNode class represents a node in binary tree
     * Contains value and references to left and right children
     */
    static public class TreeNode {
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
        // Test Case 1: Basic tree from example
        TreeNode root1 = new TreeNode(8);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(10);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(6);
        root1.right.right = new TreeNode(14);
        root1.left.right.left = new TreeNode(4);
        root1.left.right.right = new TreeNode(7);
        System.out.println("Test Case 1: " + maxAncestorDiff(root1));  // Expected: 7

        // Test Case 2: Linear tree
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        root2.right.right = new TreeNode(3);
        System.out.println("Test Case 2: " + maxAncestorDiff(root2));  // Expected: 2

        // Test Case 3: Single node
        TreeNode root3 = new TreeNode(1);
        System.out.println("Test Case 3: " + maxAncestorDiff(root3));  // Expected: 0
    }

    /**
     * Approach: DFS with Min-Max Tracking
     * 
     * Intuition:
     * - Track minimum and maximum values seen along current path
     * - At each leaf, maximum difference will be max-min
     * - Update min/max at each node while traversing
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - n is number of nodes in tree
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * - Worst case O(n) for skewed tree
     * - Best case O(log n) for balanced tree
     */
    public static int maxAncestorDiff(TreeNode root) {
        if (root == null)
            return 0;                                   // Empty tree
        return maxDiff(root, root.val, root.val);      // Start with root value
    }

    /**
     * Helper method to find maximum difference using DFS
     * Tracks minimum and maximum values seen along current path
     */
    public static int maxDiff(TreeNode root, int max, int min) {
        if (root == null)
            return max - min;                          // Leaf reached, return difference
            
        max = Math.max(root.val, max);                // Update maximum
        min = Math.min(root.val, min);                // Update minimum
        
        int left = maxDiff(root.left, max, min);      // Process left subtree
        int right = maxDiff(root.right, max, min);    // Process right subtree
        
        return Math.max(left, right);                 // Return larger difference
    }
}
