/**
 * Problem Statement:
 *     LeetCode 100. Same Tree
 * 
 *     Given the roots of two binary trees p and q, check if they are the same tree.
 *     Two binary trees are considered the same if they are structurally identical
 *     and the nodes have the same value.
 * 
 * Input:
 *     - Root nodes of two binary trees (p and q)
 *     - Trees can have any number of nodes
 *     - Node values can be any integer
 * 
 * Output:
 *     - Boolean indicating if trees are identical
 * 
 * Example:
 *     Input: 
 *     Tree1:    1      Tree2:    1
 *              / \             / \
 *             2   3          2   3
 *     
 *     Output: true
 *     Explanation: Both trees have same structure and values
 */

public class j01IsSameTree {

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

        // Test Case 1: Identical trees
        TreeNode p1 = new TreeNode(1);
        p1.left = new TreeNode(2);
        p1.right = new TreeNode(3);

        TreeNode q1 = new TreeNode(1);
        q1.left = new TreeNode(2);
        q1.right = new TreeNode(3);

        System.out.println("Test Case 1 (Identical Trees): " +
                isSameTree(p1, q1)); // Expected: true

        // Test Case 2: Different values
        TreeNode p2 = new TreeNode(1);
        p2.left = new TreeNode(2);

        TreeNode q2 = new TreeNode(1);
        q2.left = new TreeNode(3);

        System.out.println("Test Case 2 (Different Values): " +
                isSameTree(p2, q2)); // Expected: false

        // Test Case 3: Different structures
        TreeNode p3 = new TreeNode(1);
        p3.left = new TreeNode(2);

        TreeNode q3 = new TreeNode(1);
        q3.right = new TreeNode(2);

        System.out.println("Test Case 3 (Different Structures): " +
                isSameTree(p3, q3)); // Expected: false

        // Test Case 4: Empty trees
        System.out.println("Test Case 4 (Empty Trees): " +
                isSameTree(null, null)); // Expected: true
    }

    /**
     * Approach: Recursive DFS Comparison
     * 
     * Intuition:
     * - Compare nodes recursively using pre-order traversal
     * - At each step, check:
     *   1. If both nodes are null (identical empty subtrees)
     *   2. If either node is null (different structures)
     *   3. If values are different
     *   4. Recursively compare left and right subtrees
     * 
     * Time Complexity: O(min(n,m))
     * - Visit each node until finding a difference
     * - n,m are sizes of trees p and q
     * 
     * Space Complexity: O(min(h1,h2))
     * - h1,h2 are heights of trees
     * - Recursion stack depth
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true; // Both empty - identical
        if (p == null || q == null)
            return false; // One empty - different
        if (p.val != q.val)
            return false; // Different values

        return isSameTree(p.left, q.left) // Compare subtrees
                && isSameTree(p.right, q.right);
    }
}
