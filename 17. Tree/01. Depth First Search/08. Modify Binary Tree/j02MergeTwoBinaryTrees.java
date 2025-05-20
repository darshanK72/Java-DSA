/**
 * Problem Statement:
 *     LeetCode 617. Merge Two Binary Trees
 * 
 *     Given two binary trees root1 and root2, merge them into a new binary tree.
 *     The merge rule is: If two nodes overlap, sum node values; otherwise, use
 *     non-null node as the new node.
 * 
 * Input:
 *     - Two root nodes of binary trees
 *     - Trees can have any number of nodes
 * 
 * Output:
 *     - Root of merged tree
 * 
 * Example:
 *     Input: 
 *     Tree1:     1         Tree2:    3
 *               / \               /  \
 *              3   2            6    1
 *             /                  \    \
 *            5                    2    9
 *     
 *     Output:     4
 *               /   \
 *              9     3
 *             / \     \
 *            5   2     9
 */

public class j02MergeTwoBinaryTrees {

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
        // Test Case 1: Basic merge
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(3);
        t1.right = new TreeNode(2);
        t1.left.left = new TreeNode(5);

        TreeNode t2 = new TreeNode(3);
        t2.left = new TreeNode(6);
        t2.right = new TreeNode(1);
        t2.left.right = new TreeNode(2);
        t2.right.right = new TreeNode(9);

        System.out.println("Test Case 1:");
        System.out.println("Tree 1 inorder:");
        printInorder(t1);
        System.out.println("\nTree 2 inorder:");
        printInorder(t2);
        TreeNode merged = mergeTrees(t1, t2);
        System.out.println("\nMerged tree inorder:");
        printInorder(merged);

        // Test Case 2: One empty tree
        TreeNode t3 = new TreeNode(1);
        t3.left = new TreeNode(2);
        
        System.out.println("\n\nTest Case 2:");
        System.out.println("Merge with empty tree:");
        TreeNode merged2 = mergeTrees(t3, null);
        printInorder(merged2);
    }

    /**
     * Preorder DFS with In-place Modification Approach
     * 
     * Intuition:
     * - Process current nodes first, then recurse to children
     * - Modify first tree in-place to store merged result
     * - Handle null nodes by returning non-null subtree
     * 
     * Time Complexity: O(min(n1,n2))
     * - Only process overlapping nodes
     * - n1, n2 are sizes of input trees
     * 
     * Space Complexity: O(min(h1,h2))
     * - h1, h2 are heights of trees
     * - Recursion stack size
     * 
     * @param root1 Root of first tree (will be modified)
     * @param root2 Root of second tree
     * @return Root of merged tree
     */
    public static TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // Base cases: if either tree is empty
        if (root1 == null && root2 == null)
            return null;
        if (root1 == null)
            return root2;
        if (root2 == null)
            return root1;

        // Merge current nodes
        root1.val += root2.val;

        // Recursively merge children
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);

        return root1;
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
}
