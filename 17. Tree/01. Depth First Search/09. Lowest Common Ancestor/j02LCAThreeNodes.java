/**
 * Problem: Lowest Common Ancestor of Three Nodes
 * 
 * Problem Statement:
 *     Given a binary tree and three nodes, find the lowest common ancestor (LCA) of all
 *     three nodes. The LCA is defined as the lowest node in tree that is an ancestor of
 *     all three given nodes.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Three integer values representing nodes to find LCA for
 * 
 * Output:
 *     - Node that is the LCA of all three nodes
 * 
 * Example:
 *     Input: 
 *           3
 *          / \
 *         5   1
 *        / \   \
 *       6   2   8
 *          /
 *         7
 *     
 *     node1 = 6, node2 = 7, node3 = 8
 *     Output: 3 (LCA of 6, 7, and 8)
 */

public class j02LCAThreeNodes {

    /**
     * Binary Tree Node class with generic type support
     */
    static class BinaryTreeNode<T> {
        T data;
        BinaryTreeNode<T> left;
        BinaryTreeNode<T> right;

        BinaryTreeNode(T data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
    
    /**
     * Approach: Modified LCA Algorithm
     * 
     * Intuition:
     * - Extension of two-node LCA algorithm
     * - If any of three nodes found, it's potential LCA
     * - Search both subtrees for remaining nodes
     * - Node where paths to three nodes meet is LCA
     * 
     * Example visualization:
     *           3
     *          / \
     *         5   1
     *        / \   \
     *       6   2   8
     *          /
     *         7
     * 
     * For nodes 6,7,8:
     * 1. Search finds 6 in left subtree
     * 2. Search finds 7 in left subtree
     * 3. Search finds 8 in right subtree
     * 4. All paths meet at 3 -> LCA
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(h) - recursion stack height
     * 
     * @param root Root node of binary tree
     * @param node1 First target node value
     * @param node2 Second target node value
     * @param node3 Third target node value
     * @return LCA node of all three nodes
     */
    public static BinaryTreeNode<Integer> lcaOfThreeNodes(BinaryTreeNode<Integer> root, 
            int node1, int node2, int node3) {
        // Base cases
        if (root == null) return null;                       // Empty tree
        if (root.data == node1 || root.data == node2 || 
            root.data == node3) return root;                 // Found target node

        // Recursively search both subtrees
        BinaryTreeNode<Integer> left = lcaOfThreeNodes(root.left, node1, node2, node3);
        BinaryTreeNode<Integer> right = lcaOfThreeNodes(root.right, node1, node2, node3);

        // Process results
        if (left != null && right != null) return root;      // Found LCA
        if (left != null) return left;                       // LCA in left subtree
        else return right;                                   // LCA in right subtree
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
         *          /
         *         7
         */
        BinaryTreeNode<Integer> root1 = new BinaryTreeNode<>(3);
        root1.left = new BinaryTreeNode<>(5);
        root1.right = new BinaryTreeNode<>(1);
        root1.left.left = new BinaryTreeNode<>(6);
        root1.left.right = new BinaryTreeNode<>(2);
        root1.right.right = new BinaryTreeNode<>(8);
        root1.left.right.left = new BinaryTreeNode<>(7);
        
        System.out.println("Test Case 1 - LCA of nodes 6, 7, and 8");
        System.out.println("Expected: 3");
        System.out.println("Output: " + lcaOfThreeNodes(root1, 6, 7, 8).data);

        // Test Case 2: All nodes in left subtree
        System.out.println("\nTest Case 2 - LCA of nodes 6, 7, and 2");
        System.out.println("Expected: 5");
        System.out.println("Output: " + lcaOfThreeNodes(root1, 6, 7, 2).data);

        // Test Case 3: One node is ancestor of others
        System.out.println("\nTest Case 3 - LCA of nodes 5, 6, and 7");
        System.out.println("Expected: 5");
        System.out.println("Output: " + lcaOfThreeNodes(root1, 5, 6, 7).data);
    }
}
