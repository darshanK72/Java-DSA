/**
 * Problem Statement:
 *     LeetCode 1110. Delete Nodes And Return Forest
 * 
 *     Given the root of a binary tree and an array of integers to_delete, return a forest
 *     (a disjoint union of trees) after deleting all nodes with values in to_delete.
 *     Each tree in the forest should be the remaining subtree after removing nodes.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Array of node values to delete
 * 
 * Output:
 *     - List of root nodes of remaining trees
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        /   / \
 *       4   5   6
 *     to_delete = [3, 5]
 *     
 *     Output: [1, 6]
 *     Explanation: 
 *     After deleting 3 and 5:
 *           1
 *          /     6
 *         2
 *        /
 *       4
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class j04DeleteNodesAndReturnForest {

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
        // Test Case 1: Multiple deletions
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.right.left = new TreeNode(5);
        root1.right.right = new TreeNode(6);
        
        int[] toDelete1 = {3, 5};
        System.out.println("Test Case 1:");
        List<TreeNode> forest1 = delNodes(root1, toDelete1);
        System.out.println("Number of trees in forest: " + forest1.size());
        for (TreeNode root : forest1) {
            System.out.print("Tree root value: " + root.val + " ");
        }

        // Test Case 2: Delete root node
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        
        int[] toDelete2 = {1};
        System.out.println("\n\nTest Case 2:");
        List<TreeNode> forest2 = delNodes(root2, toDelete2);
        System.out.println("Number of trees in forest: " + forest2.size());
    }

    /**
     * DFS with HashMap Tracking Approach
     * 
     * Intuition:
     * - Use HashMap to quickly check if node should be deleted
     * - Use DFS to traverse tree and track if current node is a root
     * - When node is deleted:
     *   1. Process children as new roots
     *   2. Don't add current node to result
     * - When node is kept:
     *   1. Add to result if it's a root
     *   2. Process children while checking for deletion
     * 
     * Time Complexity: O(n)
     * - Visit each node exactly once
     * 
     * Space Complexity: O(n)
     * - HashMap stores to_delete values
     * - Result list stores forest nodes
     * - Recursion stack is O(h), h = height
     * 
     * @param root Root of binary tree
     * @param to_delete Array of values to delete
     * @return List of root nodes of remaining trees
     */
    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        // Create HashMap for O(1) lookup of values to delete
        HashMap<Integer, Boolean> deleteMap = new HashMap<>();
        for (int val : to_delete) {
            deleteMap.put(val, true);
        }
        
        ArrayList<TreeNode> forest = new ArrayList<>();
        processNodes(root, deleteMap, true, forest);
        return forest;
    }

    /**
     * Helper method to process nodes using DFS
     * 
     * @param root Current node being processed
     * @param deleteMap Map containing values to delete
     * @param isRoot Whether current node could be a root
     * @param forest List to store roots of remaining trees
     */
    private static void processNodes(TreeNode root, HashMap<Integer, Boolean> deleteMap, 
            boolean isRoot, ArrayList<TreeNode> forest) {
        if (root == null)
            return;

        boolean shouldDelete = deleteMap.containsKey(root.val);

        // Process current node based on deletion status
        if (shouldDelete) {
            // Node will be deleted, process children as new roots
            processNodes(root.left, deleteMap, true, forest);
            processNodes(root.right, deleteMap, true, forest);
        } else {
            // Keep node, add to forest if it's a root
            if (isRoot)
                forest.add(root);

            // Check and process children
            TreeNode left = root.left;
            TreeNode right = root.right;

            // Disconnect children that should be deleted
            if (left != null && deleteMap.containsKey(left.val))
                root.left = null;
            if (right != null && deleteMap.containsKey(right.val))
                root.right = null;

            // Process children as non-roots
            processNodes(left, deleteMap, false, forest);
            processNodes(right, deleteMap, false, forest);
        }
    }
}
