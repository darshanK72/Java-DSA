/**
 * Problem Statement:
 *     LeetCode 652. Find Duplicate Subtrees
 * 
 *     Given the root of a binary tree, return all duplicate subtrees.
 *     For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 *     Two trees are duplicate if they have the same structure with the same node values.
 * 
 * Input:
 *     - Root node of binary tree
 *     - Tree can have any number of nodes
 * 
 * Output:
 *     - List of root nodes of duplicate subtrees
 * 
 * Example:
 *     Input: 
 *           1
 *          / \
 *         2   3
 *        /   / \
 *       4   2   4
 *          /
 *         4
 *     
 *     Output: [2,4]
 *     Explanation: 
 *     - 2 -> 4 appears twice
 *     - 4 appears twice as leaf nodes
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class j08FindDuplicateSubtrees {

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

        // Test Case 1: Tree with multiple duplicates
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.right.left = new TreeNode(2);
        root1.right.right = new TreeNode(4);
        root1.right.left.left = new TreeNode(4);

        System.out.println("Test Case 1:");
        List<TreeNode> result1 = findDuplicateSubtrees(root1);
        System.out.println("Duplicate subtree roots: " +
                result1.stream().map(node -> node.val).toList()); // Expected: [2, 4]

        // Test Case 2: No duplicates
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);

        System.out.println("\nTest Case 2:");
        List<TreeNode> result2 = findDuplicateSubtrees(root2);
        System.out.println("Duplicate subtree roots: " +
                result2.stream().map(node -> node.val).toList()); // Expected: []

        // Test Case 3: Single node duplicates
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(2);

        System.out.println("\nTest Case 3:");
        List<TreeNode> result3 = findDuplicateSubtrees(root3);
        System.out.println("Duplicate subtree roots: " +
                result3.stream().map(node -> node.val).toList()); // Expected: [2]
    }

    /**
     * Approach: Serialization with HashMap
     * 
     * Intuition:
     * - Convert each subtree to a unique string representation
     * - Use HashMap to track frequency of each subtree
     * - Add root to result when finding second occurrence
     * 
     * Time Complexity: O(n²)
     * - Visit each node and build string at each node
     * - String concatenation takes O(n) at each node
     * 
     * Space Complexity: O(n²)
     * - Store string representation for each subtree
     * - Each string can be O(n) length
     */
    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        HashMap<String, Integer> map = new HashMap<>(); // Track subtree frequencies
        ArrayList<TreeNode> out = new ArrayList<>(); // Store duplicate roots
        findDuplicatePaths(root, map, out);
        return out;
    }

    /**
     * Helper method to serialize subtrees and find duplicates
     * Returns: String representation of current subtree
     * Updates: out list with roots of duplicate subtrees
     */
    private static String findDuplicatePaths(TreeNode root,
            HashMap<String, Integer> map,
            ArrayList<TreeNode> out) {
        if (root == null)
            return "N"; // Null marker for empty subtree

        // Create unique string representation
        String path = root.val + "," +
                findDuplicatePaths(root.left, map, out) + "," +
                findDuplicatePaths(root.right, map, out);

        // Add to result if second occurrence
        if (map.containsKey(path) && map.get(path) == 1) {
            out.add(root);
        }

        // Update frequency
        map.put(path, map.getOrDefault(path, 0) + 1);

        return path;
    }
}
