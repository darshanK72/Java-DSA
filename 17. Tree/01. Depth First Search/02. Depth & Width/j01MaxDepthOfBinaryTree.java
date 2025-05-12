/**
 * Problem Statement:
 *     LeetCode 104. Maximum Depth of Binary Tree
 * 
 *     Given the root of a binary tree, return its maximum depth.
 *     A binary tree's maximum depth is the number of nodes along the
 *     longest path from the root node down to the farthest leaf node.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can be empty or have any number of nodes
 * 
 * Output:
 *     - Integer representing maximum depth of tree
 * 
 * Example:
 *     Input: root = [3,9,20,null,null,15,7]
 *           3
 *          / \
 *         9  20
 *            / \
 *           15  7
 *     Output: 3
 */

public class j01MaxDepthOfBinaryTree {

    /**
     * TreeNode class represents a node in binary tree
     * Each node contains a value and references to left and right children
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic tree
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println("Test Case 1 (Basic Tree):");
        System.out.println("Max Depth: " + maxDepth(root1));  // Expected: 3

        // Test Case 2: Single node
        TreeNode root2 = new TreeNode(1);
        System.out.println("\nTest Case 2 (Single Node):");
        System.out.println("Max Depth: " + maxDepth(root2));  // Expected: 1

        // Test Case 3: Empty tree
        System.out.println("\nTest Case 3 (Empty Tree):");
        System.out.println("Max Depth: " + maxDepth(null));   // Expected: 0

        // Test Case 4: Linear tree
        TreeNode root4 = new TreeNode(1);
        root4.right = new TreeNode(2);
        root4.right.right = new TreeNode(3);
        System.out.println("\nTest Case 4 (Linear Tree):");
        System.out.println("Max Depth: " + maxDepth(root4));  // Expected: 3
    }

    /**
     * Approach: Recursive Depth Calculation
     * 
     * Intuition:
     * - Max depth = 1 + max(left subtree depth, right subtree depth)
     * - Base case: empty tree has depth 0
     * - Recursively calculate depth of left and right subtrees
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
    public static int maxDepth(TreeNode root) {
        if (root == null)
            return 0;                                    // Base case: empty tree
        
        int leftMaxDepth = maxDepth(root.left);         // Get depth of left subtree
        int rightMaxDepth = maxDepth(root.right);       // Get depth of right subtree
        
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;  // Return max depth
    }
}
