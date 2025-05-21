/**
 * Problem Statement:
 *     LeetCode 222. Count Complete Tree Nodes
 * 
 *     Given the root of a complete binary tree, return the number of nodes in the tree.
 *     A complete binary tree is a binary tree in which every level, except possibly 
 *     the last, is completely filled, and all nodes in the last level are as far left 
 *     as possible.
 * 
 * Input:
 *     - Root node of complete binary tree
 * 
 * Output:
 *     - Number of nodes in the tree
 * 
 * Example:
 *     Input: 
 *            1
 *           / \
 *          2   3
 *         / \  /
 *        4  5 6
 *     
 *     Output: 6
 *     Explanation: All levels are filled except last level which is filled from left
 */

public class j04CountNodesInCompleteBinaryTree {

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
        // Test Case 1: Perfect binary tree
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);
        System.out.println("Test Case 1: " + countNodes(root1));  // Expected: 7

        // Test Case 2: Complete but not perfect
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        System.out.println("Test Case 2: " + countNodes(root2));  // Expected: 5
    }

    /**
     * Binary Search with Height Properties Approach
     * 
     * Intuition:
     * 1. Use special property of complete binary tree:
     *    - All levels except last are fully filled
     *    - Last level filled from left to right
     * 2. Compare left and right heights:
     *    - If equal -> tree is perfect -> use 2^h - 1 formula
     *    - If different -> recursively count left and right subtrees
     * 3. This works because:
     *    - At least one subtree must be perfect
     *    - We can skip counting nodes in perfect subtree
     * 
     * Time Complexity: O(log²n)
     * - O(log n) for height calculations
     * - O(log n) levels of recursion
     * 
     * Space Complexity: O(log n)
     * - Recursion stack depth
     * 
     * @param root Root node of complete binary tree
     * @return Total number of nodes
     */
    public static int countNodes(TreeNode root) {
        if (root == null)
            return 0;

        int leftHeight = leftHeight(root);
        int rightHeight = rightHeight(root);

        // Perfect binary tree case
        if (leftHeight == rightHeight)
            return (1 << leftHeight) - 1;  // 2^h - 1

        // Recursive case for incomplete levels
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * Helper method to get left height
     * Follows leftmost path to leaf
     */
    public static int leftHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + leftHeight(root.left);
    }

    /**
     * Helper method to get right height
     * Follows rightmost path to leaf
     */
    public static int rightHeight(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + rightHeight(root.right);
    }
}