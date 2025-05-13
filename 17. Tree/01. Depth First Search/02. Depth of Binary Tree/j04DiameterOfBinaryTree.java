/**
 * Problem Statement:
 *     LeetCode 543. Diameter of Binary Tree
 * 
 *     Given the root of a binary tree, return the length of the diameter of the tree.
 *     The diameter of a binary tree is the length of the longest path between any two
 *     nodes in a tree. This path may or may not pass through the root.
 *     The length of a path between two nodes is represented by the number of edges
 *     between them.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can be empty or have any number of nodes
 * 
 * Output:
 *     - Integer representing the diameter of the tree (length of longest path)
 * 
 * Example:
 *     Input: root = [1,2,3,4,5]
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *     Output: 3 (path: 4->2->1->3 or 5->2->1->3)
 * 
 *     Input: root = [1,2]
 *           1
 *          /
 *         2
 *     Output: 1 (path: 2->1)
 */

public class j04DiameterOfBinaryTree {

    /**
     * TreeNode class represents a node in binary tree
     */
    static class TreeNode {
        int val;        // Value stored in the node
        TreeNode left;  // Reference to left child
        TreeNode right; // Reference to right child

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Approach: Post-order DFS with Global Variable
     * 
     * Intuition:
     * - The diameter of a tree is the maximum of:
     *   1. Diameter of left subtree
     *   2. Diameter of right subtree
     *   3. Longest path passing through root (left height + right height)
     * - Use post-order traversal to calculate heights and update diameter
     * - Use array to store diameter (similar to global variable) for pass by reference
     * 
     * Time Complexity: O(n) - Visit each node once
     * Space Complexity: O(h) - Recursion stack depth, where h is height of tree
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        // Base case: empty tree has diameter 0
        if (root == null)
            return 0;
        
        // Base case: single node has diameter 0
        if (root.left == null && root.right == null)
            return 0;
        
        // Array to store diameter (using array to pass by reference)
        int[] diameter = new int[1];
        
        // Calculate max depth and update diameter
        maxDepth(root, diameter);
        
        // Return diameter - 1 because diameter is number of edges
        return diameter[0] - 1;
    }

    /**
     * Helper method to calculate max depth and update diameter
     * 
     * @param root Current node
     * @param diameter Array to store maximum diameter found
     * @return Maximum depth of current subtree
     */
    public static int maxDepth(TreeNode root, int[] diameter) {
        // Base case: empty tree has depth 0
        if (root == null)
            return 0;
        
        // Calculate max depth of left subtree
        int leftMaxDepth = maxDepth(root.left, diameter);
        
        // Calculate max depth of right subtree
        int rightMaxDepth = maxDepth(root.right, diameter);
        
        // Update diameter if current path is longer
        // Add 1 for current node
        diameter[0] = Math.max(diameter[0], leftMaxDepth + rightMaxDepth + 1);
        
        // Return max depth of current subtree
        return Math.max(leftMaxDepth, rightMaxDepth) + 1;
    }
}
