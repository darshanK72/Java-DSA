/**
 * LeetCode 236: Lowest Common Ancestor of a Binary Tree
 * 
 * Problem Statement:
 *     Given a binary tree, find the lowest common ancestor (LCA) of two given nodes.
 *     The LCA is defined as the lowest node in T that has both p and q as descendants
 *     (where we allow a node to be a descendant of itself).
 * 
 * Input:
 *     - Root node of binary tree
 *     - Two nodes p and q whose LCA needs to be found
 * 
 * Output:
 *     - Node that is the LCA of p and q
 * 
 * Example:
 *     Input: 
 *           3
 *          / \
 *         5   1
 *        / \   \
 *       6   2   8
 *     
 *     p = 5, q = 1
 *     Output: 3 (LCA of 5 and 1)
 *     
 *     p = 5, q = 2
 *     Output: 5 (LCA of 5 and 2)
 */

public class j01LowestCommonAncestor {

    /**
     * TreeNode class representing each node in the binary tree
     */
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Approach: Recursive DFS with Bottom-up Processing
     * 
     * Intuition:
     * - If root is null or equals p or q, it's a potential LCA
     * - Recursively search left and right subtrees
     * - If both subtrees return non-null, current node is LCA
     * - If one subtree returns non-null, that's the LCA
     * 
     * Example visualization:
     *           3
     *          / \
     *         5   1
     *        / \   \
     *       6   2   8
     * 
     * For p=5, q=1:
     * 1. Search left subtree -> finds 5
     * 2. Search right subtree -> finds 1
     * 3. Both found -> 3 is LCA
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(h) - recursion stack height
     * 
     * @param root Root node of binary tree
     * @param p First node
     * @param q Second node
     * @return Lowest Common Ancestor node
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base cases
        if (root == null) return null;                       // Empty tree
        if (root == p || root == q) return root;            // Found target node

        // Recursively search left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // Process results
        if (left != null && right != null) return root;     // Found LCA
        if (left != null) return left;                      // LCA in left subtree
        else return right;                                  // LCA in right subtree
    }

    /**
     * Main method to test the implementation
     */
    public static void main(String[] args) {
        // Test Case 1: Regular binary tree
        /**
         *           3
         *          / \
         *         5   1
         *        / \   \
         *       6   2   8
         */
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(8);

        TreeNode p1 = root.left;      // node 5
        TreeNode q1 = root.right;     // node 1
        System.out.println("Test Case 1 - LCA of 5 and 1");
        System.out.println("Expected: 3");
        System.out.println("Output: " + lowestCommonAncestor(root, p1, q1).val);

        TreeNode p2 = root.left;      // node 5
        TreeNode q2 = root.left.right;// node 2
        System.out.println("\nTest Case 2 - LCA of 5 and 2");
        System.out.println("Expected: 5");
        System.out.println("Output: " + lowestCommonAncestor(root, p2, q2).val);
    }
}
