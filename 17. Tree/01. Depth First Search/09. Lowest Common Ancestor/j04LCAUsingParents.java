/**
 * Problem: Lowest Common Ancestor Using Parent Pointers
 * 
 * Problem Statement:
 *     Given two nodes in a binary tree where each node has a pointer to its parent,
 *     find their lowest common ancestor (LCA). The LCA is defined as the lowest node
 *     that is an ancestor of both nodes.
 * 
 * Input:
 *     - Two nodes n1 and n2 in the binary tree
 * 
 * Output:
 *     - Node that is the LCA of n1 and n2
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
 *     n1 = 6, n2 = 8
 *     Output: 3 (LCA of 6 and 8)
 */

import java.util.HashSet;

public class j04LCAUsingParents {

    /**
     * Binary Tree Node class with parent pointer
     */
    static class BinaryTreeNode {
        int data;
        BinaryTreeNode left;
        BinaryTreeNode right;
        BinaryTreeNode parent;

        BinaryTreeNode(int data) {
            this.data = data;
            left = null;
            right = null;
            parent = null;
        }
    }

    /**
     * Approach: HashSet to Track Path to Root
     * 
     * Intuition:
     * - Use parent pointers to move up the tree
     * - Store all ancestors of first node in HashSet
     * - First ancestor of second node that's in set is LCA
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
     * For nodes 6,8:
     * 1. Store path from 6 to root: [6,5,3]
     * 2. Move up from 8: 8->1->3
     * 3. First match found at 3 -> LCA
     * 
     * Time Complexity: O(h) - h is height of tree
     * Space Complexity: O(h) - to store ancestors
     * 
     * @param n1 First node
     * @param n2 Second node
     * @return LCA node of n1 and n2
     */
    public static BinaryTreeNode lowestCommonAncestor(BinaryTreeNode n1, BinaryTreeNode n2) {
        HashSet<BinaryTreeNode> ancestors = new HashSet<>();  // Store ancestors of n1
        
        // Store all ancestors of n1
        while (n1 != null) {
            ancestors.add(n1);                                // Add current node
            n1 = n1.parent;                                  // Move up to parent
        }

        // Find first ancestor of n2 that's also ancestor of n1
        while (n2 != null) {
            if (ancestors.contains(n2)) return n2;           // Found LCA
            n2 = n2.parent;                                 // Move up to parent
        }
        return null;                                        // No common ancestor
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
        BinaryTreeNode root = new BinaryTreeNode(3);
        BinaryTreeNode node5 = new BinaryTreeNode(5);
        BinaryTreeNode node1 = new BinaryTreeNode(1);
        BinaryTreeNode node6 = new BinaryTreeNode(6);
        BinaryTreeNode node2 = new BinaryTreeNode(2);
        BinaryTreeNode node8 = new BinaryTreeNode(8);
        BinaryTreeNode node7 = new BinaryTreeNode(7);

        // Set up parent pointers
        root.left = node5; node5.parent = root;
        root.right = node1; node1.parent = root;
        node5.left = node6; node6.parent = node5;
        node5.right = node2; node2.parent = node5;
        node1.right = node8; node8.parent = node1;
        node2.left = node7; node7.parent = node2;
        
        System.out.println("Test Case 1 - LCA of nodes 6 and 8");
        System.out.println("Expected: 3");
        System.out.println("Output: " + lowestCommonAncestor(node6, node8).data);

        System.out.println("\nTest Case 2 - LCA of nodes 6 and 7");
        System.out.println("Expected: 5");
        System.out.println("Output: " + lowestCommonAncestor(node6, node7).data);
    }
}
