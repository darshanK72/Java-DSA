/**
 * Problem Statement:
 *     LeetCode 226. Invert Binary Tree
 * 
 *     Given the root of a binary tree, invert the tree, and return its root.
 *     To invert a binary tree, swap every left node with its corresponding right node.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 * 
 * Output:
 *     - Root of inverted tree
 * 
 * Example:
 *     Input: 
 *           4
 *          / \
 *         2   7
 *        / \ / \
 *       1  3 6  9
 *     
 *     Output:
 *           4
 *          / \
 *         7   2
 *        / \ / \
 *       9  6 3  1
 */

public class j04InvertTree {

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

        // Test Case 1: Complete binary tree
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(9);

        System.out.println("Test Case 1:");
        System.out.println("Before inversion:");
        TreeNode inverted1 = invertTree(root1);
        printLevelOrder(inverted1); // Print level order to visualize the tree
        System.out.println("After inversion:");

        // Test Case 2: Unbalanced tree
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);

        System.out.println("\nTest Case 2:");
        System.out.println("Before inversion:");

        TreeNode inverted2 = invertTree(root2);
        printLevelOrder(inverted2); // Print level order to visualize the tree
        System.out.println("After inversion:");

        // Test Case 3: Single node
        TreeNode root3 = new TreeNode(1);
        System.out.println("\nTest Case 3 (Single Node):");
        TreeNode inverted3 = invertTree(root3);
        printLevelOrder(inverted3); // Print level order to visualize the tree
        System.out.println("After inversion:");
    }

    /**
     * Approach: Post-order DFS with Swap
     * 
     * Intuition:
     * - Process subtrees first (post-order)
     * - After processing subtrees, swap left and right children
     * - Return modified root
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     */
    public static TreeNode invertTree(TreeNode root) {
        if (root == null)
            return root; // Empty tree case

        TreeNode left = invertTree(root.left); // Process left subtree
        TreeNode right = invertTree(root.right); // Process right subtree

        root.left = right; // Swap children
        root.right = left;

        return root;
    }

    /**
     * Helper method to print tree level by level
     */
    private static void printLevelOrder(TreeNode root) {
        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val + " ");
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            System.out.println();
        }
    }
}
