/**
 * Problem Statement:
 *     LeetCode 113. Path Sum II
 * 
 *     Given the root of a binary tree and an integer targetSum, return all root-to-leaf paths 
 *     where the sum of the node values in the path equals targetSum. Each path should be 
 *     returned as a list of the node values, not node references.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Target sum (integer)
 *     - Node values can be negative
 * 
 * Output:
 *     - List of all paths (each path is a list of node values)
 * 
 * Example:
 *     Input: 
 *           5
 *          / \
 *         4   8
 *        /   / \
 *       11  13  4
 *      /  \    / \
 *     7    2  5   1
 *     targetSum = 22
 *     
 *     Output: [[5,4,11,2], [5,8,4,5]]
 *     Explanation: There are two paths whose sum equals targetSum:
 *     1. 5 -> 4 -> 11 -> 2
 *     2. 5 -> 8 -> 4 -> 5
 */

import java.util.ArrayList;
import java.util.List;

public class j02PathSumII {

    /**
     * TreeNode class represents a node in binary tree
     */
    public static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Multiple valid paths
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.right.right.left = new TreeNode(5);
        root1.right.right.right = new TreeNode(1);

        System.out.println("Test Case 1 (Target=22):");
        System.out.println(pathSum(root1, 22)); // Expected: [[5,4,11,2], [5,8,4,5]]

        // Test Case 2: No valid paths
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);

        System.out.println("\nTest Case 2 (Target=7):");
        System.out.println(pathSum(root2, 7)); // Expected: []

        // Test Case 3: Negative values
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(-2);
        root3.right = new TreeNode(3);

        System.out.println("\nTest Case 3 (Target=-1):");
        System.out.println(pathSum(root3, -1)); // Expected: [[1,-2]]
    }

    /**
     * Approach: DFS with Path Building
     * 
     * Intuition:
     * - Use DFS to explore all paths from root to leaf
     * - Maintain current path and subtract node values from target
     * - When reaching a leaf, check if remaining target equals leaf value
     * - Use backtracking to try all possible paths
     * 
     * Time Complexity: O(n²)
     * - Visit each node once O(n)
     * - Creating new path list for each valid path O(n)
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * - curr list stores at most h nodes
     */
    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> out = new ArrayList<>();
        getPathsWithTargetSum(root, targetSum, new ArrayList<>(), out);
        return out;
    }

    /**
     * Helper method for DFS path finding
     * @param root: Current node being processed
     * @param targetSum: Remaining sum to find
     * @param curr: Current path being built
     * @param out: List to store all valid paths
     */
    public static void getPathsWithTargetSum(TreeNode root, int targetSum,
            ArrayList<Integer> curr, List<List<Integer>> out) {
        if (root == null)
            return; // Base case

        // Check leaf node
        curr.add(root.val);
        if (root.left == null && root.right == null) {
            if (root.val == targetSum) { // Found valid path
                out.add(new ArrayList<>(curr)); // Add copy of current path
            }
        }
        // Add current node to path
        getPathsWithTargetSum(root.left, targetSum - root.val, curr, out);
        getPathsWithTargetSum(root.right, targetSum - root.val, curr, out);
        curr.remove(curr.size() - 1); // Backtrack
    }
}
