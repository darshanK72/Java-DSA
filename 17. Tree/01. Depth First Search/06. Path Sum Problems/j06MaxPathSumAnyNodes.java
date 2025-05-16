/**
 * Problem Statement:
 *     LeetCode 124. Binary Tree Maximum Path Sum
 * 
 *     Given a binary tree, find the maximum path sum. The path may start and end at any node
 *     in the tree. A path is a sequence of nodes where each pair of adjacent nodes has a 
 *     direct edge connecting them.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 *     - Node values can be negative
 * 
 * Output:
 *     - Maximum path sum possible between any two nodes
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *     
 *     Output: 6
 *     Explanation: 
 *     Optimal path is 2 -> 1 -> 3 with sum = 6
 *     Other paths: 2 -> 1 = 3, 1 -> 3 = 4, single nodes = 1, 2, or 3
 */

public class j06MaxPathSumAnyNodes {

    /**
     * TreeNode class represents a node in binary tree
     * Contains value and references to left and right children
     */
    public static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic binary tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        System.out.println("Test Case 1: " + maxPathSum(root1));  // Expected: 6

        // Test Case 2: Tree with negative values
        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);
        System.out.println("Test Case 2: " + maxPathSum(root2));  // Expected: 42

        // Test Case 3: Single node
        TreeNode root3 = new TreeNode(-3);
        System.out.println("Test Case 3: " + maxPathSum(root3));  // Expected: -3
    }

    /**
     * Main method to find maximum path sum
     * 
     * Approach:
     * - Use DFS to traverse tree and track maximum path sum
     * - At each node, consider it as highest point of path
     * - Handle negative values by taking maximum with 0
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * 
     * @param root Root node of binary tree
     * @return Maximum path sum possible in tree
     */
    public static int maxPathSum(TreeNode root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;      // Initialize with smallest possible value
        findMaxPath(root, max);
        return max[0];
    }

    /**
     * Helper method to calculate maximum path sum using DFS
     * 
     * Algorithm:
     * 1. Handle null node (return 0)
     * 2. Get maximum paths from left and right subtrees
     *    - Take max with 0 to handle negative values
     * 3. Update global maximum considering current node as peak
     * 4. Return maximum single path through current node
     * 
     * @param root Current node being processed
     * @param max Array to store maximum path sum found
     * @return Maximum sum of path ending at current node
     */
    private static int findMaxPath(TreeNode root, int[] max) {
        if (root == null)
            return 0;                     // Base case
            
        // Get maximum paths from children (ignore negative paths)
        int left = Math.max(0, findMaxPath(root.left, max));
        int right = Math.max(0, findMaxPath(root.right, max));
        
        // Update maximum path sum through current node
        max[0] = Math.max(max[0], left + right + root.val);
        
        // Return maximum path ending at current node
        return Math.max(left, right) + root.val;
    }
}
