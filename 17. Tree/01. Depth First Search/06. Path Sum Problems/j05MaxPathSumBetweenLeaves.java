/**
 * Problem Statement:
 *     Maximum Path Sum between Two Leaf Nodes (GeeksForGeeks)
 * 
 *     Given a binary tree, find the maximum path sum between two leaf nodes.
 *     A path between two leaves is a sequence of nodes connecting one leaf to another,
 *     where no node appears more than once.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 *     - Node values can be negative
 * 
 * Output:
 *     - Maximum sum path between any two leaves
 *     - Return -1 if no valid path exists
 * 
 * Example:
 *     Input: 
 *           -15
 *          /   \
 *         5     6
 *        / \   / \
 *      -8   1 3   4
 *     
 *     Output: 6
 *     Explanation: 
 *     Path: -8 -> 5 -> -15 -> 6 -> 4
 *     Sum = -8 + 5 + (-15) + 6 + 4 = -8
 */

public class j05MaxPathSumBetweenLeaves {

    /**
     * TreeNode class represents a node in binary tree
     * Contains data and references to left and right children
     */
    public static class TreeNode {
        int data;
        TreeNode left, right;

        TreeNode(int x) {
            data = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree with multiple paths
        TreeNode root1 = new TreeNode(-15);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(-8);
        root1.left.right = new TreeNode(1);
        root1.right.left = new TreeNode(3);
        root1.right.right = new TreeNode(4);
        System.out.println("Test Case 1: " + findMaxSumPath(root1));  // Expected: 6

        // Test Case 2: Single path
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        System.out.println("Test Case 2: " + findMaxSumPath(root2));  // Expected: 6

        // Test Case 3: Invalid tree (single leaf)
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        System.out.println("Test Case 3: " + findMaxSumPath(root3));  // Expected: -1
    }

    /**
     * Main method to find maximum path sum between leaves
     * 
     * Approach:
     * - Use DFS to traverse tree and maintain maximum path sum
     * - For each node, consider it as root of potential path
     * - Update maximum when valid path through current node is found
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * 
     * @param root Root node of binary tree
     * @return Maximum path sum between leaves, -1 if no valid path
     */
    public static long findMaxSumPath(TreeNode root) {
        long[] max = new long[1];
        max[0] = -1;                    // Initialize with invalid path marker
        pathSum(root, max);
        return max[0];
    }

    /**
     * Helper method to calculate path sums using DFS
     * 
     * Algorithm:
     * 1. Handle base cases:
     *    - Null node returns 0
     *    - Leaf node returns its value
     * 2. Get left and right subtree sums
     * 3. If both children exist:
     *    - Update maximum if valid path through current node
     *    - Return maximum path to parent
     * 4. Return path sum through existing child
     * 
     * @param root Current node being processed
     * @param max Array to store maximum path sum found
     * @return Sum of maximum path from current node to a leaf
     */
    public static long pathSum(TreeNode root, long[] max) {
        if (root == null)
            return 0;                    // Base case: empty node
            
        if (root.left == null && root.right == null)
            return root.data;            // Base case: leaf node
            
        long left = pathSum(root.left, max);    // Get left subtree path
        long right = pathSum(root.right, max);  // Get right subtree path
        
        if (root.left != null && root.right != null) {
            max[0] = Math.max(max[0], left + right + root.data);  // Update maximum
            return Math.max(left, right) + root.data;             // Return best path
        }
        
        // Return path through existing child
        return (root.left != null) ? (left + root.data) : (right + root.data);
    }
}
