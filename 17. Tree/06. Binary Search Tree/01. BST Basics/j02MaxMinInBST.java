/**
 * Custom Problem: Find Maximum and Minimum Values in BST
 * 
 * Problem Statement:
 *     Given a Binary Search Tree, find the maximum and minimum values stored in it.
 *     For an empty tree, return -1.
 * 
 * Input:
 *     - TreeNode root (root node of BST)
 *     - Node values are integers
 * 
 * Output:
 *     - int (maximum or minimum value in BST)
 * 
 * Example:
 *     Input Tree:
 *          5
 *         / \
 *        3   7
 *       / \   \
 *      2   4   8
 * 
 *     findMin() returns: 2
 *     findMax() returns: 8
 */

public class j02MaxMinInBST {
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    /**
     * Finds minimum value in BST
     * 
     * Intuition:
     * - In BST, minimum value is leftmost node
     * - Keep going left until reaching leaf
     * 
     * Explanation:
     * - Handle null tree case
     * - Traverse left pointers until null
     * - Return value of last node
     * 
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(1) constant space
     * 
     * @param root    Root node of BST
     * @return       Minimum value in BST, -1 if empty
     */
    public static int findMin(TreeNode root) {
        // Handle empty tree case
        if (root == null) {
            return -1;
        }
        
        // Traverse to leftmost node
        TreeNode current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.val;
    }

    /**
     * Finds maximum value in BST
     * 
     * Intuition:
     * - In BST, maximum value is rightmost node
     * - Keep going right until reaching leaf
     * 
     * Explanation:
     * - Handle null tree case
     * - Traverse right pointers until null
     * - Return value of last node
     * 
     * Time Complexity: O(h) where h is height of tree
     * Space Complexity: O(1) constant space
     * 
     * @param root    Root node of BST
     * @return       Maximum value in BST, -1 if empty
     */
    public static int findMax(TreeNode root) {
        // Handle empty tree case
        if (root == null) {
            return -1;
        }
        
        // Traverse to rightmost node
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.val;
    }

    /**
     * Helper method to print BST structure
     */
    private static void printTree(TreeNode root, String indent, String prefix) {
        if (root == null) {
            System.out.println(indent + prefix + "null");
            return;
        }
        System.out.println(indent + prefix + root.val);
        printTree(root.left, indent + "    ", "L── ");
        printTree(root.right, indent + "    ", "R── ");
    }

    public static void main(String[] args) {
        // Test Case 1: Basic BST
        System.out.println("\nBasic BST Test:");
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(8);
        
        System.out.println("Tree structure:");
        printTree(root1, "", "Root: ");
        System.out.println("Minimum value: " + findMin(root1));  // Expected: 2
        System.out.println("Maximum value: " + findMax(root1));  // Expected: 8

        // Test Case 2: Single node
        System.out.println("\nSingle Node Test:");
        TreeNode root2 = new TreeNode(1);
        System.out.println("Minimum value: " + findMin(root2));  // Expected: 1
        System.out.println("Maximum value: " + findMax(root2));  // Expected: 1

        // Test Case 3: Empty tree
        System.out.println("\nEmpty Tree Test:");
        System.out.println("Minimum value: " + findMin(null));   // Expected: -1
        System.out.println("Maximum value: " + findMax(null));   // Expected: -1
    }
}
