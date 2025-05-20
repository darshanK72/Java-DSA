/**
 * Problem Statement:
 *     LeetCode 1325. Delete Leaves With a Given Value
 * 
 *     Given a binary tree root and an integer target, delete all the leaf nodes with value target.
 *     Note that once you delete a leaf node with value target, if its parent becomes a 
 *     leaf node and has the value target, it should also be deleted (you need to continue 
 *     doing that until you cannot).
 * 
 * Input:
 *     - Root node of binary tree
 *     - Target value (integer)
 * 
 * Output:
 *     - Root of modified tree after removing target leaves
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        /   / \
 *       2   2   4
 *     target = 2
 *     
 *     Output:
 *           1
 *            \
 *             3
 *              \
 *               4
 *     
 *     Explanation:
 *     1. First remove leaves with value 2
 *     2. After removal, node 2 becomes leaf with value 2
 *     3. Remove this new leaf node
 */

public class j03DeleteLeavesWithGivenValue {

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
        // Test Case 1: Multiple target leaves
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(2);
        root1.right.left = new TreeNode(2);
        root1.right.right = new TreeNode(4);
        
        System.out.println("Test Case 1 (target=2):");
        System.out.println("Before:");
        printInorder(root1);
        root1 = removeLeafNodes(root1, 2);
        System.out.println("\nAfter:");
        printInorder(root1);

        // Test Case 2: All nodes are target
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        
        System.out.println("\n\nTest Case 2 (target=2):");
        System.out.println("Before:");
        printInorder(root2);
        root2 = removeLeafNodes(root2, 2);
        System.out.println("\nAfter:");
        printInorder(root2);
    }

    /**
     * Helper method to print tree inorder
     */
    private static void printInorder(TreeNode root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.val + " ");
        printInorder(root.right);
    }

    /**
     * Approach : Post-order Traversal
     * 
     * Intuition:
     * - Use post-order traversal (process children first)
     * - After processing children, check if current node:
     *   1. Has become a leaf (both children null)
     *   2. Has target value
     * - If both conditions true, remove node by returning null
     * - Continue process until no more leaves with target value
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     * 
     * @param root Root node of binary tree
     * @param target Value to remove from leaves
     * @return Root of modified tree
     */
    public static TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null)
            return null;
            
        // Process children first (post-order)
        root.left = removeLeafNodes(root.left, target);
        root.right = removeLeafNodes(root.right, target);
        
        // Check if current node should be removed
        if (root.val == target && root.left == null && root.right == null) {
            return null;    // Remove leaf with target value
        }
        return root;       // Keep non-leaf or non-target nodes
    }

    
}