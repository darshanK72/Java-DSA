/**
 * Problem Statement:
 *     LeetCode 1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree
 * 
 *     Given two binary trees original and cloned and given a reference to a node target 
 *     in the original tree. The cloned tree is a copy of the original tree.
 *     Return a reference to the same node in the cloned tree.
 * 
 * Input:
 *     - Original tree root
 *     - Cloned tree root
 *     - Target node in original tree
 * 
 * Output:
 *     - Reference to corresponding node in cloned tree
 * 
 * Example:
 *     Input: 
 *     Original:   7        Cloned:    7
 *                / \                  / \
 *               4   3               4   3
 *              /                   /
 *             6                   6
 *     target = 3
 *     
 *     Output: Reference to node 3 in cloned tree
 */

public class j06GetNodeInCoppiedTree {

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
        // Test Case 1: Basic tree
        TreeNode original1 = new TreeNode(7);
        original1.left = new TreeNode(4);
        original1.right = new TreeNode(3);
        original1.left.left = new TreeNode(6);

        TreeNode cloned1 = new TreeNode(7);
        cloned1.left = new TreeNode(4);
        cloned1.right = new TreeNode(3);
        cloned1.left.left = new TreeNode(6);

        TreeNode target1 = original1.right; // Node with value 3
        TreeNode result1 = getTargetCopy(original1, cloned1, target1);
        System.out.println("Test Case 1: Found node with value: " +
                (result1 != null ? result1.val : "null")); // Expected: 3

        // Test Case 2: Target is leaf
        TreeNode original2 = new TreeNode(1);
        original2.left = new TreeNode(2);
        original2.right = new TreeNode(3);

        TreeNode cloned2 = new TreeNode(1);
        cloned2.left = new TreeNode(2);
        cloned2.right = new TreeNode(3);

        TreeNode target2 = original2.left; // Node with value 2
        TreeNode result2 = getTargetCopy(original2, cloned2, target2);
        System.out.println("Test Case 2: Found node with value: " +
                (result2 != null ? result2.val : "null")); // Expected: 2

        // Test Case 3: Single node
        TreeNode original3 = new TreeNode(1);
        TreeNode cloned3 = new TreeNode(1);
        TreeNode result3 = getTargetCopy(original3, cloned3, original3);
        System.out.println("Test Case 3: Found node with value: " +
                (result3 != null ? result3.val : "null")); // Expected: 1
    }

    /**
     * Approach: Pre-order DFS with Reference Matching
     * 
     * Intuition:
     * - Traverse both trees simultaneously using pre-order DFS
     * - When original node matches target, return corresponding cloned node
     * - Search left subtree first, then right if target not found
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * - Stop when target is found
     * 
     * Space Complexity: O(h)
     * - h is height of tree (recursion stack)
     */
    public static final TreeNode getTargetCopy(final TreeNode original,
            final TreeNode cloned,
            final TreeNode target) {
        if (original == null && cloned == null)
            return null; // Base case: empty trees

        if (original == target)
            return cloned; // Found target node

        TreeNode node = getTargetCopy(original.left, cloned.left, target);
        if (node != null)
            return node; // Found in left subtree

        node = getTargetCopy(original.right, cloned.right, target);
        return node; // Return result from right subtree
    }
}
