/**
 * Problem Statement:
 *     LeetCode 1448. Count Good Nodes in Binary Tree
 * 
 *     Given a binary tree root, a node X in the tree is named good if in the path
 *     from root to X there are no nodes with a value greater than X.
 *     Return the number of good nodes in the binary tree.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Node values can be any integer
 *     - Tree can have up to 10^5 nodes
 * 
 * Output:
 *     - Number of good nodes in the tree
 * 
 * Example:
 *     Input: 
 *           3
 *          / \
 *         1   4
 *        /   / \
 *       3   1   5
 *     Output: 4
 *     Explanation: 
 *     - 3 is good (no nodes greater than 3 in path)
 *     - 4 is good (no nodes greater than 4 in path)
 *     - 3 is good (no nodes greater than 3 in path after root)
 *     - 5 is good (no nodes greater than 5 in path)
 */

public class j08CountGoodNodes {

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
        // Test Case 1: Example tree
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.left = new TreeNode(3);
        root1.right.left = new TreeNode(1);
        root1.right.right = new TreeNode(5);
        System.out.println("Test Case 1: " + goodNodes(root1));  // Expected: 4

        // Test Case 2: All increasing values
        TreeNode root2 = new TreeNode(1);
        root2.right = new TreeNode(2);
        root2.right.right = new TreeNode(3);
        System.out.println("Test Case 2: " + goodNodes(root2));  // Expected: 3

        // Test Case 3: All decreasing values
        TreeNode root3 = new TreeNode(3);
        root3.left = new TreeNode(2);
        root3.left.left = new TreeNode(1);
        System.out.println("Test Case 3: " + goodNodes(root3));  // Expected: 1

        // Test Case 4: Single node
        TreeNode root4 = new TreeNode(1);
        System.out.println("Test Case 4: " + goodNodes(root4));  // Expected: 1
    }

    /**
     * Approach: DFS with Maximum Value Tracking
     * 
     * Intuition:
     * - Use DFS to traverse tree while tracking maximum value in current path
     * - Node is good if its value is >= maximum seen so far
     * - Update maximum when moving down the path
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - Constant time operations at each node
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     */
    public static int goodNodes(TreeNode root) {
        if (root == null)
            return 0;                                   // Handle empty tree
        return countGoodNodes(root, Integer.MIN_VALUE); // Start with minimum value
    }

    /**
     * Helper method to count good nodes using DFS
     * max: maximum value seen in current path from root
     */
    private static int countGoodNodes(TreeNode root, int max) {
        if (root == null)
            return 0;                                   // Base case
            
        int count = 0;
        if (root.val >= max) {                         // Check if current node is good
            count = 1;                                 // Count current node
            max = root.val;                           // Update maximum
        }
        
        // Add counts from left and right subtrees
        return count + 
               countGoodNodes(root.left, max) + 
               countGoodNodes(root.right, max);
    }
}
