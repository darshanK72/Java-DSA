/**
 * Problem Statement:
 *     LeetCode 1339. Maximum Product of Splitted Binary Tree
 * 
 *     Given the root of a binary tree, split the binary tree into two subtrees by removing 
 *     one edge. The product of the sums of the subtrees should be maximized.
 *     Return the maximum product possible after the split.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree values can be any positive integer
 * 
 * Output:
 *     - Maximum product of two subtree sums modulo 10^9 + 7
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        /   / \
 *       4   5   6
 *     
 *     Output: 110
 *     Explanation: 
 *     Remove edge between 3 and parent:
 *     Left subtree sum = 7 (1 + 2 + 4)
 *     Right subtree sum = 14 (3 + 5 + 6)
 *     Product = 7 * 14 = 110
 */

public class j01MaxProductOfSplittedBinaryTree {

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
        // Test Case 1: Basic tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.right.left = new TreeNode(5);
        root1.right.right = new TreeNode(6);
        
        System.out.println("Test Case 1: " + 
            maxProduct(root1));  // Expected: 110

        // Test Case 2: Linear tree
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        root2.right.right = new TreeNode(3);
        
        System.out.println("Test Case 2: " + 
            maxProduct(root2));  // Expected: 6
    }

    /**
     * Two-Pass DFS Approach
     * 
     * Intuition:
     * 1. First pass: Calculate total sum of tree
     * 2. Second pass: Try each edge split and track maximum product
     * - For each node, its subtree sum represents one part
     * - Remaining sum (total - subtree) represents other part
     * 
     * Time Complexity: O(n)
     * - Two passes through the tree
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * 
     * @param root Root of binary tree
     * @return Maximum product possible after splitting
     */
    public static int maxProduct(TreeNode root) {
        int MOD = 1000000007;
        long totalSum = getSubtreeSum(root);      // First pass
        long[] maxProduct = new long[1];
        findMaxProduct(root, totalSum, maxProduct); // Second pass
        return (int) (maxProduct[0] % MOD);
    }

    /**
     * Helper method to find maximum product using DFS
     * 
     * @param root Current node being processed
     * @param totalSum Total sum of all nodes
     * @param maxProduct Array to store maximum product found
     * @return Sum of current subtree
     */
    private static long findMaxProduct(TreeNode root, long totalSum, long[] maxProduct) {
        if (root == null)
            return 0;

        // Get sums of left and right subtrees
        long leftSum = findMaxProduct(root.left, totalSum, maxProduct);
        long rightSum = findMaxProduct(root.right, totalSum, maxProduct);
        
        // Calculate current subtree sum
        long subtreeSum = leftSum + rightSum + root.val;
        
        // Update maximum product if current split is better
        maxProduct[0] = Math.max(maxProduct[0], 
                               (totalSum - subtreeSum) * subtreeSum);
        
        return subtreeSum;
    }

    /**
     * Helper method to calculate total sum of tree
     * 
     * @param root Root node of tree/subtree
     * @return Sum of all nodes in tree/subtree
     */
    private static long getSubtreeSum(TreeNode root) {
        if (root == null)
            return 0L;
        
        return root.val + 
               getSubtreeSum(root.left) + 
               getSubtreeSum(root.right);
    }
}
